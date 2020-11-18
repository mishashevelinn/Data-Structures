package matrix;

public interface Matrix {
    int getSize();
    double get(int i, int j) throws Exception;
    void put(int i, int j, double x) throws Exception;
    void transpose();
    void multByConstant(int C);
}
