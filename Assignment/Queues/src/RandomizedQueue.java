import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
/**
 * Created by MinhTam on  2/25/2018.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Deque<Item> data;
    public RandomizedQueue(){
        data = new Deque<>();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    public void enqueue(Item item){
        int r = StdRandom.uniform(100);
        if(r % 2 == 0){
            data.addFirst(item);
        }
        else {
            data.addLast(item);
        }
    }
    public Item dequeue(){
       return data.removeFirst();
    }

    public Item sample(){
        return data.removeFirst();
    }
    @Override
    public Iterator<Item> iterator() {
        return data.iterator();
    }
}
