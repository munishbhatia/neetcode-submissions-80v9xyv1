//Preconstruct Feed solution
// class Twitter {
//     private final int MAX_USER_ID = 100;
//     private final int MAX_TWEETS_IN_FEED = 10;
//     private final Set<Integer>[] followers;
//     private final ArrayDeque<Integer>[] newsFeed;

//     public Twitter() {
//         followers = new Set[MAX_USER_ID + 1];
//         newsFeed = new ArrayDeque[MAX_USER_ID + 1];

//         for(int i=0; i<= MAX_USER_ID; ++i) {
//             followers[i] = new HashSet<>();
//             newsFeed[i] = new ArrayDeque<>();
//         }
//     }
    
//     public void postTweet(int userId, int tweetId) {
//         for(int follower : followers[userId]) {
//             addPostToTimeline(follower, tweetId); //Add post to the feed of the user's followers
//         }
//         addPostToTimeline(userId, tweetId); //Add post to the user's own feed
//     }
    
//     public List<Integer> getNewsFeed(int userId) {
//         final List<Integer> feed = new ArrayList<>(newsFeed[userId]);
//         Collections.reverse(feed);
//         return feed;
//     }
    
//     public void follow(int followerId, int followeeId) {
//         followers[followeeId].add(followerId);
//     }
    
//     public void unfollow(int followerId, int followeeId) {
//         followers[followeeId].remove(followerId);
//     }

//     private void addPostToTimeline(int userId, int tweetId) {
//         newsFeed[userId].offerFirst(tweetId);
//         if(newsFeed[userId].size() > MAX_TWEETS_IN_FEED) {
//             newsFeed[userId].pollLast();
//         }
//     }
// }

//Runtime feed generation solution
class Twitter {
    private final int MAX_USER_ID = 100;
    private final int MAX_TWEETS_IN_FEED = 10;
    private final Set<Integer>[] follows; //People this person follows
    private final List<int[]>[] userTweets; //User's own tweets that they post

    private static int TWEET_SEQUENCE = 0; //NOT IDEAL

        public Twitter() {
        follows = new Set[MAX_USER_ID + 1];
        userTweets = new List[MAX_USER_ID + 1];

        for(int i=0; i<= MAX_USER_ID; ++i) {
            follows[i] = new HashSet<>();
            follows[i].add(i);
            userTweets[i] = new ArrayList<>();
        }
    }
    
    public void postTweet(int userId, int tweetId) {
        final List<int[]> tweets = userTweets[userId];
        tweets.add(new int[]{tweetId, TWEET_SEQUENCE++}); //This should be synchronized; Or we use Datetime/Instance but in that case we'll have to rollout a type for this, like List<TweetInfo>
        // if(tweets.size() > MAX_TWEETS_IN_FEED) { //This caused problem; shouldn't have been done in the first place
        //     tweets.remove(tweets.size() - 1);
        // }
    }
    
    //This one creates the feed by maintaining a min heap of size MAX_TWEETS_IN_FEED while processing complete feed from the user or the people they follow one at a time
    //So we take 10 most recent tweets from a user
    //If we see an 11th tweet, we add that and poll the min heap to remove the tweet with minimum sequence, i.e. the oldest tweet in PQ
    //This was we get 10 most recent tweets from followee1
    //Then we process the tweets from followee2, followee3, ..., followee[N]
    //The user itself is included in it's set of followers so tweets from the user are considered in this process
    //End result is we get 10 most recent tweets from the user and all their followees combined
    public List<Integer> getNewsFeedInitial(int userId) {
        //Generate news feed for the user based on their own posts and those of the people they follow
        final PriorityQueue<int[]> newsFeed = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1])); //Min Heap
        
        //Process tweets from people this user follows, including itself
        for(int followeeId : follows[userId]) {
            for(int[] tweet : userTweets[followeeId]) {
                newsFeed.offer(tweet);
                if(newsFeed.size() > MAX_TWEETS_IN_FEED) {
                    newsFeed.poll();
                }
            }
        }

        final List<Integer> result = new LinkedList<>();
        while(!newsFeed.isEmpty()) {
            result.addFirst(newsFeed.poll()[0]);
        }
        return result;
    }

    //This method tries to optimize the idea in getNewsFeedInitial method a bit
    //Here we consider the last tweets from all the users (user + followees) and add the most recent tweet to the minHeap (PQ)
    //We advance the pointer on the tweet list for the user (followee or the user itself) whose tweet we add to the PQ
    //We continue this process till we either get the 10 tweets for the timeline or we don't have any more tweets to process (we consumed all the lists and the pointers are at -1 for all the lists)
    //This led to a 2ms improvement in the overall time, but would likely yield better results for larger inputs
    //We don't end up processing complete timelines for every user + follower to prepare the list here. 
    public List<Integer> getNewsFeed(int userId) {
        //Generate news feed for the user based on their own posts and those of the people they follow
        final PriorityQueue<int[]> newsFeed = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1])); //Min Heap

        final Map<Integer, Integer> followeeTweetSequenceTracker = new HashMap<>();
        
        //Process tweets from people this user follows, including itself
        for(int followeeId : follows[userId]) {
            followeeTweetSequenceTracker.put(followeeId, userTweets[followeeId].size() - 1);
        }

        int[] maxSoFarTweetSeq = new int[]{-1, -1};
        int maxTweetFolloweeId = -1;
        while(newsFeed.size() < 10) {
            for(int followeeId : follows[userId]) {
                if(followeeTweetSequenceTracker.get(followeeId) < 0) continue;

                int[] curr = userTweets[followeeId].get(followeeTweetSequenceTracker.get(followeeId));
                if(maxSoFarTweetSeq[1] < curr[1]) {
                    maxSoFarTweetSeq[0] = curr[0];
                    maxSoFarTweetSeq[1] = curr[1];
                    maxTweetFolloweeId = followeeId;
                }
            }

            if(maxSoFarTweetSeq[1] == -1) break;
            
            newsFeed.offer(maxSoFarTweetSeq);
            maxSoFarTweetSeq = new int[]{-1, -1};
            followeeTweetSequenceTracker.put(maxTweetFolloweeId, followeeTweetSequenceTracker.get(maxTweetFolloweeId) - 1);
        }

        final List<Integer> result = new LinkedList<>();
        while(!newsFeed.isEmpty()) {
            result.addFirst(newsFeed.poll()[0]);
        }
        return result;
    }
    
    public void follow(int followerId, int followeeId) {
        follows[followerId].add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(followerId != followeeId) {
            follows[followerId].remove(followeeId);
        }
    }
}
