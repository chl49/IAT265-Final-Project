package home;

import java.awt.image.BufferedImage;
import util.ImageLoader;
//Superclass for all buttons
public abstract class Button extends Drawable{
	public Button(double x, double y,  double s) {
		super(x,y,s);
		//img = ImageLoader.loadImage("assets/start.png");
	}
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		if (x > (xPos - ((double) img.getWidth()) / 2 * scale) && x < (xPos + ((double) img.getWidth())/2*scale) && y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
	}

}
