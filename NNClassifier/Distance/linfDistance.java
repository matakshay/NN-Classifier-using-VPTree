/**
    @author Akshay Mattoo
*/

package NNClassifier.Distance;

import NNClassifier.*;
import NNClassifier.Distance.*;
import NNClassifier.VPTree.*;

import java.util.ArrayList;

/**
    Implements DistanceFunction interface for Chebyshev distance metric
*/
public class linfDistance implements DistanceFunction {

    /**
        Computes the Chebyshev distance (linf Distance) between two Image vectors
        @param two Image objects
        @return Chebyshev distance between Image one and two
    */
    public double distance (Image one, Image two)
    {
        double dist = 0.0;

        if (one==null || two==null)
        {
            System.out.println("ERROR! Image was found to be null");
            return dist;
        }

        ArrayList <Integer> p1 = one.getPixels();
        ArrayList <Integer> p2 = two.getPixels();

        if (p1.size() != p2.size())
        {
            System.out.println("ERROR! Images do not have equal number of pixels");
            return dist;
        }

        for (int i=0; i<p1.size(); ++i)
        {
            double x1 = (double)(p1.get(i));
            double x2 = (double)(p2.get(i));
            double diff = Math.abs(x1-x2);

            dist = Math.max(dist, diff);
        }

        return dist;
    }
}
