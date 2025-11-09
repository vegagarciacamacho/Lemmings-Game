package tp1.logic.lemmingRoles;

import tp1.logic.Direction; 
import tp1.logic.Position;
import tp1.logic.gameobjects.*;
import tp1.view.Messages; 

public class WalkerRole extends AbstractRole {
	
	private static final String NAME = Messages.WALKER_ROL_NAME;
	private static final String SHORTCUT = "W";
	private static final String HELP = Messages.WALKER_ROL_HELP;
	

	@Override
	public void start(Lemming lemming) {
		
	}
	
	@Override
    public boolean canParse(String input) {
        return input.equalsIgnoreCase(NAME) || input.equalsIgnoreCase(SHORTCUT);
    } 
	
	
	@Override
    public LemmingRole createInstance(Position position) {
        return new WalkerRole(); // Crea una nueva instancia con la posición dada
    }
	  
	//Va manejando al lemming
	@Override
	public void play(Lemming lemming) {
		lemming.walkOrFall();
	}
	
    @Override
	//Revisa la caida del lemming, y si es > MAX_FALL, el lemming muere
	public void handleFall(Lemming lemming) {
		int MAX_FALL = 2;
        if (lemming.getFallDistance() > MAX_FALL) {
            lemming.die(); 
        } 
	}
	

	public String getIcon(Lemming lemming) {
  		if ( lemming.getDirection()== Direction.RIGHT)
  			return Messages.LEMMING_RIGHT; 
  		else if (lemming.getDirection() == Direction.LEFT)
  			return Messages.LEMMING_LEFT;
  		else if (lemming.getDirection()== Direction.DOWN) {
  			if(lemming.getPreviousDirection() == Direction.LEFT) {
  				return Messages.LEMMING_LEFT;
  			}
  			else if(lemming.getPreviousDirection()  == Direction.RIGHT) {
  				return Messages.LEMMING_RIGHT;
  			}
  		}
  		return Messages.EMPTY; 
  	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getHelp() {
		return HELP;
	}

	@Override
	public String getRoleType() {
		return "WalkerRole";
	}
	
	
	@Override
	public String toString() {
		return getName();
	}

	@Override
	public String helpText() {
		return HELP;
	}	
	
	
}
