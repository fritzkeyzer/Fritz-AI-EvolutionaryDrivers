import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Car{
	
	private static int number = 0;
	private static double speed = 0;
	private static double totSpeed = 0;
	private static double totTime = 0;
	private static Point pos;
	private static double direction = 0;
	private static Line[] carLines = new Line[3];
	private static Line[] eyeLines;
	private static Color carColor = StdDraw.RED;
	private static Color deadColor = StdDraw.BLACK;
	private static double[] eyes;
	private static Line [] trackLines;
	private static Line [] checkPoints;
	private static int lastCheckPoint = -1;
	private static int checkPointsCrossed = 0;
	private static DNA myDNA;
	private static boolean alive = false;
	private static double distanceTravelled = 0;
	private static double timeElapsed = 0;
	
	private static double arrowHeight = 0.04;
	private static double arrowWidth = 0.016;
	
	private static double fitness = 0;
	
	private static int framesTooSlow = 0;
	private static int framesNoProgress = 0;
	private static boolean progress = false;
	
	public Car(){
		number ++;
		//System.out.println("Car() "+number);
	}
	
	public static void reset(DNA _dna){
		timeElapsed = 0;
		fitness = 0;
		distanceTravelled = 0;
		checkPointsCrossed = 0;
		lastCheckPoint = -1;
		speed = 0;
		direction = Track.getSpawnDirection();
		pos = new Point(Track.getSpawn().x, Track.getSpawn().y );
		//pos = new Point(0.4,0.5);
		
		framesTooSlow = 0;
		
		alive = true;
		myDNA = _dna;
		trackLines = Track.getLines();
		checkPoints = Track.getCheckPoints();
		calcLines(carColor);
	}
	
	public static void update(double _deltaTime){
		calcEyes();
		
		//use NN and DNA to convert eyes to controls
		double[] sensors = new double[eyes.length];
		for (int i = 0; i < eyes.length; i++){
			sensors[i] = eyes[i];
		}
		double[] nnOut = NeuralNet.runNet(myDNA.getValues(), sensors);
		
		
		
		if (alive){
			control(nnOut, 10);
			//control(inputs, _deltaTime);
		}
		else{
			carColor = deadColor;
		}
		
		if (speed <= 0.00001){
			framesTooSlow ++;
			
			if (framesTooSlow == 20){
				alive = false;
			}
		}
		else {
			framesTooSlow = 0;
		}
		
		if (progress == false){
			framesNoProgress ++;
			
			if (framesNoProgress == 1000){
				alive = false;
			}
		}
		else{
			framesNoProgress = 0;
		}
		
		calcLines(carColor);
		timeElapsed += _deltaTime;
	}
	
	public static void control(double[] _inputs, double _deltaTime){
		boolean[] arrows =  new boolean[4];
		if (_inputs[0] > 0){
			arrows[0] = bin(_inputs[0]);
			arrows[1] = false;
		}
		else if (_inputs[0] < 0){
			arrows[1] = bin(-_inputs[0]);
			arrows[0] = false;
		}
		else{
			arrows[0] = false;
			arrows[1] = false;
		}
		if (_inputs[1] > 0){
			arrows[2] = bin(_inputs[1]);
			arrows[3] = false;
		}
		else if (_inputs[1] < 0){
			arrows[3] = bin(-_inputs[1]);
			arrows[2] = false;
		}
		else{
			arrows[2] = false;
			arrows[3] = false;
		}
		
		
		double acceleration = 0;
		
		if (arrows[0] && !arrows[1]){
			//accel
			double topSpeed = 0.0004;
			
			acceleration = (topSpeed-speed)*0.002;
			
		}
		else if (arrows[1] && !arrows[0]){
			//brake
			acceleration = -speed*0.002;
		}
		else {
			//freewheel
			if (speed>0){
				acceleration = -0.00000001;
			}
		}
		if (arrows[2] && !arrows[3]){
			//left
			direction += 0.0015*_deltaTime;
		}
		else if (arrows[3] && !arrows[2]){
			//right
			direction -= 0.0015*_deltaTime;
		}
		else{
			//continue straight
		}
		
		Point oldPos = pos;
		
		speed += acceleration*_deltaTime;
		pos.x += speed*_deltaTime*Math.cos(direction);
		pos.y += speed*_deltaTime*Math.sin(direction);
		
		distanceTravelled += Math.hypot(speed*_deltaTime*Math.cos(direction), speed*_deltaTime*Math.sin(direction));
		totSpeed += speed;
		totTime += _deltaTime;
		
		collisionDetect();
		checkPointCheck();
	}
	
	public static void calcLines(Color _color){
		
		double pi2 = Math.PI/2;
		Point head = new Point((pos.x+Math.cos(direction)*arrowHeight), (pos.y+Math.sin(direction)*arrowHeight));
		Point right= new Point((pos.x+Math.cos(direction-pi2)*arrowWidth), (pos.y+Math.sin(direction-pi2)*arrowWidth));
		Point left = new Point((pos.x+Math.cos(direction-pi2)*-arrowWidth), (pos.y+Math.sin(direction-pi2)*-arrowWidth));
		
		carLines[0] = new Line(head, right, _color);
		carLines[1] = new Line(head, left, _color);
		carLines[2] = new Line(left, right, _color);
	}
	
	public static Line[] getLines(){
		return carLines;
	}
	
	public static Line[] getEyeLines(){
		return eyeLines;
	}
	
	public static void calcEyes(){
		eyes = new double[5];
		eyeLines = new Line[5];
		
		double pi4 = Math.PI/4;
		double pi8 = Math.PI/8;
		
		//vision to be coded in here, hardcode values in for now
		double eye0 = 0;		//forward
		double eye1 = pi4;		//hard left
		double eye2 = -pi4;		//hard right
		double eye3 = pi8;		//slight left
		double eye4 = -pi8;		//slight right
		
		eyes[0] = rayCast(eye0);
		eyes[1] = rayCast(eye1);
		eyes[2] = rayCast(eye2);
		eyes[3] = rayCast(eye3);
		eyes[4] = rayCast(eye4);
		
		
		eyeLines [0] = new Line(pos, new Point(pos.x + eyes[0]*Math.cos(eye0+direction), pos.y + eyes[0]*Math.sin(eye0+direction)), StdDraw.PINK);
		eyeLines [1] = new Line(pos, new Point(pos.x + eyes[1]*Math.cos(eye1+direction), pos.y + eyes[1]*Math.sin(eye1+direction)), StdDraw.PINK);
		eyeLines [2] = new Line(pos, new Point(pos.x + eyes[2]*Math.cos(eye2+direction), pos.y + eyes[2]*Math.sin(eye2+direction)), StdDraw.PINK);
		eyeLines [3] = new Line(pos, new Point(pos.x + eyes[3]*Math.cos(eye3+direction), pos.y + eyes[3]*Math.sin(eye3+direction)), StdDraw.PINK);
		eyeLines [4] = new Line(pos, new Point(pos.x + eyes[4]*Math.cos(eye4+direction), pos.y + eyes[4]*Math.sin(eye4+direction)), StdDraw.PINK);
		
		//System.out.println("eye0 = "+eyes[0]);
	}
	
	private static double rayCast(double _angle){
		//get equation for raycast line:
		double rayM = Math.tan(direction+_angle);
		double rayC = pos.y-(rayM*pos.x);
		
		double rayLength = 10;
		Point rayHead = new Point(rayLength*Math.cos(direction+_angle), rayLength*Math.sin(direction+_angle));
		Point rayTail = pos;
		
		double dist = -1;
		
		for (int i = 0; i < trackLines.length; i++){
			//for each track line check the intersection point between the ray and the line
			Point head = trackLines[i].getPoints()[0];
			Point tail = trackLines[i].getPoints()[1];
			
			if (Line2D.linesIntersect(rayHead.x, rayHead.y, rayTail.x, rayTail.y, head.x, head.y, tail.x, tail.y)){
				Point intPoint = intersection(rayHead.x, rayHead.y, rayTail.x, rayTail.y, head.x, head.y, tail.x, tail.y);
				
				double thisDist = Math.hypot(intPoint.x-pos.x, intPoint.y-pos.y);
				
				if (thisDist >=0){
					if (dist == -1){
						//this is the first line to intersect
						dist = thisDist;
					}
					
					else if (thisDist < dist){
						dist = thisDist;
					}
				}
			}
		}
		
		if (dist == -1){
			dist = 9999;
		}
		
		return dist;
	}
	
	private static void collisionDetect(){
		//for each of the cars lines, check if it crosses through a track line...
		
		for (int i = 0; i < trackLines.length; i++){
			//for each track line
			Point head = trackLines[i].getPoints()[0];
			Point tail = trackLines[i].getPoints()[1];
			
			//check if each of the car lines intersect
			for (int j = 0; j < carLines.length; j++){
				Point headB = carLines[j].getPoints()[0];
				Point tailB = carLines[j].getPoints()[1];
				
				if (Line2D.linesIntersect(head.x, head.y, tail.x, tail.y, headB.x, headB.y, tailB.x, tailB.y)){
					alive = false;
					//System.out.println("Crash");
					//System.out.println(distanceTravelled);
				}
			}
		}
	}
	
	private static void checkPointCheck(){
		
		progress = false;
		
		for (int i = 0; i < checkPoints.length; i++){
			//for each track line
			Point head = checkPoints[i].getPoints()[0];
			Point tail = checkPoints[i].getPoints()[1];
			
			//check if each of the car lines intersect
			for (int j = 0; j < carLines.length; j++){
				Point headB = carLines[j].getPoints()[0];
				Point tailB = carLines[j].getPoints()[1];
				
				if (Line2D.linesIntersect(head.x, head.y, tail.x, tail.y, headB.x, headB.y, tailB.x, tailB.y)){
					//car has crossed a checkpoint:'
					if (lastCheckPoint == -1){
						lastCheckPoint = i;
						progress = true;
						checkPointsCrossed ++;
					}
					else if (i > lastCheckPoint){
						checkPointsCrossed ++;
						lastCheckPoint = i;
						progress = true;
					}
				}
			}
		}
		
		
		
		if (lastCheckPoint == (checkPoints.length-1)){
			alive = false;
			fitness = lastCheckPoint + (1/timeElapsed);
		}
		
		else{
			fitness = lastCheckPoint;
			
			Point[] checkLn = checkPoints[lastCheckPoint+1].getPoints();
			
			fitness += 0.0001/(Line2D.ptSegDist(checkLn[0].x, checkLn[0].y, checkLn[1].x, checkLn[1].y, pos.x, pos.y));
		}
		
	}
	
	public static Point intersection(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){
		double d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
		if (d == 0) return null;
  
		double xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
		double yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;
  
		return new Point(xi,yi);
	}
	
	private static boolean bin(double _in){
		if (_in >= 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static double getDist(){
		return distanceTravelled;
	}
	
	public static boolean getAlive(){
		return alive;
	}
	
	public static double getFitness(){
		return fitness;
	}
}