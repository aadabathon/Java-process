import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class BTree<K extends Comparable<K>, V> {
  private final int t;
  private Node root;
  private int size;

  private final class Node {
    boolean leaf;
    ArrayList<K> keys = new ArrayList<>();
    ArrayList<V> vals = new ArrayList<>();
    ArrayList<Node> children = new ArrayList<>();
    Node(boolean leaf) { this.leaf = leaf; }
    boolean isFull() { return keys.size() == 2 * t - 1; }
    int keyCount() { return keys.size(); }
  }

  public BTree(int minDegree) {
    if (minDegree < 2) throw new IllegalArgumentException("t >= 2");
    this.t = minDegree;
    this.root = new Node(true);
    this.size = 0;
  }

  public int size() { return size; }
  public boolean isEmpty() { return size == 0; }

  public V get(K key) {
    if (key == null) throw new IllegalArgumentException();
    return search(root, key);
  }

  private V search(Node x, K key) {
    int i = lowerBound(x.keys, key);
    if (i < x.keyCount() && cmp(key, x.keys.get(i)) == 0) return x.vals.get(i);
    if (x.leaf) return null;
    return search(x.children.get(i), key);
  }

  public V put(K key, V val) {
    if (key == null) throw new IllegalArgumentException();
    V old = get(key);
    if (root.isFull()) {
      Node s = new Node(false);
      s.children.add(root);
      splitChild(s, 0);
      root = s;
    }
    insertNonFull(root, key, val, old == null);
    if (old == null) size++;
    return old;
  }

  private void insertNonFull(Node x, K key, V val, boolean countNew) {
    int i = lowerBound(x.keys, key);
    if (x.leaf) {
      if (i < x.keyCount() && cmp(key, x.keys.get(i)) == 0) {
        x.vals.set(i, val);
      } else {
        x.keys.add(i, key);
        x.vals.add(i, val);
      }
      return;
    }
    if (i < x.keyCount() && cmp(key, x.keys.get(i)) == 0) {
      x.vals.set(i, val);
      return;
    }
    Node c = x.children.get(i);
    if (c.isFull()) {
      splitChild(x, i);
      if (cmp(key, x.keys.get(i)) > 0) i++;
    }
    insertNonFull(x.children.get(i), key, val, countNew);
  }

  private void splitChild(Node x, int i) {
    Node y = x.children.get(i);
    Node z = new Node(y.leaf);
    for (int k = 0; k < t - 1; k++) {
      z.keys.add(y.keys.remove(t));
      z.vals.add(y.vals.remove(t));
    }
    if (!y.leaf) {
      for (int k = 0; k < t; k++) z.children.add(y.children.remove(t));
    }
    K midK = y.keys.remove(t - 1);
    V midV = y.vals.remove(t - 1);
    x.keys.add(i, midK);
    x.vals.add(i, midV);
    x.children.add(i + 1, z);
  }

  public V remove(K key) {
    if (key == null) throw new IllegalArgumentException();
    V old = get(key);
    if (old == null) return null;
    delete(root, key);
    if (root.keyCount() == 0 && !root.leaf) root = root.children.get(0);
    size--;
    return old;
  }

  private void delete(Node x, K key) {
    int idx = lowerBound(x.keys, key);
    if (idx < x.keyCount() && cmp(key, x.keys.get(idx)) == 0) {
      if (x.leaf) {
        x.keys.remove(idx);
        x.vals.remove(idx);
      } else {
        Node y = x.children.get(idx);
        Node z = x.children.get(idx + 1);
        if (y.keyCount() >= t) {
          Map.Entry<K,V> pred = maxEntry(y);
          x.keys.set(idx, pred.getKey());
          x.vals.set(idx, pred.getValue());
          delete(y, pred.getKey());
        } else if (z.keyCount() >= t) {
          Map.Entry<K,V> succ = minEntry(z);
          x.keys.set(idx, succ.getKey());
          x.vals.set(idx, succ.getValue());
          delete(z, succ.getKey());
        } else {
          mergeChildren(x, idx);
          delete(y, key);
        }
      }
      return;
    }
    if (x.leaf) return;
    Node child = x.children.get(idx);
    if (child.keyCount() == t - 1) {
      Node left = idx - 1 >= 0 ? x.children.get(idx - 1) : null;
      Node right = idx + 1 <= x.keyCount() ? x.children.get(idx + 1) : null;
      if (left != null && left.keyCount() >= t) {
        child.keys.add(0, x.keys.get(idx - 1));
        child.vals.add(0, x.vals.get(idx - 1));
        if (!left.leaf) child.children.add(0, left.children.remove(left.children.size() - 1));
        x.keys.set(idx - 1, left.keys.remove(left.keyCount() - 1));
        x.vals.set(idx - 1, left.vals.remove(left.vals.size() - 1));
      } else if (right != null && right.keyCount() >= t) {
        child.keys.add(x.keys.get(idx));
        child.vals.add(x.vals.get(idx));
        if (!right.leaf) child.children.add(right.children.remove(0));
        x.keys.set(idx, right.keys.remove(0));
        x.vals.set(idx, right.vals.remove(0));
      } else {
        if (right != null) {
          mergeChildren(x, idx);
        } else {
          mergeChildren(x, idx - 1);
          child = x.children.get(idx - 1);
        }
      }
    }
    delete(x.children.get(idx), key);
  }

  private void mergeChildren(Node x, int i) {
    Node y = x.children.get(i);
    Node z = x.children.get(i + 1);
    y.keys.add(x.keys.remove(i));
    y.vals.add(x.vals.remove(i));
    y.keys.addAll(z.keys);
    y.vals.addAll(z.vals);
    if (!y.leaf) y.children.addAll(z.children);
    x.children.remove(i + 1);
  }

  private Map.Entry<K,V> maxEntry(Node x) {
    Node cur = x;
    while (!cur.leaf) cur = cur.children.get(cur.children.size() - 1);
    int j = cur.keyCount() - 1;
    return new AbstractMap.SimpleImmutableEntry<>(cur.keys.get(j), cur.vals.get(j));
  }

  private Map.Entry<K,V> minEntry(Node x) {
    Node cur = x;
    while (!cur.leaf) cur = cur.children.get(0);
    return new AbstractMap.SimpleImmutableEntry<>(cur.keys.get(0), cur.vals.get(0));
  }

  private int lowerBound(ArrayList<K> a, K key) {
    int lo = 0, hi = a.size();
    while (lo < hi) {
      int mid = (lo + hi) >>> 1;
      int c = cmp(key, a.get(mid));
      if (c > 0) lo = mid + 1; else hi = mid;
    }
    return lo;
  }

  private int cmp(K a, K b) { return a.compareTo(b); }

  public boolean containsKey(K key) { return get(key) != null; }

  public Iterable<K> keys() {
    ArrayList<K> out = new ArrayList<>();
    traverseKeys(root, out);
    return out;
  }

  private void traverseKeys(Node x, ArrayList<K> out) {
    if (x.leaf) {
      out.addAll(x.keys);
      return;
    }
    for (int i = 0; i < x.keyCount(); i++) {
      traverseKeys(x.children.get(i), out);
      out.add(x.keys.get(i));
    }
    traverseKeys(x.children.get(x.keyCount()), out);
  }

  public static void main(String[] args) {
    BTree<Integer,String> t = new BTree<>(3);
    for (int i = 0; i < 100; i++) t.put(i, "v"+i);
    for (int i = 0; i < 100; i += 2) t.remove(i);
    System.out.println(t.size());
    System.out.println(t.get(7));
    System.out.println(t.containsKey(8));
    System.out.println(t.containsKey(10));
  }
}
