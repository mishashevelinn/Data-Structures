package Heap;


import java.io.InputStreamReader;
import java.io.StreamTokenizer;

import static java.lang.System.exit;

public class HeapTest {
    public static void main(String[] args) throws Exception {
        Heap h = new Heap();
        InputStreamReader reader = new InputStreamReader(System.in);
        StreamTokenizer tokens = new StreamTokenizer(reader);
        System.out.print("Enter your commands - what would you like to do? (end with Q) : \n");

        /* Our code starts here */
        String curr ;
        int val;
        for (int i = 0; i < 10; i++) {
            h.insert(new HeapData(i));
        }
        h.PrintLargest(5);
        while ( tokens.nextToken() == StreamTokenizer.TT_WORD ) {
            // works while receiving inputs
            switch (curr = tokens.sval) {	// handles every case of current token
                case "Add":
                    tokens.nextToken() ;
                    val = (int) tokens.nval;
                    h.insert(new HeapData(val));
                    break;
                case "E":
                    System.out.println(h.isEmpty() ? "true" : "false");
                    break;
                case "F":
                    System.out.println(h.isFull() ? "true" : "false");
                    break;
                case "C":
                    h.clear();
                    break;
                case "Min":
                    System.out.println(h.removeMin().getPriority());
                    break;
                case "P":
                    System.out.println(h);
                    break;
                case "K":
                    tokens.nextToken();
                    int k  = (int)tokens.nval;
                    h.PrintLargest(k);
                    break;
                case "Q":
                    System.out.println("Bye Bye!");
                    exit(0);
                default:
                    System.out.println(curr + " is not a saved word in the program.");
            }

        }
    }
}
