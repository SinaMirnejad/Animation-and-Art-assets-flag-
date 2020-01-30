package Game;

import java.util.ArrayList;

import Engine.*;

public class GridCollider extends Component{
	
	public static ArrayList<GridCollider> colliders = new ArrayList<GridCollider>();
	public static ArrayList<Collision> collisions = new ArrayList<Collision>();
	
	
	public class Collision{
		GameObject ob1, ob2;
		
		public Collision(GameObject o1,GameObject o2) {
			ob1 = o1;
			ob2 = o2;
		}
		
		@Override
		public boolean equals(Object x){
			boolean EQ =true;
			
			if(x.getClass() != Collision.class)
				EQ = false;
			else if(!ob1.equals(((Collision)x).ob1) || !ob2.equals(((Collision)x).ob2) )
				EQ = false;
			return EQ;
		}
	}
	
	public GridCollider(GameObject parent) {
		super(parent);
		colliders.add(this);
		Priority = -1;
	}
	
	@Override
	public void logic() {
		for(GridCollider i : colliders) {
			if(!i.equals(this)) {
				if(i.parent.posX == parent.posX && i.parent.posY == parent.posY) {
					Collision temp = new Collision(this.parent,i.parent);
					if(!this.collisions.contains(temp)) 
						collisions.add(temp);
				}
			}
		}
	}
	
	
	public static void reset() {
		collisions= new ArrayList<Collision>();
	}
}
