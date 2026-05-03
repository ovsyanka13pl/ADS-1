package pl.ovsyanka13.linked_list_2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedList2Test {

    private LinkedList2 linkedList2;

    @BeforeEach
    void setUp() {
        linkedList2 = new LinkedList2();
        linkedList2.addInTail(new Node(1));
        linkedList2.addInTail(new Node(2));
        linkedList2.addInTail(new Node(3));
        linkedList2.addInTail(new Node(4));
        linkedList2.addInTail(new Node(5));
    }

    @AfterEach
    void tearDown() {
        linkedList2 = null;
    }

    @Test
    void find() {
        // then
        assertEquals(1, linkedList2.find(1).value);
        assertEquals(2, linkedList2.find(2).value);
        assertEquals(3, linkedList2.find(3).value);
        assertEquals(4, linkedList2.find(4).value);
        assertEquals(5, linkedList2.find(5).value);
    }

    @Test
    void findAll() {
        // given
        linkedList2.addInTail(new Node(3));
        linkedList2.addInTail(new Node(3));
        linkedList2.addInTail(new Node(3));
        // then
        assertEquals(4, linkedList2.findAll(3).size());
    }

    @Test
    void remove() {
        // when
        linkedList2.remove(1);
        // then
        assertEquals(4, linkedList2.count());
        // when
        linkedList2.remove(2);
        // then
        assertEquals(3, linkedList2.count());
        // when
        linkedList2.remove(3);
        // then
        assertEquals(2, linkedList2.count());
        // when
        linkedList2.remove(4);
        // then
        assertEquals(1, linkedList2.count());
        // when
        linkedList2.remove(5);
        // then
        assertEquals(0, linkedList2.count());
    }

    @Test
    void removeAll() {
        // given
        linkedList2.addInTail(new Node(3));
        linkedList2.addInTail(new Node(3));
        linkedList2.addInTail(new Node(3));
        // when
        linkedList2.removeAll(3);
        // then
        assertEquals(0, linkedList2.findAll(3).size());
    }

    @Test
    void clear() {
        // when
        linkedList2.clear();
        // then
        assertEquals(0, linkedList2.count());
    }

    @Test
    void count() {
        // then
        assertEquals(5, linkedList2.count());
    }

    @Test
    void insertAfter() {
        // given
        Node node = new Node(6);
        // when
        linkedList2.insertAfter(linkedList2.find(2), node);
        // then
        assertEquals(6, linkedList2.count());
        assertEquals(6, linkedList2.find(2).next.value);
    }

    @Test
    void insertAfterWhenNodeAfterIsNull() {
        // given
        Node node = new Node(6);
        // when
        linkedList2.clear();
        linkedList2.insertAfter(null, node);
        // then
        assertEquals(1, linkedList2.count());
        assertEquals(6, linkedList2.find(6).value);
    }

    @Test
    void insertAfterIntoEmptyLinkedList() {
        // given
        Node node = new Node(6);
        LinkedList2 linkedListEmpty = new LinkedList2();
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
        LinkedList2 linkedListEmpty = new LinkedList2();
        // when
        linkedListEmpty.insertAfter(new Node(4), node);
        // then
        assertEquals(0, linkedListEmpty.count());
        assertNull(linkedListEmpty.find(6));
        assertNull(linkedListEmpty.head);
    }

    @Test
    void insertToHead() {
        // given
        Node node = new Node(6);
        // when
        linkedList2.insertToHead(node);
        // then
        assertEquals(6, linkedList2.count());
        assertEquals(6, linkedList2.find(6).value);
        assertEquals(6, linkedList2.head.value);
    }

    @Test
    void contains() {
        // then
        assertFalse(linkedList2.contains(new Node(15)));
        // given
        Node node16 = new Node(16);
        // when
        linkedList2.addInTail(node16);
        // then
        assertTrue(linkedList2.contains(node16));
        assertFalse(linkedList2.contains(null));
    }
}