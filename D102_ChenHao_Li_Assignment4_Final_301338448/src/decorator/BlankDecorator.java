package decorator;

import java.awt.Graphics2D;

//Superclass more detailed decorators
public class BlankDecorator implements Room {
	
	protected Room baseRoom;
	
	public BlankDecorator(Room baseRoom) {
		this.baseRoom = baseRoom;
	}

	public void showRoom(Graphics2D g2) {
		baseRoom.showRoom(g2);
	}
	public void setRoomImg(int newState) {
		baseRoom.setRoomImg(newState);
	}
}
