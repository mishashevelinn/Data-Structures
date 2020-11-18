package matrix;

public class Main {
    public static void main(String[] args) throws Exception {
        Matrix mat = new SparseMatrix(4, 1);
        for (int i = 1; i < 3; i++) {
            for (int j = 2; j < 3; j++) {
                mat.put(i, j, i + j);
            }
        }
        System.out.println(mat.get(1, 1)+"retrieving an element at position [1,1]\n");
        mat.multByConstant(4); //multiplication in O(1)
        System.out.println(mat+"After multiplication by 4\n");
        mat.put(0, 2, 3);
        mat.transpose(); //transpose and vice versa O(1)
        System.out.println(mat.toString()+"After one transpose\n");
        mat.transpose();
        System.out.println(mat.toString()+"Second transpose brings a matrix to its initial state\n");
        try {
            mat.put(1, -2, 1);
        }
        catch (Exception e){
            System.out.println("Out of bounds error handled for demonstration\nAttempt to access " +
                    "an entry on position [-2, 1]\n");//range error
        }
        mat.put(0,0, 10);
        System.out.println(mat+"inserting 10 on position [0, 0]");
        System.out.println("A matrix shape is "+mat.getSize()+" x "+mat.getSize()+", 2D\n"); //describing shape with getSize
    }


}
