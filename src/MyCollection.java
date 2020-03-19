import java.util.*;

public class MyCollection<T> extends AbstractCollection<T> {

    private int size;
    private T[] collection;

    public MyCollection() {
        size = 0;
        collection = (T[]) new Object[100];
    }

    public MyCollection(Collection<? extends T> collection) {
        size = collection.size();
        this.collection = (T[]) new Object[size];
        Iterator it = collection.iterator();
        for (int i =0; i < size; i++) {
            this.collection[i] = (T) it.next();
        }
    }

    private class MyIterator implements Iterator<T> {

        private int cursor;

        private MyIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < collection.length;
        }

        @Override
        public T next() {
            if (cursor > collection.length-1) {
                throw new NoSuchElementException();
            }
            return collection[cursor++];
        }
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T t) {
        if (size < collection.length) {
            collection[++size] = t;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (size + c.size() < collection.length) {
            Iterator<? extends T> it = c.iterator();
            for (T elements : c) {
                collection[++size] = it.next();
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        collection = (T[]) new Object[100];
    }

    @Override
    public boolean contains(Object o) {
        for (Object c: collection) {
            if (c.equals(o)) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCollection<?> that = (MyCollection<?>) o;
        return size == that.size &&
                Arrays.equals(collection, that.collection);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(collection);
        return result;
    }
}
