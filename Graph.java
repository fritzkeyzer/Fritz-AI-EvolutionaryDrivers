import java.awt.Color;
import java.awt.Font;
import java.util.*;


public class Graph{
	
	private static boolean init = false;
	private static String title = "Graph";
	
	private static String defaultFont = "Monospaced";
	
	private static ArrayList<DataSet> dataSets= new ArrayList<DataSet>();
	
	public static void main(String[] args){
		
		show();
		
	}
	
	private static void init(){
		MyDraw.enableDoubleBuffering();
		MyDraw.setCanvasSize(800, 800);
		MyDraw.setScale(-1, 1);
		init = true;
	}
	
	public static void show(){
		if (!init){init();}
		MyDraw.clear(MyDraw.BLACK);
		
		
		
		//Drawing logic goes here:
		
		//Graph title
		MyDraw.setPenColor(MyDraw.WHITE);
		textSize(30);
		MyDraw.text(0, 0.9, title);
		
		//Graph Frame:
		MyDraw.setPenColor(MyDraw.WHITE);
		
		MyDraw.line(-0.9, 0.8, -0.9, -0.7);
		MyDraw.line(0.5, 0.8, 0.5, -0.7);
		MyDraw.line(-0.9, 0.8, 0.5, 0.8);
		MyDraw.line(-0.9, -0.7, 0.5, -0.7);
		
		
		for (DataSet dataSet : dataSets){
			MyDraw.setPenColor(dataSet.color);
		}
		
		
		
		
		
		MyDraw.show();
	}
	
	public void addSet(DataSet _set){
		dataSets.add(_set);
	}
	
	
	private static void textSize(int _size){
		Font font = new Font(defaultFont, Font.PLAIN, _size);
		MyDraw.setFont(font);
	} 
	
}