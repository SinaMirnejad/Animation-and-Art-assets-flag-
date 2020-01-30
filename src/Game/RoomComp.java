package Game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Engine.*;

public class RoomComp extends Component{

	public ArrayList<BufferedImage> mySPRT;
	
	public RoomComp(GameObject object, String name ) {
		super(object);
		
		try {
			mySPRT = FlagMain.assets.getImageList(name);
		} catch (ResourceNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.Priority = -2;
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public void graphics(Graphics2D G) {
		G.drawImage(mySPRT.get(0), 0, 0, Main.size*6, Main.size*3, null);
	}

}
