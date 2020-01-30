package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.GridCollider.Collision;
import Engine.*;

public class PlayerComp extends Component {

	int oX,oY,dir = 0;
	public ArrayList<BufferedImage> mySPRT;
	int LU=0;
	int SPRTindex =0,SPRTindex2 =0;
	boolean timingFlag = false,timingFlag2 = false;
	AffineTransform oldPos, headPos;
	double speed = 5;
	boolean standing;
	boolean Shooting;
	boolean ShootingLogicFlag = false;
	int faceDir =0;
	
	public PlayerComp(GameObject parent, String SPRT){
		super(parent);
		
		try {
			mySPRT = FlagMain.assets.getImageList(SPRT);
		} catch (ResourceNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parent.position = new AffineTransform();
		parent.position.setToIdentity();
		parent.position.scale(2, 2);
		parent.position.translate(400, 200);
		
		headPos = new AffineTransform(parent.position);
		headPos.translate(-10, -42);
		oldPos = new AffineTransform(parent.position);
		parent.posX = Main.size;
		oX = parent.posX;
		oY = parent.posY;
	}
	
	@Override
	public void logic() {
		//parent.position.setToIdentity();
		//System.out.println(Main.fraimes%2);
		boolean standingFlag = true;
		boolean shootingFlag = false;
		String state = "Stand";
		
		if(FlagMain.LCount%2 == 0) {
			for(Collision i : GridCollider.collisions) {
				if(i.ob1.equals(parent)) {
					//System.out.println(i.ob1.name +  " collision with " + i.ob2.name);
					parent.posX = oX;
					parent.posY = oY;
				}
			}
			
			oX = parent.posX;
			oY = parent.posY;
			
			AnimationHandler AH = (AnimationHandler) parent.getComponent(AnimationHandler.class);
			
			if (InputHandler.pending.contains(KeyEvent.VK_UP)) {
				faceDir =2;
				shootingFlag = true;
			}
			if (InputHandler.pending.contains(KeyEvent.VK_DOWN)) {
				faceDir =0;
				shootingFlag = true;
			}
			if (InputHandler.pending.contains(KeyEvent.VK_RIGHT)) {
				faceDir =1;
				shootingFlag = true;
			}
			if (InputHandler.pending.contains(KeyEvent.VK_LEFT)) {
				faceDir =3;
				shootingFlag = true;
			}
			if(!this.ShootingLogicFlag && timingFlag ) {
				if(shootingFlag ) {
					new Bullet(faceDir,parent.position);
				}
			}
			
			if(InputHandler.pending.contains(KeyEvent.VK_W)) {
				if(parent.position.getTranslateY() > 30) {
					parent.position.translate(0, -1 * speed);
					this.headPos.translate(0, -1 * speed);
					dir =0;
					standingFlag = false;
					state = "UpDown";
				}
			} 
			if(InputHandler.pending.contains(KeyEvent.VK_S)) {
				if(parent.position.getTranslateY() < (Main.size -40) * 3) {
					parent.position.translate(0, speed);
					this.headPos.translate(0, speed);
					dir =1;
					standingFlag = false;
					state = "UpDown";
				}
			}
			if(InputHandler.pending.contains(KeyEvent.VK_D)) {
				if(parent.position.getTranslateX() < (Main.size + 35 )* 5) {
					parent.position.translate(speed, 0);
					this.headPos.translate(speed, 0);
					dir =2;
					standingFlag = false;
					state = "Right";
				}
			}
			if(InputHandler.pending.contains(KeyEvent.VK_A)) {
				if(parent.position.getTranslateX() > 55) {
				parent.position.translate(-1 * speed, 0);
				this.headPos.translate(-1 * speed, 0);
				dir =3;
				standingFlag = false;
				state = "Left";
				}
			}
			
			
			AH.State = state;
			
			if (parent.posY > Main.size*2-1)
				parent.posY = Main.size*2-1;
			if (parent.posY <= 0)
				parent.posY = 0;
			if (parent.posX > Main.size-1)
				parent.posX = Main.size-1;
			if (parent.posX <= 0)
				parent.posX = 0;
			
			((GridCollider)parent.getComponent(GridCollider.class)).logic();
			
	
			for(Collision i : GridCollider.collisions) {
				if(i.ob1.equals(parent)) {
					//System.out.println(i.ob1.name +  " collision with " + i.ob2.name);
					parent.posX = oX;
					parent.posY = oY;
				}
			}
			
			if(InputHandler.pending.contains(KeyEvent.VK_SPACE)) {
				InputHandler.pending.remove(new Integer(KeyEvent.VK_SPACE));
			}
			
			if(InputHandler.pending.contains(KeyEvent.VK_P)) {
				//Main.paused = !Main.paused ;
				InputHandler.pending.remove(new Integer(KeyEvent.VK_P));
				
			}
			this.standing = standingFlag;
			this.Shooting = shootingFlag;
		}
				
		ShootingLogicFlag = timingFlag;
		//System.out.println("posX " + parent.posX + " posY " + parent.posY + " dir " + dir);
	}

	@Override
	public void graphics(Graphics2D G) {
		G.setColor(Color.RED);
		G.fillRect(parent.posX*DebugScale, parent.posY*DebugScale, 10, 10);
		//SPRTindex = 0;
		if (FlagMain.LCount%15  < 5 ) {
			this.timingFlag =true;
		}
		if (FlagMain.LCount%15 > 5 && this.timingFlag) {
			SPRTindex = (SPRTindex + 1)%2;
			this.timingFlag =false;
		}
		
		if(!this.Shooting) {
			SPRTindex = 0;
		}
		
		
		if (FlagMain.LCount % 5 < 3 ) {
			this.timingFlag2 =true;
		}
		if (FlagMain.LCount % 5 > 3 && this.timingFlag2) {
			SPRTindex2 = (SPRTindex2 + 1)%10;
			if(SPRTindex == 2) {
				
			}
			this.timingFlag2 =false;
		}
		if(this.standing) {
			SPRTindex2 = 0;
		}
		
		if(!mySPRT.isEmpty()) {
			
			switch (dir){
			case 0 :
				SPRTindex2 += 8; 
				
				G.drawImage(mySPRT.get(SPRTindex2),parent.position,null);
				SPRTindex2 -= 8;
				break;
			case 1 :
				SPRTindex2 += 8; 
				G.drawImage(mySPRT.get(SPRTindex2),parent.position,null);
				SPRTindex2 -= 8;
				break;
			case 2 :
				SPRTindex2 += 18; 
				G.drawImage(mySPRT.get(SPRTindex2),parent.position,null);
				SPRTindex2 -= 18;
				break;
			case 3 :
				SPRTindex2 += 28; 
				G.drawImage(mySPRT.get(SPRTindex2),parent.position,null);
				SPRTindex2 -= 28;
				break;
			}
			
			
			switch(this.faceDir) {
			case 0:
				G.drawImage(mySPRT.get(SPRTindex),this.headPos,null);
				break;
			case 1:
				G.drawImage(mySPRT.get(SPRTindex+2),this.headPos,null);
				break;
			case 2:
				G.drawImage(mySPRT.get(SPRTindex+4),this.headPos,null);
				break;
			case 3:
				G.drawImage(mySPRT.get(SPRTindex+6),this.headPos,null);
				break;
			}
		}
	}

	


}
