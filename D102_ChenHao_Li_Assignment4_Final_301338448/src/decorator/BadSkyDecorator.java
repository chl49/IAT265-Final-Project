package decorator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import main.OSPanel;
import util.ImageLoader;

import static main.OSPanel.*;

//Decorator for Glitched Sky Image
public class BadSkyDecorator extends BlankDecorator {
	private BufferedImage img;
	private long start, end, total, seconds;
	private int newImg = 0;
	private boolean switchup = false;
	
	public BadSkyDecorator(Room baseRoom) {
		super(baseRoom);
		img = ImageLoader.loadImage("assets/brokensky1.png");
	}

	public void showRoom(Graphics2D g2) {
		super.showRoom(g2);
		end = System.nanoTime();
		total = end - start;
		seconds = TimeUnit.MILLISECONDS.convert(total, TimeUnit.NANOSECONDS);
		if(seconds>=200) {
			start=System.nanoTime();
			switchup=true;
			setRoomImg(newImg);
			newImg+=1;
			if(newImg>4) {
				newImg=0;
			}
		}
		addBack(g2);
	}
	
	public void setRoomImg(int newState) {
		super.setRoomImg(newState);
		switch (newState) {
		    case 0:
		    	img = ImageLoader.loadImage("assets/brokensky1.png");
		    	break;
		    case 1:
		    	img = ImageLoader.loadImage("assets/brokensky2.png");
		    	break;
		    case 2:
		    	img = ImageLoader.loadImage("assets/brokensky3.png");
		    	break;
		    case 3:
		    	img = ImageLoader.loadImage("assets/brokensky4.png");
		    	break;
		    case 4:
		    	img = ImageLoader.loadImage("assets/brokensky5.png");
		    	break;
		    
		}
	}

	private void addBack(Graphics2D g2) {
		//g2.setColor(new Color(200, 200, 0));
		AffineTransform at = g2.getTransform();
		//g2.draw(new Rectangle2D.Double(OSPanel.W_WIDTH/6, 0, 2*OSPanel.W_WIDTH/3, 3*OSPanel.W_HEIGHT/4 ));
		g2.translate(OSPanel.W_WIDTH/6, 0);
		g2.drawImage(img, 0, 0, null);
		//g2.fill(new Rectangle2D.Double(50, -100, 200, 100));
		//g2.setColor(Color.BLACK);
		//g2.draw(new Line2D.Double(60, -100, 70, 0));
		//g2.draw(new Line2D.Double(240, -100, 230, 0));
		g2.setTransform(at);
	}
}
