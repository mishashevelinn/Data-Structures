package BSTree;

import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class TestBSTree {
    InputStreamReader reader; // = new InputStreamReader(System.in);
    StreamTokenizer tokens; // = new StreamTokenizer(reader);
    BSTree bst;

    TestBSTree() {

        bst = new BSTree();

        reader = new InputStreamReader(System.in);

        tokens = new StreamTokenizer(reader);

    }
    void run() throws Exception {
        int n;


        while (true) {

            if ((tokens.nextToken()) == StreamTokenizer.TT_WORD) {

                switch (tokens.sval) {

                    case "add":
                        tokens.nextToken();
                        n = (int) tokens.nval;
                        bst.insert(n);
                        break;

                    case "find":

                        tokens.nextToken();
                        n = (int) tokens.nval;
                        System.out.println(bst.retrive(n).getKey());
                        break;

                    case "K":
                        bst.Inorder();


                        break;

                    case "E":

                        System.out.println(bst.isEmpty());

                        break;

                    case "F":

                        System.out.println(bst.isFull());

                        break;

                    case "C":
                        bst.clear();

                        break;

                    case "Q":

                        return;

                    case "M" :
                        System.out.println(bst.findMax().getKey());

                    case "Parent" :
                        tokens.nextToken();
                        int k = (int)tokens.nval;
                        System.out.println(bst.findParent(k).getKey());
                    case"D":
                        tokens.nextToken();
                        int t = (int)tokens.nval;
                        bst.delete(t);
                    default:
                        System.out.println("No such option\nPlease try again");
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        TestBSTree test = new TestBSTree();
        try {
            test.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}



