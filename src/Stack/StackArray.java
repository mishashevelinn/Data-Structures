package Stack;
/*Fields:
*       size - size of the stack
*       element - array of elements
*       top - index of an element, entered a stack last*/
public class StackArray<T> implements Stack<T>{
    private int size;
    private T[] element;
    private int top;

/*Constructor:
*           single parameter size - sets a capacity of inner array which is capacity of stack itself
*           initializes and inner array with fixed size, according to a parameter
*           sets top to be -1, represents an empty array, since negative index in array is illegal*/
public StackArray(int size){
        this.size = size;
        element =(T[]) new Object[size];
        top = -1;
    }
/*push method appends an element to the end of array
since top is increments with each push, last element
always will be indexed by top. It fits fine a first push, sine top
was initialized as -1
Complexity - O(1)
*/
    @Override
    public void push(Object newElement) {
        if(isFull())
            try {
                throw new Exception("FullStackException");
            } catch (Exception e) {
                e.printStackTrace();
            }
        element[++top] = (T) newElement;
    }
/*Decreasing the top is enough to remove an elemnt from stack
* since top always points at last element
* also returns a pop'd element as required
* complexity - O(1)*/
    @Override
    public T pop() {
        if(isEmpty())
            try{
                throw new Exception("EmptyStackException");
            }
        catch (Exception e){
                e.printStackTrace();
        }
        T element;
        element = this.element[top];
        top--;
        return element;
    }

    @Override
    /*enough that isEmpty method would return true,
    * to claim that Stack is empty
    * Setting top to be its initial value -1
    * defines a return value of isEmpty
    * complexity - O(1)*/
    public void clear() {
        top = -1;
    }


    @Override
    public boolean isEmpty() { return top < 0;}


    @Override
    public boolean isFull() {
        return top + 1 == size;
    }

    int getSize(){ return size; }

    public String toString(){
        StringBuilder s;
        s = new StringBuilder("[");

        if(isEmpty()) return "[ ]";

        for (int i = 1; i <= top; i++) {
            s.append(", ").append(element[i]);
        }

        s.append(']');

        return  s.toString();
    }
}
