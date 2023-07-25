package util;

import java.lang.reflect.Array;

public class ObjectRemover {
    private ObjectRemover() {}

    public static <T> T[] removeObjectFromArray(T[] tArray, T t) {
        T[] newTArray = (T[]) Array.newInstance(tArray.getClass().getComponentType(), tArray.length - 1);
        int index = 0;
        for (int i = 0; i < tArray.length; i++) {
            if (tArray[i].equals(t)) {
                continue;
            }
            newTArray[index] = tArray[i];
            index++;
        }
        return newTArray;
    }
}

