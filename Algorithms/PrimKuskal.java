import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * PrimKuskal implements both Prim's and Kruskal's algorithms to compute a Minimum Spanning Tree (MST)
 * of a weighted, undirected graph.
 *
 * Key idea (MST):
 * - Connect all vertices with minimum total edge weight
 * - No cycles
 * - Graph must be connected to get a spanning tree (otherwise you get a minimum spanning forest)
 *
 * Graph representation:
 * - Undirected edges are stored in an adjacency list for Prim
 * - A separate edge list is kept for Kruskal
 */
public class PrimKuskal {

    /** Simple undirected weighted edge */
    public static class Edge {
        public final int u;
        public final int v;
        public final double w;

        public Edge(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return u + " --(" + w + ")--> " + v;
        }
    }

    /** Result wrapper for MST algorithms */
    public static class MSTResult {
        public final List<Edge> edges;     // edges in the MST (or MSF if disconnected)
        public final double totalWeight;   // sum of weights of edges

        public MSTResult(List<Edge> edges, double totalWeight) {
            this.edges = edges;
            this.totalWeight = totalWeight;
        }
    }

    /**
     * Graph storing:
     * - adjacency list for Prim
     * - edge list for Kruskal
     */
    public static class Graph {
        public final int n;
        public final List<List<Edge>> adj;
        public final List<Edge> edges;

        public Graph(int n) {
            this.n = n;
            this.adj = new ArrayList<>(n);
            for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
            this.edges = new ArrayList<>();
        }

        /**
         * Add an undirected edge (u <-> v) with weight w.
         * We store it in adjacency list both ways for Prim.
         * We store one copy in edges list for Kruskal.
         */
        public void addUndirectedEdge(int u, int v, double w) {
            Edge e = new Edge(u, v, w);
            edges.add(e);
            adj.get(u).add(new Edge(u, v, w));
            adj.get(v).add(new Edge(v, u, w));
        }
    }

    /**
     * Prim's Algorithm (MST):
     * - Think: "grow a tree from a starting vertex"
     * - Maintain a fringe of candidate edges that cross from the built tree to outside vertices
     * - Always take the cheapest edge that adds a NEW vertex (no cycles)
     *
     * Data structures:
     * - inMST[v] = whether v is already in the growing MST
     * - min-heap of "candidate edges" (by weight)
     *
     * Time:
     * - With adjacency list + binary heap: O(E log E) (often stated O(E log V))
     *
     * If the graph is disconnected, this returns a minimum spanning forest (MSF)
     * by running Prim from every not-yet-covered vertex.
     */
    public static MSTResult primMST(Graph g) {
        boolean[] inMST = new boolean[g.n];
        List<Edge> mst = new ArrayList<>();
        double total = 0.0;

        // Heap stores edges by smallest weight first
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.w));

        for (int start = 0; start < g.n; start++) {
            if (inMST[start]) continue;

            // Start a new component
            inMST[start] = true;
            for (Edge e : g.adj.get(start)) pq.add(e);

            while (!pq.isEmpty()) {
                Edge e = pq.poll();
                int u = e.u;
                int v = e.v;
                
                // We only want edges that bring in a NEW vertex.
                // Since we push edges from MST -> outside, a valid edge usually has inMST[u]=true and inMST[v]=false,
                // but due to duplicates, we just guard against cycles with inMST[v].
                if (inMST[v]) continue;

                // Accept this edge into the MST
                inMST[v] = true;
                mst.add(new Edge(u, v, e.w));
                total += e.w;

                // Add all outgoing edges from the newly included vertex
                for (Edge out : g.adj.get(v)) {
                    if (!inMST[out.v]) pq.add(out);
                }
            }
        }

        return new MSTResult(mst, total);
    }

    /**
     * Kruskal's Algorithm (MST):
     * - Think: "pick edges globally from smallest to largest"
     * - Add an edge IF it doesn't create a cycle
     * - Cycle detection is done using DSU / Union-Find
     *
     * Data structures:
     * - Sort all edges by weight
     * - DSU keeps track of which vertices are already connected
     *
     * Time:
     * - Sorting edges: O(E log E)
     * - DSU operations: ~O(E α(V)) (almost constant)
     *
     * If the graph is disconnected, returns a minimum spanning forest (MSF).
     */
    public static MSTResult kruskalMST(Graph g) {
        List<Edge> sorted = new ArrayList<>(g.edges);
        sorted.sort(Comparator.comparingDouble(e -> e.w));

        DSU dsu = new DSU(g.n);
        List<Edge> mst = new ArrayList<>();
        double total = 0.0;

        for (Edge e : sorted) {
            // If u and v are already connected, adding this edge forms a cycle => skip it
            if (dsu.find(e.u) == dsu.find(e.v)) continue;

            // Otherwise, it's safe to add
            dsu.union(e.u, e.v);
            mst.add(e);
            total += e.w;

            // Early stop: in a connected graph, MST has exactly (V - 1) edges
            if (mst.size() == g.n - 1) break;
        }

        return new MSTResult(mst, total);
    }

    /**
     * Disjoint Set Union (Union-Find) with:
     * - path compression
     * - union by rank
     *
     * Used by Kruskal to efficiently detect cycles.
     */
    private static class DSU {
        private final int[] parent;
        private final int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) return;

            if (rank[ra] < rank[rb]) {
                parent[ra] = rb;
            } else if (rank[ra] > rank[rb]) {
                parent[rb] = ra;
            } else {
                parent[rb] = ra;
                rank[ra]++;
            }
        }
    }

    // Optional tiny demo (delete if your assignment forbids mains)
    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addUndirectedEdge(0, 1, 4);
        g.addUndirectedEdge(0, 2, 3);
        g.addUndirectedEdge(1, 2, 1);
        g.addUndirectedEdge(1, 3, 2);
        g.addUndirectedEdge(2, 3, 4);
        g.addUndirectedEdge(3, 4, 2);
        g.addUndirectedEdge(4, 5, 6);

        MSTResult prim = primMST(g);
        MSTResult kruskal = kruskalMST(g);

        System.out.println("Prim total: " + prim.totalWeight);
        for (Edge e : prim.edges) System.out.println("  " + e);

        System.out.println("\nKruskal total: " + kruskal.totalWeight);
        for (Edge e : kruskal.edges) System.out.println("  " + e);
    }
}
