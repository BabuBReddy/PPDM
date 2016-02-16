package ppdm.preprocessing;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
public class DataSelection {
	public void getDataIntoDesiredForm(MetaData md)
	{
		
		File fout = new File("./data/transformedData.txt");
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(fout);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(new File(md.fileName)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line=null;
		try 
		{
			while((line=br.readLine())!=null)
			{
				String transformed="";
				String attributes[] = line.split(md.separator);
				transformed = getTransformedString(attributes,md);
				try
				{
					bw.write(transformed);
					bw.newLine();
					}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
			}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public String getTransformedString(String attributes[], MetaData md)
	{
		String result="";
		String missingValues[]=getMissingValues();
		DataTransformation dt = new DataTransformation();
		int index=0;
		String attributeDetails[] = md.attributeDetails.split(";");
		for(int i=0;i<md.attributeDescription.length();i++)
		{
			if(md.attributeDescription.charAt(i)=='2')
				continue;
			else if(md.attributeDescription.charAt(i)=='0')
			{
				if(attributes[i].equals(md.missingCharacter))
					result+=missingValues[index];
				else
					result+=dt.getNumericalAttributeCategory(attributeDetails[0],Integer.parseInt(attributes[i]));
				result+=",";
				index++;
				}
			else if(md.attributeDescription.charAt(i)=='1')
			{
				if(attributes[i].equals(md.missingCharacter))
					result+=missingValues[index];
				else
					result+=dt.getCategoricalAttributeCategory(attributeDetails[index],attributes[i]);
				result+=",";
				index++;
				}
			}
		return result.substring(0, result.length()-1);
		/*
		if(attributes[0].equals(md.missingCharacter))
		 
			result+=missingValues[0];
		else
			result+=dt.getAgeCategory(Integer.parseInt(attributes[0]));
		result+=",";

		if(attributes[1].equals("?"))
			result+=missingValues[1];
		else
			result+=dt.getWorkClassCategory(attributes[1]);
		result+=",";
		if(attributes[4].equals("?"))
			result+=missingValues[2];
		else
			result+=attributes[4];
		result+=",";
		if(attributes[5].equals("?"))
			result+=missingValues[3];
		else
			result+=dt.getMarriedStatusCategory(attributes[5]);
		result+=",";
		if(attributes[6].equals("?"))
			result+=missingValues[4];
		else
			result+=dt.getOccupationCategory(attributes[6]);
		result+=",";
		if(attributes[7].equals("?"))
			result+=missingValues[5];
		else
			result+=dt.getRelationshipCategory(attributes[7]);
		result+=",";				
		if(attributes[9].equals("?"))
			result+=missingValues[6];
		else
			result+=dt.getGenderCategory(attributes[9]);
		result+=",";				
		if(attributes[12].equals("?"))
			result+=missingValues[7];
		else
			result+=attributes[12];
		result+=",";
		if(attributes[14].equals("?"))
			result+=missingValues[8];
		else
			result+=dt.getSalaryCategory(attributes[14]);
			*/
		}
	public String[] getMissingValues()
	{
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(new File("./data/missingValues.txt")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line=null;
		try {
			line = br.readLine();
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
		return line.split(" ");
		}
}
