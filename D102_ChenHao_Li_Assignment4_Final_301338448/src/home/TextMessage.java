package home;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.OSPanel;
import util.ImageLoader;

public class TextMessage {
	private double xPos;
	private double yPos;
	private double scale;
	private BufferedImage img;
	private String st;
	private int state;

	public TextMessage(double x, double y,  double s) {
		st = "Objective: Find and Remove Virus. Click start button";
		state=0;
	}

	public void drawText(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(0, 0);
		g2.scale(1.0, 1.0);
		Font f = new Font("Courier", Font.BOLD, 15);
		FontMetrics metrics = g2.getFontMetrics(f);
		g2.setFont(f);
		g2.setColor(Color.green);
		g2.drawString(st,
				OSPanel.W_WIDTH/2-metrics.stringWidth(st)/2, 
	                600);
		g2.setTransform(at);
	}
	
	public void setState(int newState) {
		if (newState == 0) {
			st = "Objective: Find and Remove Virus. Click start button";
			state=newState;
		}//closed oven
		else if (newState == 1) {
			st = "Click on RemoveSoft";
			state=newState;
		}
		else if (newState == 2) {
			st = "Complete! Click on End to see End Screen!";
			state=newState;
		}
		else if (newState == 5) {
			st = "Find Virus and Drag to Trash";
			state=newState;
		}
		else if (newState == 4) {
			st = "Wait For Virus Scan";
			state=newState;
		}
		else if (newState == -1) {
			st = "End! Click start button to restart";
			state=newState;
		}
		

	}

}
