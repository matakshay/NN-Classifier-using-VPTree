package NNClassifier.Classifier;

import NNClassifier.*;
import NNClassifier.Classifier.*;
import NNClassifier.VPTree.*;

import java.util.Collection;

public interface Classifier
{
	public void train (Collection <Image> trainingSet);

	public void test (Collection <Image> testSet);

	public double getAccuracy (Collection <Integer> groundLabels);
}
