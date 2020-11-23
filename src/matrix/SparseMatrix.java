package matrix;

import labs.singlyLL.SLinkedList;

public class SparseMatrix implements Matrix {
    private SLinkedList<SparseMatrixEntry> entryList;
    private final int size;
    private double defaultValue;
    private boolean T;
    private int scalar;

    public SparseMatrix(int size, double defaultValue) throws Exception {
        this.size = size;
        this.defaultValue = defaultValue;
        entryList = new SLinkedList<>();
        T = false;
        scalar = 1;

        entryList.insert(new SparseMatrixEntry(defaultValue, -1, -1));
    }

    public SparseMatrix(SparseMatrix other) {
        this.size = other.size;
        this.defaultValue = other.defaultValue;
        this.entryList = other.entryList;
        this.T = other.T;
        this.scalar = other.scalar;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public double get(int i, int j) throws Exception {
        SparseMatrixEntry old_cursor = entryList.getCursor();
        if (T) {
            int temp;
            temp = i;
            i = j;
            j = temp;
        }

        if ((i < 0 || i >= size) || j < 0 || j >= size)
            throw new Exception("IndexOutOfBoundsException");

        entryList.gotoBeginning();
        while (this.entryList.gotoNext()) {
            SparseMatrixEntry entry = this.entryList.getCursor();
            if (entry.getI() == i && entry.getJ() == j) {
                this.entryList.gotoBeginning();
                while (entryList.getCursor() != old_cursor)
                    entryList.gotoNext();
                return entry.getValue() * scalar;
            }


        }

        this.entryList.gotoBeginning();
        while(entryList.getCursor() != old_cursor){
            entryList.gotoNext();
        }
        return defaultValue;



    }

    @Override
    public void put(int i, int j, double x) throws Exception {
        if (T) {
            int temp;
            temp = i;
            i = j;
            j = temp;
        }
        entryList.gotoBeginning();
        if ((i < 0 || i >= size) || j < 0 || j >= size)
            throw new Exception("IndexOutOfBoundsException");
        if (!entryList.gotoNext()) {
            entryList.insert(new SparseMatrixEntry(x, i, j));
            return;
        }

        entryList.gotoBeginning();
        while (this.entryList.gotoNext()) {
            SparseMatrixEntry entry = entryList.getCursor();
            if (entry.getI() == i && entry.getJ() == j) {
                entry.setValue(x / scalar);
                return;
            }

        }
        entryList.insert(new SparseMatrixEntry(x / scalar, i, j));
    }

    @Override
    public void transpose() {
        T = !T;
    } //O(1)

    @Override
    public void multByConstant(int C) {
        scalar *= C;
        defaultValue *= C;

    }

    public SparseMatrix add(SparseMatrix other) throws Exception {
       SparseMatrix res  = new SparseMatrix(size, defaultValue+other.defaultValue);
       entryList.gotoBeginning();
       while( entryList.gotoNext()){
           SparseMatrixEntry curr_entry = entryList.getCursor();
           int curr_i = curr_entry.getI();
           int curr_j = curr_entry.getJ();
           double cur_val = other.get(curr_i, curr_j) + get(curr_i, curr_j);
           res.put(curr_i, curr_j, cur_val);
       }
       other.entryList.gotoBeginning();
       while(other.entryList.gotoNext()){
           SparseMatrixEntry curr_entry = other.entryList.getCursor();
           int curr_i = curr_entry.getI();
           int curr_j = curr_entry.getJ();
           int orig_i = curr_i;
           int orig_j = curr_j;
           if(other.T){
               int temp = curr_i;
               curr_i = curr_j;
               curr_j = temp;
           }
           double cur_val = get(curr_i, curr_j) + other.get(curr_i, curr_j);
           res.put(curr_i, curr_j, cur_val);
       }
       return res;
    }

    public SparseMatrix substract(SparseMatrix other) throws Exception {
        other.multByConstant(-1);
        return this.add(other);
    }

    @Override
    public String toString() {
        //entryList.gotoBeginning();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    res.append(get(i, j)).append(" ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            res.append('\n');
        }
        return res.toString();
    }
}



