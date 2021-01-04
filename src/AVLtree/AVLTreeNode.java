package AVLtree;

public class AVLTreeNode {
    private int key;
    private int height;
    private AVLTreeNode left;
    private AVLTreeNode right;

    public AVLTreeNode(int elem, int height, AVLTreeNode left, AVLTreeNode right)
    {
        key = elem;
        this.height = height;
        this.left = left;
        this.right = right;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLTreeNode getLeft() {
        return left;
    }

    public void setLeft(AVLTreeNode left) {
        this.left = left;
    }

    public AVLTreeNode getRight() {
        return right;
    }

    public void setRight(AVLTreeNode right) {
        this.right = right;
    }
    public int getBallance()
    {
        return left.getHeight() - right.getHeight();
    }
}
