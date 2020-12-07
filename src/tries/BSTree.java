package tries;


import static java.lang.Math.max;

public class BSTree {
    private BSTreeNode root;

    public BSTree() {
        root = null;
    }

    public void insert(int newKey) {
        root = insertParthner(root, newKey);    //Calling a recursive partner which returns a pointer to a new root, after insertion.
    }

    private BSTreeNode insertParthner(BSTreeNode root, int newKey) {
        if (root == null)                       //Case when tree is empty. Set children to be nulls and place an element in root Node.
        {
            root = new BSTreeNode(newKey, null, null);
            return root;

        }
        //If the tree isn't empty, recursively traverse the tree, till a place of new node found.

        /*Continuing the search in left subtree, discarding the right subtree*/
        if (newKey < root.getKey())
        {
            root.setLeft(insertParthner(root.getLeft(), newKey));
        }
        /*Continuing the search in right subtree, discarding the left subtree*/
        if (newKey > root.getKey()) {
            root.setRight(insertParthner(root.getRight(), newKey));
        }

        return root;
    }
    public BSTreeNode retrive(int SearchKey)
    {
        return retriveParthner(root, SearchKey);
    }

    private BSTreeNode retriveParthner(BSTreeNode root, int searchKey) {
        //Empty tree case
        if(root == null)
            return root;
        //Stop condition
        if(root.getKey() == searchKey)
            return root;
        /*If smaller, discard right subtree*/
        if(root.getKey() < searchKey)
            return retriveParthner(root.getLeft(), searchKey);

        /*Otherwise, discard left subtree*/
        return retriveParthner(root.getRight(), searchKey);
    }

    public void clear(){
        root = null;
    }
    public boolean isEmpty ( ){
        return root == null;
    }
    public boolean isFull ( ){
        return false;
    }

    public String Preorder(){
        StringBuilder sb = new StringBuilder();
        PreorderParthner(root, sb);
        System.out.println(sb.toString());

        return sb.toString();
    }

    private void PreorderParthner(BSTreeNode root, StringBuilder sb) {
        if(root == null)
            return;
        sb.append(root.getKey());
        PreorderParthner(root.getLeft(), sb);
        PreorderParthner(root.getRight(), sb);
    }

    public String Inorder(){
        StringBuilder sb = new StringBuilder();
        InorderParthner(root, sb);
        System.out.println(sb.toString());
        return sb.toString();
    }

    private void InorderParthner(BSTreeNode root, StringBuilder sb) {
        if(root == null)
            return;

        PreorderParthner(root.getLeft(), sb);
        sb.append(root.getKey());
        PreorderParthner(root.getRight(), sb);
    }

    public String Postorder(){
        StringBuilder sb = new StringBuilder();
        PostorderParthner(root, sb);
        System.out.println(sb.toString());
        return sb.toString();

    }
    public void PostorderParthner(BSTreeNode root, StringBuilder sb)
    {
        if (root == null)
            return;

        PostorderParthner(root.getLeft(), sb);

        PostorderParthner(root.getLeft(), sb);

        sb.append(root.getKey());
    }
    public int height(){
        return heightParthner(root);

    }
    private int heightParthner(BSTreeNode root) {
        if(root == null)
        {
            return -1;
        }
        return 1 + max(heightParthner(root.getLeft()), heightParthner(root.getRight()));
    }

}

