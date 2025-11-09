package tp1.logic.lemmingRoles;


import tp1.logic.Position;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;

public interface LemmingRole {
    void start(Lemming lemming);
    void play(Lemming lemming);
    String getIcon(Lemming lemming);
	String getName();
    String getHelp();
    
    
    boolean receiveInteraction(GameItem other, Lemming owner);
    boolean interactWith(Lemming receiver, Lemming owner);
    boolean interactWith(Wall wall, Lemming owner);
    boolean interactWith(ExitDoor door, Lemming owner);
    
	void handleFall(Lemming lemming);
	
    boolean canParse(String input);
	LemmingRole createInstance(Position position);
	
	String getRoleType();
	String helpText();
	
	public boolean igual(Object obj);
}

