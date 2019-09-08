import java.awt.Color;
import java.util.*;

public class DataSet{
	
	public final String name;
	public final Color color;
	
	private ArrayList<Double> xValues = new ArrayList<Double>();
	private ArrayList<Double> yValues = new ArrayList<Double>();
	private ArrayList<String> labels = new ArrayList<String>();
	
	public DataSet(String _name, Color _color){
		name = _name;
		color = _color;
	}
	
	public void addPoint(double _x, double _y, String _label){
		xValues.add(_x);
		yValues.add(_y);
		labels.add(_label);
	}
	
	public ArrayList<Double> getXValues(){
		return xValues;
	}
	
	public ArrayList<Double> getYValues(){
		return yValues;
	}
	
	public ArrayList<String> getLabels(){
		return labels;
	}
	
	
}