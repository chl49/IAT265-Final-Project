package art;

import java.awt.*;
import java.awt.geom.*;

import static main.OSPanel.*;
import static util.Util.*;
import processing.core.*;


//Perlin Noise Art
public class NoisySpiral {
	private float centX = W_WIDTH/2;
	private float centY = W_HEIGHT/2-80;
	private float radius = 10;
	private float x, y;
	//private float x = 0;
	//private float y = -50;
	private float lastx = 0;
	private float lasty = 0;
	//private float lasty = 0;
	private GeneralPath spPath = new GeneralPath();
	private float noiseSeed = random(10);
	private PApplet pa = new PApplet();
	private boolean off = false;


	public void drawSpiral(Graphics2D g2, float ang) {
			radius += 0.5;
			noiseSeed  += 0.05;
			float noiseRadius = radius + (pa.noise(noiseSeed) * 200) - 100;

		    float ra = radians(ang);

			AffineTransform at = g2.getTransform();
			g2.translate(centX, centY);
			if(off) {
				x=lastx;
				y=lasty;
			}
			else {
				x = noiseRadius * (float) Math.cos(ra);
				y = noiseRadius * (float) Math.sin(ra);
			}
			
			spPath.moveTo(x, y);
			spPath.lineTo(lastx, lasty);
			
			g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.cyan);
 			g2.draw(spPath);
			g2.setTransform(at);

			// store the ending location for next round drawing
			lastx = x;
			lasty = y;
			
			//System.out.println("noise(3)=" + pa.noise(3)+ " random=" + Math.random());
	}
	
	public void setSwitch(boolean boo) {
		off=boo;
	}
	
}