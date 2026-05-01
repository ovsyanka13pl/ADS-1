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

    @Test
    void insertAfter() {
        // given
        Node node = new Node(6);
        // when
        linkedList.insertAfter(linkedList.find(2), node);
        // then
        assertEquals(6, linkedList.count());
        assertEquals(6, linkedList.find(2).next.value);
    }

    @Test
    void insertAfterWhenNodeAfterIsNull() {
        // given
        Node node = new Node(6);
        // when
        linkedList.clear();
        linkedList.insertAfter(null, node);
        // then
        assertEquals(1, linkedList.count());
        assertEquals(6, linkedList.find(6).value);
    }

    @Test
    void insertAfterIntoEmptyLinkedList() {
        // given
        Node node = new Node(6);
        LinkedList linkedListEmpty = new LinkedList();
        // when
        linkedListEmpty.insertAfter(null, node);
        // then
        assertEquals(1, linkedListEmpty.count());
        assertEquals(6, linkedListEmpty.find(6).value);
        assertEquals(6, linkedListEmpty.head.value);
        assertEquals(node, linkedListEmpty.head);
    }

    @Test
    void insertAfterIfNodeAfterIsNotInLinkedList() {
        // given
        Node node = new Node(6);
        LinkedList linkedListEmpty = new LinkedList();
        // when
        linkedListEmpty.insertAfter(new Node(4), node);
        // then
        assertEquals(0, linkedListEmpty.count());
        assertNull(linkedListEmpty.find(6));
        assertNull(linkedListEmpty.head);
    }

    @Test
    void joinLists() {
        // given
        LinkedList linkedList = new LinkedList();
        LinkedList firsList = new LinkedList();
        LinkedList secondList = new LinkedList();
        firsList.addInTail(new Node(1));
        firsList.addInTail(new Node(2));
        firsList.addInTail(new Node(3));
        secondList.addInTail(new Node(4));
        secondList.addInTail(new Node(5));
        secondList.addInTail(new Node(6));

        // when
        LinkedList resultList = linkedList.joinLists(firsList, secondList);

        // then
        assertEquals(5, resultList.head.value);
        assertEquals(7, resultList.head.next.value);
        assertEquals(9, resultList.tail.value);
    }
}