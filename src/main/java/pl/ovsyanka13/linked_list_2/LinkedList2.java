package pl.ovsyanka13.linked_list_2;

import java.util.*;

public class LinkedList2 {
    public Node head;
    public Node tail;
    private int size;

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

    // задача 1
    // реализоавть метод поиска первого узла
    // сложность решения: временная - O(n), пространственная - O(1)
    // пробегаем по списку с сравниваем значение. если оно совпадает - возвращаем ноду
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

    // задача 2
    // реализоавть метод поиска всех узлов
    // сложность решения: временная - O(n), пространственная - O(1)
    // пробегаем по списку с сравниваем значение. если оно совпадает - добавляем ноду в список и идем дальше
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

    // задача 3
    // реализоавть метод удаления узла по значению
    // сложность решения: временная - O(n), пространственная - O(1)
    // пробегаем по списку с сравниваем значение. если оно совпадает - перекладываем ссылки предыдущей ноды на следующую
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

    // задача 4
    // реализоавть метод удаления всех узлов по значению
    // сложность решения: временная - O(n), пространственная - O(1)
    // пробегаем по списку с сравниваем значение. если оно совпадает - перекладываем ссылки предыдущей ноды на следующую
    // повторяем пока не останется совпадений
    public void removeAll(int _value) {
        boolean isRemoved = remove(_value);
        while (isRemoved) {
            isRemoved = remove(_value);
        }
    }

    // задача 7
    // реализоавть метод очистки
    // сложность решения: временная - O(1), пространственная - O(1)
    // сбрасываем head, tail и size
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int count() {
        return this.size;
    }

    // задача 5
    // реализоавть метод вставки ноды после заданного узла
    // сложность решения: временная - O(n), пространственная - O(1)
    // пробегаем по списку. Находим ноду, после которой надо вставить, перебиваем ссылки
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

    // задача 6
    // реализоавть метод вставки ноды в начало
    // сложность решения: временная - O(1), пространственная - O(1)
    // вставляем ноду в начало, перебиваем ссылки
    public void insertToHead(Node _nodeToInsert) {
        if (_nodeToInsert == null) {
            return;
        }
        insertAfter(null, _nodeToInsert);
    }

    // задача 9
    // реализоавть метод реверса списка
    // сложность решения: временная - O(n), пространственная - O(1)
    // добавляем ноды prev, next, current. Идем по списку и переставляем ссылки
    public void reverse() {
        Node previous = null;
        Node next = null;
        Node current = this.head;
        this.tail = this.head;
        while (current != null) {
            next = current.next;
            current.next = previous;
            current.prev = next;
            previous = current;
            current = next;
        }
        this.head = previous;
    }

    // задача 10
    // проверить, зациклен ли лист
    // сложность решения: временная - O(n), пространственная - O(1)
    // добавляем ноды fast и slow. Fast меняется в два раза быстрее. Если fast = slow - значит есть цикл
    public boolean isCycle() {
        Node slow = this.head;
        Node fast = this.head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // задача 12
    // объединить два отсортированных списка в третий отсортирванный
    // сложность решения: временная - O(n), пространственная - O(1)
    // делаем указатели на первый лист и на второй лист. сравниваем их значение. Тот, чье значение меньше - добавляем
    // ноду в новый лист и сдвигаем указатель вперед на этом листе.
    public LinkedList2 mergeSortedLists(LinkedList2 first, LinkedList2 second) {
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

    // задача 11
    // отсортировать список
    // сложность решения: временная - O(nlogn), пространственная - O(n)
    // разбивае лист на половину, затем еще на половину и так, пока не останется 2 элемента в листе. сортируем их. Потом
    // мержим два отсортированных листа
    public LinkedList2 mergeSort(LinkedList2 list) {
        if (list.head == null || list.head.next == null) {
            return list;
        }
        Pair<LinkedList2, LinkedList2> pair = new Pair<>(new LinkedList2(), new LinkedList2());
        halfSplit(list.head, pair);
        pair.first = mergeSort(pair.first);
        pair.second = mergeSort(pair.second);

        return mergeSortedLists(pair.first, pair.second);
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

    public boolean contains(Node _node) {
        if (_node == null) {
            return false;
        }
        Node node = this.head;
        while (node != null) {
            if (node == _node) {
                return true;
            }
            node = node.next;
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
