package AVLtree;

public class Main {
    public static void main(String[] args) {
        AVLTree t = new AVLTree();
        for (int i = 0; i < 4; i++) {
            t.insert(i);
        }
        System.out.println(t);
    }
}
