package Test.Utilities;

import Source.Utilities.CustomList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomListTest {

    @Test
    void testAddAndSize() {
        CustomList<String> customList = new CustomList<>();
        customList.add("Hello");
        customList.add("World");

        assertEquals(2, customList.size());
    }

    @Test
    void testGet() {
        CustomList<Integer> customList = new CustomList<>();
        customList.add(1);
        customList.add(2);

        assertEquals(1, customList.get(0));
        assertEquals(2, customList.get(1));
    }

    @Test
    void testSet() {
        CustomList<String> customList = new CustomList<>();
        customList.add("First");
        customList.set(0, "Updated");

        assertEquals("Updated", customList.get(0));
    }

    @Test
    void testIndexOutOfBoundsOnGet() {
        CustomList<String> customList = new CustomList<>();
        customList.add("Item");

        assertThrows(IndexOutOfBoundsException.class, () -> customList.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> customList.get(-1));
    }

    @Test
    void testIndexOutOfBoundsOnSet() {
        CustomList<String> customList = new CustomList<>();
        customList.add("Item");

        assertThrows(IndexOutOfBoundsException.class, () -> customList.set(1, "NewItem"));
        assertThrows(IndexOutOfBoundsException.class, () -> customList.set(-1, "NewItem"));
    }

    @Test
    void testClear() {
        CustomList<String> customList = new CustomList<>();
        customList.add("Item1");
        customList.add("Item2");

        customList.clear();
        assertEquals(0, customList.size());
    }

    @Test
    void testResize() {
        CustomList<Integer> customList = new CustomList<>();
        for (int i = 0; i < 15; i++) {
            customList.add(i);
        }
        assertEquals(15, customList.size());
        for (int i = 0; i < 15; i++) {
            assertEquals(i, customList.get(i));
        }
    }
}
