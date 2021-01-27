package AVLtree;



import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class TestAVLTree {

    InputStreamReader reader; // = new InputStreamReader(System.in);
    StreamTokenizer tokens; // = new StreamTokenizer(reader);
    AVLTree avl;

    TestAVLTree() {

        avl = new AVLTree();

        reader = new InputStreamReader(System.in);

        tokens = new StreamTokenizer(reader);

    }
    void run() throws Exception {
        int n;


        while (true) {

            if ((tokens.nextToken()) == StreamTokenizer.TT_WORD) {

                switch (tokens.sval) {

                    case "ADDkey":
                        tokens.nextToken();
                        n = (int) tokens.nval;
                        avl.insert(n);
                        break;

                    case "FINDkey":

                        tokens.nextToken();
                        n = (int) tokens.nval;
                        AVLTreeNode node = avl.retrieve(n);
                        if(node == null) {
                            System.out.println("No such key.");
                            break;
                        }
                        System.out.println(node.getKey());
                        break;

                    case "K":
                        System.out.println(avl);
                        break;

                    case "E":

                        System.out.println(avl.isEmpty());

                        break;

                    case "F":

                        System.out.println(avl.isFull());

                        break;

                    case "C":
                        avl.clear();

                        break;

                    case "Q":

                        return;

                    default:
                        System.out.println("No such option.\nPlease try again");
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        TestAVLTree test = new TestAVLTree();
        try {
            test.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
