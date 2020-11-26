package matrix;

public class TestSparseMatrix {
    public static void main(String[] args) throws Exception {
        SparseMatrix mat = new SparseMatrix(4, 1);
        for (int i = 1; i < 3; i++) {
            for (int j = 2; j < 3; j++) {
                mat.put(i, j, i + j);
            }
        }
        System.out.println(mat.get(1, 1)+" retrieving an element at position [1,1]\n");
        mat.multByConstant(4); //multiplication in O(1)
        System.out.println(mat+"After multiplication by 4\n");
        mat.put(0, 2, 3);
        mat.transpose(); //transpose and vice versa O(1)
        System.out.println(mat.toString()+"After one transpose\n");
        mat.transpose();
        System.out.println(mat.toString()+"Second transpose brings a matrix to its initial state\n");
        try {
            mat.put(1, 2, 1);
        }
        catch (Exception e){
            System.out.println("Out of bounds error handled for demonstration\nAttempt to access " +
                    "an entry on position [-2, 1]\n");//range error
        }
        mat.put(0,0, 10);
        System.out.println(mat+"inserting 10 on position [0, 0]");
        System.out.println("A matrix shape is "+mat.getSize()+" x "+mat.getSize()+", 2D\n"); //describing shape with getSize
        System.out.println(mat.get(2,2));

        SparseMatrix mat1 = new SparseMatrix(5, 5);
        mat1.put(0,0, 3);
        mat1.put(0, 1, 2);
        mat1.put(1, 0, 5);
        mat1.put(2, 1, 8);
        mat1.put(2,3, 19);

        mat1.transpose();
        System.out.println("mat1:");
        System.out.println(mat1);

        SparseMatrix mat2 = new SparseMatrix(5, 3);

        mat2.put(0, 0, 10);
        mat2.put(1,2, 4);
        mat2.put(2,3, 18);
        mat2.put(3,3, 40);



        mat2.transpose();
        System.out.println("mat2");
        System.out.println(mat2);
        System.out.println("mat1^T + mat2 = ");


        System.out.println(mat1.add(mat2));

        System.out.println("mat1^T - mat2^T");


        System.out.println(mat1.substract(mat2));



    }


}
