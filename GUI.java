import java.awt.*;

public class GUI{
	
	private static int panelTextSize = 20;
	
	public static void title(String _text){
		GraphicsHandler.setColor(StdDraw.WHITE);
		GraphicsHandler.textSize(100);
		
		GraphicsHandler.drawText(0, 0.9, _text);
	}
	
	
	public static void leftPanel(String[] _lines){
		GraphicsHandler.setColor(StdDraw.WHITE);
		GraphicsHandler.textSize(panelTextSize);
		
		for(int i = 0; i < _lines.length; i++){
			double y = 0.7 - (i*0.1);
			GraphicsHandler.drawTextLeft(-0.9, y, _lines[i]);
		}
	}
	
	public static void middlePanel(String[] _lines){
		GraphicsHandler.setColor(StdDraw.WHITE);
		GraphicsHandler.textSize(panelTextSize);
		
		for(int i = 0; i < _lines.length; i++){
			double y = 0.7 - (i*0.1);
			GraphicsHandler.drawText(0, y, _lines[i]);
		}
	}
	
	public static void rightPanel(String[] _lines){
		GraphicsHandler.setColor(StdDraw.WHITE);
		GraphicsHandler.textSize(panelTextSize);
		
		for(int i = 0; i < _lines.length; i++){
			double y = 0.7 - (i*0.1);
			GraphicsHandler.drawTextRight(0.9, y, _lines[i]);
		}
	}
}