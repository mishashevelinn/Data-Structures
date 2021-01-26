package singlyLL;

import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class TestSListChar {
    InputStreamReader reader; // = new InputStreamReader(System.in);
    StreamTokenizer tokens; // = new StreamTokenizer(reader);
    SLinkedList<Character> list;


    TestSListChar(SLinkedList<Character> sLinkedList) {

        list = sLinkedList;

        reader = new InputStreamReader(System.in);

        tokens = new StreamTokenizer(reader);

    }

    void run() throws Exception {
        char ch;


        while (true) {

            if ((tokens.nextToken()) == StreamTokenizer.TT_WORD) {

                switch (tokens.sval) {

                    case "Q":
                        return;

                    case "Add":
                        tokens.nextToken();
                        ch = tokens.sval.charAt(0);
                        list.insert(ch);
                        break;

                    case "Cur":

                        System.out.println(list.getCursor());

                        break;

                    case "N":

                        System.out.println(list.gotoNext());

                        break;

                    case "B":

                        System.out.println(list.gotoBeginning());

                        break;
                    case "End":
                        System.out.println(list.gotoEnd());
                        break;
                    case "P":
                        System.out.println(list.gotoPrior());
                        break;
                    case "Print":
                        System.out.println(list.toString());
                        break;
                    case "Mv":
                        tokens.nextToken();
                        ch = tokens.sval.charAt(0);
                        list.replace(ch);
                        break;
                    case "Del":
                        list.remove();
                        break;
                }

            }

        }

    }
}

