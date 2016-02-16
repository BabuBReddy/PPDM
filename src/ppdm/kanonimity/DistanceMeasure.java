package ppdm.kanonimity;

import java.util.ArrayList;
import ppdm.kanonimity.Constants;

public class DistanceMeasure {
	public double getDistance(ArrayList<Double> point1, ArrayList<Double> point2)
	{
		Constants constants = new Constants();
		double distance =0;
		for(int i=0;i<point1.size()-1;i++)
			distance+=manhattonDistance(constants.weights[i],point1.get(i),point2.get(i));
		return distance;
	}
	public double manhattonDistance(double weight,double point1,double point2)
	{
		return weight* Math.abs(point1-point2);
	}
	
}
