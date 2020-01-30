package Engine;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class InputHandler implements MouseMotionListener,KeyListener{
	
	
	static public ArrayList<Integer> pressed = new ArrayList<Integer>() ;
	static public ArrayList<Integer> pending = new ArrayList<Integer>() ;
	static public int MX, MY;
	
	
	
	public InputHandler(JFrame g) {
		g.addKeyListener(this);
		g.addMouseMotionListener(this);
		//g.setFocusable(true);
	}
	

	public void Reset() {
		//Called once at the end of each logic cycle 
		ArrayList<Integer> temp = new ArrayList<Integer>(pending); 
		for(Integer i : pending) {
			if(!pressed.contains(i))
				temp.remove(i);
		}
		pending = temp;
		for(Integer i : pressed) {
			if(!pending.contains(i))
				pending.add(i);
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		if(!pending.contains(new Integer(e.getKeyCode()))) {
			pending.add( new Integer(e.getKeyCode()) );
		}
		if(!pressed.contains(new Integer(e.getKeyCode()))) {
			pressed.add( new Integer(e.getKeyCode()) );
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(InputHandler.pressed.contains(e.getKeyCode()))
			pressed.remove(new Integer( e.getKeyCode()));
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println(e.getX() + "   " + e.getY());
		MX = e.getX();
		MY = e.getY();
	}	
	
}
