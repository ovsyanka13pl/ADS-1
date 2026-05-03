package pl.ovsyanka13.linked_list_2;

import java.util.*;

public class LinkedList2 {
    public Node head;
    public Node tail;
    public int size;

    public LinkedList2() {
        head = null;
        tail = null;
        size = 0;
    }

    public LinkedList2(Node head, Node tail, int size) {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    public void addInTail(Node _item) {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
        size++;
    }

    public Node find(int _value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == _value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;
        while (node != null) {
            if (node.value == _value)
                nodes.add(node);
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
                    } else {
                        currentNode.next.prev = previousNode;
                    }
                    size--;
                } else {
                    if (size != 0) {
                        this.head = this.head.next;
                        size--;
                        if (size == 0) {
                            this.tail = null;
                        } else {
                            this.head.prev = null;
                        }
                    }
                }
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return false;
    }

    public void removeAll(int _value) {
        boolean isRemoved = remove(_value);
        while (isRemoved) {
            isRemoved = remove(_value);
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
        if (_nodeToInsert == null || _nodeAfter == _nodeToInsert) {
            return;
        }
        if (_nodeAfter != null && !contains(_nodeAfter)) {
            return;
        }
        if (this.head == null) {
            this.head = _nodeToInsert;
            this.tail = _nodeToInsert;
            this.head.prev = null;
            this.head.next = null;
        } else {
            if (_nodeAfter == null) {
                _nodeToInsert.next = this.head;
                this.head.prev = _nodeToInsert;
                _nodeToInsert.prev = null;
                this.head = _nodeToInsert;
            } else {
                _nodeToInsert.next = _nodeAfter.next;
                _nodeToInsert.prev = _nodeAfter;
                if (_nodeAfter == this.tail) {
                    this.tail = _nodeToInsert;
                } else {
                    _nodeAfter.next.prev = _nodeToInsert;
                }
                _nodeAfter.next = _nodeToInsert;
            }
        }
        size++;
    }

    public void insertToHead(Node _nodeToInsert) {
        if (_nodeToInsert == null) {
            return;
        }
        insertAfter(null, _nodeToInsert);
    }

    public boolean contains(Node _node) {
        if (_node == null) {
            return false;
        }
        Node current = this.head;
        while (current != null) {
            if (current == _node) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}

class Node {
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }

    public Node(int _value, Node next) {
        value = _value;
        this.next = next;
        prev = null;
    }
}
