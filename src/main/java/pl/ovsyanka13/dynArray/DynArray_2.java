package pl.ovsyanka13.dynArray;

import java.lang.reflect.Array;
import java.util.Arrays;

// Задание 3. Задача 6
// Реализовать динамический массив на основе банковского метода
// Изменены два метода: append и insert. Добавлена переменная bank, которая копит баланс при вставке.
// Если bank превышает стоимость вставки и массив заполнен, создаем новый массив, увеличивая размер на стоимость
// По сути, временная сложность не меняется. Все равно нужно пробегать весь массив и копировать его.
// Этим способом мы просто не даем разрастаться массива в геометрической прогрессии
// Сложность вставки при увеличении массива - O(n), но в среднем O(1)
public class DynArray_2<T> {
    public T[] array;
    public int count;
    public int capacity;
    public int bank;
    Class clazz;

    public DynArray_2(Class clz) {
        clazz = clz; // нужен для безопасного приведения типов
        // new DynArray<Integer>(Integer.class);

        count = 0;
        bank = 0;
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
            int price = 1;
            while (price * 2 <= count + 1) {
                price *= 2;
            }
            if (bank >= price) {
                bank -= price;
                makeArray(array.length + price);
            } else {
                makeArray(count * 2);
            }
        }
        array[count] = itm;
        bank += 3;
        bank -= 1;
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
            int price = 1;
            while (price * 2 <= count + 1) {
                price *= 2;
            }
            if (bank >= price) {
                bank -= price;
                makeArray(array.length + price);
            } else {
                makeArray(count * 2);
            }
        }
        T[] secondArray = Arrays.copyOfRange(array, index, count);
        array[index] = itm;
        System.arraycopy(secondArray, 0, array,  index+1, secondArray.length);
        bank += 3;
        bank -= 1;
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
