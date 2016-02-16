package ppdm.kanonimity;

public class Retransformation 
{
	public String tranformNumbericalValue(String attributeDetails, int value)
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
	public String tranformCategoricalValue(String attributeDetails, int value)
	{
		String values[] = attributeDetails.split(" ");
		String result="";
		for(int i=1;i<values.length;i++)
		{
			String category[] = values[i].split(":");
			if(Integer.parseInt(category[1])==value)
			{
				result=category[0];
				break;
				}
			}
		return result;
		}
	}
