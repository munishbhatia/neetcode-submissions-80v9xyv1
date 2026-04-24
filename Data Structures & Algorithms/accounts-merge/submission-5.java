class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if(accounts == null) return null;

        final int count = accounts.size();
        if(count == 0) return new ArrayList<List<String>>();

        final List<Integer>[] graph = IntStream.range(0, count).mapToObj(i -> new ArrayList<Integer>()).toArray(List[]::new);
        final Map<String, Integer> emailSeenAt = new HashMap<>();
        
        for(int i = 0; i < count; ++i) {
            for(String email : accounts.get(i).subList(1, accounts.get(i).size())) {
                if(emailSeenAt.containsKey(email)) {
                    addUndirectedEdge(graph, i, emailSeenAt.get(email));
                } else {
                    emailSeenAt.put(email, i);
                }
            }
        }

        final List<List<String>> mergedAccounts = new ArrayList<>();
        final Set<Integer> visited = new HashSet<>();
        
        for(int i = 0; i < count; ++i) {
            if(!visited.contains(i)) {
                Set<String> emailsInAccount = new HashSet<>();
                dfs(accounts, graph, visited, emailsInAccount, i);

                mergedAccounts.add(new ArrayList<>(emailsInAccount));
            }
        }

        return mergedAccounts;
    }

    private void addUndirectedEdge(List<Integer>[] graph, int n1, int n2) {
        graph[n1].add(n2);
        graph[n2].add(n1);
    }

    private void dfs(List<List<String>> accounts, List<Integer>[] graph, Set<Integer> visited, Set<String> emailsInMergedAccount, int node) {
        visited.add(node);
        emailsInMergedAccount.addAll(accounts.get(node));
        
        for(int i : graph[node]) {
            if(!visited.contains(i)) {
                dfs(accounts, graph, visited, emailsInMergedAccount, i);
            }
        }
    }
}