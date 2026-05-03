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
        linkedList2.reverse();
        Node current = linkedList2.head;
        while (current != null) {
            System.out.print(current.value + "-->");
            current = current.next;
        }
        System.out.println();
        System.out.println("-----------------------");
        LinkedList2_2 linkedList2_2 = new LinkedList2_2();
        linkedList2_2.addInTail(new Node(1));
        linkedList2_2.addInTail(new Node(2));
        linkedList2_2.addInTail(new Node(3));
        linkedList2_2.addInTail(new Node(4));
        linkedList2_2.addInTail(new Node(5));
        linkedList2_2.addInTail(new Node(6));
        linkedList2_2.addInTail(new Node(7));
        Node current_2 = linkedList2_2.head;
        while (current_2 != null) {
            System.out.print(current_2.value + "-->");
            current_2 = current_2.next;
        }


    }
}
