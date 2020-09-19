/**
    @author Akshay Mattoo
*/

package NNClassifier.VPTree;

import NNClassifier.*;
import NNClassifier.VPTree.*;

import java.util.ArrayList;
import java.util.Random;

/**
    Class which represents an image
    The pixels values of the image are stored in an ArrayList
*/
public class Image
{
    private ArrayList <Integer> pixels;

    /**
        Constructor
        @param ArrayList of pixels
    */
    public Image (ArrayList <Integer> arr)
    {
        pixels = arr;
    }

    /**
        @return ArrayList of pixels
    */
    public ArrayList <Integer> getPixels ()
    {
        return pixels;
    }

    /**
        Checks whether img is identical to current image
        Compares each pixel value of the two images
        @param Image which is to be compared
        @return true if both images are identical, false otherwise
    */
    public boolean equals (Image img)
    {
        if (img == null) return false;

        ArrayList <Integer> arr = img.getPixels();

        if (arr.size() != pixels.size()) return false;

        for (int i=0; i<arr.size(); ++i)
        {
            if (arr.get(i) != pixels.get(i)) return false;
        }
        return true;
    }
}
