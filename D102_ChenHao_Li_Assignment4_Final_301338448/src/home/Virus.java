package home;

import static util.ImageLoader.loadImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
//Class for Virus files. Virus files grants clear condition in panel
public class Virus extends Filetype{
	//protected double xPos;
	//protected double yPos;
	//protected double scale;
	//protected String name;

	protected BufferedImage img;
	
	public Virus(double x, double y, double s, String n) {
		super(x,y,s,n);
		img = loadImage("assets/doc.png");

	}
	@Override
	public void draw(Graphics2D g2) {
		//drawButton(g2);
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);
		String st1 = name;
		Font f = new Font("Courier", Font.BOLD, 50);
		FontMetrics metrics = g2.getFontMetrics(f);
		g2.setFont(f);
		g2.setColor(Color.gray);
		g2.drawString(st1,
	                -metrics.stringWidth(st1)/2, 
	                img.getHeight()-80);

		g2.setTransform(transform);
	}
	@Override
	public boolean dragged(double x, double y){
		boolean dragged = false;
		
		if (x > (xPos - ((double) img.getWidth()) / 2 * scale) && x < (xPos + ((double) img.getWidth())/2*scale) && y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			dragged = true;
		
		return dragged;
	}
}
