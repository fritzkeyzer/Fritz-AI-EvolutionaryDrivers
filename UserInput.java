public class UserInput{
	
	private static boolean escJuanTap = false;
	private static boolean pJuanTap = false;
	private static boolean backspaceJuanTap = false;
	private static boolean enterJuanTap = false;
	
	
	
	
	public static boolean pressedEscape(){
		
		//returns true if is being pressed
		if (StdDraw.isKeyPressed(27) == true && escJuanTap == false){	//escape is pressed:
			escJuanTap = true;
			return true;
		}
		
		else if (StdDraw.isKeyPressed(27) == false && escJuanTap == true){	//reset oneshot:
			escJuanTap = false;
		}
		
		return false;
	}
	
	public static boolean pressedP(){
		
		//returns true if is being pressed
		if (StdDraw.isKeyPressed(80) == true && pJuanTap == false){	//P is pressed:
			pJuanTap = true;
			return true;
		}
		
		else if (StdDraw.isKeyPressed(80) == false && pJuanTap == true){	//reset oneshot:
			pJuanTap = false;
		}
		
		return false;
	}
	
	public static boolean pressedBackspace(){
		
		//returns true if is being pressed
		if (StdDraw.isKeyPressed(8) == true && backspaceJuanTap == false){	//backspace is pressed:
			//backspaceJuanTap = true;
			return true;
		}
		
		else if (StdDraw.isKeyPressed(8) == false && backspaceJuanTap == true){	//reset oneshot:
			backspaceJuanTap = false;
		}
		
		return false;
	}
	
	public static boolean pressedEnter(){
		
		//returns true if is being pressed
		if (StdDraw.isKeyPressed(10) == true && enterJuanTap == false){	//key is pressed:
			enterJuanTap = true;
			return true;
		}
		
		else if (StdDraw.isKeyPressed(10) == false && enterJuanTap == true){	//reset oneshot:
			enterJuanTap = false;
		}
		
		return false;
	}
	
	public static boolean mousePressed(){
		if (StdDraw.mousePressed()) return true;
		return false;
	}
	
	public static double mouseX(){
		return StdDraw.mouseX();
	}
	
	public static double mouseY(){
		return StdDraw.mouseY();
	}
	
	public static boolean hasNextKeyTyped(){
		if (StdDraw.hasNextKeyTyped()) return true;
		return false;
	}
	
	public static char nextKeyTyped(){
		return StdDraw.nextKeyTyped();
	}
	
	public static boolean w(){
		if (StdDraw.isKeyPressed(87)) return true;
		return false;
	}
	
	public static boolean a(){
		if (StdDraw.isKeyPressed(65)) return true;
		return false;
	}
	
	public static boolean s(){
		if (StdDraw.isKeyPressed(83)) return true;
		return false;
	}
	
	public static boolean d(){
		if (StdDraw.isKeyPressed(68)) return true;
		return false;
	}
	
}