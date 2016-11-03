/**
 * Created by jb on 11/2/16.
 */
public class BSTNode<T extends Comparable<T>> implements Comparable<BSTNode<T>> {
    private T value;
    private BSTNode<T> left;
    private BSTNode<T> right;

    public BSTNode(final T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public void setLeft(final T left) {
        this.left = new BSTNode<>(left);
    }
    public void setRight(final T right) {
        this.right = new BSTNode<>(right);
    }
    public void setLeft(final BSTNode<T> left) { this.left = left; }
    public void setRight(final BSTNode<T> right) { this.right = right; }
    public void dropRight() { this.right = null; }
    public void dropLeft() { this.left = null; }

    public BSTNode<T> left() { return left; }
    public BSTNode<T> right() { return right; }
    public T value() { return value; }

    @Override public int compareTo(final BSTNode<T> other) {
        return this.value.compareTo(other.value);
    }
    public boolean isLeaf() {
        return left == null && right == null;
    }
    public boolean hasLeft() { return left != null; }
    public boolean hasRight() { return right != null; }
    public boolean hasOneChild() { return left == null ^ right == null; }
    public boolean hasTwoChildren() { return left != null && right != null; }

    private int maxDepth(final int currentLevel) {
        if (this.isLeaf()) {
            return currentLevel;
        }
        final int lMaxDepth = left == null ? currentLevel : left.maxDepth(currentLevel + 1);
        final int rMaxDepth = right == null ? currentLevel : right.maxDepth(currentLevel + 1);

        return Math.max(lMaxDepth, rMaxDepth);
    }

    public int maxDepth() {
        return maxDepth(1);
    }

    public T min() {
        if (this.left == null) {
            return this.value;
        } else {
            return this.left.min();
        }
    }

    public T max() {
        if (this.right == null) {
            return this.value;
        } else {
            return this.right.max();
        }
    }

    public String valueString() {
        return value.toString();
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        if (right != null) {
            stb.append("/----- ");
            stb.append(right.toString());
            stb.append('\n');
        }
        stb.append(valueString());
        if (left != null) {
            stb.append('\n');
            stb.append("\\----- ");
            stb.append(left.toString());
        }
        return stb.toString();
    }
}
