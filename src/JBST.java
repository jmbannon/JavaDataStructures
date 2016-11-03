import java.util.ArrayList;

/**
 * Created by jb on 10/30/16.
 */
public abstract class JBST<N extends BSTNode<T>, T extends Comparable<T>> {
    private BSTNode<T> rootNode;
    private int nodeCount;

    public JBST() {
        this.rootNode = null;
        this.nodeCount = 0;
    }

    public JBST(final T root) {
        this.rootNode = new BSTNode<>(root);
        this.nodeCount = 1;
    }

    public void insert(final T toInsert) {
        if (this.rootNode == null) {
            this.rootNode = new BSTNode<>(toInsert);
        } else {
            int compareInt;
            BSTNode<T> prevNode;
            BSTNode<T> currentNode = this.rootNode;
            do {
                prevNode = currentNode;
                compareInt = toInsert.compareTo(currentNode.value());
                if (compareInt <= 0) {
                    currentNode = currentNode.left();
                } else {
                    currentNode = currentNode.right();
                }
            } while (currentNode != null);

            if (compareInt <= 0) {
                prevNode.setLeft(toInsert);
            } else {
                prevNode.setRight(toInsert);
            }
        }
        ++this.nodeCount;
    }

    public void remove(final T toRemove) {
        boolean isLeftChild = false;
        int compareInt;
        BSTNode<T> prevNode;
        BSTNode<T> currentNode = this.rootNode;
        do {
            prevNode = currentNode;
            compareInt = toRemove.compareTo(currentNode.value());
            if (compareInt < 0) {
                currentNode = currentNode.left();
                isLeftChild = true;
            } else if (compareInt > 0) {
                currentNode = currentNode.right();
                isLeftChild = false;
            } else {
                break;
            }
        } while (currentNode != null);

        if (currentNode == null) {
            return;
        }

        if (currentNode.hasOneChild()) {
            if (isLeftChild) {
                prevNode.setLeft((currentNode.left() == null) ? currentNode.right() : currentNode.left());
            } else {
                prevNode.setRight((currentNode.left() == null) ? currentNode.right() : currentNode.left());
            }
        } else if (currentNode.hasTwoChildren()) {
            final T toSet = currentNode.right().min();
            this.remove(toSet);
            if (isLeftChild) {
                prevNode.setLeft(toSet);
            } else {
                prevNode.setRight(toSet);
            }
        } else {
            if (currentNode.compareTo(this.rootNode) == 0) {
                this.rootNode = null;
            } else if (isLeftChild) {
                prevNode.dropLeft();
            } else {
                prevNode.dropRight();
            }
        }
        --nodeCount;
    }

    private ArrayList<T> preOrderInternal(BSTNode<T> currentNode) {
        final ArrayList<T> toRet = new ArrayList<>();
        if (currentNode == null) {
            return toRet;
        }

        toRet.add(currentNode.value());
        if (currentNode.hasLeft()) toRet.addAll(preOrderInternal(currentNode.left()));
        if (currentNode.hasRight()) toRet.addAll(preOrderInternal(currentNode.right()));

        return toRet;
    }

    private ArrayList<T> inOrderInternal(BSTNode<T> currentNode) {
        final ArrayList<T> toRet = new ArrayList<>();
        if (currentNode == null) {
            return toRet;
        }

        if (currentNode.hasLeft()) toRet.addAll(inOrderInternal(currentNode.left()));
        toRet.add(currentNode.value());
        if (currentNode.hasRight()) toRet.addAll(inOrderInternal(currentNode.right()));

        return toRet;
    }

    private ArrayList<T> postOrderInternal(BSTNode<T> currentNode) {
        final ArrayList<T> toRet = new ArrayList<>();
        if (currentNode == null) {
            return toRet;
        }

        if (currentNode.hasLeft()) toRet.addAll(postOrderInternal(currentNode.left()));
        if (currentNode.hasRight()) toRet.addAll(postOrderInternal(currentNode.right()));
        toRet.add(currentNode.value());
        return toRet;
    }

    public ArrayList<T> preOrder() { return preOrderInternal(this.rootNode); }
    public ArrayList<T> inOrder() { return inOrderInternal(this.rootNode); }
    public ArrayList<T> postOrder() { return postOrderInternal(this.rootNode); }

    private ArrayList<T> getLayerNodesInternal(int layer, BSTNode<T> currentNode, int currentLayer) {
        final ArrayList<T> toRet = new ArrayList<>();
        if (layer == currentLayer) {
            toRet.add(currentNode.value());
        } else {
            if (currentNode.hasLeft()) {
                toRet.addAll(getLayerNodesInternal(layer, currentNode.left(), currentLayer + 1));
            }
            if (currentNode.hasRight()) {
                toRet.addAll(getLayerNodesInternal(layer, currentNode.right(), currentLayer + 1));
            }
        }
        return toRet;
    }

    public ArrayList<T> getLayerNodes(int layer) {
        return getLayerNodesInternal(layer, this.rootNode, 1);
    }

    public int height() {
        return this.rootNode.maxDepth();
    }
    public T min() {
        return this.rootNode == null ? null : this.rootNode.min();
    }
    public T max() {
        return this.rootNode == null ? null : this.rootNode.max();
    }
    @Override public String toString() {
        return rootNode.toString();
    }


}
