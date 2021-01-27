package Stack;

public class MaxStack {
    private class Node{
        int value;
        Node next;
        Node oldMax;
        Node(){
            next = null;
            }
    }
    Node stack;
    Node maxStack;
    Node max;
    MaxStack(){
        stack = null;
        maxStack = null;
    }
    public void push(int  newElement){
        if(stack == null){
            Node n = new Node();
            n.value = newElement;
            stack = n;
            max = n;
        }
        else{
            Node n = new Node();
            n.value = newElement;
            n.next = stack;
            stack = n;
            if(newElement > max.value){
                n.oldMax = max;
                max = n;
            }
        }
    }
    public int pop(){
        if(stack == null){
            throw new NullPointerException("null");
        }
        if(stack.oldMax!= null ){
            max = stack.oldMax;
        }
        int top = stack.value;
        stack = stack.next;
        return top;
    }
    public int max() throws Exception {
        if(max == null) throw new Exception("Max is Null");
        return max.value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(stack == null)
            sb.append("[ ]");
        else{
            Node temp = stack;
            sb.append("[");
            do{
                sb.append(temp.value);
                sb.append(", ");
            }while ((temp = temp.next) != null);
            sb.append("]");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        MaxStack maxStack = new MaxStack();
        for (int i = 0; i < 10; i++) {
            maxStack.push(i);

        }
        System.out.println(maxStack);
        System.out.println(maxStack.max());
        maxStack.pop();
        System.out.println(maxStack.max());
        maxStack.push(13);
        System.out.println(maxStack.max());
        maxStack.pop();
        System.out.println(maxStack);
        System.out.println(maxStack.max());
    }
}
