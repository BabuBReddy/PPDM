package ppdm.kanonimity;

import java.io.IOException;
import java.util.ArrayList;

import ppdm.preprocessing.MetaData;

public class Cluster {
	public ArrayList<Double> centroid = new ArrayList<Double>();
	public ArrayList< ArrayList<Double>> points = new ArrayList<ArrayList<Double> >();
	public int trueCount;
	public Cluster()
	{
		try {
			centroid=generateCentroid();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Double> getCentroid()
	{
		return centroid;		
	}
	public void setCentroid(ArrayList<Double> centroid1)
	{
		centroid=centroid1;
	}
	public ArrayList<ArrayList<Double>> getPoints()
	{
		return points;
	}
	public void addPoint(ArrayList<Double> point)
	{
		points.add(point);
	}
	public ArrayList<Double> generateCentroid() throws IOException
	{
		Constants constants = new Constants();
		MetaData md = new MetaData();
		ArrayList<Integer> minValues = new ArrayList<Integer>();
		ArrayList<Integer> maxValues = new ArrayList<Integer>();
		minValues=md.getMinValues();
		maxValues=md.getMaxValues();
		int index=0;
		ArrayList<Double> centroid = new ArrayList<Double>();
		for(int i=0; i<md.attributeDescription.length();i++)
		{
			if(md.attributeDescription.charAt(i)!='2')
			{
				centroid.add(getRandom(minValues.get(index),maxValues.get(index)));
				index++;
				}
			}
		return centroid;
	}
	public double getRandom(int min, int max)
	{
		while(true)
		{
			double randomValue=max*Math.random();
			if(randomValue>=min)
			{
				return randomValue;		
				}
			}
		}
	public void setTrueCount(int value)
	{
		trueCount=value;
		}
	public int getTrueCount()
	{
		return trueCount;
		}
	}
