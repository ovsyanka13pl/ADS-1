package pl.ovsyanka13.dynArray;

public class MultiDynArray_2<T> {
    public DynArray array;
    public int dimensions;
    public int[] sizes;
    Class clazz;

    public MultiDynArray_2(Class clz, int dimensions, int... sizes) {
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
