class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if(accounts == null) return null;

        final int count = accounts.size();
        if(count == 0) return new ArrayList<List<String>>();

        final int[] parent = new int[count];
        final int[] size = new int[count];

        for(int i = 0; i < count; ++i) {
            parent[i] = i;
            size[i] = 1;
        }

        final Map<String, Integer> emailSeenAt = new HashMap<>();
        for(int i = 0; i < count; ++i) {
            for(String email : accounts.get(i).subList(1, accounts.get(i).size())) {
                System.out.println(String.format("Account Idx: %d; Email: %s; SeenAlready? - %b", i, email, emailSeenAt.containsKey(email)));
                if(emailSeenAt.containsKey(email)) {
                    System.out.println(String.format("Merging %d and %d", i, emailSeenAt.get(email)));
                    union(parent, size, i, emailSeenAt.get(email));
                    System.out.println(Arrays.toString(parent));
                } else {
                    emailSeenAt.put(email, i);
                }
            }
        }

        final int max = Arrays.stream(parent).max().getAsInt();
        final List<Set<String>> mergedAccounts = new ArrayList<>(max + 1);
        for(int i = 0; i <= max; ++i) {
            mergedAccounts.add(new HashSet<>());
        }

        for(int i = 0; i < count; ++i) {
            Set<String> set = mergedAccounts.get(find(parent, parent[i]));
            
            if(set.isEmpty()) {
                set.addAll(accounts.get(i));
                continue;
            }

            set.addAll(accounts.get(i).subList(1, accounts.get(i).size())); //Skip adding the account name again
        }

        final List<List<String>> response = mergedAccounts.stream().filter(s -> s.size() > 0).map(ArrayList::new).collect(Collectors.toList()); //This still does not have emails in sorted order per account
        // response.forEach(l -> Collections.sort(l.subList(1, l.size())));
        return response;
    }

    private int find(int[] parent, int node) {
        if(parent[node] != node) {
            parent[node] = find(parent, parent[node]);
        }

        return parent[node];
    }

    private void union(int[] parent, int[] size, int n1, int n2) {
        final int p1 = find(parent, n1);
        final int p2 = find(parent, n2);

        if(p1 == p2) return;

        if(size[p1] >= size[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
            return;
        }

        parent[p1] = p2;
        size[p2] += size[p1];
    }
}