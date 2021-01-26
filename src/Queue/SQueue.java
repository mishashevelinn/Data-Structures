package Queue;
import Stack.Stack;
import Stack.StackList;
import Stack.StackArray;

class SQueue<AnyType> implements Queue {
    // Stack-based queue class
    final int DEFAULT_SIZE = 10;
    Stack<AnyType> eStack; //Enqueue stack
    Stack<AnyType> dStack; //Dequeue stack
    int curr_size;
    int size;
// Constructors and helper method setup
    public SQueue() {
        setup(DEFAULT_SIZE);
    } // Constructor: default size
    public SQueue(int size) {
        this.size = size;
      eStack = new StackList<AnyType>(size);
      dStack = new StackList<AnyType>(size);
    } // Constructor: sets size
    // Class methods
    private void setup(int size) {


        curr_size = 0;
    } // Called by Constructors only


    @Override
    public void enqueue(Object newElement) throws Exception {
        if(!isFull()) {
            eStack.push((AnyType) newElement);
            curr_size++;
            return;
        }
        throw new Exception("queue is full");
    }

    @Override
    public Object dequeue() {
        if(isEmpty()){
            try {
                throw new Exception("Stack is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Transfer all elements of enqueue stack to dequeue stack and then pop dequeue stack
        if(dStack.isEmpty()){
            while (!eStack.isEmpty())
            {
                dStack.push(eStack.pop());
            }
            curr_size--;
            return dStack.pop();
        }
        //Pop from deq. stack
        curr_size--;
        return dStack.pop();
    }

    @Override
    public void clear() {
        eStack.clear();
        dStack.clear();
        curr_size = 0;
    }

    @Override
    public boolean isEmpty() {
        return curr_size == 0;
    }

    @Override
    public boolean isFull() {
        return curr_size == size;
    }

    @Override
    public void showStructure() {
        System.out.print("DSTACK: ");
        System.out.println(dStack);
        System.out.print("ESTACK: ");
        System.out.println(eStack);

    }
//---Insert method implementations for the interface Queue here ---//
} // class SQueue
