import java.awt.Color;

public class Line{
	Point head;
	Point tail;
	Color color;
	
	public Line(Point _head, Point _tail, Color _color){
		head = _head;
		tail = _tail;
		color = _color;
	}
	
	public Point[] getPoints(){
		Point [] out = new Point[2];
		out[0] = head;
		out[1] = tail;
		
		return out;
	}
	
	public Color getColor(){
		return color;
	}
}