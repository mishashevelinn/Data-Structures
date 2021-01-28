package Graph;

public class Graph {
    // Default number of vertices (a constant)
    public static final int DEF_MAX_GRAPH_SIZE = 10;
    // "Weight" of a missing edge (a constant) — the max int value
    public static final int INFINITE_EDGE_WT = Integer.MAX_VALUE;
    private int size; // Actual number of vertices in the graph
    private final int capacity;
    private final Vertex[] vertexList; // Vertex list
    private int[][] adjMatrix; // Adjacency matrix (a 2D array)

    public Graph() {
        this(DEF_MAX_GRAPH_SIZE);
    }


    public Graph(int maxNumber) {
        capacity = maxNumber;
        vertexList = new Vertex[capacity];
        size = 0;
        adjMatrix = new int[capacity][capacity];  //2D array is initialized by zeroes by Java's default;
    }


    /*Appending vertex to the end of array*/
    public void insertVertex(Vertex newVertex) {
        vertexList[size] = newVertex;
        size++;
        //Incrementing the current size
    }

    // Insert vertex

    public void insertEdge(String v1, String v2, int wt) {
        int i = index(v1);
        int j = index(v2);
        if (i == -1 || j == -1) try {
            throw new Exception("One of vertexes does not exist");
        } catch (Exception e) {
            e.printStackTrace();
        }//Checking whether vertexes are in the structure

        /*Since the graph is symmetric - for each arrow in graph, the corresponding inversed arrow is also
         * belongs to the graph, assign symmetric indexes in the matrix*/
        adjMatrix[i][j] = wt;   //From v1 to v2;
        adjMatrix[j][i] = wt;   //From v2 to v1;

    } // Insert edge.

    public Vertex retrieveVertex(String v) {
        int i = index(v);
        if (i == -1) {
            return null;
        }//Check the precondition of vertex existence in the structure
        return vertexList[i];
    } // Get vertex


    public int edgeWeight(String v1, String v2) {
        int i = index(v1);
        int j = index(v2);
        if (i == -1 || j == -1) try {
            throw new Exception("One of vertexes does not exist");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*since graph is symmetric - [i,j] and [j,i] positions in the matrix are equal*/
        return adjMatrix[i][j];
    } // Get edge wt


    public void removeVertex(String v) {
        int index = index(v); //Finding the index of vertex in list of vertexes - 1D array.
        if (isEmpty()) try {
            throw new Exception("Graph is Empty");
        }//Checking that precondition holds.
        catch (Exception e) {
            e.printStackTrace();
        }
        if (index == -1) try {
            throw new Exception("Vertex does not exist");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Single vertex  is represented by column and row.*/
        adjMatrix[index] = new int[size]; //nullify a column
        for (int i = 0; i < size; i++) {  //nullify a row
            adjMatrix[i][index] = 0;
        }

        vertexList[index] = null;                 //Not necessary. For demonstration purpose only.
        vertexList[index] = vertexList[size - 1]; //Moving last element of vertexes list to an empty slot.
        size--;                                   //Updating the size.
    } // Remove vertex

    public void removeEdge(String v1, String v2) {
        int i = index(v1);
        int j = index(v2);
        if (i == -1 || j == -1) try {
            throw new Exception("One of vertexes does not exist;");
        } catch (Exception e) {
            e.printStackTrace();
        }//Checking the precondition
        adjMatrix[i][j] = 0; //Nullifying the weights if edges - representing edge's absence.
        adjMatrix[j][i] = 0; //Doing so, also for symmetric entry in the matrix.
    } // Remove edge

    public void clear() {
        adjMatrix = new int[capacity][capacity]; //nullify all
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < size; i++) {
            sb.append(vertexList[i].getLabel());
            sb.append(" ");
        }
        sb.append('\n');
        for (int i = 0; i < size; i++) {

            sb.append(vertexList[i].getLabel());
            sb.append(" ");

            for (int j = 0; j < size; j++) {
                sb.append(adjMatrix[i][j]);
                sb.append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /*Gets a string - labels of vertex and searches it in the vertexes list by linear iteration
     * Returns an index of vertex or null if vertex not found*/
    private int index(String v) {
        for (int i = 0; i < size; i++) {
            if (vertexList[i] != null && vertexList[i].getLabel().equals(v))
                return i;
        }
        return -1;

    } // Converts vertex label to an adjacency matrix index

    /*Gets row and column indexes. Returns a wight of the edge by accessing 2D array of weights*/
    private int getEdge(int row, int col) {
        if (row < 0 || row > size || col < 0 || col > size) {
            try {
                throw new Exception("Bounds Error");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adjMatrix[row][col];
    } // Get edge weight using adjacency matrix indices


} // class Graph
