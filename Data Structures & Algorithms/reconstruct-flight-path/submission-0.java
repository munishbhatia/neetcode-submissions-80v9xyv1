class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        // final Map<String, List<String>> adjList = createGraph(tickets);
        final Map<String, PriorityQueue<String>> adjList = createGraph(tickets);
        return findEulerianCircuit(adjList, "JFK");
    }

    // private Map<String, List<String>> createGraph(List<List<String>> tickets) {
    //     final Map<String, List<String>> graph = new HashMap<>();

    //     for(List<String> ticket : tickets) {
    //         graph.computeIfAbsent(ticket.get(0), x -> new ArrayList<>()).add(ticket.get(1));
    //     }

    //     return graph;
    // }

    // private List<String> findEulerianCircuit(Map<String, List<String>> graph, String start) {
    //     final Stack<String> stack = new Stack();
    //     final List<String> circuit = new ArrayList<>();

    //     stack.push(start);

    //     while(!stack.isEmpty()) {
    //         String currPort = stack.peek();

    //         if(graph.getOrDefault(currPort, new ArrayList<>()).isEmpty()) {
    //             circuit.add(stack.pop());
    //             continue;
    //         };

    //         String dest = graph.get(currPort).remove(0);
    //         stack.push(dest);
    //     }

    //     Collections.reverse(circuit);
    //     return circuit;
    // }

    private Map<String, PriorityQueue<String>> createGraph(List<List<String>> tickets) {
        final Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for(List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), x -> new PriorityQueue<>()).add(ticket.get(1));
        }

        return graph;
    }

    private List<String> findEulerianCircuit(Map<String, PriorityQueue<String>> graph, String start) {
        final Stack<String> stack = new Stack();
        final List<String> circuit = new ArrayList<>();

        stack.push(start);

        while(!stack.isEmpty()) {
            String currPort = stack.peek();

            if(graph.getOrDefault(currPort, new PriorityQueue<>()).isEmpty()) {
                circuit.add(stack.pop());
                continue;
            };

            String dest = graph.get(currPort).poll();
            stack.push(dest);
        }

        Collections.reverse(circuit);
        return circuit;
    }
}
