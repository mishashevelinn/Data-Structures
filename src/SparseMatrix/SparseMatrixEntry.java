package SparseMatrix;

public class SparseMatrixEntry {
    boolean T;
    private double value;
    private int i;
    private int j;

    public SparseMatrixEntry(double val, int i, int j) {
        this.value = val;
        this.i = i;
        this.j = j;

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
