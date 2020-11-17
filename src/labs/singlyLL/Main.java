package labs.singlyLL;

public class Main {

    public static void main(String[] args) throws Exception {
        SLinkedList<Character> sl = new SLinkedList<Character>();
        TestSListChar test = new TestSListChar(sl);
        /*for (int i = 0; i < 22; i++) {
            sl.insert(i);
        }
        System.out.println(sl.toString());*/
        test.run();
    }
}