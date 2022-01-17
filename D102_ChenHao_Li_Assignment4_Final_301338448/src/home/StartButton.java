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

//draw the lamp 
public class StartButton extends Button  {
	private boolean lightOn = false;

	// constructor
	public StartButton(double x, double y,  double s) {
		super(x,y,s);
		img = ImageLoader.loadImage("assets/start.png");
	}

	@Override
	public void draw(Graphics2D g2) {
		//drawButton(g2);
		AffineTransform transform = g2.getTransform(); // save(x~y)
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		st="Start";
		Font f = new Font("Courier", Font.BOLD, 50);
		FontMetrics metrics = g2.getFontMetrics(f);
		g2.setFont(f);
		g2.setColor(Color.gray);
		g2.drawString(st,
	                -metrics.stringWidth(st)/2, 
	                img.getHeight()-100);
		g2.setTransform(transform);
	}
}
