import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
/**
 * Created by MinhTam on  2/25/2018.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    // Store Items in a ResizingArray
    private Item[] a;
    private int size;
    /**
     * "Construct an empty randomized queue"
     */

    public RandomizedQueue()
    {
        a = (Item[]) new Object[1];
        size = 0;
    }
    /**
     * "Is the queue empty?"
     */

    public boolean isEmpty()
    {
        return (size <= 0);
    }



    /**
     * "Return the number of items on [sic] the queue"
     */

    public int size()
    {
        return size;
    }



    /**
     * "Add the item"
     * "Throw a java.lang.NullPointerException if the client attempts to add a
     * null item"
     *
     * Also doubles the length of the array when it is full.
     */

    public void enqueue(Item item)
    {
        if (item == null) throw new java.lang.NullPointerException();
        if (size == a.length) resize(2 * a.length);
        a[size++] = item;
    }



    /**
     * Resizes the array a to [capacity].
     *
     * This is a quadratic operation in the length of a,
     * and so should only be performed sparingly.
     *
     * Amortizing this cost over the number of operations which
     * can be performed in the new array, however,
     * the ResizingArray is constant.
     */

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) copy[i] = a[i];
        a = copy;
    }



    /**
     * "Remove and return a random item"
     * "Throw a java.util.NoSuchElementException if the client attempts to
     * sample or dequeue an item from an empty randomized queue"
     *
     * This operation picks an element at random to return, then overwrites
     * the Item at that index with the last Item in the array, then sets
     * the last Item-containing element to null.
     *
     * Also halves the length of the array when it is one-quarter empty.
     */

    public Item dequeue()
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(size);
        Item ans = a[index];
        if (index != size - 1) a[index] = a[size - 1];
        a[--size] = null;
        if (size >= 1 && size == a.length / 4) resize(a.length / 2);
        return ans;

    }
    /**
     * "Return (but do not remove) a random item"
     * "Throw a java.util.NoSuchElementException if the client attempts to
     * sample or dequeue an item from an empty randomized queue".
     */

    public Item sample()
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return a[StdRandom.uniform(size)];
    }



    @Override
    /**
     * "Return an independent iterator over items in random order"
     */
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }



    /**
     * A copy of the original resizing array with the ability to dequeue only.
     */

    private class RandomizedQueueIterator implements Iterator<Item> {
        // Store Items in a ResizingArray
        private int subsize = size;
        private final Item[] copy;
        /**
         * Copy the Items in the original array to the Iterator.
         */

        private RandomizedQueueIterator() {
            copy = (Item[]) new Object[subsize];
            for (int i = 0; i < subsize; i++) copy[i] = a[i];
        }



        @Override
        public boolean hasNext() { return subsize > 0; }
        @Override
        /**
         * "Throw a java.lang.UnsupportedOperationException if the client calls
         * the remove() method in the iterator"
         */

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }



        @Override
        /**
         * "Throw a java.util.NoSuchElementException if the client calls the
         * next() method in the iterator and there are no more items to return"
         *
         * This method is dequeue from above, but without resizing.
         */

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            int index = StdRandom.uniform(subsize);
            Item ans = copy[index];
            if (index != subsize - 1) copy[index] = copy[subsize - 1];
            copy[--subsize] = null;
            return ans;
        }
    }
}
