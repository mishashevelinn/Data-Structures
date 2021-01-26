package Queue;

public interface Queue<AnyType> // Constants & Methods common to queue ADTs
{
    public static final int DEF_MAX_QUEUE_SIZE = 10;// Default maximum queue size

    // Queue manipulation operations
    public void enqueue(AnyType newElement) throws Exception; // Enqueue element at rear

    public AnyType dequeue(); // Dequeue element from front

    public void clear(); // Remove all elements from queue

    // Queue status operations
    public boolean isEmpty(); //Is Queue empty?

    public boolean isFull(); //Is Queue full?

    public void showStructure(); //Outputs the elements in the queue
} // interface Queue