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

// Задание 3. Задача 7
// Реализовать многомерный динамический массив
// За основу взят DynArray, т.е. это будет массив динамических массивов DynArray
// DynArray содержит в себе DynArray и так далее, пока в конце не будет DynArray<T>
// Временная сложность вставки остается O(1), а в случаях расширения массива - O(n).
// Даже если понадобится расширять, допустим 3 массива  - это будет O(3n), что эквивалентно O(n)
class MultiDynArray<T> {
    public DynArray array;
    public int dimensions;
    public int[] sizes;
    Class clazz;

    public MultiDynArray(Class clz, int dimensions, int... sizes) {
        this.clazz = clz;
        this.dimensions = dimensions;
        this.sizes = sizes;

        array = createLevel(0);
    }

    private DynArray createLevel(int level) {
        DynArray result;

        if (level == dimensions - 1) {
            result = new DynArray(clazz);
        } else {
            result = new DynArray(DynArray.class);
        }

        for (int i = 0; i < sizes[level]; i++) {
            if (level == dimensions - 1) {
                result.append(null);
            } else {
                result.append(createLevel(level + 1));
            }
        }

        return result;
    }

    public T getItem(int... indexes) {
        if (indexes.length != dimensions) {
            throw new ArrayIndexOutOfBoundsException();
        }

        DynArray current = array;

        for (int i = 0; i < dimensions - 1; i++) {
            current = (DynArray) current.getItem(indexes[i]);
        }

        return (T) current.getItem(indexes[dimensions - 1]);
    }

    public void setItem(T item, int... indexes) {
        if (indexes.length != dimensions) {
            throw new ArrayIndexOutOfBoundsException();
        }

        DynArray current = array;

        for (int i = 0; i < dimensions - 1; i++) {
            while (indexes[i] >= current.count) {
                if (i == dimensions - 2) {
                    current.append(new DynArray(clazz));
                } else {
                    current.append(new DynArray(DynArray.class));
                }
            }

            current = (DynArray) current.getItem(indexes[i]);
        }

        while (indexes[dimensions - 1] >= current.count) {
            current.append(null);
        }

        current.array[indexes[dimensions - 1]] = item;
    }
}
