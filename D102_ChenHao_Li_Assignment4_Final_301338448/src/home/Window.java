package home;
//SuperClass for window classes
public abstract class Window extends Drawable{
	protected int state;
	
	public Window(double x, double y,  double s) {
		super(x,y,s);
	}
	
	public int getState() {
		return state;
	}
	public abstract void setState(int newState);
	
	

}
