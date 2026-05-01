package pl.ovsyanka13.linked_list;

import java.util.ArrayList;

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

    public void removeAll(int _value) {
        boolean removed = remove(_value);
        while (removed) {
            removed = remove(_value);
        }
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int count() {
        return this.size;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        // здесь будет ваш код вставки узла после заданного

        // если _nodeAfter = null ,
        // добавьте новый элемент первым в списке
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
