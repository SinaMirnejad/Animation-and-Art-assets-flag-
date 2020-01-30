package Engine;


import java.awt.Graphics;
import java.awt.Graphics2D;

public class Component {
	

	public static int DebugScale = 5;
	
	public GameObject parent = null; 
	public boolean active = true;
	public int Priority = 0 ;
	
	
	public Component(GameObject object){
		parent = object;
	}
	
	public void graphics(Graphics2D G) {
		
	}
	
	public void logic() {
		
	}
}
