package MyLinkedList;

//ASSIGNMENT OF DATA STRUCTURES

/*
Authors:
- NIYOKWIZERWA ERIC           221011517
- MUREKEZI ISMAEL             221017483
- ISINGIZWE MUNEZERO VICTOR   221012557
- MAHORO MPAKANYI FLORIEN     221007396
*/


public class MyLinkedList {

    class Node {

        int data;
        Node next;

        Node() {
            next = null;
        }

        Node(int a) {
            this.data = a;
            next = null;
        }

        public int value() {
            return data;
        }
    }
    Node head;
    Node tail;
    int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public MyLinkedList(int a) {
        head = tail = new Node(a);
        size = 1;
    }

    public void insertAtFront(int a) {
        Node newNode = new Node(a);
        if (head != null) {

            newNode.next = head;
            head = newNode;
        } else {
            head = newNode;
            tail = newNode;
        }
        ++size;

    }

    public void insertAtBack(int a) {
        Node newNode = new Node(a);
        tail.next = newNode;
        tail = newNode;
        ++size;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[ ]";
        } else {
            Node current = head;
            String s = "[";
            while (current != null) {
                s += current.data;
                if (current.next == null) {
                    s += "]";
                } else {
                    s += ",";
                }
                current = current.next;
            }
            return s;
        }

    }

    public boolean isEmpty() {
        return head == null;
    }

    public void reverse() {
        if (this.size() <= 1)// empty or we have one node!
        {
            return;
        } else {

            Node previous = this.head;
            Node forward = previous.next;
            Node other;
            while (forward.next != null) {
                other = forward.next;
                forward.next = previous;
                previous = forward;
                forward = other;
            }
            forward.next = previous;
            Node temp = head;
            head = tail;
            tail = temp;
            tail.next = null;
        }
    }

    public void insertAt(int a, int i) {
        if (i < 0 || i > this.size()) {
            return;
        } else if (i == 0) {
            insertAtFront(a);
        } else if (i == size()) {
            insertAtBack(a);
        } else {
            Node current = head;
            Node forward = head.next;
            for (int j = 1; j < i; ++j) {
                current = current.next;
                forward = forward.next;
            }
            Node newNode = new Node(a);
            current.next = newNode;
            newNode.next = forward;
        }
    }

    public boolean equals(MyLinkedList l) {
        if (this.size() != l.size()) {
            return false;
        }
        Node head1 = this.head;
        Node head2 = l.head;
        while (head1 != null) {
            if (head1.data != head2.data) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }

    public MyLinkedList concatenate(MyLinkedList l) {
        this.head.next = l.head;
       // this.head = l.tail.next;
      //  this.tail = l.head;
     
        this.tail = null;
        
        return this;
    }

    public static boolean isSorted(MyLinkedList l) //helper method to check if a linked l is sorted
    {//returns true if the Linked List is sorted in ascending order.
        Node currNode = l.head;
        while (currNode.next != null) {
            if (currNode.data > currNode.next.data) {
                return false;
            }
            currNode = currNode.next;
        }

        return true;
    }

    public MyLinkedList merge(MyLinkedList l)//Merges two sorted ls into a sorted one
    {
        if (!isSorted(this)) {
            return null;
        }
        if (!isSorted(l)) {
            return null;
        }
        Node firstCurrent, secondCurrent, resultNode;
        if (this.head.data < l.head.data) {
            firstCurrent = this.head.next;
            secondCurrent = l.head;
        } else {
            this.head = l.head;
            secondCurrent = l.head.next;
            firstCurrent = this.head;
        }
        resultNode = this.head;
        while (firstCurrent != null || secondCurrent != null) {
          
            if (firstCurrent == null) {
                resultNode.next=secondCurrent;
                resultNode = secondCurrent;
                secondCurrent = secondCurrent.next;
            } else if (secondCurrent == null) {
                resultNode.next=firstCurrent;
                resultNode = firstCurrent;
                firstCurrent = firstCurrent.next;

            } else if (firstCurrent.data < secondCurrent.data) {
                resultNode.next = firstCurrent;
                resultNode=firstCurrent;
                firstCurrent = firstCurrent.next;
            }
            else{
                resultNode.next=secondCurrent;
                resultNode=secondCurrent;
                secondCurrent=secondCurrent.next;
            }
            
        }
        
        this.tail=resultNode;
        resultNode.next=null;
        return this;
    }
    public static void main(String args[]) {
        MyLinkedList l1 = new MyLinkedList();
        l1.insertAtFront(1);
        l1.insertAtBack(29);
        l1.insertAtBack(40);
        l1.insertAtBack(41);
        l1.insertAtFront(-5);
      
         System.out.println("=====================================================");
        System.out.println(" Is List_one sorted?:" + isSorted(l1));
        System.out.println("First list is:");
        System.out.println(l1.toString());
        MyLinkedList l2 = new MyLinkedList();
        l2.insertAtFront(15);
        l2.insertAtBack(16);
        l2.insertAtBack(17);
        l2.insertAtBack(22);
        l2.insertAtBack(31);
        System.out.println("Is List_two sorted?:" + isSorted(l2));
        System.out.println("Second list is:");
        System.out.println(l2.toString());
        
        
        System.out.println("MergedList will be:");
        System.out.println(l1.merge(l2).toString());
        System.out.println("Concatenated list will be ");
        System.out.println(l1.concatenate(l2));
        System.out.println("=====================================================");
        

    }
}