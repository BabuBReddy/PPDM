package ppdm.preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MetaData 
{
	public String fileName="";
	public String separator="";
	public String missingCharacter="";
	public String attributeDescription = "";
	public String attributeDetails = "";
	public int anonimityLevel;
	public int numberTuples;
	public MetaData() throws IOException
	{
		setDescription();
	}
	public void setDescription() throws IOException
	{
		BufferedReader br=null;
		try 
		{
			br = new BufferedReader(new FileReader(new File("./data/metaData.config")));
			} 
		catch (FileNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		fileName=br.readLine();
		separator=br.readLine();
		missingCharacter=br.readLine();
		attributeDescription=br.readLine();
		String temp="";
		while((temp=br.readLine()) != null)
		{
			attributeDetails+=temp;
			attributeDetails+=";";
			}
		//System.out.println(fileName);
		//System.exit(0);
		setAnonimityLevel();
		setNumberOfTuples();
		}
	private void setAnonimityLevel()
	{
		String attributeDetails1[] = attributeDetails.split(";");
		anonimityLevel=Integer.parseInt(attributeDetails1[attributeDetails1.length-3]);
		}
	public int getAnonimityLevel()
	{
		return anonimityLevel;
		}
	private void setNumberOfTuples()
	{
		String attributeDetails1[] = attributeDetails.split(";");
		numberTuples=Integer.parseInt(attributeDetails1[attributeDetails1.length-2]);
		}
	public int getNumberOfTuples()
	{
		return numberTuples;
		}
	public ArrayList<Integer> getMinValues()
	{
		ArrayList<Integer> minValues = new ArrayList<Integer>();
		String attributes[] = attributeDetails.split(";");
		int index=0;
		for(int i=0;i<attributeDescription.length();i++)
		{
			if(attributeDescription.charAt(i)=='0')
			{
				minValues.add(Integer.parseInt(attributes[index].split(" ")[0]));
				index++;
				}
			else if(attributeDescription.charAt(i)=='1')
			{
				String tempAttributes[] = attributes[index].split(" ");
				int min=Integer.MAX_VALUE;
				for(int j=1;j<tempAttributes.length;j++)
				{
					int value = Integer.parseInt(tempAttributes[j].split(":")[1]);
					if(min>value)
						min=value;
					}
				minValues.add(min);
				index++;
				}
			else
				continue;
			}
		return minValues;
		}
	public ArrayList<Integer> getMaxValues()
	{
		ArrayList<Integer> maxValues = new ArrayList<Integer>();
		String attributes[] = attributeDetails.split(";");
		int index=0;
		for(int i=0;i<attributeDescription.length();i++)
		{
			if(attributeDescription.charAt(i)=='0')
			{
				maxValues.add(Integer.parseInt(attributes[index].split(" ")[1]));
				index++;
				}
			else if(attributeDescription.charAt(i)=='1')
			{
				String tempAttributes[] = attributes[index].split(" ");
				int max=Integer.MIN_VALUE;
				for(int j=1;j<tempAttributes.length;j++)
				{
					int value = Integer.parseInt(tempAttributes[j].split(":")[1]);
					if(max<value)
						max=value;
					}
				maxValues.add(max);
				index++;
				}
			else
				continue;
			}
		return maxValues;
		}
	}
	