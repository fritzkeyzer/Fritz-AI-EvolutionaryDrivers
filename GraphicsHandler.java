import java.awt.*;

public class GraphicsHandler{
	
	private static final int resW = 900;
	private static final int resH = 900;
	private static String defaultFont = "Monospaced";
	private static boolean init = false;
	
	private static Color clearColor = StdDraw.DARK_GRAY;
	
	public static void init(){
		
		textSize(12);
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(resW, resH);
		StdDraw.setScale(-1, 1);
		StdDraw.clear(clearColor);
		init = true;
	}
	
	public static void start(){
		if (!init){init();}
		StdDraw.clear(clearColor);
	}
	
	public static void end(){
		StdDraw.show();
	}
	
	public static void drawLine(Line _toDraw){
		setColor(_toDraw.getColor());
		Point [] points = _toDraw.getPoints();
		StdDraw.line(points[0].x, points[0].y, points[1].x, points[1].y);
	}
	
	public static void drawFilledCircle(double _x, double _y, double _radius){
		StdDraw.filledCircle(_x, _y, _radius);
	}
	
	public static void drawFilledPolygon(double[] _x, double[] _y){
		StdDraw.filledPolygon(_x, _y);
	}
	
	public static void drawText(double _x, double _y, String _text){
		StdDraw.text(_x, _y, _text);
	}
	
	public static void drawTextLeft(double _x, double _y, String _text){
		StdDraw.textLeft(_x, _y, _text);
	}
	
	public static void drawTextRight(double _x, double _y, String _text){
		StdDraw.textRight(_x, _y, _text);
	}
	
	public static void setColor(Color _color){
		StdDraw.setPenColor(_color);
	}
	
	public static void setRadius(double _rad){
		StdDraw.setPenRadius(_rad);
	}
	
	public static void textSize(int _size){
		Font font = new Font(defaultFont, Font.PLAIN, _size);
		StdDraw.setFont(font);
	}   
	
}