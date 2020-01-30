package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import Engine.*;

public class Obstacles extends Component{


	String name;
	Random rand = new Random();
	public Obstacles(GameObject object) {
		super(object);
		
		this.Priority = 0;
		parent.posY = rand.nextInt(Main.size*2-2) +1 ;
		parent.posX = rand.nextInt(Main.size-2) +1;
		//System.out.println(parent.posX + " " + parent.posY );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void graphics(Graphics2D G) {
		G.setColor(Color.GREEN);
		G.fillRect(parent.posX*DebugScale, parent.posY* DebugScale, 10, 10);
	}
	
}
