package tp1.logic.gameobjects;
import tp1.logic.Position;

public interface GameItem {
    boolean receiveInteraction(GameItem other);
    boolean interactWith(Wall wall);
    boolean interactWith(ExitDoor door);
	boolean interactWith(Lemming lemming);
	
	public boolean isSolid();
	public boolean isAlive();
	public boolean isInPosition(Position pos);
}
