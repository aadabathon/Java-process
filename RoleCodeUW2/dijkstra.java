import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Set;

public class dijkstra extends Backend{

    public List<String> findShortestPath(String start, String end) {
    ensureNode(start);
    ensureNode(end);
    Map<String, Double> dist = new HashMap<>();
    Map<String, String> prev = new HashMap<>();
    PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingDouble(dist::get));
    for (String node : graph.getAllNodes()) dist.put(node, Double.POSITIVE_INFINITY);
    dist.put(start, 0.0);
    pq.add(start);
    while (!pq.isEmpty()) {
        String current = pq.poll();
        if (current.equals(end)) break;
        for (String neighbor : graph.getNeighbors(current)) {
            double edgeWeight = graph.getEdgeWeight(current, neighbor);
            double newDist = dist.get(current) + edgeWeight;
            if (newDist < dist.get(neighbor)) {
                dist.put(neighbor, newDist);
                prev.put(neighbor, current);
                pq.add(neighbor);
            }
        }
    }
    if (!prev.containsKey(end) && !start.equals(end))
        throw new NoSuchElementException("No path found between " + start + " and " + end);
    List<String> path = new ArrayList<>();
    String step = end;
    while (step != null) {
        path.add(step);
        step = prev.get(step);
    }
    Collections.reverse(path);
    return path;
}

public double getShortestDistance(String start, String end) {
    List<String> path = findShortestPath(start, end);
    double total = 0.0;
    for (int i = 0; i < path.size() - 1; i++)
        total += graph.getEdgeWeight(path.get(i), path.get(i + 1));
    return total;
}

private void ensureNode(String name) {
    if (!nodes.contains(name)) { //Insert node of value String name into graph and into our hashset nodes when it's not already there
      graph.insertNode(name); 
      nodes.add(name);
    }
  }

}