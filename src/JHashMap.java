import java.lang.reflect.Array;

/**
 * Created by jb on 10/30/16.
 */
public class JHashMap<K, T> {

    private class JHashEntry<K, T> {
        final K key;
        final T value;

        private JHashEntry(final K key,
                           final T value) {
            this.key = key;
            this.value = value;
        }

        private boolean matchesKey(final K other) {
            return this.key.equals(other);
        }
    }

    private final int size;
    private final JHashEntry<K, T>[] hashArray;

    private JHashMap(Class<JHashEntry> c, int size) {
        this.size = size;
        this.hashArray = (JHashEntry<K, T>[]) Array.newInstance(c, size);
    }

    public JHashMap(int size) {
        this(JHashEntry.class, size);
    }

    private int getHashCode(final K key) {
        final char keyStr[] = String.valueOf(key.hashCode()).toCharArray();
        int hash = 0;
        int seed = 13;

        for (int i = 0; i < keyStr.length; i++) {
            hash = (hash * seed) + keyStr[i];
            System.out.println("Hash: " + hash + "  " + keyStr[i]);
        }
        return Math.abs(hash) % this.size;
    }

    private int getHashProbe(final int neighbor) {
        return neighbor * neighbor;
    }

    private int getPosition(final int hash,
                            final int neighbor) {
        return (hash + getHashProbe(neighbor)) % this.size;
    }

    private int getOpenPosition(final K key) {
        final int hash = this.getHashCode(key);
        System.out.println("final hash: " + hash);
        int neighbor = 0;
        int position;

        do {
            position = getPosition(hash, neighbor++) % this.size;
            System.out.println(position);
        } while (hashArray[position] != null);

        return position;
    }

    public T put(final K key, final T value) {
        hashArray[getOpenPosition(key)] = new JHashEntry<>(key, value);
        return value;
    }

    private int getPosition(final K key) {
        final int hash = getHashCode(key);
        int neighbor = 0;
        int position;

        do {
            position = getPosition(hash, neighbor++) % this.size;
        } while (hashArray[position] != null && !hashArray[position].matchesKey(key));

        return (hashArray[position] == null) ? -1 : position;
    }

    public T get(final K key) {
        final int position = getPosition(key);
        return (position < 0) ? null : this.hashArray[position].value;
    }

    public T remove(final K key) {
        final int position = getPosition(key);
        if (position < 0) {
            return null;
        } else {
            final T toReturn = this.hashArray[position].value;
            this.hashArray[position] = null;
            return toReturn;

        }
    }

}
