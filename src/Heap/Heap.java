package Heap;

public class Heap {

    private HeapData[] heap_arr;
    private static final int DEF_MAX_HEAP_SIZE = 10; // Default maximum heap size
    int size;
    int curr_size;

    public Heap ( ) {
        this(DEF_MAX_HEAP_SIZE);
    }

    public Heap ( int maxNumber ) {
        if (maxNumber <= 0) {
            maxNumber = DEF_MAX_HEAP_SIZE;
        }
        size = maxNumber;
        heap_arr = new HeapData[size + 1];
        curr_size = 0;
        heap_arr[0] = new HeapData(Integer.MIN_VALUE);
    }

    public boolean isEmpty() {
        return curr_size == 0;
    }

    public boolean isFull() {
        return curr_size == size;
    }

    private int heapify(HeapData elem) {
        // sift the elem up until heap is organized
        int i = curr_size;
        while (heap_arr[i / 2].getPriority() > elem.getPriority() ) {
            heap_arr[i] = heap_arr[i / 2];
            i /= 2;
        }
        return i;
    }

    public void insert ( HeapData newElement ) {
        if (isFull())
            return;
        ++curr_size;
        heap_arr[heapify(newElement)] = newElement;
    }

    public HeapData removeMin() // Remove min priority element
    {
        if (isEmpty())
            return null;
        // take the last elemnt and delete its location
        HeapData min = heap_arr[1], temp = heap_arr[curr_size];
        heap_arr[curr_size] = null;
        curr_size--;
        // now compare temp to root's sons, and sift down, until the heap is organized
        int i = 1;      // i is the empty spot on each itr => it's sons are 2 * i, 2 * i +1
        while ((2 * i <= curr_size && heap_arr[2 * i].getPriority() < temp.getPriority()) ||       //
                (2 * i + 1 <= curr_size && heap_arr[2 * i + 1].getPriority() < temp.getPriority()) )
        {
            if ( 2 * i + 1 <= curr_size ) {     // i has two sons
                if ( heap_arr[2 * i].getPriority() < heap_arr[2 * i + 1].getPriority() ) {
                    heap_arr[i] = heap_arr[2 * i];      // put the left son, in the empty spot
                    i *= 2;
                }
                else {                                  // put the right son, in the empty spot
                    heap_arr[i] = heap_arr[2 * i + 1];
                    i = 2 * i + 1;
                }
            }                                   // i only has a left son
            else {
                heap_arr[i] = heap_arr[2 * i];
                i *= 2;
            }
        }
        heap_arr[i] = temp;         // found temps correct location
        return min;
    }
    public void PrintLargest(int k){
        System.out.println("k = " + k);
        if(isEmpty()){
            try {
                throw new Exception("Heap is empty u foool");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Heap kHeap = new Heap(k+1);
        HeapData temp = heap_arr[1];
        temp.index = 1;
        kHeap.insert(temp);

        for (int j = 0; j < k ; j++) {

            int leftIndex = kHeap.heap_arr[1].index * 2;
            int rightIndex = kHeap.heap_arr[1].index * 2 + 1;
            if(leftIndex < curr_size)
            {
                HeapData left = heap_arr[leftIndex];
                left.index = leftIndex;
                kHeap.insert(left);
            }
            if(rightIndex < curr_size)
            {
                HeapData right = heap_arr[rightIndex];
                right.index = rightIndex;
                kHeap.insert(right);
            }

            HeapData min = kHeap.removeMin();
            if(min != null)
                System.out.println(min);
            else
                return;
        }
    }

    public String toString() {
        if (isEmpty())
            return "empty heap";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 1 ; i < curr_size ; i++) {
            sb.append(heap_arr[i].getPriority()).append(", ");
        }
        sb.append(heap_arr[curr_size].getPriority()).append(']');
        return sb.toString();
    }

    public void clear() { curr_size = 0; }      // all the values in the array are now garbage, and are unreachable

}
