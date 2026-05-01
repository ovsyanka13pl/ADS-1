package pl.ovsyanka13.linked_list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private LinkedList linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new LinkedList();
        linkedList.addInTail(new Node(1));
        linkedList.addInTail(new Node(2));
        linkedList.addInTail(new Node(3));
        linkedList.addInTail(new Node(4));
        linkedList.addInTail(new Node(5));
    }

    @AfterEach
    void tearDown() {
        linkedList = null;
    }

    @ParameterizedTest
    @CsvSource({
            "1, 4",
            "2, 4",
            "3, 4",
            "4, 4",
            "5, 4",
    })
    void remove(int valueToRemove, int expectedCount) {
        // when
        linkedList.remove(valueToRemove);
        // then
        assertEquals(expectedCount, linkedList.count());
    }

    @Test
    void removeAll() {
        // given
        linkedList.addInTail(new Node(3));
        linkedList.addInTail(new Node(3));
        linkedList.addInTail(new Node(3));
        // when
        linkedList.removeAll(3);
        // then
        assertEquals(0, linkedList.findAll(3).size());
    }

    @Test
    void clear() {
        // when
        linkedList.clear();
        // then
        assertEquals(0, linkedList.count());
    }

    @Test
    void count() {
        assertEquals(5, linkedList.count());
    }
}