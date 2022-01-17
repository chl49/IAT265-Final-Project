package home;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontMetrics;

import util.ImageLoader;

//Button for drawing RemoveSoft App icon. 
public class RemoveButton extends Button {
	//private double xPos;
	//private double yPos;
	//private double scale;
	//private BufferedImage img;
	private boolean lightOn = false;

	// constructor
	public RemoveButton(double x, double y,  double s) {
		super(x,y,s);
		img = ImageLoader.loadImage("assets/shield.png");
	}
 
	
	@Override
	public void draw(Graphics2D g2) {
		//drawButton(g2);
		AffineTransform transform = g2.getTransform(); // save(x~y)
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		String st1 = "RemoveSoft";
		Font f = new Font("Courier", Font.BOLD, 50);
		FontMetrics metrics = g2.getFontMetrics(f);
		g2.setFont(f);
		g2.setColor(Color.gray);
		g2.drawString(st1,
	                -metrics.stringWidth(st1)/2, 
	                img.getHeight()-100);
		g2.setTransform(transform);
	}
}