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
        listOfStackElements = new SLinkedList<>();
        top = null;
        curr_size = 0;
    }

    public int getSize(){ return curr_size; }


    @Override
    public void push(T newElement) {
        listOfStackElements.gotoEnd();
        listOfStackElements.insert(newElement);
        top = new SNode<>(newElement, null);
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
        listOfStackElements.gotoEnd();
        listOfStackElements.remove();
        listOfStackElements.gotoEnd();
        curr_size--;
        if (isEmpty()) {
            top = null;
            return temp;
        }
        top.setElement(listOfStackElements.getCursor());

        return temp;
    }

    @Override
    public void clear() {
        while(!isEmpty())
            pop();
    }

    @Override
    public boolean isEmpty() {
        return curr_size == 0;
    }

    public T getTop() { return top.getElement();}

    @Override
    public boolean isFull() {
        return curr_size == capacity;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        listOfStackElements.gotoBeginning();
        if(isEmpty()) return "[ ]";

        res.append("[ ");

        if( curr_size >= 1 ) res.append(listOfStackElements.getCursor());
        while (true) {
            try {
                if (!listOfStackElements.gotoNext()) break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            res.append(", " + listOfStackElements.getCursor() );
        }
        res.append(" ]");
        return res.toString();

    }
}
