package decorator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.OSPanel;
import util.ImageLoader;

import static main.OSPanel.*;
//Decorator for Clear earth image
public class EarthDecorator extends BlankDecorator {
	private BufferedImage img;

	public EarthDecorator(Room baseRoom) {
		super(baseRoom);
		img = ImageLoader.loadImage("assets/neonearth.png");
	}

	public void showRoom(Graphics2D g2) {
		super.showRoom(g2);
		addBack(g2);
	}

	private void addBack(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(OSPanel.W_WIDTH/6, 0);
		g2.drawImage(img, 0, 0, null);
		g2.setTransform(at);
	}
}