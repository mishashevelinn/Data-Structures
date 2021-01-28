package Graph;

import BSTree.BSTree;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;



public class TestGraph {
    InputStreamReader reader; // = new InputStreamReader(System.in);
    StreamTokenizer tokens; // = new StreamTokenizer(reader);
    Graph g;
    String name1;
    String name2;

    TestGraph() {


        g = new Graph();

        reader = new InputStreamReader(System.in);

        tokens = new StreamTokenizer(reader);

    }
    void run() throws Exception {
        int n;


        while (true) {

            if ((tokens.nextToken()) == StreamTokenizer.TT_WORD) {

                switch (tokens.sval) {

                    case "addV":
                        tokens.nextToken();
                        String name = tokens.sval;
                        Vertex v = new Vertex(name);
                        g.insertVertex(v);
                        break;

                    case "addE":
                        tokens.nextToken();
                        name1 = tokens.sval;
                        tokens.nextToken();
                        name2 = tokens.sval;
                        tokens.nextToken();
                        int weight = (int)tokens.nval;
                        g.insertEdge(name1, name2, weight);
                        break;

                    case "retV":
                        tokens.nextToken();
                        name1 = tokens.sval;
                        tokens.nextToken();
                        System.out.println(g.retrieveVertex(name1));
                        break;

                    case "retE":
                        tokens.nextToken();
                        name1 = tokens.sval;
                        tokens.nextToken();
                        name2 = tokens.sval;
                        System.out.println(g.edgeWeight(name1, name2));
                        break;

                    case "remV":
                        tokens.nextToken();
                        name1 = tokens.sval;
                        g.removeVertex(name1);
                        break;

                    case "remE":
                        tokens.nextToken();
                        name1 = tokens.sval;
                        tokens.nextToken();
                        name2 = tokens.sval;

                        g.removeEdge(name1, name2);
                        break;
                    case "P":
                        System.out.println(g);
                        break;

                    case "E":
                        System.out.println(g.isEmpty());
                        return;

                    case "F" :
                        System.out.println(g.isFull());
                        break;

                    case "C" :
                        g.clear();
                        break;

                    case"Q":
                        return;
                    default:
                        System.out.println("No such option\nPlease try again");
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        TestGraph test = new TestGraph();
        try {
            test.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}