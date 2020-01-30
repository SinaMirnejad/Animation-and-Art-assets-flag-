package Engine;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class AnimationHandler extends Component {

	public AffineTransform Offset;
	AffineTransform Rot   = new AffineTransform(),
					Scale = new AffineTransform(),
					Trans = new AffineTransform();

	double SizeX,SizeY;


	public AnimationComp current;
	
	public String State;
	
	public AnimationHandler(GameObject object, AnimationComp init) {
		super(object);
		current = init;
		
		this.Offset = new AffineTransform();
		this.Offset.setToIdentity();
		
		this.Rot = new AffineTransform();
		this.Rot.setToIdentity();
		
		this.Scale = new AffineTransform();
		this.Scale.setToIdentity();
		
		this.Trans = new AffineTransform();
		this.Trans.setToIdentity();
		
		SizeX = current.getSizeX();
		SizeY = current.getSizeY();
	
		// TODO Auto-generated constructor stub
	}

	public void SetPosition(double dx, double dy) {
		Trans = AffineTransform.getTranslateInstance(dx, dy);
		this.Offset.concatenate(Trans);
		this.Offset.concatenate(Rot);
		this.Offset.concatenate(Scale);
	}
	
	public void SetRotation(Double R) {
		Rot = AffineTransform.getRotateInstance(R,SizeX*Scale.getScaleX()/2 ,SizeY*Scale.getScaleY()/2 );
		this.Offset.concatenate(Trans);
		this.Offset.concatenate(Rot);
		this.Offset.concatenate(Scale);
	}
	
	public void SetScale(double Sx, double Sy) {
		Scale = AffineTransform.getScaleInstance(Sx, Sy);
		this.Offset.concatenate(Trans);
		this.Offset.concatenate(Rot);
		this.Offset.concatenate(Scale);
	}
	
	
	@Override 
	public void logic(){
		current = current.getTransition(State);
	}
	
	
	@Override
	public void graphics(Graphics2D G){
		AffineTransform pos = new AffineTransform(parent.position);
		pos.concatenate(Offset);
		current.graphics(G,pos);
	}
}
