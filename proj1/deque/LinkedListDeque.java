public class LinkedListDeque<Item> {

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
        size +=1
    }

    public boolean isEmpty() {
        if (size == 0){
            return true;
        } else {
            return false;
        }
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
        Item deletedItem = sentinel.next.item;
        Node deletedNode = sentinel.next;
        sentinel.next = deletedNode.next;
        deletedNode.next.prev = deletedNode.prev;
        if (sentinel.prev == deletedNode) {
            sentinel.prev = sentinel;
        }
        size -= 1;
        return deletedItem;
    }

    public Item removeLast() {

    }

    public Item get(int index) {

    }

    public Iterator <Item> iterator () {

    }

    public boolean equals(Object o) {

    }

    public Item getRecursive(int index) {

    }
}

