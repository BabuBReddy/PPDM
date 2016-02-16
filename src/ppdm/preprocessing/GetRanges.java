package ppdm.preprocessing;
import java.util.ArrayList;
import ppdm.preprocessing.NumericalAttributes;;
public class GetRanges {
	public void getAllRanges()
	{
		NumericalAttributes na = new NumericalAttributes();
		ArrayList<Integer> ageRanges = new ArrayList<Integer>();
		ageRanges = na.getNumericalAttributeRanges(1);
	}
}
