import java.util.*;
import java.util.stream.Collectors;

public final class MST {
  public static final class Edge<T> {
    public final T u, v;
    public final double w;
    public Edge(T u, T v, double w) { this.u = u; this.v = v; this.w = w; }
    public T other(T x) { return x.equals(u) ? v : u; }
    @Override public String toString() { return "(" + u + "-" + w + "-" + v + ")"; }
    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Edge<?> e)) return false;
      boolean same = (Objects.equals(u, e.u) && Objects.equals(v, e.v)) ||
                     (Objects.equals(u, e.v) && Objects.equals(v, e.u));
      return same && Double.compare(w, e.w) == 0;
    }
    @Override public int hashCode() {
      int h1 = Objects.hashCode(u) ^ Objects.hashCode(v);
      int h2 = Objects.hashCode(v) ^ Objects.hashCode(u);
      return Objects.hash(Math.min(h1, h2), w);
    }
  }

  public static final class Graph<T> {
    private final Map<T, List<Edge<T>>> adj = new HashMap<>();
    private final Set<Edge<T>> edges = new HashSet<>();
    public void addNode(T v) { adj.computeIfAbsent(v, k -> new ArrayList<>()); }
    public void addEdge(T u, T v, double w) {
      addNode(u); addNode(v);
      Edge<T> e = new Edge<>(u, v, w);
      if (edges.add(e)) { adj.get(u).add(e); adj.get(v).add(e); }
    }
    public Set<T> nodes() { return adj.keySet(); }
    public List<Edge<T>> incident(T v) { return adj.getOrDefault(v, List.of()); }
    public Set<Edge<T>> allEdges() { return edges; }
  }

  private static final class DSU<T> {
    private final Map<T, T> p = new HashMap<>();
    private final Map<T, Integer> r = new HashMap<>();
    DSU(Collection<T> vs) { for (T v : vs) { p.put(v, v); r.put(v, 0); } }
    T find(T x) { T y = p.get(x); if (!y.equals(x)) p.put(x, y = find(y)); return y; }
    boolean union(T a, T b) {
      a = find(a); b = find(b);
      if (a.equals(b)) return false;
      int ra = r.get(a), rb = r.get(b);
      if (ra < rb) p.put(a, b);
      else if (rb < ra) p.put(b, a);
      else { p.put(b, a); r.put(a, ra + 1); }
      return true;
    }
  }

  public static <T> List<Edge<T>> primMST(Graph<T> g, T start) {
    List<Edge<T>> mst = new ArrayList<>();
    if (!g.nodes().contains(start)) return mst;
    Set<T> vis = new HashSet<>();
    PriorityQueue<Edge<T>> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.w));
    vis.add(start);
    pq.addAll(g.incident(start));
    while (vis.size() < g.nodes().size() && !pq.isEmpty()) {
      Edge<T> e = pq.poll();
      boolean inU = vis.contains(e.u), inV = vis.contains(e.v);
      if (inU && inV) continue;
      T next = inU ? e.v : e.u;
      mst.add(e);
      vis.add(next);
      for (Edge<T> f : g.incident(next)) if (!(vis.contains(f.u) && vis.contains(f.v))) pq.add(f);
    }
    return mst;
  }

  public static <T> List<Edge<T>> kruskalMST(Graph<T> g) {
    List<Edge<T>> mst = new ArrayList<>();
    List<Edge<T>> es = new ArrayList<>(g.allEdges());
    es.sort(Comparator.comparingDouble(e -> e.w));
    DSU<T> dsu = new DSU<>(g.nodes());
    for (Edge<T> e : es) {
      if (dsu.union(e.u, e.v)) {
        mst.add(e);
        if (mst.size() == g.nodes().size() - 1) break;
      }
    }
    return mst;
  }

  public static <T> double totalWeight(Collection<Edge<T>> edges) {
    return edges.stream().collect(Collectors.summingDouble(e -> e.w));
  }
}
