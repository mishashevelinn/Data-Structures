package labs.singlyLL;


//Facilitator class for the LinkedList class
public class SNode<T> {
    private T element; // element in the list
    private SNode<T> next; // reference to the next element

    public SNode(T element, SNode<T> next) {
        this.element = element;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T newElem) {
        element = newElem;
    }

    public SNode<T> getNext() {
        return next;
    }

    public void setNext(SNode<T> nextVal) {
        next = nextVal;
    }
}

