package home;

import static util.ImageLoader.loadImage;
//Abstract class for file classes that can be mouse dragged. Includes safe and virus files
public abstract class Filetype extends Button {
	protected String name;
	protected boolean hit;
	
	public Filetype(double x, double y, double s, String n) {
		super(x,y,s);
		name = n;
		hit=false;
	}
	
	public boolean hit(Trash oven) {
		boolean hit = false;

		if (Math.abs(xPos - oven.getXPos()) < 50 && Math.abs(yPos- oven.getYPos()) < 30)
			hit = true;
		
		return hit;
	}
	
	public void setPos(double x, double y){
		xPos = x;
		yPos = y;
	}
	
	public void setHit(boolean newHit){
		hit = newHit;
	}
	public boolean getHit(){
		return hit;
	}
	
	public abstract boolean dragged(double x, double y);
	
}
