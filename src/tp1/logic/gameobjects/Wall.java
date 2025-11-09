package tp1.logic.gameobjects;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.*;
import tp1.view.Messages;

public class Wall extends GameObject{ 

	private static final String NAME = "WALL";
	private static final String SHORTCUT = "W";	
	
	private GameObjectContainer cont;
		
	public Wall (GameWorld game, Position pos){
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
            throw new ObjectParseException("Invalid position format for Wall: " + line); 
        } 
        coordinates = coordinates.substring(1, coordinates.length() - 1); 
        String[] coords = coordinates.split(","); // ["3", "2"]
        if (coords.length != 2) {
            throw new ObjectParseException("Invalid position format for Wall: " + line); 
        }

        try {
            int row = Integer.parseInt(coords[0].trim());
            int col = Integer.parseInt(coords[1].trim());
            Position position = new Position(col, row); //recordar que el constructor esta al reves
            
            if (!game.isValidPosition(position)) {
                throw new OffBoardException("Object position is off board: '" + line + "'");
            }
            
            return new Wall(game, position);

        } catch (NumberFormatException e) {
            throw new ObjectParseException("Invalid numeric values in position for Wall: " + line, e);
        } 
   
  	}
	

    @Override
    public String getIcon() {
        return Messages.WALL; // Representación de la pared
    }
    
    @Override
    public void update() {
    }
    
    @Override
    public boolean isSolid() {
    	return true;
    }

    @Override
    public boolean interactWith(Wall wall){
        // Los muros no interactúan con otros muros, por lo que devolveremos false.
        return false;
    }

    @Override
    public boolean interactWith(ExitDoor door) {
        // Definir la lógica de interacción con una puerta si es necesario
        return false;
    }

    @Override
    public boolean interactWith(Lemming lemming) {
    	
    	
    	if (lemming.isInPosition(this.pos)) {
           
    		this.isAlive = false;
    		return true;
        }
        return false; // No hubo interacción
    
    }
    
    public boolean receiveInteraction(GameItem other) {
        return other.interactWith(this); 
    }
    
    
    public void killWall(){
    	
    	this.isAlive = false;
    }
    
    public Position getPos() {
    	Position pos = new Position(this.pos.getCol(),this.pos.getRow());
    	return pos;
    }
    
    //Para el fichero de la salida del comando save
    @Override
    public String toString() {
        return String.format("(%d,%d) Wall", 
            pos.getRow(), 
            pos.getCol()
        );
    }
    
    
    
}