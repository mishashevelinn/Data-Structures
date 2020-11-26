package Stack;

public class StackArray<T> implements Stack<T>{
    private int size;
    private T[] element;
    private int top;


    StackArray(int size){
        this.size = size;
        element =(T[]) new Object[size];
        top = -1;



    }

    @Override
    public void push(Object newElement) {
        if(isFull())
            try {
                throw new Exception("FullStackException");
            } catch (Exception e) {
                e.printStackTrace();
            }
        top++;
        element[top] = (T) newElement;

    }

    @Override
    public T pop() {
        if(isEmpty())
            try{
                throw new Exception("EmptyStackException");
            }
        catch (Exception e){
                e.printStackTrace();
        }
        T element = (T) new Object();
        element = this.element[top];
        top--;
        return element;
    }

    @Override
    public void clear() {
        while (!isEmpty()){
            pop();
        }

    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top + 1 == size;
    }
    int getSize(){return size;}
}
