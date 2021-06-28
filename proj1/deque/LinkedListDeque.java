package deque;

import java.util.Iterator;

public class LinkedListDeque<Item> implements Iterable<Item>{

    // Node implementation
    public class Node{
        public Item item;
        public Node next;
        public Node prev;
        public Node(Item i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }
    // Sentinel node
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(Item item) {
        Node newNode = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(Item item) {
        Node tail = sentinel.prev;
        Node newNode = new Node(item, sentinel, tail);
        sentinel.prev = newNode;
        tail.next = newNode;
        size +=1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node pointer = sentinel.next;
        while (pointer != sentinel) {
            System.out.print(pointer.item);
            System.out.print(' ');
            pointer = pointer.next;
        }
        System.out.println();
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        /* Item deletedItem = sentinel.next.item; */
        Node deletedNode = sentinel.next;
        sentinel.next = deletedNode.next;
        deletedNode.next.prev = deletedNode.prev;
        if (sentinel.prev == deletedNode) {
            sentinel.prev = sentinel;
        }
        size -= 1;
        return deletedNode.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node deletedNode = sentinel.prev;
        Node newLast = deletedNode.prev;
        newLast.next = sentinel;
        sentinel.prev = newLast;
        size -= 1;
        return deletedNode.item;
    }

    public Item get(int index) {
        if (isEmpty()){
            return null;
        }
        Node pointer = sentinel;
        for(int i = 0; i < index && i < size; i++){
            pointer = pointer.next;
        }
        return pointer.item;
    }
    private class LinkedListDequeIterator implements Iterator<Item>{
        private Node pos;
        LinkedListDequeIterator() {
            pos = sentinel.next;
        }

        public boolean hasNext() {
            return pos != sentinel;
        }

        public Item next() {
            Item returnItem = pos.item;
            pos = pos.next;
            return returnItem;
        }
    }

    public Iterator <Item> iterator () {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (this == o){
            return true;
        } else if (! (o instanceof LinkedListDeque)) {
            return false;
        }

        LinkedListDeque<Item> other = (LinkedListDeque<Item>) o;
        if (this.size() != other.size()) {
            return false;
        }
        Iterator<Item> oIter = other.iterator();
        for (Item I : this) {
            if (!I.equals(oIter.next())) {
                return false;
            }
        }

        return true;

    }

    public Item getRecursive(int index) {
        if (index >= size){
            return null;
        } else {
            return getRecursiveHelper(sentinel.next, index);
        }
    }
    private Item getRecursiveHelper(Node curr, int index){
        if (index == 0) {
            return curr.item;
        } else {
            return getRecursiveHelper(curr.next, index - 1);
        }
    }
}

