package Source.Utilities;

public class CustomList<T> {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(T element) {
        if (size == elements.length) {
            resizeArray();
        }
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    public void set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        elements[index] = element;
    }

    public int size() {
        return size;
    }

    private void resizeArray() {
        int newCapacity = elements.length * 2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public boolean customEquals(T element1, T element2) {
        if (element1 == null || element2 == null) {
            return false;
        }
        return element1.toString().equals(element2.toString()); // Modify this logic as needed
    }

    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (customEquals((T) elements[i], element)) {
                return true;
            }
        }
        return false;
    }
}

