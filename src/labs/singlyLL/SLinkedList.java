package labs.singlyLL;

public class SLinkedList<T> implements List<T> {
    SNode<T> head;
    SNode<T> cursor;
    int size;

    public SLinkedList() {
        head = new SNode<>(null, null);
        cursor = new SNode<>(null, null);
        int size = 0;
    }

    @Override
    public void insert(T newElement) throws Exception {
        if (newElement == null) {
            throw new Exception("Null element Exception");
        }
        if (isEmpty()) {
            head = new SNode<>(newElement, null);//a case of empty List. Now list contains single element
            size++;
            cursor = head;
            return;

        }
        SNode<T> e = new SNode<>(newElement, cursor.getNext());
        e.setNext(cursor.getNext());
        cursor.setNext(e);
        cursor = e;
        size++;
    }

    @Override
    public void remove() throws Exception {
        if (isEmpty()) {
            throw new Exception("EmptyListException");
        }
        if (cursor == head) {
            head = head.getNext();
            cursor = head;
            size--;
            return;
        }
        if (cursor.getNext() == null) {
            gotoPrior();
            cursor.setNext(null);
            cursor = head;
            size--;
            return;
        }
        SNode<T> temp = cursor;
        SNode<T> old_cursor = cursor;
        gotoPrior();
        cursor.setNext(old_cursor.getNext());
        gotoNext();
        size--;

    }

    @Override
    public void replace(T newElement) throws Exception {
        if (newElement == null) {
            throw new Exception("nullArgumentException");
        }
        if (isEmpty()) {
            throw new Exception("EmptyListFetchException");
        }
        cursor.setElement(newElement);
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean gotoBeginning() {
        if (isEmpty())
            return false;
        cursor = head;
        return true;
    }

    @Override
    public boolean gotoEnd() {
        if (isEmpty())
            return false;
        while (cursor.getNext() != null) {
            cursor = cursor.getNext();
        }
        return true;
    }

    @Override
    public boolean gotoNext() throws Exception {
        if(isEmpty())
            throw new Exception("EmptyListFetchException");
        if(cursor.getNext() == null){
            return false;
        }
        cursor = cursor.getNext();
        return true;
    }

    @Override
    public boolean gotoPrior() throws Exception {
        if(isEmpty())
            throw new Exception("EmptyListFetchException");
        if(cursor.getNext() == head.getNext())
            return false;
        SNode<T> temp = head;
        while(temp.getNext() != cursor) {
            temp = temp.getNext();
        }
        cursor = temp;
        return true;
    }

    @Override
    public T getCursor() {
        return cursor.getElement();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        SNode<T> temp = head;
        res.append('{');
        if(size == 0) {
            res.append('}');
            return res.toString();
        }
        while (temp.getNext() != null){
            res.append(temp.getElement().toString());
            res.append(", ");
            temp = temp.getNext();
        }
        res.append(temp.getElement().toString());
        res.append('}');
        return res.toString();


    }
}
