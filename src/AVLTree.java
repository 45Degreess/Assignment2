package Assignment_2.src;

public class AVLTree<dataType extends Comparable>
{
    /**
     * Instance variable for storing the root node of the AVL tree
     */
    private AVLTreeNode<dataType> root;
    /**
     * Variable to store the amount of insert comparisons
     */
    public  int insertCounter =0;
    /**
     * Variable to store the amount of search comparisons
     */
    public  int searchCounter = 0;


    /**
     * Method to get the height of a node in the AVL Tree
     * @param node representing the {@link AVLTreeNode} that will be accesed
     * @return -1 of node is null, otherwise the height of the node in the tree
     */
    public int height ( AVLTreeNode<dataType> node )
    {
        if (node != null)
            return node.getHeight();
        return -1;
    }

    /**
     * Method to get the balancing factor needed to rebalance the tree after an insert of deletion
     * @param node representing the {@link AVLTreeNode} that will be accesed
     * @return 0 if node is null, otherwise the difference between the height of the right node and left node
     */
    int getBalance(AVLTreeNode<dataType> node)
    {
        if(node == null)
            return 0;
        return height(node.getRight()) - height(node.getLeft());
    }

    /**
     * Method the fixes the height of a node after a rebalance has occured
     * @param node {@link AVLTreeNode} that height will be fixed
     */
    public void fixHeight(AVLTreeNode<dataType> node)
    {
        node.setHeight(1 + Math.max(height(node.getLeft()),height(node.getRight())));
    }

    /**
     * Mathod to rotate the AVLTree right once
     * @param y {@link AVLTreeNode} of the node in the tree where the rotation will happen
     * @return returns a {@link AVLTreeNode}
     */
    public AVLTreeNode<dataType> rotateRight(AVLTreeNode<dataType> y)
    {
        AVLTreeNode<dataType> x = y.getLeft();
        AVLTreeNode<dataType> z = x.getRight();
        x.setRight(y);
        y.setLeft(z);
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    /**
     * Mathod to rotate the AVLTree left once
     * @param y {@link AVLTreeNode} of the node in the tree where the rotation will happen
     * @return returns a {@link AVLTreeNode}
     */
    public AVLTreeNode<dataType> rotateLeft(AVLTreeNode<dataType> y)
    {
        AVLTreeNode<dataType> x = y.getRight();
        AVLTreeNode<dataType> z = x.getLeft();
        x.setLeft(y);
        y.setRight(z);
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    /**
     * Mathod to rebalance the tree after insertion or deletion
     * @param z  {@link AVLTreeNode} of the node in the tree where the rebalencing will start
     * @return {@link AVLTreeNode}
     */
    AVLTreeNode<dataType> rebalance(AVLTreeNode<dataType> z) {
        fixHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.getRight().getRight()) > height(z.getRight().getLeft())) {
                z = rotateLeft(z);
            } else {
                z.setRight(rotateRight(z.getRight()));
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.getLeft().getLeft()) > height(z.getLeft().getRight()))
                z = rotateRight(z);
            else {
                z.setLeft(rotateLeft(z.getLeft()));
                z = rotateRight(z);
            }
        }
        return z;
    }
    /**Inserts data in the tree
     * @param d Object to be inserted into the tree
     */
    public void insert ( dataType d )
    {
        root = insert (d, root);
    }
    private AVLTreeNode<dataType> insert ( dataType d, AVLTreeNode<dataType> node )
    {
        insertCounter++;
        if (node == null)
        {
            return new AVLTreeNode<dataType>(d, null, null);
        }
        if (d.compareTo (node.getData()) <= 0)
        {
            insertCounter++;
            node.setLeft(insert(d, node.getLeft()));
        }
        else
        {
            insertCounter++;
            node.setRight(insert(d, node.getRight()));
        }
        return rebalance(node);
    }

    /**
     * recursively iterates through the BinarySearchTree
     * @param d Object to search the BinarySearch tree for a matching Object
     * @return  A {@link AVLTreeNode} of the Object type if the data matches
     */
    public AVLTreeNode<dataType> search(dataType d)
    {
        if(root == null)
            return null;
        return search(d,root);
    }

    public AVLTreeNode<dataType> search(dataType d,AVLTreeNode node)
    {
        searchCounter++;
        if(d.compareTo(node.getData()) ==0)
            return node;
        else if (d.compareTo(node.getData()) <0)
        {
            searchCounter++;
            if(node.getLeft() ==null)
                return null;
            else
                return search(d,node.getLeft());
        }
        else
        {
            searchCounter++;
            if(node.getRight() ==null)
                return null;
            else
                return search(d,node.getRight());
        }
    }

    /**
     * Method that return the root node of the AVL Tree
     * @return root node
     */
    public AVLTreeNode<dataType> getRoot()
    {
        return root;
    }

    /**
     * Deletes a node from the AVL Tree
     * @param d data of the node to be deleted
     */
    public void delete ( dataType d )
    {
        root = delete (d, root);
    }
    public AVLTreeNode<dataType> delete ( dataType d, AVLTreeNode<dataType> node )
    {
        if (node == null) return null;
        if (d.compareTo (node.getData()) < 0)
            node.setLeft(delete (d, node.getLeft()));
        else if (d.compareTo (node.getData()) > 0)
            node.setRight(delete (d, node.getRight()));
        else
        {
            AVLTreeNode<dataType> q = node.getLeft();
            AVLTreeNode<dataType> r = node.getRight();
            if (r == null)
                return q;
            AVLTreeNode<dataType> min = findMin (r);
            min.setRight(removeMin (r));
            min.setLeft(q);
            return rebalance (min);
        }
        return rebalance (node);
    }

    public AVLTreeNode<dataType> findMin ( AVLTreeNode<dataType> node )
    {
        if (node.getLeft() != null)
            return findMin (node.getLeft());
        else
            return node;
    }

    public AVLTreeNode<dataType> removeMin ( AVLTreeNode<dataType> node )
    {
        if (node.getLeft() == null)
            return node.getRight();
        node.setLeft(removeMin (node.getLeft()));
        return rebalance (node);
    }

    public int getSize ()
    {
        return getSize (root);
    }
    public int getSize ( AVLTreeNode<dataType> node )
    {
        if (node == null)
            return 0;
        else
            return 1 + getSize (node.getLeft ()) + getSize (node.getRight ());
    }

}
