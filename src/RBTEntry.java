/**
 * Created by jb on 11/2/16.
 */
public class RBTEntry<T extends Comparable<T>> extends BSTNode<T> {
    private boolean isRed;

    public RBTEntry(final T value) {
        super(value);
        this.isRed = true;
    }
}
