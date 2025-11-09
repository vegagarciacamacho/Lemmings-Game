package tp1.logic.lemmingRoles;

import tp1.logic.Position;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class DownCaverRole extends AbstractRole {
	
	private static final String NAME = "DownCaver";
	private static final String SHORTCUT = "DC";
	private static final String HELP = "[D]own [C]aver: Lemming caves downwards";
	
	private boolean hasCaved;

    @Override
    public void start(Lemming lemming) {
        if(lemming.isInAir()) {
        	lemming.disableRole();
        }
    }    
    
    @Override
    public void play(Lemming lemming) {

    	if(!lemming.isInAir() && hasCaved){
    		lemming.fall();
    		lemming.changeFall(0);// Si la pared es dura, vuelve a ser WalkerRole
            lemming.move(lemming.getDirection());
    		hasCaved = false;
    	}
    	else { 
    		lemming.disableRole();
    		lemming.update();
    	}
     
    }

    @Override
    public String getIcon(Lemming lemming) {
        return Messages.LEMMING_DOWN_CAVER; // Icono de minero
    }
    
    
    @Override
    public String getRoleType() {
    	return "DownCaver";
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
    public boolean canParse(String input) {
        return input.equalsIgnoreCase(NAME) || input.equalsIgnoreCase(SHORTCUT);
    }
	
	@Override
    public LemmingRole createInstance(Position position) {
        return new DownCaverRole(); 
    }

	@Override
	public boolean interactWith(Wall wall, Lemming lemming) {
		
		if(lemming.isInPosition(wall.getPos().up())) {
			wall.killWall();
			hasCaved = true;
			return true;
		}
		
		return false;
		
	}

	@Override
	public String helpText() {
		return HELP;
	}
	
	
	
}
