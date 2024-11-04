package Source.Utilities;

public class CustomEquals<T> {
    private T obj1;
    private T obj2;

    public CustomEquals(T obj1, T obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public boolean equals() {
        if (obj1 == null || obj2 == null) {
            return obj1 == obj2;
        }
        return obj1.equals(obj2);
    }
}
