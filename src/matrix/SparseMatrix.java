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


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public double get(int i, int j) throws Exception {
        if (T) {
            int temp;
            temp = i;
            i = j;
            j = temp;
        }
        if ((i < 0 || i >= size) || j < 0 || j >= size)
            throw new Exception("IndexOutOfBoundsException");
        entryList.gotoBeginning();

        while (entryList.gotoNext()) {
            SparseMatrixEntry entry = entryList.getCursor();
            if (entry.getI() == i && entry.getJ() == j)
                return entry.getValue() * scalar;
        }
        entryList.gotoBeginning();
        return entryList.getCursor().getValue() * scalar;

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


        while (entryList.gotoNext()) {
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
    }

    @Override
    public void multByConstant(int C) {
        scalar *= C;

    }

    @Override
    public String toString() {
        entryList.gotoBeginning();
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

