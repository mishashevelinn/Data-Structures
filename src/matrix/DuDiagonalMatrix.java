package matrix;
import java.lang.Math;
import java.util.Arrays;

public class DuDiagonalMatrix {
    double arr[];
    int size;
    boolean T;
    DuDiagonalMatrix(int size){
        arr = new double[(size-1) * 2];
        this.size = size;
        T = false;
    }
    double get(int i, int j){
        if(i > size || i < 0 || j < 0 || j > size){
            try {
                throw new Exception("BoundsErrorException");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(T){
            int temp = i;
            i = j;
            j = temp;
        }
        if(Math.abs(i - j ) != 1)
            return 0;
        if(i < j){
            return arr[i + j - 3];
        }
        if(i > j){
            return arr[i + j - 2];
        }
        return 0;
    }
    void put(double x, int i, int j){
            if(Math.abs(i - j) != 1){
                try {
                    throw new Exception("InveriantCorruptionException");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
         if(T){
             int temp = i;
             i = j;
             j = temp;
         }
        if(i < j){
            arr[i + j - 3] = x;
        }
        if(i > j){
            arr[i + j - 2] = x;
        }

    }
    void transpose(){
        T  =  !T;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append((int)get(i+1,j+1) + " ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DuDiagonalMatrix mat = new DuDiagonalMatrix(4);
        mat.put(2, 2,1);
        mat.put(2, 3, 4);
        System.out.println(mat.get(3,4));
        System.out.println(mat.get(4, 4));

        System.out.println(mat);
        mat.transpose();
        System.out.println(mat);
    }
}
