package tp1.logic.gameobjects;

import tp1.exceptions.*;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.Position;

public abstract class GameObject implements GameItem{

	protected Position pos;
	protected boolean isAlive;
	protected GameWorld game;
	
	
	public GameObject(GameWorld game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}

	
	public boolean isInPosition(Position p) {
        return this.pos.equals(p);
	}
 	
	public boolean isAlive() {
		return isAlive;
	}
	
	public abstract GameObject parse(String line, GameWorld game) 
			throws ObjectParseException, OffBoardException;
	
	public boolean setRole(LemmingRole newRole) {return false;}
	
	public abstract String getIcon();

	public abstract void update();
	
	public boolean isSolid() {
		return false;
	}


	public void initializeObject(Game game2) {
		this.game = game2;
		
	}
	
}