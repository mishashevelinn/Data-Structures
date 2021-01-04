package AVLtree;

public class AVLTree {
    private AVLTreeNode root;

    public AVLTree() {
        root = null;
    }//constructor, initializes an empty tree

    public void insert(int newElement) {
        root = insertHelper(root, newElement);
    }//insert element and maintain AVL invariant about height of sub-trees

    public AVLTreeNode retrieve(int Searchkey) {
        return retrieveHelper(root, Searchkey);

    }//return the Node, containing searched element, null if element is not in the structure

    public void clear() {
        root = null;
    }// Clear tree

    public boolean isEmpty() {
        return root == null;
    }//Check if tree is empty

    public boolean isFull() {
        return false;
    }//Check if tree is empty


    /*Used to rotate left the right subtree of right-heavy node
    * Use case : node is inserted to the right subtree of right child */
    private AVLTreeNode LeftRotation(AVLTreeNode node) {
        AVLTreeNode rightTemp = node.getRight(); //store right child in temporary variable

        node.setRight(rightTemp.getLeft());     //set right child's left child to be right child of current root
        rightTemp.setLeft(node);                //set right child's left child to be current root

        node.setHeight(1 + Math.max(Height(node.getLeft()), //fix heights of root and its right child, since
                Height(node.getRight()))                    //it changed while rotation
        );
        rightTemp.setHeight(1 + Math.max((Height(rightTemp.getLeft())),
                Height(rightTemp.getLeft()))
        );
        return rightTemp;   //return value of method is assigned to a Node, where balance
    }                       //was corrupted, calling it in insertHelper on a imbalanced Node

    /*Right rotation.
    * Use case: node is inserted to the left subtree of left child.*/
    private AVLTreeNode RightRotation(AVLTreeNode node) {
        AVLTreeNode leftTemp = node.getLeft();  //get a left child in a variable
        node.setLeft(leftTemp.getRight());      //set imbalanced Node's left child's right child to be a left child of a Node
        leftTemp.setRight(node);                //set the root of imbalanced subtree to be the right child of its left child

        node.setHeight(1 + Math.max(Height(node.getLeft()),
                Height(node.getRight()))                        //Fix heights of rotated Nodes
        );
        leftTemp.setHeight(1 + Math.max(Height(leftTemp.getLeft()),
                Height(leftTemp.getRight()))
        );

        return leftTemp;                    //same as in LeftRotate method

    }

    /*Use case: */
    private AVLTreeNode LeftRightRotation(AVLTreeNode node) {
        node.setLeft(LeftRotation(node.getLeft()));     //Combination of left and then right rotation
        return RightRotation(node);
    }

    private AVLTreeNode RightLeftRotation(AVLTreeNode node) {
        node.setRight(RightRotation(node.getRight()));
        return LeftRotation(node);
    }

    private AVLTreeNode insertHelper(AVLTreeNode node, int key) {
        if (node == null)
            return new AVLTreeNode(key, 0, null, null);
        if (key < node.getKey()) {
            node.setLeft(insertHelper(node.getRight(), key));
        } else {
            node.setRight(insertHelper(node.getRight(), key));
        }
        node.setHeight(1 + Math.max(Height(node.getLeft()),
                Height(node.getRight()))
        );
        int balance = getBalance(node);

        if (balance > 2) {
            if (getBalance(node.getLeft()) >= 0) {
                node = RightRotation(node);
            } else {
                node = LeftRightRotation(node);
            }
        } else if (balance < -1) {
            if (getBalance(node.getRight()) <= 0) {
                node = LeftRotation(node);

            } else {
                node = RightLeftRotation(node);
            }
        }

        return node;
    }


    private int getBalance(AVLTreeNode node) {
        if (node == null)
            return 0;
        return Height(node.getLeft()) - Height(node.getRight());
    }


    public String toString() {
        if (isEmpty())
            return "{ }";
        StringBuilder sb = new StringBuilder();
        print(root, sb);
        return sb.toString();

    }

    private void print(AVLTreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        print(root.getLeft(), sb);
        sb.append("(key: " + root.getKey() + ", ballance: " + getBalance(root) + " )\n"); //making sure there is no
        print(root.getRight(), sb);                                                       //unbalanced subtrees
    }

    private int Height(AVLTreeNode node) {
        if (node == null)
            return -1;
        return node.getHeight();
    }

    private AVLTreeNode retrieveHelper(AVLTreeNode node, int SearchKey) {

        if (node == null)
            return null;
        if (node.getKey() == SearchKey)
            return node;
        if (SearchKey < node.getKey())
            return retrieveHelper(node.getLeft(), SearchKey);
        return retrieveHelper(node.getRight(), SearchKey);

    }


}

