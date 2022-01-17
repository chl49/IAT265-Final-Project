package home;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontMetrics;

import main.OSPanel;
import util.ImageLoader;
//Drawing for intro screen
public class HomeScreen {
	private BufferedImage img;
	private String st;
	private int state;

	public HomeScreen() {
		img = ImageLoader.loadImage("assets/copeland.png");
		st = "";
		state=0;
	}

	public void drawHome(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(0, 0);
		g2.scale(1.0, 1.0);
		g2.drawImage(img, 0, 0, OSPanel.W_WIDTH, OSPanel.W_HEIGHT, null);
		Font f = new Font("Courier", Font.BOLD, 50);
		FontMetrics metrics = g2.getFontMetrics(f);
		g2.setFont(f);
		if(state==1) {
			g2.setColor(Color.red);
		}
		else {
			g2.setColor(Color.cyan);
		}
		g2.drawString(st,
				OSPanel.W_WIDTH/2-metrics.stringWidth(st)/2, 
	                50);
		g2.setTransform(at);
	}
	
	public void setHomeImg(int newState) {
		if (newState == 0) {
			st = "";
			img = ImageLoader.loadImage("assets/copeland.png");   //opened oven
			state=newState;
		}  
		else if (newState == 1) {
			st = "Infected";
			img = ImageLoader.loadImage("assets/InfectedHomeScreen.png");
			state=newState;
		}//closed oven
		else if (newState == 2) {
			st = "Safe";
			img = ImageLoader.loadImage("assets/HomeScreen.png");   //opened oven
			state=newState;
		}

	}

}
