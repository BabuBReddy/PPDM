package ppdm.preprocessing;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class NumericalAttributes {

	ArrayList<Integer> ranges = new ArrayList<Integer>();
	public ArrayList<Integer> getNumericalAttributeRanges(int index)
	{
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
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		try {
			String line=null;
			while((line = br.readLine())!=null ){
				String[] attributes = line.split(",");			
				int value = Integer.parseInt(attributes[index-1]);
				if(min>value)
						min=value;
				if(max<value)
						max=value;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ranges.add(min);
		ranges.add(max);
		return ranges;
	}
}
