package Utilities.Custom_Library;

public class CustomList<T> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    // add elements to the list
    public void add(T element) {
        if (size == elements.length) {
            resizeArray();
        }
        elements[size++] = element;
    }

    // get an element by index
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    // update an element at a specific index
    public void set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        elements[index] = element;
    }

    // return the size of the list
    public int size() {
        return size;
    }

    // resize the array when capacity is reached
    private void resizeArray() {
        int newCapacity = elements.length * 2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
}
