package art;

import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.geom.*;

import static main.OSPanel.*;
import static util.Util.*;
import processing.core.*;

//Fractal Art
public class Square {
	private float centX = W_WIDTH/2;
	private float centY = W_HEIGHT/2-80;
	
	public void drawSquare (Graphics2D g2, float x, float y, float d, float b) { //d - diameter
		AffineTransform at = g2.getTransform();
		g2.translate(centX, centY);
		//g2.draw(new Ellipse2D.Float(-d/2, -d/2, d, d ));
		g2.setColor(Color.magenta);
		g2.setStroke(new BasicStroke(1));
		g2.draw(new Rectangle2D.Float(-d/2, -d/2, d, d ));
		g2.setTransform(at);
		/*
		if (d  > 2) {
			 //d *= 0.75; //shrink d by 25% each recursion
			 d *= 0.75; //shrink d by 25% each recursion
			 drawCircle (g2, x, y, d );
		}
		*/
		if (d  < b) {
			 //d *= 0.75; //shrink d by 25% each recursion
			 d *= 1.4; //shrink d by 25% each recursion
			 drawSquare (g2, x, y, d, b);
		}
	}

//	public void drawCircle (Graphics2D g2, float x, float y, float d) { //d - diameter
//		AffineTransform at = g2.getTransform();
//		g2.translate(x, y);
//		g2.draw(new Ellipse2D.Float(-d/2, -d/2, d, d ));
//		g2.setTransform(at);
//		if (d  > 2) {
//			 d *= 0.5; //shrink d by 50% each recursion
//			 drawCircle (g2, x+d, y, d );
//			 drawCircle (g2, x-d, y, d );
//		}
//	}
	
//	public void drawCircle (Graphics2D g2, float x, float y, float d) { //d - diameter
//		AffineTransform at = g2.getTransform();
//		g2.translate(x, y);
//		g2.draw(new Ellipse2D.Float(-d/2, -d/2, d, d ));
//		g2.setTransform(at);
//		if (d  > 8) {
//			 d *= 0.5; //shrink d by 25% each recursion
//			 drawCircle (g2, x+d, y, d );
//			 drawCircle (g2, x-d, y, d );
//			 drawCircle (g2, x, y+d, d );
//			 drawCircle (g2, x, y-d, d );
//		}
//	}
}
