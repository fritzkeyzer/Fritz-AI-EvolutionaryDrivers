import java.awt.Color;
import java.util.*;

public class Track{
	
	private static Line [] trackLines;
	private static Line [] checkPoints;
	
	private static boolean init = false;
	
	private static Color trackColor = StdDraw.WHITE;
	private static Point spawnPoint;
	private static double spawnDirection;
	
	public Track(){
		//add String argument to choose text file from which to load track.
		
		if(!init){init();}
		
	}
	
	
	
	public static void init(){
		//hardcode the lines in here for now
		//load them from txt file at a later stage
		
		if (false){
			Point[] centerPoints = new Point[8];
			centerPoints[0] = new Point(-0.5, 0.5);
			centerPoints[1] = new Point(0, 0.7);
			centerPoints[2] = new Point(0.5, 0.5);
			centerPoints[3] = new Point(0.7, 0);
			centerPoints[4] = new Point(0.5, -0.5);
			centerPoints[5] = new Point(0, -0.5);
			centerPoints[6] = new Point(-0.5, -0.5);
			centerPoints[7] = new Point(-0.7, 0);
			
			drawTrack(centerPoints, 0.2, true, true);
		}
		else if (false){
			Point[] centerPoints = new Point[8];
			centerPoints[0] = new Point(-0.5, 0.5);
			centerPoints[1] = new Point(0, 0.6);
			centerPoints[2] = new Point(0.5, 0.5);
			centerPoints[3] = new Point(0.7, 0);
			centerPoints[4] = new Point(0.5, -0.5);
			centerPoints[5] = new Point(0, -0.3);
			centerPoints[6] = new Point(-0.5, -0.5);
			centerPoints[7] = new Point(-0.7, 0);
			
			drawTrack(centerPoints, 0.2, true, true);
		}
		else {
			Point[] centerPoints = new Point[27];
			centerPoints[0] = new Point(-0.7, -0.8);
			centerPoints[1] = new Point(-0.8, -0.8);
			centerPoints[2] = new Point(-0.8, -0.5);
			centerPoints[3] = new Point(-0.7, -0.2);
			centerPoints[4] = new Point(-0.7, 0);
			centerPoints[5] = new Point(-0.8, 0);
			centerPoints[6] = new Point(-0.8, 0.2);
			centerPoints[7] = new Point(-0.2, 0.2);
			centerPoints[8] = new Point(-0.2, 0.3);
			centerPoints[9] = new Point(-0.2, 0.4);
			centerPoints[10] = new Point(-0.8, 0.4);
			centerPoints[11] = new Point(-0.8, 0.6);
			centerPoints[12] = new Point(0.2, 0.6);
			centerPoints[13] = new Point(0.2, 0.4);
			centerPoints[14] = new Point(0, 0.4);
			centerPoints[15] = new Point(0, 0.2);
			centerPoints[16] = new Point(0.2, 0.2);
			centerPoints[17] = new Point(0.2, 0);
			centerPoints[18] = new Point(0.2, -0.2);
			centerPoints[19] = new Point(0.4, -0.2);
			centerPoints[20] = new Point(0.4, 0.8);
			centerPoints[21] = new Point(0.8, 0.8);
			centerPoints[22] = new Point(0.8, 0.3);
			centerPoints[23] = new Point(0.6, 0.3);
			centerPoints[24] = new Point(0.6, 0);
			centerPoints[25] = new Point(0.8, 0);
			centerPoints[26] = new Point(0.8, -0.8);
			
			drawTrack(centerPoints, 0.19, true, true);
		}
		init = true;
	}
	
	private static void drawTrack(Point[] _vertices, double _width, boolean _loop, boolean _stopper){
		spawnPoint = _vertices[1];
		spawnDirection = Math.atan2((_vertices[2].y-_vertices[1].y),(_vertices[2].x-_vertices[1].x));
		
		int le = _vertices.length;
		
		Point[] outerTrackCorners = new Point[le];
		Point[] innerTrackCorners = new Point[le];
		
		Line[] outerTrackLines;
		Line[] innerTrackLines;
		
		if (_loop){
			if (_stopper){
				outerTrackLines = new Line[le+1];
			}
			else{
				outerTrackLines = new Line[le];
			}
			innerTrackLines = new Line[le];
			checkPoints = new Line[le-1];
		}
		else{
			outerTrackLines = new Line[le+2];
			innerTrackLines = new Line[le];
			checkPoints = new Line[le-2];
		}
		
		
		
		for (int i = 0; i < le; i++){
			//from i   -> i+1
			int i1 = i+1;
			if (i1 >= le){
				i1 -= le;
			}
			//from i+1 -> i+2
			int i2 = i+2;
			if (i2 >= le){
				i2 -= le;
			}
			
			Point a = _vertices[i];
			Point b = _vertices[i1];
			Point c = _vertices[i2];
			
			double a01 = Math.atan2((b.y-a.y), (b.x-a.x));
			double a12 = Math.atan2((c.y-b.y), (c.x-b.x));
			
			double z = (a01+a12+Math.PI)/2;
			
			double y = (a01 + Math.PI/2 - z);
			
			double h = (_width/2)/(Math.cos(y));
			
			if (!_loop){
				if (i == (le-2)){
					//last line segment
					z = a01+(Math.PI/2);
					h = _width/2;
				}
				else if (i == (le-1)){
					//first line segment
					z = a12+(Math.PI/2);
					h = _width/2;
				}
			}
			
			//this is all to find the two track corners around point b
			outerTrackCorners[i] = new Point((b.x + h*Math.cos(z)), (b.y + h*Math.sin(z)));
			innerTrackCorners[i] = new Point((b.x - h*Math.cos(z)), (b.y - h*Math.sin(z)));
		}
		
		//SceneRender.drawPoints(_vertices, StdDraw.PINK);
		//SceneRender.drawPoints(outerTrackCorners, StdDraw.RED);
		//SceneRender.drawPoints(innerTrackCorners, StdDraw.BLUE);
		//
		//GraphicsHandler.end();
		
		if (_loop){
			int blah = le;
			if (_stopper){blah = le+1;}
			for (int i = 0; i < blah; i ++){
				//from i   -> i+1
				int i1 = i+1;
				if (i1 >= le){
					i1 -= le;
				}
				
				if (i < le){
					outerTrackLines[i] = new Line(outerTrackCorners[i], outerTrackCorners[i1], trackColor);
					
					innerTrackLines[i] = new Line(innerTrackCorners[i], innerTrackCorners[i1], trackColor);
					
					if (i < checkPoints.length){
						checkPoints[i] = new Line(outerTrackCorners[i], innerTrackCorners[i], StdDraw.GREEN);
					}
				}
				
				else{
					outerTrackLines[i] = new Line(outerTrackCorners[outerTrackCorners.length-1], innerTrackCorners[innerTrackCorners.length-1], trackColor);
				}
			}
		}
		else{
			for (int i = 0; i < le+2; i ++){
				//from i   -> i+1
				int i1 = i+1;
				if (i1 >= le){
					i1 -= le;
				}
				
				if (i < le-2){
					checkPoints[i] = new Line(outerTrackCorners[i1], innerTrackCorners[i1], StdDraw.GREEN);
				}
				if (i < le){
					outerTrackLines[i] = new Line(outerTrackCorners[i], outerTrackCorners[i1], trackColor);
					innerTrackLines[i] = new Line(innerTrackCorners[i], innerTrackCorners[i1], trackColor);
					
				}
				else if (i < outerTrackLines.length-1){
					outerTrackLines[i] = new Line(outerTrackCorners[0], innerTrackCorners[0], trackColor);
				}
				else{
					outerTrackLines[i] = new Line(outerTrackCorners[i-2], innerTrackCorners[i-2], trackColor);
				}
			}
		}
		//System.out.println(outerTrackLines.length);
		//System.out.println(innerTrackLines.length);
		
		trackLines = new Line[innerTrackLines.length+outerTrackLines.length];
		
		for (int i = 0; i < outerTrackLines.length; i++){
			trackLines[i] = outerTrackLines[i];
		}
		for (int i = 0; i < innerTrackLines.length; i++){
			trackLines[outerTrackLines.length+i] = innerTrackLines[i];
		}
	}
	
	public static Line[] getLines(){
		if (!init){init();}
		
		return trackLines;
	}
	
	public static Line[] getCheckPoints(){
		if (!init){init();}
		
		return checkPoints;
	}
	
	public static Point getSpawn(){
		if (!init){init();}
		
		return spawnPoint;
	}
	
	public static double getSpawnDirection(){
		if (!init){init();}
		
		return spawnDirection;
	}
	
}