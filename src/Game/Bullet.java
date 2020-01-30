package Game;

import java.awt.geom.AffineTransform;

import Engine.*;

public class Bullet extends GameObject{

	public int count =0;
	
	public Bullet(int dir, AffineTransform PL) {
		super();
		
		Component tempC = new BulletCom(this, dir,"boiS.png",PL);
		this.addLogicComponent(tempC);
		this.addGraphicsComponent(tempC);
		//this.addLogicComponent(new GridCollider(this));
		this.name = "Bullet";
	}
}