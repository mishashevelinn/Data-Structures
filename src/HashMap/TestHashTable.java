package HashMap;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class TestHashTable {
    InputStreamReader reader; // = new InputStreamReader(System.in);
    StreamTokenizer tokens; // = new StreamTokenizer(reader);
    HashTable table;

    TestHashTable() {

        table = new HashTable();

        reader = new InputStreamReader(System.in);

        tokens = new StreamTokenizer(reader);

    }

    HashTableData getStudent() throws IOException {
        tokens.nextToken();
        String name = tokens.sval;
        tokens.nextToken();
        String surname = tokens.sval;
        tokens.nextToken();
        int age = (int)tokens.nval;
        tokens.nextToken();
        int avg = (int)tokens.nval;
        tokens.nextToken();
        int id = (int)tokens.nval;
        Student s = new Student(surname, name, avg, age, id);
        return new HashTableData(s);
    }

    void run() throws Exception {
        int n;
        while (true) {

            if ((tokens.nextToken()) == StreamTokenizer.TT_WORD) {

                switch (tokens.sval) {

                    case "add":
                        table.insert(getStudent());
                        break;

                    case "find":
                        table.retrieve(getStudent());
                        break;
                    case "print":
                        System.out.println((table.toString()));
                        break;

                    case "E":
                        System.out.println(table.isEmpty());
                        break;
                    case "F":
                        System.out.println(table.isFull());
                        break;
                    case "C":
                        table.clear();
                        break;
                    case "Q":
                        return;

                    default:
                        System.out.println("No such option\nPlease try again");
                        break;
                }
            }
        }

    }
    void smart_test(){
        String names [] = {"Misha", "Tal", "Shani", "Roni", "Donald"};
        String surnames [] = {"Shevelin", "Shamir", "Parnes", "Dabach", "Trump"};
        int avg [] = {90, 100, 94, 100, 0};
        int ages [] = {26, 25, 24, 23, 22};
        int id [] = {123124, 14124, 563634, 53467123, 78346};

        HashTableData database [] = new HashTableData[5];
        System.out.println("---Adding data to HashTable...---");
        try {
            for (int i = 0; i < 5; i++) {
                Student temp = new Student(names[i], surnames[i], avg[i], ages[i], id[i]);
                HashTableData data = new HashTableData(temp);
                table.insert(data);
                database[i] = data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("---Done adding; HashTable content:---");
        System.out.println(table);
        System.out.println("---Removing Donald Trump---");
        table.remove(database[4]); //Removing Donald Trump
        System.out.println(table);
        System.out.println("---HashTable content after removal:---");
        System.out.println(table);
        System.out.println("---Making sure Donald is not there---");
        String ans = (table.retrieve(database[4])) ? database[4].getStudent().getName()+" is in Table" : database[4].getStudent().getName()+" is not there";
        System.out.println(ans); //Making sure Donald is not there
        //Trying to add existing element (duplicate)
        System.out.println("---Attempt to add Tal Shamir, despite he's already in the structure---");
        ans = (table.insert(database[1])) ? database[1].getStudent().getName()+" Successfully added to Table. " : database[1].getStudent().getName()+" is already in Table. Insertion aborted...";
        System.out.println(ans);

    }

    public static void main(String[] args) throws Exception {
        TestHashTable test = new TestHashTable();

        /*Prepared check on existing data*/
        test.smart_test();
        /*Manual check. All Student's data has to be entered manually through standard input*/
        System.out.println("---Manual check. Use keywords to test a structure manually. You can manipulate data, displayed above");
        test.run();


    }
}



