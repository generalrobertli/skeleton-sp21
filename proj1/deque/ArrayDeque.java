package deque;

import java.util.Iterator;
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private T[] next;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        next = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        // if the First pointer is before the Last pointer in the array, copy normally to new array
        if (nextFirst < nextLast - 1) {
            System.arraycopy(next,nextFirst + 1,newArray,1, size);
            nextFirst = 0;
            nextLast = size + 1;
        } else {
        // Last pointer is actually smaller than First pointer, so we need to copy everything on the right end as well
            System.arraycopy(next, 0 , newArray, 0, nextLast);
            System.arraycopy(next, nextFirst + 1 , newArray, capacity + nextFirst + 1 - next.length, next.length - nextFirst - 1);
        }
        next = newArray;
    }

    private int minusOne(int index) {
        // shifts the index past the sentinel back around to the last object
        if (index == 0) {
            return next.length - 1;
        } else {
            return index - 1;
        }

    }

    private int plusOne(int index) {
        // if we are at the last object in the array, shift back around to first object
        if (index == next.length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private double usageRatio() {
        // calculates how much of the array is being used
        return (double) size / (double) next.length;
    }

    public void addFirst(T item){
        // adds the given item to the nextFirst slot
        if (nextFirst == nextLast - 1 && next[nextFirst] != null ) {
            resize(size * 2);
        }
        next[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (nextFirst == nextLast - 1 && next[nextFirst] != null) {
            resize(size * 2);
        }
        next[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        //prints everything to the right of the First pointer and then everything to the left of the Last pointer
        for (int i = nextFirst + 1; i < next.length; i++){
            System.out.print(next[i] + " ");
        }
        for (int i = 0; i < nextLast; i++) {
            System.out.print(next[i] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T toReturn = next[nextFirst];
        next[nextFirst] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        if (usageRatio() < .25 && next. length >= 16) {
            resize(next.length / 2);
        }
        return toReturn;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T toReturn = next[nextLast];
        next[nextLast] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        if (usageRatio() < .25 && next. length >= 16) {
            resize(next.length / 2);
        }
        return toReturn;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        if (nextFirst + 1 + index < next.length) {
            return next[nextFirst + 1 + index];
        } else {
            return next[nextFirst + 1 + index - next.length];
        }
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;
        ArrayDequeIterator() {
            pos = 0;
        }
        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            T returnItem = get(pos);
            pos += 1;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals (Object o) {
        if (o == null) {
            return false;
        } else if (this == o) {
            return true;
        } else if (! (o instanceof ArrayDeque)) {
            return false;
        }

        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        Iterator<T> oIter = other.iterator();
        for (T item : this) {
            if (!item.equals(oIter.next())) {
                return false;
            }
        }
        return true;
    }
}
