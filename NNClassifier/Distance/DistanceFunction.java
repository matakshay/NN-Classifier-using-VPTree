/**
    @author Akshay Mattoo
*/

package NNClassifier.Distance;

import NNClassifier.*;
import NNClassifier.Distance.*;
import NNClassifier.VPTree.*;

/**
    Interface for implementing different Distance Metrics classes
*/
public interface DistanceFunction
{
    /**
        Method for computing the distance between two Images vectors
        @param two Image objects
        @return distance between the two Image objects
    */
    double distance (Image one, Image two);
}
