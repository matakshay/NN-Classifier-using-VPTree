/**
    @author Akshay Mattoo
*/

package NNClassifier.VPTree;

import NNClassifier.*;
import NNClassifier.VPTree.*;

import java.util.Collection;
import java.util.ArrayList;

/**
    Class to represent a vertex in the VP Tree
*/
public class Node
{
    private Item itm;
    private Node left;
    private Node right;
    private double mu;

    /**
        Contructor
        Initilises mu
        Sets left and right to null
        @param Item object for storing in this Node
    */
    public Node (Item i)
    {
        itm = i;
        left = null;
        right = null;
        mu = 0.0;
    }

    /**
        @return Item object stored in this node
    */
    public Item getItem ()
    {
        return itm;
    }

    /**
        @return left child of this Node
    */
    public Node getLeft ()
    {
        return left;
    }

    /**
        Sets left child of this  Node object
        @param left child Node object
    */
    public void setLeft (Node l)
    {
        left = l;
    }

    /**
        @return value of mu
    */
    public double getMu ()
    {
        return mu;
    }

    /**
        Sets value of mu
        @param floating point value of mu
    */
    public void setMu (double d)
    {
        mu = d;
    }

    /**
        @return right child of this Node
    */
    public Node getRight ()
    {
        return right;
    }

    /**
        Sets right child of this Node object
        @param right child Node object
    */
    public void setRight (Node r)
    {
        right = r;
    }
}
