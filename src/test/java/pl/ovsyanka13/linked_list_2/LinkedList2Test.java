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
        assertEquals(1,linkedList2.find(1).value);
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
    void reverse() {
        // when
        linkedList2.reverse();
        // then
        assertEquals(5, linkedList2.count());
        assertEquals(5, linkedList2.head.value);
        assertEquals(4, linkedList2.head.next.value);
        assertEquals(3, linkedList2.head.next.next.value);
        assertEquals(2, linkedList2.head.next.next.next.value);
        assertEquals(1, linkedList2.head.next.next.next.next.value);
        assertEquals(1, linkedList2.tail.value);
        assertEquals(2, linkedList2.tail.prev.value);
        assertEquals(3, linkedList2.tail.prev.prev.value);
        assertEquals(4, linkedList2.tail.prev.prev.prev.value);
        assertEquals(5, linkedList2.tail.prev.prev.prev.prev.value);
    }

    @Test
    void isCycle() {
        // given
        LinkedList2 notCyclic = new LinkedList2();
        notCyclic.addInTail(new Node(1));
        notCyclic.addInTail(new Node(2));
        notCyclic.addInTail(new Node(3));
        notCyclic.addInTail(new Node(4));
        notCyclic.addInTail(new Node(5));
        // when
        var expected = notCyclic.isCycle();
        // then
        assertFalse(expected);

        // given
        LinkedList2 cyclic = new LinkedList2();
        Node node_1 = new Node(1);
        cyclic.addInTail(node_1);
        cyclic.addInTail(new Node(2));
        cyclic.addInTail(new Node(3));
        cyclic.addInTail(new Node(4, node_1));
        // when
         expected = cyclic.isCycle();
        // then
        assertTrue(expected);
    }

    @Test
    void mergeSortedLists() {
        // given
        LinkedList2 firstSortedList = new LinkedList2();
        firstSortedList.addInTail(new Node(4));
        firstSortedList.addInTail(new Node(8));
        firstSortedList.addInTail(new Node(15));
        firstSortedList.addInTail(new Node(19));
        firstSortedList.addInTail(new Node(22));

        LinkedList2 secondSortedList = new LinkedList2();
        secondSortedList.addInTail(new Node(7));
        secondSortedList.addInTail(new Node(9));
        secondSortedList.addInTail(new Node(10));
        secondSortedList.addInTail(new Node(16));
        // when
        var expectedList = linkedList2.mergeSortedLists(firstSortedList, secondSortedList);
        // then
        assertEquals(9, expectedList.count());
        assertEquals(4, expectedList.head.value);
        assertEquals(7, expectedList.head.next.value);
        assertEquals(8, expectedList.head.next.next.value);
        assertEquals(9, expectedList.head.next.next.next.value);
        assertEquals(10, expectedList.head.next.next.next.next.value);
        assertEquals(15, expectedList.head.next.next.next.next.next.value);
        assertEquals(16, expectedList.head.next.next.next.next.next.next.value);
        assertEquals(19, expectedList.head.next.next.next.next.next.next.next.value);
        assertEquals(22, expectedList.head.next.next.next.next.next.next.next.next.value);
        assertEquals(22, expectedList.tail.value);
        assertEquals(19, expectedList.tail.prev.value);
        assertEquals(16, expectedList.tail.prev.prev.value);
        assertEquals(15, expectedList.tail.prev.prev.prev.value);
        assertEquals(10, expectedList.tail.prev.prev.prev.prev.value);
        assertEquals(9, expectedList.tail.prev.prev.prev.prev.prev.value);
        assertEquals(8, expectedList.tail.prev.prev.prev.prev.prev.prev.value);
        assertEquals(7, expectedList.tail.prev.prev.prev.prev.prev.prev.prev.value);
        assertEquals(4, expectedList.tail.prev.prev.prev.prev.prev.prev.prev.prev.value);
    }

    @Test
    void mergeSort() {
        // given
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(22));
        list.addInTail(new Node(15));
        list.addInTail(new Node(19));
        list.addInTail(new Node(8));
        list.addInTail(new Node(4));
        // when
        var expectedList = linkedList2.mergeSort(list);
        // then
        assertEquals(4, expectedList.head.value);
        assertEquals(8, expectedList.head.next.value);
        assertEquals(15, expectedList.head.next.next.value);
        assertEquals(19, expectedList.head.next.next.next.value);
        assertEquals(22, expectedList.head.next.next.next.next.value);
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