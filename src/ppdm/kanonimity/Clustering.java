package ppdm.kanonimity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import ppdm.preprocessing.MetaData;
import ppdm.preprocessing.MetaData;

public class Clustering {
	MetaData md=null;
	Cluster clusters[]=null;
	int numOfClusters;
	ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
	ArrayList<String> finalResult = new ArrayList<String>();
	public Clustering() throws IOException
	{
		md=new MetaData();
		}
	public void clustering() throws IOException
	{
		MetaData md =null;
		try {
			md = new MetaData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			createClusters();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readData();
		DistanceMeasure distance = new DistanceMeasure();
		for(int i=0;i<data.size();i++)
		{
			double minDistance=Double.MAX_VALUE;
			int minIndex=0;	

			for(int j=0;j<numOfClusters;j++)
			{
				double tempDistance = distance.getDistance(data.get(i),clusters[j].getCentroid());
				if(tempDistance<minDistance)
				{
					minDistance = tempDistance;
					minIndex=j;
				}
			}
			clusters[minIndex].addPoint(data.get(i));
			for(int ii=0;ii<clusters[minIndex].getPoints().size();ii++)
			updateCentroid(minIndex);
		}
        int cc=0;
		File fout = new File("./data/output.txt");
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(fout);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for(int i=0;i<numOfClusters;i++)
		{
			int c=0;
			ArrayList< ArrayList<Double>> points=new ArrayList<ArrayList<Double>>();
			points=clusters[i].getPoints();
			if(clusters[i].getPoints().size()>=md.anonimityLevel)
			{
				cc++;
			for(int j=0;j<points.size();j++)
				if(points.get(j).get(8)==1.0)
					c++;
			clusters[i].setTrueCount(c);
			retransform(bw, clusters[i].getCentroid(),clusters[i].getTrueCount(),clusters[i].getPoints().size());
			points.clear();
				}
			}
        bw.close();
	}
	public void retransform(BufferedWriter bw, ArrayList<Double> centroid, int trueCount, int clusterSize) throws IOException
	{
		String retransfer="";
		Retransformation rt = new Retransformation();
		int index=0;
		String attributeDetails[] = md.attributeDetails.split(";");
		for(int k=0;k<md.attributeDescription.length()-1;k++)
		{
			if(md.attributeDescription.charAt(k)=='2')
				continue;
			else if(md.attributeDescription.charAt(k)=='0')
			{
				retransfer+=rt.tranformNumbericalValue(attributeDetails[index],centroid.get(index).intValue());
				retransfer+=",";
				index++;
				}
			else if(md.attributeDescription.charAt(k)=='1')
			{
				retransfer+=rt.tranformCategoricalValue(attributeDetails[index],centroid.get(index).intValue());
				index++;
				retransfer+=",";
				}
			}
		for(int i=0;i<trueCount;i++)
		{
			bw.write(retransfer+"Yes");
			bw.newLine();	
			}
		for(int j=0;j<clusterSize-trueCount;j++)
		{
			bw.write(retransfer+"No");
			bw.newLine();	
			}
		}
	public void updateCentroid(int index)
	{
		ArrayList<ArrayList<Double>> points = new ArrayList<ArrayList<Double>>() ;
		points = clusters[index].getPoints();
		ArrayList<Double> centroid = new ArrayList<Double>();
		centroid = clusters[index].getCentroid();
		for(int i=0;i<centroid.size();i++)
		{
			double sum=0;
			for(int j=0;j<points.size();j++)
				sum+=points.get(j).get(i);
			sum+=centroid.get(i);
			centroid.set(i, sum/(points.size()+1));
			}
		clusters[index].setCentroid(centroid);
	}
	public void createClusters() throws IOException
	{
		
		int num=md.numberTuples;
		int k=md.anonimityLevel;
		numOfClusters=num/k;
		clusters = new Cluster[numOfClusters];
		for(int i=0;i<numOfClusters;i++)
		{
			clusters[i] = new Cluster();
		}
	}
	public void readData()
	{
		
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(new File("./data/transformedData.txt")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line=null;
		try {

			int c=0;
			while((line=br.readLine())!=null)
			{
				ArrayList<Double> row = new ArrayList<Double>();
				String values[]=line.split(",");
				for(int i=0;i<values.length;i++)
					row.add(i,Double.parseDouble(values[i]));
				data.add(row);
				//data.add(c++, row);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
