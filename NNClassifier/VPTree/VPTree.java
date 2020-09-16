package NNClassifier.VPTree;

import NNClassifier.*;
import NNClassifier.VPTree.*;
import NNClassifier.Distance.*;

import java.util.Collection;

public abstract class VPTree<T>
{
    public VPTree (Collection <Image> collection, DistanceFunction d)
    {}

    public abstract Image findOneNN (Image q);

    public abstract DistanceFunction getDistanceFunc ();
}
