package pl.ovsyanka13.dynArray;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DynArray<T> {
    public T[] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz) {
        clazz = clz; // нужен для безопасного приведения типов
        // new DynArray<Integer>(Integer.class);

        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity) {
        // array = (T[]) Array.newInstance(this.clazz, new_capacity);
        if (array == null) {
            array = (T[]) Array.newInstance(this.clazz, new_capacity);
        } else {
            array = Arrays.copyOf(array, new_capacity);
        }
        capacity = array.length;
    }

    public T getItem(int index) {
        if (index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    public void append(T itm) {
        if (count == array.length) {
            makeArray(count * 2);
        }
        array[count] = itm;
        count++;
    }

    public void insert(T itm, int index) {
        if (index > count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == count) {
            append(itm);
            return;
        }
        if (count == array.length) {
            makeArray(count * 2);
        }
        T[] secondArray = Arrays.copyOfRange(array, index, count);
        array[index] = itm;
        System.arraycopy(secondArray, 0, array,  index+1, secondArray.length);
        count++;
    }

    public void remove(int index) {
        if (index >= count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index != (count-1)) {
            T[] secondArray = Arrays.copyOfRange(array, index+1, count);
            System.arraycopy(secondArray, 0, array,  index, secondArray.length);
            array[count-1] = null;
        } else {
            array[index] = null;
        }
        count--;
        if ((count) < array.length/2) {
            int new_capacity = (int) (array.length/1.5);
            if (new_capacity < 16) {
                new_capacity = 16;
            }
            makeArray(new_capacity);
        }
    }

}
