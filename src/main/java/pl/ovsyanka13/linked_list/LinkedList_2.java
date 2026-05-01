package pl.ovsyanka13.linked_list;

import java.util.ArrayList;

public class LinkedList_2 {
    public Node_2 head;
    public Node_2 tail;
    private int size;

    public LinkedList_2() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addInTail(Node_2 item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
        size++;
    }

    public Node_2 find(int value) {
        Node_2 node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node_2> findAll(int _value) {
        ArrayList<Node_2> nodes = new ArrayList<Node_2>();
        Node_2 node = this.head;
        while (node != null) {
            if (node.value == _value) {
                nodes.add(node);
            }
            node = node.next;
        }
        return nodes;
    }

    public boolean remove(int _value) {
        Node_2 currentNode = this.head;
        Node_2 previousNode = null;
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

    public void insertAfter(Node_2 _nodeAfter, Node_2 _nodeToInsert) {
        if (_nodeToInsert == null || _nodeAfter == _nodeToInsert) {
            return;
        }
        Node_2 previousNode = null;
        Node_2 currentNode = this.head;
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

    public LinkedList joinLists(LinkedList first, LinkedList second) {
        if (first.count() == second.count()) {
            LinkedList resultList = new LinkedList();
            Node_2 firstListNode = first.head;
            Node_2 secondListNode = second.head;
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

class Node_2 {
    public int value;
    public Node_2 next;

    public Node_2(int _value) {
        value = _value;
        next = null;
    }
}
