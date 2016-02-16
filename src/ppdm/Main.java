package ppdm;

import java.io.IOException;

import ppdm.kanonimity.Clustering;
import ppdm.preprocessing.Preprocessing;
public class Main {
	public static void main(String args[]) throws IOException
	{
		
		 Preprocessing pre = new Preprocessing();
		try
		{
		pre.preprocess();
			}
		catch(Exception e)
		{
			System.out.println(e);
			}
		Clustering c = new Clustering();
		c.clustering();
	}
}
