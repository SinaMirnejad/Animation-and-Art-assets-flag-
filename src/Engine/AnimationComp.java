package Engine;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnimationComp {

	public int frameRate = 1;
	public List<BufferedImage> Image;
	
	private boolean flag = false;
	private int index;
	
	public HashMap<String,AnimationComp> transition = new HashMap<String,AnimationComp>() ;
	
	String name;
	
	public AnimationComp(String address,int indexOffset, int length, int frameRate,String Name) {
		name = Name;
		
		this.frameRate = FlagMain.runSpeed/ frameRate;
		try {
			Image = FlagMain.assets.getImageList(address).subList(indexOffset, indexOffset+length);
			
		} catch (ResourceNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public double getSizeX() {
		return Image.get(0).getWidth();
	}
	
	public double getSizeY() {
		return Image.get(0).getHeight();
	}
	
	public void setState(AnimationComp other) {
		transition.put(other.name, other);
	}
	

	
	public void SetRate(int rate) {
		this.frameRate = FlagMain.runSpeed/rate;
	}
	
	public AnimationComp getTransition(String State) {
		AnimationComp result = transition.get(State);
		
		if(result == null) {
			result = this;
		}
		
		return result;
	}
	
	public void graphics(Graphics2D G, AffineTransform position) {
		
		
		
		if(!this.flag && ((FlagMain.LCount % this.frameRate) <= this.frameRate - 2)) {
			this.flag = true;
		}else if(this.flag && ((FlagMain.LCount % this.frameRate) > this.frameRate - 2)) {
			index = (index+1)%Image.size();
			this.flag = false;
		}
		
		G.drawImage(this.Image.get(index), position, null);
		
	}

}
