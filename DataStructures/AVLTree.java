import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {
  private final class Node {
    K key; V val; Node left, right; int height = 1;
    Node(K k, V v) { key = k; val = v; }
  }

  private Node root;
  private int size;
  private V lastOld;

  public int size() { return size; }
  public boolean isEmpty() { return size == 0; }

  public V get(K key) {
    if (key == null) throw new IllegalArgumentException();
    Node n = root;
    while (n != null) {
      int c = key.compareTo(n.key);
      if (c == 0) return n.val;
      n = c < 0 ? n.left : n.right;
    }
    return null;
  }

  public boolean containsKey(K key) { return get(key) != null; }

  public V put(K key, V val) {
    if (key == null) throw new IllegalArgumentException();
    lastOld = null;
    root = insert(root, key, val);
    if (lastOld == null) size++;
    return lastOld;
  }

  public V remove(K key) {
    if (key == null) throw new IllegalArgumentException();
    lastOld = null;
    root = delete(root, key);
    if (lastOld != null) size--;
    return lastOld;
  }

  public Iterable<K> keys() {
    ArrayList<K> out = new ArrayList<>();
    inorder(root, out);
    return out;
  }

  private Node insert(Node n, K key, V val) {
    if (n == null) return new Node(key, val);
    int c = key.compareTo(n.key);
    if (c < 0) n.left = insert(n.left, key, val);
    else if (c > 0) n.right = insert(n.right, key, val);
    else { lastOld = n.val; n.val = val; return n; }
    return rebalance(n);
  }

  private Node delete(Node n, K key) {
    if (n == null) return null;
    int c = key.compareTo(n.key);
    if (c < 0) n.left = delete(n.left, key);
    else if (c > 0) n.right = delete(n.right, key);
    else {
      lastOld = n.val;
      if (n.left == null || n.right == null) {
        n = (n.left != null) ? n.left : n.right;
      } else {
        Node s = min(n.right);
        n.key = s.key; n.val = s.val;
        n.right = deleteMin(n.right);
      }
    }
    return rebalance(n);
  }

  private Node deleteMin(Node n) {
    if (n.left == null) return n.right;
    n.left = deleteMin(n.left);
    return rebalance(n);
  }

  private Node min(Node n) {
    Node cur = n;
    while (cur.left != null) cur = cur.left;
    return cur;
  }

  private void inorder(Node n, ArrayList<K> out) {
    if (n == null) return;
    inorder(n.left, out);
    out.add(n.key);
    inorder(n.right, out);
  }

  private int h(Node n) { return n == null ? 0 : n.height; }

  private void update(Node n) { n.height = 1 + Math.max(h(n.left), h(n.right)); }

  private int bf(Node n) { return h(n.left) - h(n.right); }

  private Node rebalance(Node n) {
    if (n == null) return null;
    update(n);
    int b = bf(n);
    if (b > 1) {
      if (bf(n.left) < 0) n.left = rotateLeft(n.left);
      return rotateRight(n);
    }
    if (b < -1) {
      if (bf(n.right) > 0) n.right = rotateRight(n.right);
      return rotateLeft(n);
    }
    return n;
  }

  private Node rotateRight(Node y) {
    Node x = y.left;
    Node t2 = x.right;
    x.right = y;
    y.left = t2;
    update(y);
    update(x);
    return x;
  }

  private Node rotateLeft(Node x) {
    Node y = x.right;
    Node t2 = y.left;
    y.left = x;
    x.right = t2;
    update(x);
    update(y);
    return y;
  }

  public static void main(String[] args) {
    AVLTree<Integer,String> t = new AVLTree<>();
    for (int i = 0; i < 100; i++) t.put(i, "v"+i);
    for (int i = 0; i < 100; i += 2) t.remove(i);
    System.out.println(t.size());
    System.out.println(t.get(7));
    System.out.println(t.containsKey(8));
    System.out.println(t.containsKey(10));
  }
}
