package Stack;

import labs.singlyLL.SLinkedList;
import labs.singlyLL.SNode;

/*Singly Linked List based Stack implementation*/
public class StackList<T> implements Stack<T> {
    private SNode<T> top;   //Points on recently added elemnet
    private int curr_size;  //current size
    private int capacity;   //capacity
    SLinkedList<T> listOfStackElements; //Generic Singly Linked List


    /*Constructor:
    Sets a capacity according to passed parameter
    top is set to null
    current size = 0 represents an empty stack*/
    public StackList(int size){
        capacity = size;
        listOfStackElements = new SLinkedList<>();
        top = null;
        curr_size = 0;
    }

    public int getSize(){ return curr_size; }

/*addling a new element to the end of linked list*/
    @Override
    public void push(T newElement) {
        listOfStackElements.gotoEnd();  //this is just re-check. since Stack doesn't provide an option to
        listOfStackElements.insert(newElement); //move the cursor of inner Linked List, it will be always on end
        top = new SNode<>(newElement, null);    //O(1)
        curr_size++;                        //increment the current size
    }


    /*Removing a last added element
    * O(1)
    * Go to end calls are just to reassure that cursor points to a last element
    * It supposed already point at last*/
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
        if (isEmpty()) {    //handling a case when it was a single element in stack
            top = null;     //such that following call to getCursor won't get a NullPointerException
            return temp;
        }
        top.setElement(listOfStackElements.getCursor());
        return temp;    //returning a pop'd element
    }

    @Override
    public void clear() {
        listOfStackElements.clear();
        top = null;
        curr_size = 0;
        //O(1)
    }

    @Override
    public boolean isEmpty() {
        return curr_size == 0;
    }


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
