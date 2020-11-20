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
        defaultValue *= C;

    }

    public SparseMatrix add(SparseMatrix other) throws Exception {
        boolean index_match = false;
        if (this.size != other.size)
            throw new Exception("Can't perform addition on matrices of different shape");
        SparseMatrix res = new SparseMatrix(size, defaultValue + other.defaultValue);

        this.entryList.gotoBeginning();      //set all cursors to the most common element
        other.entryList.gotoBeginning();

        //temp_other.entryList.gotoNext();     //we want to start to compare from first non-common element in list
        // temp_this.entryList.gotoNext();
        while (this.entryList.gotoNext()) {
            this.entryList.gotoNext();
            int ThisI = this.entryList.getCursor().getI();                  //first iteration moves the cursor from common element
            int ThisJ = this.entryList.getCursor().getJ();
            double thisVal = this.get(ThisI, ThisJ);
            other.entryList.gotoBeginning();

            while (other.entryList.gotoNext()) {

                int otherI = other.entryList.getCursor().getI();
                int otherJ = other.entryList.getCursor().getJ();
                double otherVal = other.get(otherI, otherJ);

                if (ThisI == otherI && ThisJ == otherJ) {
                    res.entryList.insert(new SparseMatrixEntry((thisVal + otherVal), ThisI, ThisJ));
                    this.entryList.remove();
                    other.entryList.remove();
                    index_match = true;
                    break;
                }
            }
            if (index_match)
                break;
            res.entryList.insert(new SparseMatrixEntry(thisVal + other.defaultValue, ThisI, ThisJ));
            this.entryList.remove();
        }
        other.entryList.gotoBeginning();
        if (!other.entryList.gotoNext())
            return res;
        other.entryList.gotoBeginning();
        while (other.entryList.gotoNext()) {
            int otherI = other.entryList.getCursor().getI();
            int otherJ = other.entryList.getCursor().getJ();
            res.entryList.insert(new SparseMatrixEntry(other.get(otherI, otherJ) + this.defaultValue, otherI, otherJ));
        }
        return res;
    }

    public SparseMatrix substract(SparseMatrix other) throws Exception {
        other.multByConstant(-1);
        return this.add(other);
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



