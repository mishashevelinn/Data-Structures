package SparseMatrix;

import singlyLL.SLinkedList;

public class SparseMatrix implements Matrix {
    private SLinkedList<SparseMatrixEntry> entryList;
    private final int size;
    private double defaultValue;
    private boolean T;
    private int scalar;

    public SparseMatrix(int size, double defaultValue) {
        this.size = size;
        this.defaultValue = defaultValue;
        entryList = new SLinkedList<>();
        T = false;
        scalar = 1;

        try {
            entryList.insert(new SparseMatrixEntry(defaultValue, -1, -1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getSize() {
        return size;
    }

    /*Get is the basic function which helps to reduce complexity by providing functionality to other methods
     * like transpose, and multiplication by a scalar. Since we have to check whether indexes, passed to a method
     * do exists in list, a method works in O(r) */
    @Override
    public double get(int i, int j) {
        SparseMatrixEntry old_cursor = entryList.getCursor();   //represents transpose
        if (T) {
            int temp;       //basic swap of integers
            temp = i;
            i = j;
            j = temp;
        }

        if ((i < 0 || i >= size) || j < 0 || j >= size) //bounds check
            try {
                throw new Exception("IndexOutOfBoundsException");
            } catch (Exception e) {
                e.printStackTrace();
            }

        //Here is a check whether an entry of provided to a method indexes, already in list
        entryList.gotoBeginning();
        while (true) {
            try {
                if (!this.entryList.gotoNext()) break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            SparseMatrixEntry entry = this.entryList.getCursor();
            if (entry.getI() == i && entry.getJ() == j) { //if an entry exists
                this.entryList.gotoBeginning();
                while (entryList.getCursor() != old_cursor) {   //rewind cursor back from
                    try {                                       //where it entered the method
                        entryList.gotoNext();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return entry.getValue() * scalar;               //and return a value of entry
            }                                                   //multiplied by a scalar which is a field


        }

        this.entryList.gotoBeginning();                 //if an entry with provided indexes isn't in list
        while (entryList.getCursor() != old_cursor) {     //it means its default value
            try {                                       //now rewind the cursor to its original state
                entryList.gotoNext();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defaultValue;                            //and return default value


    }

    /*Put plays nearly the same role as get.
     Checking whether a Node exists in list, causes O(r) complexity*/
    @Override
    public void put(int i, int j, double x) {
        if (T) {                    //we have to swap given indexes in order to
            int temp;               //correctly plug in the value in transposed matrix
            temp = i;
            i = j;
            j = temp;
        }
        //a check whether an entry with give indexes already in list
        entryList.gotoBeginning();
        if ((i < 0 || i >= size) || j < 0 || j >= size)
            try {                                                        //bounds check
                throw new Exception("IndexOutOfBoundsException");
            } catch (Exception e) {
                e.printStackTrace();
            }
        try {                           //if there is only a head of lists - default value,
            if (!entryList.gotoNext()) {                    //we just plugging in a new node
                entryList.insert(new SparseMatrixEntry(x, i, j));   //and return
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //if there are more than one entry which differs from most common value, we have to iterate
        entryList.gotoBeginning();  //over whole list to find out if there is a node with indexes,  matching the passed
        while (true) {              //to method indexes
            try {
                if (!this.entryList.gotoNext()) break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            SparseMatrixEntry entry = entryList.getCursor();
            if (entry.getI() == i && entry.getJ() == j) {
                entry.setValue(x / scalar);             //if there is one, updating its value after dividing it by a
                return;                                 //scalar and return.
            }

        }
        try {                                             //if not, append the new node to the list
            entryList.insert(new SparseMatrixEntry(x / scalar, i, j));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    /*A flag, used in get and put methods, to handle a swaps of i and j
     * works in O(1)*/
    public void transpose() {
        T = !T;
    }

    @Override
    /*changes a flag, used in get and put methods to handle return values
     * Works in O(1), changes default value as well*/
    public void multByConstant(int C) {
        if (C == 0) {
            try {
                throw new Exception("Zero scalar multiplication is not allowed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        scalar *= C;
        defaultValue *= C;

    }

    /*Addition of two matrices. Its natural to use already existing methods - get and put.
     * We make two iterrations of both of lists, representing matrices. in each iteration, for
     * each element of list we apply a put method to add its sum with corresponding element
     * of the other matrix to a result matrix. put is good for that job, because it's not appending
     * already existing nodes to a list, but only updates the values. However each call for put costs us O(r).
     * So to sum up, we work O(r^2) each while cycle. Noticing that some calls of get ( O(r) ) are done as well but
     * it is not affecting a total complexity since O(3*r^2) = O(r^2)   */
    public SparseMatrix add(SparseMatrix other) {
        SparseMatrix res = null;       //a result matrix
        try {
            res = new SparseMatrix(size, defaultValue + other.defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        entryList.gotoBeginning();
        while (true) {     //iterating over whole list
            try {
                if (!entryList.gotoNext()) break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            SparseMatrixEntry curr_entry = entryList.getCursor();
            int curr_i = curr_entry.getI();     //collecting indexes
            int curr_j = curr_entry.getJ();      //and value info
            if (T) {                               //handling a transpose case
                int temp = curr_i;
                curr_i = curr_j;
                curr_j = temp;
            }
            double cur_val = 0;
            try {                        //adding corresponding values
                cur_val = other.get(curr_i, curr_j) + get(curr_i, curr_j);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //and appending it to a result matrix
            res.put(curr_i, curr_j, cur_val);
        }

        //iterating over a second, argument matrix
        other.entryList.gotoBeginning();
        while (true) {
            try {
                if (!other.entryList.gotoNext()) break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            SparseMatrixEntry curr_entry = other.entryList.getCursor();
            int curr_i = curr_entry.getI();
            int curr_j = curr_entry.getJ();
            //collecting info about an entry
            if (other.T) {                     //while handing a transpose case
                int temp = curr_i;
                curr_i = curr_j;
                curr_j = temp;
            }

            double cur_val = get(curr_i, curr_j) + other.get(curr_i, curr_j);
            res.put(curr_i, curr_j, cur_val);
        }            //summing up and appending a new entry to a resulting list
        return res;
    }

    public SparseMatrix substract(SparseMatrix other) {
        if (size != other.size) {       //To subtraction, enough to call to add function,
            try {               //with argument, multiplied by -1.
                throw new Exception("Matrices shapes must be equal");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        other.multByConstant(-1);               //since multiplication by a scalar is a cheap
        SparseMatrix res = this.add(other);        //but object impactive operation

        other.multByConstant(-1);               //rollback the changes to the argument
        return res;
    }


    public SparseMatrix NaiveLinearTransform(int m, double n) {
        SparseMatrix temp;
        temp = this;
        SparseMatrix scalarMatrix = new SparseMatrix(size, 0);
        for (int i = 0; i < size; i++) {
            scalarMatrix.put(i, i, n);
        }
        temp.multByConstant(m);
        return temp.add(scalarMatrix);
    }

    public SparseMatrix FastLinearTransform(int m, double n) throws Exception {
        SparseMatrix temp = new SparseMatrix(size, m * defaultValue);
        entryList.gotoBeginning();
        do {
            int i = entryList.getCursor().getI();
            int j = entryList.getCursor().getJ();
            double tempVal = entryList.getCursor().getValue();
            if (i == j) {
                temp.entryList.insert(new SparseMatrixEntry(tempVal * m + n, i, j));
            }
            else
            {
                temp.entryList.insert(new SparseMatrixEntry(tempVal * m,i,j));
            }

        } while (entryList.gotoNext());

        for(int i = 0; i < size; i++) {
            if(temp.get(i,i) == temp.defaultValue)
            {
                temp.entryList.insert(new SparseMatrixEntry(defaultValue*m+n, i, i));
            }
        }
        return temp;
    }

    void rowSwap(int m, int r) throws Exception {
        if(m < 0 || m > size || r < 0 || r > size){
            try {
                throw new Exception("BoundsErrorException");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        entryList.gotoBeginning();
        if(!T) {
            do {
                if (entryList.getCursor().getI() == m) {
                    entryList.getCursor().setI(r);
                } else if (entryList.getCursor().getI() == r) {
                    entryList.getCursor().setI(m);
                }
            } while (entryList.gotoNext());
        }
        else{
            do {
                if (entryList.getCursor().getJ() == m) {
                    entryList.getCursor().setJ(r);
                } else if (entryList.getCursor().getJ() == r) {
                    entryList.getCursor().setJ(m);
                }
            } while (entryList.gotoNext());

        }
    }


    public void MultRowByConst(double c, int row) throws Exception {
        if (row < 0 || row > size) {
            throw new Exception("BoundErrorException");
        }
        entryList.gotoBeginning();
        do {
            int i = entryList.getCursor().getI();
            int j = entryList.getCursor().getJ();
            double val = entryList.getCursor().getValue();
            if (i == row) {
                entryList.getCursor().setValue(val * c);
            }
            for (int k = 0; k < size; k++) {
                if (get(row, k) == defaultValue) {
                    entryList.insert(new SparseMatrixEntry(defaultValue * c, row, k));
                }
            }
        }while(entryList.gotoNext());
    }
    SparseMatrix product(SparseMatrix X) throws Exception {
        if(X.size != size){
            try {
                throw new Exception("Product is not defined");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SparseMatrix res = new SparseMatrix(size, 0);
        entryList.gotoBeginning();
        X.entryList.gotoBeginning();
       // X.entryList.gotoNext();
        while(entryList.gotoNext()){
            SparseMatrixEntry self = entryList.getCursor();
            int i = self.getI();
            int j = self.getJ();
            while(X.entryList.gotoNext()) {
                SparseMatrixEntry x = X.entryList.getCursor();
                int k = x.getI();
                int l = x.getJ();
                if( T && X.T){
                    X.transpose();
                    transpose();
                    res = X.product(this);
                    res.transpose();
                    System.out.println("T && x.T");

                    return res;
                }
                else if(T){
                    System.out.println("T");

                    int temp = i;
                    i = j;
                    j = temp;
                }
                else if(X.T){
                    int temp = k;
                    k = l;
                    l = temp;
                }
                if (j == k) {

                    System.out.println("(" + i + "," + j + ")" + ",(" + k + "," + l + ")");
                    double pair_res = self.getValue() * x.getValue();
                    res.put(i, l, pair_res + res.get(i, l));
                }
            }
        }
        return res;
    }
    boolean TransposeEquals(SparseMatrix X) throws Exception {
        if(entryList.getSize() != X.entryList.getSize()){
            return false;
        }
        entryList.gotoBeginning();
        X.entryList.gotoBeginning();

        while(entryList.gotoNext()){
            SparseMatrixEntry selfEntry = entryList.getCursor();
            int i = selfEntry.getI();
            int j = selfEntry.getJ();
            double selfVal = selfEntry.getValue();
            if(selfVal != X.get(j, i)){
                return false;
            }
        }
        return true;
    }



    /*Once again using existing method get, to determine whether an i,j element is
     * a common element or not*/
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



