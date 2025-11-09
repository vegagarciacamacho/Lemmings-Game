package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameObjectContainer;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class MetalWall extends Wall {
	
	private static final String NAME = "MetalWall";
	private static final String SHORTCUT = "MW";
	
	private GameObjectContainer cont;
	

	public MetalWall (GameWorld game, Position pos){
		super(game, pos);
	}
	
	@Override
	public  GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException{
		
		String[] words = line.trim().split("\\s+");
		
	    if (!(words[1].equalsIgnoreCase(NAME)||words[1].equalsIgnoreCase(SHORTCUT))) {
	        return null; 
	    }

		if (words.length != 2) {
	        throw new ObjectParseException("Incorrect parameter count for Wall.");
	    }
	    
	    String coordinates = words[0];
        if (!(coordinates.startsWith("(") && coordinates.endsWith(")"))) {
            throw new ObjectParseException("Invalid position format for MetalWall: " + line);
        }
        
        coordinates = coordinates.substring(1, coordinates.length() - 1); 
        String[] coords = coordinates.split(","); // ["3", "2"]
        if (coords.length != 2) {
            throw new ObjectParseException("Invalid position format for MetalWall: " + line);
        }

        try {
            int row = Integer.parseInt(coords[0].trim());
            int col = Integer.parseInt(coords[1].trim());
            Position position = new Position(col, row); //recordar que el constructor esta al reves
            
            if (!game.isValidPosition(position)) {
                throw new OffBoardException("Object position is off board: '" + line + "'");
            }
                       

            return new MetalWall(game, position);

        } catch (NumberFormatException e) {
            throw new ObjectParseException("Invalid numeric values in position for MetalWall: " + line, e);
        }
        
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

    @Override
    public String getIcon() {
        return Messages.METALWALL; // Representación de la pared
    }

    @Override 
    public void update() {
    	
    }
    
    public boolean receiveInteraction(GameItem other) {
        return false; 
    }
	
    @Override
    public boolean interactWith(ExitDoor door) {
        return false;
    }
    @Override
    public boolean interactWith(Wall wall) {
        return false;
    }
    
    public boolean interactWith(Lemming lemming) {
        return false;
    }
	
	//Para el fichero de salida del comando save
    @Override
    public String toString() {
        return String.format("(%d,%d) MetalWall", 
            pos.getRow(), 
            pos.getCol()
        );
    }
	
}
