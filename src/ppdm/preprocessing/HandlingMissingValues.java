package ppdm.preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import ppdm.preprocessing.DataTransformation;;
public class HandlingMissingValues {
	
	public void handlingMissigValues(int index)
	{ 
		int count=0;
		int value=0;
		DataTransformation dt = new DataTransformation();
		BufferedReader br = null;
		File f = new File("./data/adultset.data");
		FileReader fr = null;
		try
		{
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();			
		}
		try 
		{
			String line=null;
			while((line = br.readLine())!=null )
			{
				String attributes[] = line.split(",");
				if(attributes[index-1].equals("?"))
					;
				else
				{
					count++;
					if(index==1)
						value=value+dt.getAgeCategory(Integer.parseInt(attributes[index-1]));
					if(index==2)
						value=value+dt.getWorkClassCategory(attributes[index-1]);
					if(index==5)
						value=value+(Integer.parseInt(attributes[index-1]));
					if(index==6)
						value=value+dt.getMarriedStatusCategory(attributes[index-1]);
					if(index==7)
						value=value+dt.getOccupationCategory(attributes[index-1]);
					if(index==8)
						value=value+dt.getRelationshipCategory(attributes[index-1]);
					if(index==10)
						if(attributes[index-1].equals("Male"))
							value++;
						else
							value--;
					if(index==13)
						value=value+Integer.parseInt(attributes[index-1]);
					if(index==15)
						if(attributes[index-1].equals(">50K"))
							value++;
						else
							value--;
				}
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw =null;
		try
		{
			bw = new BufferedWriter(new FileWriter("./data/missingValues.txt"));
			} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	   try {
		bw.append(""+value);
		bw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void getAllMissingValues()
	{
		HandlingMissingValues hmv = new HandlingMissingValues();
		//for(int i=0;i<15;i++)
			//hmv.handlingMissigValues(1);
			hmv.handlingMissigValues(15);
	}
}*/
