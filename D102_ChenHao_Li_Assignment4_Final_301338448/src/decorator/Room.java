package decorator;

import java.awt.Graphics2D;
import static main.OSPanel.*;
//Interface for decorators

public interface Room {
	public void showRoom(Graphics2D g2);
	public void setRoomImg(int newState);
}
