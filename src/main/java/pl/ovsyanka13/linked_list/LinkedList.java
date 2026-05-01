package pl.ovsyanka13.linked_list;

import java.util.*;

/*
 Задание 1
 Реализовать LinkedList
*/
public class LinkedList {
    public Node head;
    public Node tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
        size++;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    // задача 4
    // реализоавть метод findAll
    // сложность решения: временная - O(n), пространственная - O(n)
    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;
        while (node != null) {
            if (node.value == _value) {
                nodes.add(node);
            }
            node = node.next;
        }
        return nodes;
    }

    // задача 1
    // реализоавть метод remove
    // сложность решения: временная - O(n), пространственная - O(1)
    public boolean remove(int _value) {
        Node currentNode = this.head;
        Node previousNode = null;
        while (currentNode != null) {
            if (currentNode.value == _value) {
                if (previousNode != null) {
                    previousNode.next = currentNode.next;
                    if (currentNode.next == null) {
                        this.tail = previousNode;
                    }
                } else {
                    this.head = this.head.next;
                    if (this.head == null) {
                        this.tail = null;
                    }
                }
                size--;
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return false;
    }

    // задача 2
    // реализоавть метод removeAll
    // сложность решения: временная - O(n), пространственная - O(1)
    public void removeAll(int _value) {
        boolean removed = remove(_value);
        while (removed) {
            removed = remove(_value);
        }
    }

    // задача 3
    // реализоавть метод clear
    // сложность решения: временная - O(1), пространственная - O(1)
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // задача 5
    // реализоавть метод count
    // сложность решения: временная - O(1), пространственная - O(1)
    public int count() {
        return this.size;
    }

    // задача 6
    // реализоавть метод insertAfter
    // сложность решения: временная - O(n), пространственная - O(1)
    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeToInsert == null || _nodeAfter == _nodeToInsert) {
            return;
        }
        Node previousNode = null;
        Node currentNode = this.head;
        while (currentNode != null) {
            if (currentNode == _nodeAfter) {
                previousNode = _nodeAfter;
                break;
            }
            currentNode = currentNode.next;
        }

        if (_nodeAfter != null && previousNode == null) {
            return;
        }
        if (this.head == null) {
            this.head = _nodeToInsert;
            this.tail = _nodeToInsert;
        } else {
            if (_nodeAfter == null) {
                _nodeToInsert.next = this.head;
                this.head = _nodeToInsert;
            } else {
                _nodeToInsert.next = _nodeAfter.next;
                _nodeAfter.next = _nodeToInsert;
                if (_nodeAfter == this.tail) {
                    this.tail = _nodeToInsert;
                }
            }
        }
        size++;
    }

    // задача 8
    // реализоавть метод суммирования значений нод
    // сложность решения: временная - O(n), пространственная - O(n)
    // пробегаем по первому и второму спискам одновременно, создаем новый список, куда кладем суммы значений двух списков
    public LinkedList joinLists(LinkedList first, LinkedList second) {
        if (first.count() == second.count()) {
            LinkedList resultList = new LinkedList();
            Node firstListNode = first.head;
            Node secondListNode = second.head;
            for (int i=0; i < first.count(); i++) {
                resultList.addInTail(new Node(firstListNode.value + secondListNode.value));
                firstListNode = firstListNode.next;
                secondListNode = secondListNode.next;
            }
            return resultList;
        } else {
            return null;
        }
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int _value) {
        value = _value;
        next = null;
    }
}
