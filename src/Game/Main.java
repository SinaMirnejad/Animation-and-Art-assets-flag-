package Game;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Engine.AnimationComp;
import Engine.AnimationHandler;
import Engine.Component;
import Engine.FlagMain;
import Engine.GameObject;
import Engine.InputHandler;
import Game.*;

public class Main {

	public static FlagMain grid;
	public static int size = 300;
	public static InputHandler IH;
	public static ArrayList<GameObject> elements = new ArrayList<GameObject>();
	public static int fraimes = 0;
	
	
	public static void main(String[] args)  {
		
		
		
		grid  = new FlagMain(size, 2, 3, "Rogue-Like2", "Assets\\");
		
		GameObject BG = new GameObject();		
		BG.addGraphicsComponent(new RoomComp(BG, "empt.png"));
		//player.addLogicComponent(new GridCollider(player));
		BG.name = "room";
		
		
		GameObject player = new GameObject();		
		Component tempC = new PlayerComp(player, "boiS.png");
		player.addLogicComponent(tempC);
		player.addGraphicsComponent(tempC);
		player.addLogicComponent(new GridCollider(player));
		
		AnimationComp UD = new AnimationComp("boiS.png",8,10,10,"UpDown"),
					  RI = new AnimationComp("boiS.png",18,10,10,"Right"),
					  LE = new AnimationComp("boiS.png",28,10,10,"Left"),
					  ST = new AnimationComp("boiS.png",8,1,10,"Stand");
		
		
		UD.setState(RI);
		UD.setState(LE);
		UD.setState(ST);
		LE.setState(UD);
		LE.setState(RI);
		LE.setState(ST);
		RI.setState(UD);
		RI.setState(LE);
		RI.setState(ST);
		ST.setState(UD);
		ST.setState(LE);
		ST.setState(RI);
		
		AnimationHandler AH = new AnimationHandler(player,UD);
		AH.SetPosition(50, 50);
		player.addLogicComponent(AH);
		player.addGraphicsComponent(AH);
		player.name = "player";
		
		
		// Add Comp here! -----------------------
		
		
		
		
		//------------------------------------------
		/*
		for(int i = 0; i < 250; i++ ) {
			GameObject temp = new GameObject();
			temp.addLogicComponent(new GridCollider(temp));
			tempC = new Obstacles(temp);
			temp.addLogicComponent(tempC);
			temp.addGraphicsComponent(tempC);
			temp.name = "obstacle " + i;
		}
		
		GameObject Advosary = new GameObject();
		tempC = new Adversary(Advosary);
		Advosary.addLogicComponent(tempC);
		Advosary.addGraphicsComponent(tempC);
		Advosary.addLogicComponent(new GridCollider(Advosary));
		Advosary.name = "Advosary";
		*/
		
		
		grid.start();
		
		
	}
	
	
}
