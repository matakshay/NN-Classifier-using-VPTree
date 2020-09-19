/**
	@author Akshay Mattoo
*/

package NNClassifier.Classifier;

import NNClassifier.*;
import NNClassifier.Classifier.*;
import NNClassifier.VPTree.*;

import java.util.Collection;
import java.util.ArrayList;

/**
    Interface for implementing different Nearest Neighbour Classifiers classes
*/
public interface Classifier
{
	/**
		Trains the classifer using the train data
		@param train dataset
	*/
	public void train (Collection <Image> trainingSet);

	/**
		Runs the classifer over the test data and stores the predictions
		@param test dataset
	*/
	public void test (Collection <Image> testSet);

	/**
		Computes the accuracy of the classifier on the test data
		@return Accuracy as a floating point value
	*/
	public double getAccuracy (Collection <Integer> groundLabels);
}
