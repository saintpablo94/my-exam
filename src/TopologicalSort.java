
import java.util.*; // For List, Map.

public final class TopologicalSort {
    /**
     * Given a directed acyclic graph, returns a topological sorting of the
     * nodes in the graph.  If the input graph is not a DAG, throws an
     * IllegalArgumentException.
     *
     * @param g A directed acyclic graph.
     * @return A topological sort of that graph.
     * @throws IllegalArgumentException If the graph is not a DAG.
     */
    public static <T> List<T> sort(DirectedGraph<T> g) {
        /* Construct the reverse graph from the input graph. */
        DirectedGraph<T> gRev = reverseGraph(g);

        /* Maintain two structures - a set of visited nodes (so that once we've
         * added a node to the list, we don't label it again), and a list of
         * nodes that actually holds the topological ordering.
         */
        List<T> result = new ArrayList<T>();
        Set<T> visited = new HashSet<T>();

        /* We'll also maintain a third set consisting of all nodes that have
         * been fully expanded.  If the graph contains a cycle, then we can
         * detect this by noting that a node has been explored but not fully
         * expanded.
         */
        Set<T> expanded = new HashSet<T>();

        /* Fire off a DFS from each node in the graph. */
        for (T node: gRev)
            explore(node, gRev, result, visited, expanded);

        /* Hand back the resulting ordering. */
        return result;
    }


    /**
     * Recursively performs a DFS from the specified node, marking all nodes
     * encountered by the search.
     *
     * @param node The node to begin the search from.
     * @param g The graph in which to perform the search.
     * @param ordering A list holding the topological sort of the graph.
     * @param visited A set of nodes that have already been visited.
     * @param expanded A set of nodes that have been fully expanded.
     */
    private static <T> void explore(T node, DirectedGraph<T> g,
                                    List<T> ordering, Set<T> visited,
                                    Set<T> expanded) {
        /* Check whether we've been here before.  If so, we should stop the
         * search.
         */
        if (visited.contains(node)) {
            /* There are two cases to consider.  First, if this node has
             * already been expanded, then it's already been assigned a
             * position in the final topological sort and we don't need to
             * explore it again.  However, if it hasn't been expanded, it means
             * that we've just found a node that is currently being explored,
             * and therefore is part of a cycle.  In that case, we should 
             * report an error.
             */
            if (expanded.contains(node)) return;
            throw new IllegalArgumentException("Graph contains a cycle.");
        }
        
        /* Mark that we've been here */
        visited.add(node);

        /* Recursively explore all of the node's predecessors. */
        for (T predecessor: g.edgesFrom(node))
            explore(predecessor, g, ordering, visited, expanded);

        /* Having explored all of the node's predecessors, we can now add this
         * node to the sorted ordering.
         */
        ordering.add(node);

        /* Similarly, mark that this node is done being expanded. */
        expanded.add(node);
    }

    /**
     * Returns the reverse of the input graph.
     *
     * @param g A graph to reverse.
     * @return The reverse of that graph.
     */
    private static <T> DirectedGraph<T> reverseGraph(DirectedGraph<T> g) {
        DirectedGraph<T> result = new DirectedGraph<T>();

        /* Add all the nodes from the original graph. */
        for (T node: g)
            result.addNode(node);

        /* Scan over all the edges in the graph, adding their reverse to the
         * reverse graph.
         */
        for (T node: g)
            for (T endpoint: g.edgesFrom(node))
                result.addEdge(endpoint, node);

        return result;
    }
}