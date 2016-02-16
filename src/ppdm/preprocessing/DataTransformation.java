package ppdm.preprocessing;
import ppdm.preprocessing.HandlingMissingValues;
public class DataTransformation {
	public String getNumericalAttributeCategory(String attributeDetails, int value)
	{
		/*
		String ranges[] = attributeDetails.split(" ");
		int min = Integer.parseInt(ranges[0]);
		int max = Integer.parseInt(ranges[1]);
		int interval = Integer.parseInt(ranges[2]);
		int result = min/interval;
		while(true)
		{

			if(min<=value && (min+interval)>value)
				break;
			min+=interval;
			result++;
			}
		return result+"";
		*/
		return value+"";
		}
	public String getCategoricalAttributeCategory(String attributeDetails, String value)
	{
		String values[] = attributeDetails.split(" ");
		String result="";
		for(int i=0;i<values.length;i++)
		{
			String category[] = values[i].split(":");
			if(category[0].equals(value))
			{
				result=category[1];
				break;
				}
			}
		return result;
		}
}
