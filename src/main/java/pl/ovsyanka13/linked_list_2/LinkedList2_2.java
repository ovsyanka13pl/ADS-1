package pl.ovsyanka13.linked_list_2;

import java.util.*;

public class LinkedList2_2 {

    public void reverse(LinkedList2 list) {
        Node previous = null;
        Node next = null;
        Node current = list.head;
        list.tail = list.head;
        while (current != null) {
            next = current.next;
            current.next = previous;
            current.prev = next;
            previous = current;
            current = next;
        }
        list.head = previous;
    }

    public boolean isCycle(LinkedList2 list) {
        Node slow = list.head;
        Node fast = list.head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public LinkedList2 mergeSort(LinkedList2 list) {
        if (list.head == null || list.head.next == null) {
            return list;
        }
        Pair<LinkedList2, LinkedList2> pair = new Pair<>(new LinkedList2(), new LinkedList2());
        halfSplit(list.head, pair);
        pair.first = mergeSort(pair.first);
        pair.second = mergeSort(pair.second);

        return mergeLists(pair.first, pair.second);
    }

    public void halfSplit(Node head, Pair<LinkedList2, LinkedList2> pair) {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            pair.first.head = head;
            pair.second.head = null;
        } else {
            Node slow = head;
            Node fast = head.next;
            while (fast != null) {
                fast = fast.next;
                if (fast != null) {
                    fast = fast.next;
                    slow = slow.next;
                }
            }
            pair.first.head = head;
            pair.second.head = slow.next;
            slow.next = null;
        }
    }

    public LinkedList2 mergeLists(LinkedList2 first, LinkedList2 second) {
        Node firstNode = first.head;
        Node secondNode = second.head;
        if (firstNode == null) {
            return second;
        } else if (secondNode == null) {
            return first;
        }
        Node mergeHead;
        if (firstNode.value <= secondNode.value) {
            mergeHead = firstNode;
            firstNode = firstNode.next;
        } else {
            mergeHead = secondNode;
            secondNode = secondNode.next;
        }

        Node mergeTail = mergeHead;
        while (firstNode != null && secondNode != null) {
            Node temp = null;
            if (firstNode.value <= secondNode.value) {
                temp = firstNode;
                firstNode = firstNode.next;
            } else {
                temp = secondNode;
                secondNode = secondNode.next;
            }
            mergeTail.next = temp;
            temp.prev = mergeTail;
            mergeTail = temp;
        }
        if (firstNode != null) {
            mergeTail.next = firstNode;
            firstNode.prev = mergeTail;
        } else if (secondNode != null) {
            mergeTail.next = secondNode;
        }
        while (mergeTail.next != null) {
            mergeTail = mergeTail.next;
        }
        return new LinkedList2(mergeHead, mergeTail, first.size + second.size);
    }
}
