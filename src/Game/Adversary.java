package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Engine.GameObject;
import Game.GridCollider.Collision;


public class Adversary extends Obstacles{
	
	boolean death = false;
	
	public Adversary(GameObject object) {
		super(object);
		parent.posX = 0;
		parent.posY = Main.size *2 -1;
	}
	
	@Override
	public void logic() {
		for(Collision i : GridCollider.collisions) {
			if(i.ob1.equals(parent) && i.ob2.name.equals("Bullet")) {
				death =true;
			}
		}
		
		if(death) {
			Main.elements.remove(parent);
			GridCollider.colliders.remove(parent.getComponent(GridCollider.class));
			//Main.grid.setColor(parent.posX, parent.posY, Color.WHITE);
		}
	}
	
	@Override
	public void graphics(Graphics2D G) {
		G.setColor(Color.BLACK);
		G.fillRect(parent.posX*DebugScale, parent.posY*DebugScale, 10, 10);
	}
}
