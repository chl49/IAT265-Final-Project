package home;

import java.awt.*;
import java.awt.image.BufferedImage;
//Abstract superclass for all interactable drawn objects
public abstract class Drawable {
	protected double xPos;
	protected double yPos;
	protected double scale;
	protected BufferedImage img;
	protected String st;
	public Drawable(double x, double y,  double s) {
		xPos = x;
		yPos = y;
		scale = s;
		//img = ImageLoader.loadImage("assets/shield.jpeg");
	}
	
	public abstract void draw(Graphics2D g2);

}
