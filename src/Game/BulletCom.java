package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Engine.Component;
import Engine.FlagMain;
import Engine.GameObject;
import Engine.InputHandler;
import Engine.ResourceNotFound;
import Game.GridCollider.Collision;

public class BulletCom extends Component {

	int direction,oX,oY;
	boolean death = false;
	public ArrayList<BufferedImage> mySPRT; 
	static boolean LeftEye= true;
	public double eyeOffset = 55;
	double speed =20;
	
	public BulletCom(GameObject object, int dir, String SPRT, AffineTransform PL) {
		super(object);
		try {
			mySPRT = FlagMain.assets.getImageList(SPRT);
		} catch (ResourceNotFound e) {
			e.printStackTrace();
		}
		
		parent.position = new AffineTransform(PL);
		parent.position.scale(.25, .25);
		parent.position.translate(40, -55);;
		switch(dir) {
		case 0:
			if(LeftEye)
				parent.position.translate(-1 * eyeOffset, 0);
			else
				parent.position.translate( eyeOffset, 0);
			break;
		case 1:
			/*if(LeftEye)
				parent.position.translate(0,-1 * eyeOffset);
			else
				parent.position.translate(0, eyeOffset);
			*/
			break;
		case 2:
			parent.position.translate(0, -80);
			if(LeftEye)
				parent.position.translate(eyeOffset, 0);
			else
				parent.position.translate( -1*eyeOffset, 0);
			
			break;
		case 3:
			/*if(LeftEye)
				parent.position.translate(0, eyeOffset);
			else
				parent.position.translate(0, -1* eyeOffset);
			
			break;*/
		}
		LeftEye = !LeftEye;
		
		direction = dir;
		this.Priority = 0;
		if(this.direction == 2)
			super.Priority = -1;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void logic() {
		// TODO Auto-generated method stub
		if(parent.position.getTranslateX() < 0 || parent.position.getTranslateY() < 0||
		   parent.position.getTranslateX() > Main.size*5 ||
		   parent.position.getTranslateY() > Main.size*3 )
			death = true;

		if(death) {
			Main.grid.elements.remove(this.parent);
			//GridCollider.colliders.remove(parent.getComponent(GridCollider.class));
			//Main.grid.setColor(parent.posX, parent.posY, Color.WHITE);
		}
		
		
		
		oX = parent.posX;
		oY = parent.posY;
		
		if(direction == 0) {
			parent.position.translate(0, speed);
		} 
		if(direction == 1) {
			parent.position.translate( speed,0);
		}
		if(direction == 2) {
			parent.position.translate(0,-1* speed);
		}
		if(direction == 3) {
			parent.position.translate(-1 * speed,0);
		}
		
		
		if (parent.posY > Main.size*2-1) {
			parent.posY = Main.size*2-1;
			death =true;
		}
		if (parent.posY < 0) {
			parent.posY = 0;
			death =true;
		}
		if (parent.posX > Main.size-1) {
			parent.posX = Main.size-1;
			death =true;
		}
		if (parent.posX < 0) {
			parent.posX = 0;
			death =true;
		}

		
		//((GridCollider)parent.getComponent(GridCollider.class)).logic();
		for(Collision i : GridCollider.collisions) {
			if(i.ob1.equals(parent)) {
				//System.out.println(i.ob1.name +  " collision with " + i.ob2.name);
				death =true;
			}
		}
		
		
		
		//System.out.println("posX " + parent.posX + " posY " + parent.posY);
	}

	@Override
	public void graphics( Graphics2D G ) {
		//if(!death) {
		
			G.drawImage(mySPRT.get(38), parent.position, null);
			//G.setColor(Color.BLUE);
			//G.fillRect(parent.posX*DebugScale, parent.posY*DebugScale, 5, 5);
		//}
		
	}
}
