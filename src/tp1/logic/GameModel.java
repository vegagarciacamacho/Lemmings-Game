package tp1.logic;

import tp1.logic.lemmingRoles.LemmingRole;
import tp1.exceptions.*;

public interface GameModel {
	
	void update();
	void reset();
	public void setFinished(boolean b);
	void nextCycle();
	boolean isFinished();
	void load(String fileName) throws GameLoadException;
	void save(String fileName) throws GameLoadException;
	boolean hasGameConfiguration();
	public String fileName();
	
    public boolean setRoleAt(Position position, LemmingRole role)
    		throws OffBoardException;
    
   public boolean isValidLevel(int level);
   public void setLevel(int level);
   
}
