package Heap;

public class HeapData {
    private int priority;

    public HeapData(int n) {
        priority = n;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
