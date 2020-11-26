package Stack;

import labs.singlyLL.SLinkedList;
import labs.singlyLL.SNode;

public class StackList<T> implements Stack<T> {
    private SNode<T> top;
    private int curr_size;
    private int capacity;
    SLinkedList<T> listOfStackElements;

    public StackList(int size){
        capacity = size;
        top = null;
        curr_size = 0;
    }

    public int getSize(){ return curr_size; }


    @Override
    public void push(T newElement) {
        top = new SNode<>(newElement, top);
        curr_size++;
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
        T temp = top.getElement();
        top = top.getNext();
        curr_size--;
        return temp;
    }

    @Override
    public void clear() {
        while(!isEmpty())
            pop();
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public boolean isFull() {
        return curr_size == capacity;
    }
}
