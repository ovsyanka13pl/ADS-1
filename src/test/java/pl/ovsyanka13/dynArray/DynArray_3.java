package pl.ovsyanka13.dynArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynArray_3 {

    DynArray<Integer> dynArray = new DynArray<Integer>(Integer.class);

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < dynArray.array.length; i++) {
            dynArray.array[i] = i;
            dynArray.count++;
        }
    }

    @AfterEach
    public void tearDown() {
        dynArray.array = null;
        dynArray.count = 0;
    }

    @Test
    void makeArray() {
        // when
        dynArray.makeArray(20);
        // then
        assertEquals(0, (long)dynArray.array[0]);
        assertEquals(1, (long)dynArray.array[1]);
        assertEquals(15, (long)dynArray.array[15]);
        assertNull(dynArray.array[16]);
        assertEquals(20, (long)dynArray.capacity);
    }

    @Test
    void getItem() {
        // then
        assertEquals(0, (long)dynArray.getItem(0));
        assertEquals(1, (long)dynArray.getItem(1));
        assertEquals(15, (long)dynArray.getItem(15));
        dynArray.makeArray(20);
        assertNull(dynArray.getItem(16));
    }

    @Test
    void append() {
        // when
        dynArray.append(16);
        // then
        assertEquals(16, (long)dynArray.getItem(16));
        assertEquals(5, (long)dynArray.getItem(5));
        assertEquals(17, dynArray.count);
        assertEquals(32, dynArray.capacity);
    }

    @Test
    void insert() {
        // when
        dynArray.insert(17, 2);
        // then
        assertEquals(17, (long)dynArray.getItem(2));
        assertEquals(2, (long)dynArray.getItem(3));
        assertEquals(17, dynArray.count);
        assertEquals(32, dynArray.capacity);
        assertNull(dynArray.getItem(17));
        assertNull(dynArray.getItem(18));
    }

    @Test
    public void insertToTheEnd() {
        // when
        dynArray.insert(16, 16);
        // then
        assertEquals(16, (long)dynArray.getItem(16));
        assertEquals(17, dynArray.count);
        assertEquals(32, dynArray.capacity);
        assertNull(dynArray.getItem(17));
        assertNull(dynArray.getItem(18));
    }

    @Test
    void remove() {
        // when
        dynArray.remove(15);
        // then
        assertEquals(15, dynArray.count);
        assertEquals(16, dynArray.capacity);

        // when
        dynArray.remove(7);
        // then
        assertEquals(14, dynArray.count);
        assertEquals(16, dynArray.capacity);
    }

    @Test
    public void removeWhenBufferChanged() {
        // when
        dynArray.insert(16, 16);
        // then
        assertEquals(17, dynArray.count);
        assertEquals(32, dynArray.capacity);
        // when
        dynArray.remove(2);
        // then
        assertEquals(16, dynArray.count);
        assertEquals(32, dynArray.capacity);
        // when
        dynArray.remove(3);
        // then
        assertEquals(15, dynArray.count);
        assertEquals(21, dynArray.capacity);
    }
}