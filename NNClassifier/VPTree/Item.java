/**
    @author Akshay Mattoo
*/

package NNClassifier.VPTree;

import NNClassifier.*;
import NNClassifier.VPTree.*;

import java.util.Collection;
import java.util.ArrayList;

/**
    Class for representing an item
*/
public class Item
{
    private Image img;
    private ArrayList <Double> hist;

    /**
        Constructor
        @param Image object
    */
    public Item (Image i)
    {
        img = i;
        hist = new ArrayList <Double> ();
    }

    /**
        @return Image stored in the object
    */
    public Image getImage ()
    {
        return img;
    }

    /**
        @return ArrayList hist
    */
    public ArrayList <Double> getHist ()
    {
        return hist;
    }

    /**
        Appends a new value to hist
        @param double d
    */
    public void push (double d)
    {
        hist.add(d);
    }

    /**
        @return last value in hist
    */
    public double tail ()
    {
        return hist.get(hist.size()-1);
    }

    /**
        Checks whether two Item objects are identical or not
        Compares the images of the two items and then compares each corresponding value in hist
        @return true if the two objects are identical, false otherwise
    */
    public boolean equals (Item itm)
    {
        if (itm == null) return false;
        if (img.equals(itm.getImage()) == false) return false;
        if (itm.getHist().size() != hist.size()) return false;

        for (int i=0; i<hist.size(); ++i)
        {
            if (hist.get(i) != itm.getHist().get(i)) return false;
        }
        return true;
    }
}
