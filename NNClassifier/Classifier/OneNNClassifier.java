/**
	@author Akshay Mattoo
*/

package NNClassifier.Classifier;

import NNClassifier.*;
import NNClassifier.Classifier.*;
import NNClassifier.Distance.*;
import NNClassifier.VPTree.*;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;

/**
	Class which implements Classifier interface for a 1-Nearest Neighbour Classifier
*/
public class OneNNClassifier implements Classifier
{
	private VPTreeImpl tree;
	private HashMap <Image, Integer> imgTolabel;
	private DistanceFunction metric;
	private Collection <Image> pred;

	/**
		Constructor
		Configures the distance metric to be used
		@param Distance metric (object of a class which implments DistanceFunction interface)
	*/
	public OneNNClassifier (DistanceFunction d)
	{
		if (d == null)
		{
			System.out.println("ERROR! Distance metric passed as null");
		}
		metric = d;
		tree = null;
		imgTolabel = null;
		pred = null;
	}

	/**
		Builds a VP Tree using the train data with distance function "metric"
		@param train dataset
	*/
	@Override
	public void train (Collection <Image> trainingSet)
	{
		if (trainingSet == null)
		{
			System.out.println("ERROR! Train data passed as null");
			return;
		}
		if (trainingSet.size() == 0)
		{
			System.out.println("ERROR! Train dataset is empty");
		}
		tree = new VPTreeImpl (trainingSet, metric);
	}

	/**
		Builds a VP Tree using the train data with distance function "metric"
		Stores the labels
		@param train dataset, train labels
	*/
	public void train (Collection <Image> trainImages, Collection <Integer> trainLabels)
	{
		if (trainImages == null)
		{
			System.out.println("ERROR! Train data passed as null");
			return;
		}
		if (trainImages.size() == 0)
		{
			System.out.println("ERROR! Train dataset is empty");
		}
		if (trainLabels == null)
		{
			System.out.println("ERROR! Train labels passed as null");
			return;
		}
		if (trainLabels.size() != trainImages.size())
		{
			System.out.println("ERROR! Train data and label sizes don't match");
			return;
		}
		tree = new VPTreeImpl (trainImages, metric);

		imgTolabel = new HashMap <Image, Integer> ();
		for (int i=0; i<trainImages.size(); ++i)
		{
			Image img = ((ArrayList <Image>)(trainImages)).get(i);
			Integer l = ((ArrayList <Integer>)(trainLabels)).get(i);
			imgTolabel.put(img, l);
		}
	}

	/**
		Runs the classifer over the test data and stores the predictions as pred;
		@param test dataset
	*/
	@Override
	public void test (Collection <Image> testImages)
	{
		if (testImages == null)
		{
			System.out.println("ERROR! Test data passed as null");
			return;
		}
		if (testImages.size() == 0)
		{
			System.out.println("ERROR! Test dataset is empty");
		}
		pred = new ArrayList <Image> ();

		for (int i=0; i<testImages.size(); ++i)
		{
			Image img = tree.findOneNN(((ArrayList <Image> )(testImages)).get(i));
			pred.add(img);
		}
	}

	/**
		@return the prediction using test data
	*/
	public Collection <Image> getPredictions ()
	{
		if (pred == null)
		{
			if (tree == null)  System.out.println("ERROR! Classifier has not been trained");
			else  System.out.println("ERROR! Test data has not been provided");
			return null;
		}
		return pred;
	}

	/**
		@return the predicted labels
	*/
	public Collection <Integer> getPredictedLabels ()
	{
		if (pred == null)
		{
			if (tree == null)  System.out.println("ERROR! Classifier has not been trained");
			else  System.out.println("ERROR! Test data has not been provided");
			return null;
		}
		if (imgTolabel == null)
		{
			if (tree == null)  System.out.println("ERROR! Classifier has not been trained");
			else  System.out.println("ERROR! Train labels have not been provided");
			return null;
		}

		ArrayList <Integer> arr = new ArrayList <Integer> ();
		for (Image img : pred)
		{
			int label = imgTolabel.get(img);
			arr.add(label);
		}
		return arr;
	}

	/**
		Computes the accuracy of the classifier on the test data
		@return Accuracy as a floating point value
	*/
	@Override
	public double getAccuracy (Collection <Integer> groundLabels)
	{
		if (groundLabels == null)
		{
			System.out.println("ERROR! Ground labels passed as null");
			return 0.0;
		}
		if (pred == null)
		{
			System.out.println("ERROR! Test data has not yet been provided");
			return 0.0;
		}
		if (groundLabels.size() != pred.size())
		{
			System.out.println("ERROR! Number of ground-truth labels does not match number of test images");
			return 0.0;
		}

		double total = (double)(groundLabels.size());
		ArrayList <Image> predictions = (ArrayList <Image>)(pred);
		double correct = 0.0;
		for (int i=0; i<groundLabels.size(); ++i)
		{
			// System.out.println("i = "+i);
			// if (((ArrayList <Integer>)(groundLabels)).get(i) == null)
			// {
			// 	System.out.println("i="+i+" groundLabel is null");
			// 	continue;
			// }
			// if (predictions.get(i) == null)
			// {
			// 	System.out.println("i="+i+" pred is null");
			// 	continue;
			// }
			// if (!imgTolabel.containsKey(predictions.get(i)))
			// {
			// 	System.out.println("i="+i+" imgTolabel[pred] is null");
			// 	continue;
			// }
			if (((ArrayList <Integer>)(groundLabels)).get(i) == imgTolabel.get(predictions.get(i)))  correct += 1.0;
		}

		return correct/total;
	}

	/**
		Uses the VP Tree to find the nearest neigbour of img
		@param Image img
		@return the predicted nearest neighbour of img
	*/
	public Image predict (Image img)
	{
		if (img == null)
		{
			System.out.println("ERROR! Image object passed as null");
			return null;
		}

		return tree.findOneNN(img);
	}

	/**
		Uses the VP Tree to find the nearest neigbour of img
		@param Image img
		@return the predicted nearest neighbour of img
	*/
	public int predictLabel (Image img)
	{
		if (img == null)
		{
			System.out.println("ERROR! Image object passed as null");
			return -1;
		}

		return imgTolabel.get(tree.findOneNN(img));
	}

	/**
		@return the VP Tree used for classification
	*/
	public VPTreeImpl getTree ()
	{
		if (tree == null)
		{
			System.out.println("ERROR! Classifier has not been trained yet so VP Tree has not been built");
			return null;
		}
		return tree;
	}
}
