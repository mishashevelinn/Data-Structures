package Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        Queue q = new SQueue<Integer>(10);
        for (int i = 0; i <5; i++) {
            q.enqueue(i);
        }
        q.dequeue();
        for (int i = 5; i < 10; i++) {
            q.enqueue(i);
        }
        q.showStructure();
    }

}
