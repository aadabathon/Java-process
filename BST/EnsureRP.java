public class EnsureRP<T extends Comparable<T>>{
    protected RedBlackNode<T> root;
    protected void ensureRedProperty(RedBlackNode<T> n) {
    while (n != null && n != root && n.getParent() != null && !n.getParent().isBlackNode) {
        RedBlackNode<T> p = n.getParent();
        RedBlackNode<T> g = p.getParent();
        if (g == null) break;

        boolean parentIsLeft = (p == g.getLeft());
        RedBlackNode<T> u = parentIsLeft ? g.getRight() : g.getLeft();

        if (u != null && !u.isBlackNode) {
            p.isBlackNode = true;
            u.isBlackNode = true;
            g.isBlackNode = false;
            n = g;
            continue;
        }

        if (parentIsLeft && n == p.getRight()) {
            rotate(n, p);
            n = p;
        } else if (!parentIsLeft && n == p.getLeft()) {
            rotate(n, p);
            n = p;
        }

        p = n.getParent();
        g = p != null ? p.getParent() : null;
        if (g == null) break;

        p.isBlackNode = true;
        g.isBlackNode = false;
        rotate(p, g);
        break;
    }
    if (root != null) root.isBlackNode = true;
}

protected void rotate(RedBlackNode<T> child, RedBlackNode<T> parent) {
    if (child == null || parent == null) return;

    if (child == parent.getLeft()) {
        RedBlackNode<T> B = child.getRight();
        child.setRight(parent);
        transplant(parent, child);
        parent.setLeft(B);
        if (B != null) B.setParent(parent);
        parent.setParent(child);
    } else if (child == parent.getRight()) {
        RedBlackNode<T> B = child.getLeft();
        child.setLeft(parent);
        transplant(parent, child);
        parent.setRight(B);
        if (B != null) B.setParent(parent);
        parent.setParent(child);
    }
}

private void transplant(RedBlackNode<T> u, RedBlackNode<T> v) {
    RedBlackNode<T> up = u.getParent();
    v.setParent(up);
    if (up == null) {
        root = v;
    } else if (u == up.getLeft()) {
        up.setLeft(v);
    } else {
        up.setRight(v);
    }
}


private boolean isBlack(RedBlackNode<T> n) { return n == null || n.isBlackNode; }
private boolean isRed(RedBlackNode<T> n) { return n != null && !n.isBlackNode; }
private RedBlackNode<T> siblingOf(RedBlackNode<T> x, RedBlackNode<T> p) {
    if (p == null) return null;
    return (x == p.getLeft()) ? p.getRight() : p.getLeft();
}
// Fixing Deletion property mishaps, ie. Black path violations.
// Call after a delete when a black node was removed, passing the replacement x and its parent p.
// If the removed node had a single red child, set that child black and return (no double-black).
protected void fixDoubleBlack(RedBlackNode<T> x, RedBlackNode<T> p) {
    while (x != root && isBlack(x)) {
        RedBlackNode<T> s = siblingOf(x, p);

        if (isRed(s)) { // DB-1
            s.isBlackNode = true;
            p.isBlackNode = false;
            rotate(s, p);
            s = siblingOf(x, p);
        }

        boolean xIsLeft = (x == (p != null ? p.getLeft() : null));
        RedBlackNode<T> sLeft  = (s != null ? s.getLeft()  : null);
        RedBlackNode<T> sRight = (s != null ? s.getRight() : null);

        if (isBlack(sLeft) && isBlack(sRight)) { // DB-2
            if (s != null) s.isBlackNode = false;
            x = p;
            p = (x != null) ? x.getParent() : null;
            if (x == null) break;
            continue;
        }

        if (xIsLeft) {
            if (isBlack(sRight)) { // DB-3 (near red, far black)
                if (sLeft != null) sLeft.isBlackNode = true;
                if (s != null) s.isBlackNode = false;
                rotate(sLeft, s);
                s = siblingOf(x, p);
                sRight = (s != null ? s.getRight() : null);
            }
            if (s != null) s.isBlackNode = p.isBlackNode; // DB-4
            if (p != null) p.isBlackNode = true;
            if (sRight != null) sRight.isBlackNode = true;
            rotate(s, p);
        } else {
            if (isBlack(sLeft)) { // DB-3 mirror
                if (sRight != null) sRight.isBlackNode = true;
                if (s != null) s.isBlackNode = false;
                rotate(sRight, s);
                s = siblingOf(x, p);
                sLeft = (s != null ? s.getLeft() : null);
            }
            if (s != null) s.isBlackNode = p.isBlackNode; // DB-4 mirror
            if (p != null) p.isBlackNode = true;
            if (sLeft != null) sLeft.isBlackNode = true;
            rotate(s, p);
        }
        x = root;
        break;
    }
    if (x != null) x.isBlackNode = true;
    if (root != null) root.isBlackNode = true;
}

}