package Stack;

import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class TestLStack {
    InputStreamReader reader; // = new InputStreamReader(System.in);
    StreamTokenizer tokens; // = new StreamTokenizer(reader);
    StackList<Integer> stack;


    TestLStack() {

        stack = new StackList<Integer>(10);

        reader = new InputStreamReader(System.in);

        tokens = new StreamTokenizer(reader);

    }

    void run() throws Exception {
        int n;


        while (true) {

            if ((tokens.nextToken()) == StreamTokenizer.TT_WORD) {

                switch (tokens.sval) {

                    case "Q":

                        return;

                    case "Push":

                        tokens.nextToken();
                        n = (int) tokens.nval;
                        stack.push(n);

                        break;

                    case "Pop":

                        stack.pop();

                        break;

                    case "P":

                        System.out.println(stack);

                        break;

                    case "F":

                        System.out.println(stack.isFull());

                        break;

                    case "E":

                        System.out.println(stack.isEmpty());

                        break;

                    case "C":

                        stack.clear();

                        break;

//                    case "T":
//                        System.out.println(stack.getTop());
//                        break;

                    default:

                        System.out.println("No such option\nPlease try again");

                        break;
                }

            }

        }

    }

    public static void main(String[] args) {
        TestLStack test = new TestLStack();
        try {
            test.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

