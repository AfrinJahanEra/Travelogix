package Custom;

import java.util.ArrayList;
import java.util.List;

public class Customequals {


    public static <T> boolean equals(List<T> list1, List<T> list2) {

        if (list1 == null && list2 == null) {
            return true;
        }


        if (list1 == null || list2 == null) {
            return false;
        }


        if (list1.size() != list2.size()) {
            return false;
        }


        for (int i = 0; i < list1.size(); i++) {
            T elem1 = list1.get(i);
            T elem2 = list2.get(i);


            if (!equalsElement(elem1, elem2)) {
                return false;
            }
        }


        return true;
    }


    private static <T> boolean equalsElement(T elem1, T elem2) {

        if (elem1 == null && elem2 == null) {
            return true;
        }


        if (elem1 == null || elem2 == null) {
            return false;
        }


        return elem1.equals(elem2);
    }
}

