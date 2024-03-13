package Assignment_2.src;

/**
 * Node of the AVLTree
 * @param <dataType> data type of the node data
 */
public class AVLTreeNode<dataType extends Comparable>
{

        /**
         * Data of the node
         */
        private dataType data;
        /**
         * Node to the left of the current node in the tree
         */
        private AVLTreeNode<dataType> left;
        /**
         * Node to the right of the current node in the tree
         */
        private AVLTreeNode<dataType> right;

        private int height;

        /**
         * Constructor method for node
         * @param data incoming data for the node
         * @param left incoming node to the left of the current node
         * @param right incoming node to the right of the current node
         */
        public AVLTreeNode(dataType data,AVLTreeNode left,AVLTreeNode right)
        {
            this.data = data;
            this.left = left;
            this.right = right;
            height = 0;
        }

        /**
         * @return the node to the left of the current node
         */
        public AVLTreeNode<dataType> getLeft()
        {
            return left;
        }

        /**
         * @return the node to the right of the current node
         */
        public AVLTreeNode<dataType> getRight()
        {
            return right;
        }


        /**
         * @return the data of the node
         */
        public dataType getData()
        {
            return data;
        }

        /**
         * Sets the value of the node to the left of current node
         * @param inLeft incoming value for left node
         */
        public void setLeft(AVLTreeNode inLeft)
        {
            left = inLeft;
        }

        /**
         * Sets the value of the node to the left of current node
         * @param inRight incoming right node
         */
        public void setRight(AVLTreeNode inRight)
        {
            right = inRight;
        }

        /**
         * Sets the value of the data of the current node
         * @param inData incoming node data
         */
        public void setData(dataType inData)
        {
            data = inData;
        }

        public int getHeight()
        {
            return height;
        }

        public void setHeight(int height)
        {
            this.height = height;
        }
}
