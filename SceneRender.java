import java.awt.Color;

public class SceneRender{

	private static Line [] trackLines;
	private static boolean init = false;
	
	
	public static void drawLines(Line[] _toDraw){
		for (int i = 0; i < _toDraw.length; i++){
			GraphicsHandler.setRadius(0.005);
			GraphicsHandler.drawLine(_toDraw[i]);
			//System.out.println("SceneRender.drawLines()  _toDraw["+i+"]");
		}
		
	}
	
	public static void drawPoints(Point[] _in, Color _color){
		GraphicsHandler.setColor(_color);
		for (int i =0; i < _in.length; i++){
			GraphicsHandler.drawFilledCircle(_in[i].x, _in[i].y, 0.01);
		}
	}
	
}