package home;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.geom.*;
import java.awt.Font;
import java.awt.FontMetrics;

import main.OSPanel;
import util.ImageLoader;

public class RemoveWindow extends Window{
	//Class for OS Window. Draws borders on different channels and taskbars
	public RemoveWindow(double x, double y,  double s) {
		super(x,y,s);
		//img = ImageLoader.loadImage("assets/shield.jpeg");
		st = "";
		state=0;
	}
	@Override
	public void draw(Graphics2D g2) {
		//drawWindow(g2);
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(1.0, 1.0);
		//g2.setColor(Color.black);
		//g2.fill(new Rectangle2D.Double(-OSPanel.W_WIDTH/3, -OSPanel.W_HEIGHT/2, 2*OSPanel.W_WIDTH/3, 3*OSPanel.W_HEIGHT/4 ));
		g2.setColor(Color.cyan);
		//g2.strokeWidth(Color.cyan);
		g2.setStroke(new BasicStroke(10));
		//g2.draw(new Rectangle2D.Double(-OSPanel.W_WIDTH/3, -OSPanel.W_HEIGHT/2, 2*OSPanel.W_WIDTH/3, 3*OSPanel.W_HEIGHT/4 ));
		g2.draw(new Rectangle2D.Double(OSPanel.W_WIDTH/6, 0, 2*OSPanel.W_WIDTH/3, 3*OSPanel.W_HEIGHT/4 ));
		g2.draw(new Rectangle2D.Double(0, 0, OSPanel.W_WIDTH/6, 3*OSPanel.W_HEIGHT/4 ));
		g2.draw(new Rectangle2D.Double(5*OSPanel.W_WIDTH/6, 0, OSPanel.W_WIDTH/6, 3*OSPanel.W_HEIGHT/4 ));
		g2.draw(new Rectangle2D.Double(0, 3*OSPanel.W_HEIGHT/4 , OSPanel.W_WIDTH, OSPanel.W_HEIGHT/4 ));
		//g2.drawImage(img, 0, 0, OSPanel.W_WIDTH, OSPanel.W_HEIGHT, null);
		
		Font f = new Font("Courier", Font.BOLD, 30);
		FontMetrics metrics = g2.getFontMetrics(f);
		g2.setFont(f);
		if(state==-1) {
			g2.setColor(Color.red);
		}
		else {
			g2.setColor(Color.cyan);
		}
		g2.drawString(st,
				OSPanel.W_WIDTH/2-metrics.stringWidth(st)/2, 
				OSPanel.W_HEIGHT/2+150);
	                
		g2.setTransform(at);
	}
	
	
	@Override
	public void setState(int newState) {
		if (newState == 0) {
			st = "";
			state=newState;
		}//closed oven
		else if (newState == -1) {
			st = "Loading...   Parsing Files for Virus";
			//img = ImageLoader.loadImage("assets/InfectedHomeScreen.png");
			state=newState;
		}//closed oven
		else if (newState == 1) {
			st = "Completed.  Virus found at File DOOM";
			//img = ImageLoader.loadImage("assets/HomeScreen.png");   //opened oven
			state=newState;
		}
	}

}