package Stack;

public interface Stack<T> {
    public final int CAPACITY = 50;
    public static final int DEF_MAX_STACK_SIZE = 10;
    public void push(T newElement); // Push newElement onto stack
    public T pop(); // Pop Element from top of stack
    public void clear(); // Remove all elements from stack
    public boolean isEmpty(); // Return true if stack is empty
    public boolean isFull(); // Return true if stack is full

}

