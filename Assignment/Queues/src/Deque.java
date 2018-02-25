import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by MinhTam on  2/25/2018.
 */
public class Deque <Item> implements Iterable<Item>  {
    private Node head = null, tail = null;
    private int size = 0;
    private class Node{
        private Item item;
        private Node next;
        Node(Item item){
            this.item = item;
            this.next = null;
        }
    }

    /**
     * @return Deque is emplty
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     *
     * @return return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * add the item to the front
     * @param item to add
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (head == null){
            head = tail = new Node(item);
        }
        else {
            Node newNode = new Node(item);
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    /**
     * add the item to the end
     * @param item add the item to the end
     */
    public void addLast(Item item){
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (head == null){
            head = tail = new Node(item);
        }
        else {
            Node newNode = new Node(item);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     *  remove and return the item from the front
     * @return  remove and return the item from the front
     */
    public Item removeFirst() {
        if (this.isEmpty()){
            throw new NoSuchElementException();
        }
        Item result = head.item;
        head = head.next;
        return result;
    }

    /**
     * remove and return the item from the end
     * @return return the item from the end
     */
    public Item removeLast() {
        if (this.isEmpty()){
            throw new NoSuchElementException();
        }
        Item result = tail.item;
        Node p = head;
        while ( p.next != tail){
            p = p.next;
        }
        p.next = null;
        tail = p;
        return result;
    }
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>    {
        private Node current = head;
        public boolean hasNext(){
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next(){
            Item item = current.item;
            current   = current.next;
            return item;
        }
    }
}
