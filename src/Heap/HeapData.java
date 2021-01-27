package Heap;

public class HeapData {
    private int priority;
    public int index = 0;

    public HeapData(int n) {
        priority = n;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "HeapData{" +
                "priority=" + priority +
                ", index=" + index +
                '}';
    }
}
