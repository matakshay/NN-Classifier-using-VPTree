/**
	@author Akshay Mattoo
*/

package NNClassifier;

import NNClassifier.*;
import NNClassifier.Classifier.*;
import NNClassifier.Distance.*;
import NNClassifier.VPTree.*;

import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args)
	{
		//Set Path to Train and Test dataset
		String trainImagesFile = "NNClassifier/Data/train_images";
		String trainLabelsFile = "NNClassifier/Data/train_labels";
		String testImagesFile = "NNClassifier/Data/test_images";
		String testLabelsFile = "NNClassifier/Data/test_labels";


		// Read Train Images
		System.out.print("Reading train images...");
		FileInputStream fin = null;
		ArrayList <Image> trainImages = null;
		try
        {
			fin = new FileInputStream (trainImagesFile);
			trainImages = readImages (fin);
			System.out.print("\tRead!\n");
		}
        catch (FileNotFoundException e)
        {
			System.out.print("\tERROR! File not found\n");
		}

		// Read Train Labels
		System.out.print("Reading train labels...");
		fin = null;
		ArrayList <Integer> trainLabels = null;
		try
        {
			fin = new FileInputStream (trainLabelsFile);
			trainLabels = readLabels (fin);
			System.out.print("\tRead!\n");
		}
        catch (FileNotFoundException e)
        {
			System.out.print("\tERROR! File not found\n");
		}


		OneNNClassifier classifier = null;
		System.out.print("Setting distance metric as...");
		if (args.length==0 || ((String)args[0]).equals("l2"))
		{
			l2Distance dist = new l2Distance ();
			classifier = new OneNNClassifier (dist);
			System.out.print("\tEuclidean distance\n");
		}
		else if (((String)(args[0])).equals("l1"))
		{
			l1Distance dist = new l1Distance ();
			classifier = new OneNNClassifier (dist);
			System.out.print("\tManhattan distance\n");
		}
		else if (((String)(args[0])).equals("linf"))
		{
			linfDistance dist = new linfDistance ();
			classifier = new OneNNClassifier (dist);
			System.out.print("\tChebyshev distance\n");
		}
		else
		{
			System.out.print("\tInput not recognized! Using Euclidean distance by default\n");
			l2Distance dist = new l2Distance ();
			classifier = new OneNNClassifier (dist);
		}


		// Train Classifier
		System.out.print("Training classifier...");
		classifier.train(trainImages, trainLabels);
		System.out.print("\tDone!\n");


		// Read Test Images
		System.out.print("Reading test images...");
		fin = null;
		ArrayList <Image> testImages = null;
		try
        {
			fin = new FileInputStream (testImagesFile);
			testImages = readImages (fin);
			System.out.print("\tRead!\n");
		}
        catch (FileNotFoundException e)
        {
			System.out.print("\tERROR! File not found\n");
		}

		// Read Test Labels
		System.out.print("Reading test labels...");
		fin = null;
		ArrayList <Integer> testLabels = null;
		try
        {
			fin = new FileInputStream (testLabelsFile);
			testLabels = readLabels (fin);
			System.out.print("\tRead!\n");
		}
        catch (FileNotFoundException e)
        {
			System.out.print("\tERROR! File not found\n");
		}


		System.out.print("Running classifier over test data...");
		classifier.test (testImages);
		System.out.print("\tPredictions stored!\n");


		System.out.println("Calculating Accuracy...");
		double acc = classifier.getAccuracy(testLabels);
		System.out.println("Accuracy = " + acc);
	}

	private static ArrayList <Image> readImages (FileInputStream fin)
	{
        DataInputStream din = new DataInputStream (fin);

        int magicNum = readInt(din);
		int numItems = readInt(din);
		int row = readInt(din);
        int col = readInt(din);

		ArrayList <Image> data = new ArrayList <Image> ();
		for (int i=0; i<numItems; ++i)
		{
			ArrayList <Integer> pixels = new ArrayList <Integer> ();
			for (int j=0; j<row*col; ++j)
			{
				int p = 0;
                try
                {
					p = fin.read();
				}
                catch (IOException e)
                {
					System.out.println("ERROR! Could not read images");
				}

				pixels.add(p);
			}

            Image img = new Image (pixels);
			data.add(img);
		}
		return data;
	}

	private static ArrayList <Integer> readLabels (FileInputStream fin)
	{
        DataInputStream din = new DataInputStream (fin);

        int magicNum = readInt(din);
		int numItems = readInt(din);

		ArrayList <Integer> labels = new ArrayList <Integer> ();
		for (int i=0; i<numItems; ++i)
		{
            int label = 0;
            try
            {
				label = fin.read();
			}
            catch (IOException e)
            {
				System.out.println("ERROR! Could not read labels");
			}

			labels.add(label);
		}
		return labels;
	}

	private static int readInt (DataInputStream din)
    {
        try
        {
			return din.readInt();
		}
        catch (IOException e)
        {   }

        return 0;
    }

}
