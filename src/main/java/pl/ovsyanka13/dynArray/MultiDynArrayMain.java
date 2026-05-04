package pl.ovsyanka13.dynArray;

public class MultiDynArrayMain {
    public static void main(String[] args) {
        MultiDynArray_2<Integer> myArr = new MultiDynArray_2<>(Integer.class, 3, 2, 3, 4);
        myArr.setItem(777, 1, 2, 3);
        System.out.println(myArr.getItem(1, 2, 3));
    }
}
