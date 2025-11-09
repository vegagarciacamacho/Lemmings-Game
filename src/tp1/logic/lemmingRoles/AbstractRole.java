package tp1.logic.lemmingRoles;


import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;

public abstract class AbstractRole implements LemmingRole {

    public boolean receiveInteraction(GameItem other, Lemming lemming) {
        return other.interactWith(lemming);     
    }

    public boolean interactWith(Lemming receiver, Lemming owner) {
        return false; 
    }

    public boolean interactWith(Wall wall, Lemming owner) {
        return false; 
    }

    public boolean interactWith(ExitDoor door, Lemming owner) {
        return false;
    }
 
    public abstract String helpText();
    
    @Override
	//Revisa la caida del lemming, y si es > MAX_FALL, el lemming muere
	public void handleFall(Lemming lemming) {
		int MAX_FALL = 2;
        if (lemming.getFallDistance() > MAX_FALL) {
            lemming.die(); 
        } 
	}
    
    @Override
    public String getRoleType() {
    	return "WalkerRole";
    }
    
    @Override
    public boolean igual(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false; // Diferente tipo
        LemmingRole role = (LemmingRole) obj;
        return this.getName().equals(role.getName()); 
    }

}

