package matrix;

public class Main {
    public static void main(String[] args) throws Exception {
        Matrix mat = new SparseMatrix(4, 1);
        for (int i = 1; i < 3; i++) {
            for (int j = 2; j < 3; j++) {
                mat.put(i,j, i+j);
            }
        }
        System.out.println(mat.toString());
    }



}
