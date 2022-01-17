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
//Decorator for Clean Sky Image
public class SkyDecorator extends BlankDecorator {
	private BufferedImage img;
	
	public SkyDecorator(Room baseRoom) {
		super(baseRoom);
		img = ImageLoader.loadImage("assets/neonsky.png");
	}

	public void showRoom(Graphics2D g2) {
		super.showRoom(g2);
		addSky(g2);
	}

	private void addSky(Graphics2D g2) {
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
