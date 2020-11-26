package matrix;

public interface Matrix {
    int getSize();

    double get(int i, int j) ;

    void put(int i, int j, double x) ;

    void transpose();

    void multByConstant(int C);
}
