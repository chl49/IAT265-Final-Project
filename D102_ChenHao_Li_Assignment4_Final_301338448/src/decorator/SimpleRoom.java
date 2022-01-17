package decorator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import util.ImageLoader;

import static main.OSPanel.*;
//Decorator for Blank Image
public class SimpleRoom implements Room {
	protected BufferedImage img;
	protected int state;
	protected long start, end, total, seconds;
	protected int newImg = 0;
	protected boolean switchup = false;

	public SimpleRoom(){
		//img = ImageLoader.loadImage("assets/start.png");
	}
	
	@Override
	public void showRoom(Graphics2D g2){
		//g2.setColor(new Color(0, 150, 180));
		//g2.fill(new Rectangle2D.Double(W_WIDTH/2-300, W_HEIGHT/2-200, 600, 400));
		//g2.scale(0.4, 0.4);
		//g2.drawImage(img, img.getWidth()/2, img.getHeight()/2, null);
	}
	@Override
	public void setRoomImg(int newState) {
		state=newState;
	}

}