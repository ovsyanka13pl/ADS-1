package pl.ovsyanka13.linked_list_2;

import pl.ovsyanka13.linked_list.LinkedList_2;

public class Main {
    public static void main(String[] args) {
        LinkedList2 linkedList2 = new LinkedList2();
        linkedList2.addInTail(new Node(1));
        linkedList2.addInTail(new Node(2));
        linkedList2.addInTail(new Node(3));
        linkedList2.addInTail(new Node(4));
        linkedList2.addInTail(new Node(5));
        linkedList2.addInTail(new Node(6));
        linkedList2.addInTail(new Node(7));
        Node current = linkedList2.head;
        while (current != null) {
            System.out.print(current.value + "-->");
            current = current.next;
        }
        System.out.println();
    }
}
