/**
    @author Akshay Mattoo
*/

package NNClassifier.VPTree;

import NNClassifier.*;
import NNClassifier.VPTree.*;
import NNClassifier.Distance.*;

import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;

/**
    Class to represent a VP Tree
	It inherits the VPTree abstract class
*/
public final class VPTreeImpl extends VPTree
{
	private Node root;
	private DistanceFunction dFunc;
	private double tau;
	private Image best;

	/**
		Constructor
		@param the train dataset of images, Distance metric d to be used
	*/
	public VPTreeImpl(Collection <Image> collection, DistanceFunction d)
	{
		super(collection, d);

		dFunc = d;
		tau = java.lang.Double.MAX_VALUE;
		best = null;

		ArrayList <Item> list = new ArrayList <Item> ();
		for (Image img : collection)
		{
			Item itm = new Item (img);
			list.add(itm);
		}

		root = recurse (list);
	}

	/**
		Helper function of the Constructor
		Recursively builds the VP Tree
		@param ArrayList of Item objects
		@return Node object which is the root of the VP Tree
	*/
	private Node recurse (ArrayList <Item> list)
	{
		if (list.size()==0) return null;

		Node n = new Node (getVP(list));

		deleteItem (list, n.getItem());

		for (Item itm : list)
		{
			double dist = dFunc.distance(itm.getImage(), n.getItem().getImage());
			itm.push(dist);
		}

		double mu = getMedian (list);
		n.setMu(mu);

		ArrayList <Item> L = new ArrayList <Item> ();
		ArrayList <Item> R = new ArrayList <Item> ();

		for (Item itm : list)
		{
			if (itm.tail() < mu)  L.add(itm);
			else  R.add(itm);
		}

		n.setLeft(recurse(L));
		n.setRight(recurse(R));

		return n;
	}

	/**
		Helper function to select the Vantage Point
		@param ArrayList of Item objects
		@return Item object containing the Vantage Point
	*/
	private Item getVP (ArrayList <Item> list)
	{
		if (list.size()==1) return list.get(0);

		int size = 100;
		if (list.size() < 100) size = list.size();

		ArrayList <Item> p;
		if (size != list.size())  p = getSample (list, size);
		else  p = list;

		double best_spread = 0.0;
		Item best = list.get(0);
		for (Item i : p)
		{
			ArrayList <Item> d;
			if (size != list.size())  d = getSample (list, size);
			else  d = list;

			ArrayList <Double> arr = new ArrayList <Double> ();
			for (Item j : d)
			{
				arr.add(dFunc.distance(j.getImage(), i.getImage()));
			}

			Collections.sort(arr);

			size = arr.size();
			double mu = 0.0;
			if ((size%2)==1)  mu = arr.get(size/2);
			else  mu = (arr.get(size/2)+arr.get((size/2)-1))/2.00;

			double spread = 0.0;
			for (double f : arr)
			{
				spread += ((f-mu)*(f-mu));
			}

			if (spread > best_spread)
			{
				best_spread = spread;
				best = i;
			}
		}

		return best;
	}

	/**
		Helper function to get a random sample of data points
		This is used by getVP() method to select the Vantage Point
		@param ArrayList of Item objects and size of sample
		@return ArrayList of Item objects representing the sample
	*/
	private ArrayList <Item> getSample (ArrayList <Item> list, int size)
	{
		if (list.size() <= size) return list;

		Random rand = new Random();
		ArrayList <Item> ans = new ArrayList <Item> ();
		HashSet <Integer> set = new HashSet <Integer> ();

		while (ans.size() < size)
		{
			int i = rand.nextInt(list.size());
			if (set.contains(i)) continue;
			set.add(i);
			ans.add(list.get(i));
		}
		return ans;
	}

	/**
		Helper function to delete an Item object from the ArrayList
		@param ArrayList of Item objects
	*/
	private void deleteItem (ArrayList <Item> list, Item itm)
	{
		for (int i=0; i<list.size(); ++i)
		{
			if ((list.get(i)).equals(itm))
			{
				list.remove(i);
				return;
			}
		}
		return;
	}

	/**
		Helper function to get the Median distance of the data points from vantage point
		@param ArrayList of Item objects
		@return median as a double
	*/
	private double getMedian (ArrayList <Item> list)
	{
		if (list.size()==0) return 0.0;

		ArrayList <Double> arr = new ArrayList <Double> ();
		for (Item i : list)
		{
			arr.add(i.tail());
		}

		Collections.sort(arr);
		int size = arr.size();

		if ((size%2)==1) return arr.get(size/2);
		else return (arr.get(size/2)+arr.get((size/2)-1))/2.00;
	}

	/**
		Finds the nearest neighbor of Image q from the Images in VP Tree
		@param query Image object q
		@return nearest neighbor Image object
	*/
	@Override
	public Image findOneNN(Image q)
	{
		tau = java.lang.Double.MAX_VALUE;
		best = q;

		search (root, q);

		return best;
	}

	/**
		Helper function to recursively search the VP Tree for the nearest neighbor of q
		It sets "best" to the nearest neighbor found so far
		@param Node of VP Tree, query Image object
	*/
	private void search (Node n, Image q)
	{
		if (n==null) return;
		if ((n.getItem().getImage()).equals(q))
		{
			tau = 0.0;
			best = n.getItem().getImage();
			return;
		}

		double dist = dFunc.distance(q, n.getItem().getImage());
		double mu = n.getMu();

		if (dist < tau)
		{
			tau = dist;
			best = n.getItem().getImage();
		}

		// If tau circle lies completely outside mu circle
		if (dist > tau+mu)
		{
			search (n.getRight(), q);
		}
		//If tau circle lies completely inside mu circle
		else if (dist < Math.max(tau, mu)-Math.min(tau, mu))
		{
			search (n.getLeft(), q);
		}
		//If both circles overlap
		else
		{
			search (n.getLeft(), q);
			search (n.getRight(), q);
		}
	}

	/**
        @return the DistanceFunction metric used in building this VP Tree
    */
	@Override
	public DistanceFunction getDistanceFunc()
	{
		return dFunc;
	}
}
