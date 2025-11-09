package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException; 
import tp1.exceptions.OffBoardException;
import tp1.logic.*;
import tp1.view.Messages;

public class ExitDoor extends GameObject{
	
	private static final String NAME = "ExitDoor";
	private static final String SHORTCUT = "ED";
	
	private GameObjectContainer cont;  //en principio funciona sin que exitdoor dependa de GameObjectContainer pero
											//no lo quiero quitar del todo por si acaso
	
	public ExitDoor(GameWorld game, Position pos/*, GameObjectContainer container*/){
		super(game, pos);
		//cont = container;
	}

	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException{
		
		
		String[] words = line.trim().split("\\s+");
		
	    if (!(words[1].equalsIgnoreCase(NAME) || words[1].equalsIgnoreCase(SHORTCUT))) {
	        return null; //No corresponde a este tipo de objeto, puede corresponder a otro asi que no lanzamos excepcion
	        				//simplemente devolvemos null
	    }
	    
	    if (words.length != 2) { //hay un error en los parametros introducidos
	        throw new ObjectParseException("Incorrect parameter count for ExitDoor.");
	    }
	
	    String coordinates = words[0];
        if (!(coordinates.startsWith("(") && coordinates.endsWith(")"))) {
            throw new ObjectParseException("Invalid position format for ExitDoor: " + line);
        }
        coordinates = coordinates.substring(1, coordinates.length() - 1); 
        String[] coords = coordinates.split(","); // ["3", "2"]
        if (coords.length != 2) {
            throw new ObjectParseException("Invalid position format for ExitDoor: " + line);
        }

        try {
            int row = Integer.parseInt(coords[0].trim());
            int col = Integer.parseInt(coords[1].trim());
            Position position = new Position(col, row); //recordar que el constructor esta al reves
            
            if (!game.isValidPosition(position)) {
                throw new OffBoardException("Object position is off board: '" + line + "'");
            }
            
            return new ExitDoor(game, position); 
            

        } catch (NumberFormatException e) {
            throw new ObjectParseException("Invalid numeric values in position for ExitDoor: " + line, e);
        }
    }
	
    @Override
    public String getIcon() {
        return Messages.EXIT_DOOR; 
    }
    
    @Override
    public void update() {
    }
    
    @Override
    public boolean interactWith(Wall wall) {
        return false;
    }

    @Override
    public boolean interactWith(ExitDoor door) {
        // La puerta de salida no interactúa con otras puertas de salida
        return false;
    }

    @Override
    public boolean interactWith(Lemming lemming) {
    	if(this.pos.equals(lemming.pos)) { 
        	return true;
    	}
    	return false;
    }
    
    @Override
    public boolean receiveInteraction(GameItem other) {
        return other.interactWith(this); 
    }
    
    //Para el fichero de salida del comando save
    @Override
    public String toString() {
        return String.format("(%d,%d) ExitDoor", 
            pos.getRow(), 
            pos.getCol()
        );
    }

}
    
    
    
    

