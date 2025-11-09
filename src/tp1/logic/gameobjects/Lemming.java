package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.RoleParseException;
import tp1.logic.*; 

import tp1.view.Messages;

import tp1.logic.lemmingRoles.*;

public class Lemming extends GameObject{
	
	private static final String NAME = "LEMMING";
	private static final String SHORTCUT = "L";
	
	private Direction dir;
	private Direction dir_anterior;
	private boolean isAlive;
	private LemmingRole rol;
	private int fallDistance;
	//private GameObjectContainer container;
	
    public Lemming(GameWorld game, Position pos, LemmingRole role){
    	super(game, pos);
        this.dir = Direction.RIGHT;
        this.dir_anterior = Direction.RIGHT;
        this.isAlive = true; // Inicialmente, el lemming está vivo
        this.fallDistance = 0;
        this.rol = role;
       // this.container =container;
	}
    
	public  GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException{
		
		String[] words = line.trim().split("\\s+");
		

	    if (!(words[1].equalsIgnoreCase(NAME) || words[1].equalsIgnoreCase(SHORTCUT))) {
	        return null; 
	    }

	    if (words.length != 5) {
	        throw new ObjectParseException("Incorrect parameter count for Lemming.");
	    }
	    
	    //nosotros obtenemos en los pasos 1 y 2 posicion y rol para poder crear un lemming de primeras
	    //luego ya vamos comprobando sus atributos direccion, falldistance 
	 
	    //Obtenemos la posicion
	    String coordinates = words[0];
        if (!(coordinates.startsWith("(") && coordinates.endsWith(")"))) {
            throw new ObjectParseException("Invalid position format for Lemming: " + line);
        }
        
        coordinates = coordinates.substring(1, coordinates.length() - 1); 
        String[] coords = coordinates.split(","); // ["3", "2"]
        if (coords.length != 2) {
            throw new ObjectParseException("Invalid position format for Lemming: " + line);
        }
        
        Position position;
        
        try {
        	int row = Integer.parseInt(coords[0].trim());
            int col = Integer.parseInt(coords[1].trim());
            position = new Position(col, row); 

        } catch (NumberFormatException e) {
            throw new ObjectParseException(Messages.INVALID_OBJECT_POSITION.formatted(line) + Messages.LINE_SEPARATOR);
        }
        
        if (!game.isValidPosition(position)) {
            throw new OffBoardException(Messages.OFF_BOARD.formatted(line) + Messages.LINE_SEPARATOR);
        }
       
        //Obtenemos el rol
        try {
			this.rol = LemmingRoleFactory.parse(words[4]); 
		} catch (RoleParseException e) {
			throw new ObjectParseException(Messages.INVALID_LEMMING_ROLE.formatted(line));
		} 
        
       // GameObjectContainer cont = this.container;
               
        Lemming lemming = new Lemming (game, position, rol);
        
        //Obtenemos la direccion
        if(words[2].equalsIgnoreCase("RIGHT")){
        	lemming.dir = Direction.RIGHT; 
        } else if (words[2].equalsIgnoreCase("LEFT")) {
        	lemming.dir = Direction.LEFT;

        } else if (words[2].equalsIgnoreCase("UP") || words[2].equalsIgnoreCase("DOWN") || words[2].equalsIgnoreCase("NONE")){
        	throw new ObjectParseException("Invalid lemming direction: \"%s\"".formatted(line) + Messages.LINE_SEPARATOR);
        } else {
        	throw new ObjectParseException("Unknown object direction: \"%s\"".formatted(line) + Messages.LINE_SEPARATOR);
        }
        
        //Obtenemos la caida
        lemming.fallDistance = Integer.parseInt(words[3]); //hace throws de la excepcion automaticamente si no se introduce un numero decimal
       /* if(lemming.fallDistance > 0) {
        	lemming.dir_anterior = lemming.dir;
        	lemming.dir = Direction.DOWN;
        }*/
        
        game.incrementNumLemmings();
        //una vez inicializados los atributos con exito
	    return lemming;
	}

    public void disableRole() {
    	this.rol = new WalkerRole();
    }

    //Actualizar el estado del juego
    @Override
	public void update() {		
	    if (isAlive) {
	      this.rol.play(this);     
	    }
	}    

    @Override
    public boolean setRole(LemmingRole newRole) {
    	if(newRole == null || newRole.igual(this.rol)) {
    		return false;
    	}
    	rol = newRole;
		rol.start(this);
		return true; 
    } 
    

  //Dibuja el lemming
    @Override
  	public String getIcon() {
    	return rol.getIcon(this);
  	}
    
    //Mata a un lemming
	public void die() {
        if (isAlive) {
            isAlive = false;
            game.lemmingHasDied();  
        }
    }
	
	//Devuelve cuantos ciclos lleva cayendo el lemming
	public int getFallDistance() {
		return fallDistance;
	}
	
	//Devuelve un booleano en funcion de si el lemming esta vivo
	public boolean isAlive() {
		return isAlive;
	}
	
	//Devuelve un booleano indicando si el lemming esta en el aire
	public boolean isInAir() {
		Position pos_abajo = new Position(pos.getCol(), pos.getRow() + 1);
	    return !game.isSolidAt(pos_abajo, this); 
	}
	
	//Devuelve un booleano indicando si el lemming tiene que cambiar de direccion (se va a chocar con una pared)
	public boolean isInWall() {
	    if (dir == Direction.RIGHT) {
	        Position pos_derech = new Position(pos.getCol() + 1, pos.getRow());
	        return (pos_derech.getCol() >= Game.DIM_X || game.isSolidAt(pos_derech, this));
	    } else if (dir == Direction.LEFT) { 
	        Position pos_izq = new Position(pos.getCol() - 1, pos.getRow());
	        return (pos_izq.getCol() < 0 || game.isSolidAt(pos_izq, this));
	    }
	    return false; // No hay pared si no se está moviendo
	}
	
	public boolean isInSoftFloor() {
		Position pos_debajo = new Position(pos.getCol(), pos.getRow() - 1);
		return(game.isSolidAt(pos_debajo, this));
	}
	

	
	public void fall(){
		fallDistance++;
        if (pos.getRow() + 1 >= Game.DIM_Y) { //comprobamos si la posición abajo está fuera del tablero para ver si hay que llamar a die()
            die();  
            return; 
        }	        
        
        if(dir != Direction.DOWN) {
        	dir_anterior = dir;
        }
        
        dir = Direction.DOWN;
	}
	//Establece la direccion en la que se tiene que mover el lemming
	public void walkOrFall() { 
		
		if (isInAir()) { //si esta en el aire 
			fall();
			
		}
		else if(dir == Direction.DOWN) { //si esta empezando a caer
			rol.handleFall(this);
			
			dir = dir_anterior;
			fallDistance = 0;
		}
		
		if (isInWall()) { //si se va a chocar con una pared
			dir_anterior = dir;
			dir = (dir == Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT;
			return;
        }
		
	    game.receiveInteractionsFrom(this); 

		pos = move(dir);  
		

	} 
	   
    //Mueve al lemming
	public Position move(Direction dir) {       
		int newCol = pos.getCol() + dir.getX(); 
        int newRow = pos.getRow() + dir.getY(); 
        
        if(newCol >= 0 && newCol < Game.DIM_X ) {
        	 pos = new Position(newCol, newRow); 
        }else {
        	if(dir == Direction.RIGHT) {
        		dir = Direction.LEFT;
        	}else if(dir == Direction.LEFT){
        		dir = Direction.RIGHT;
        	}
        }
        
        return pos; 
    }

	//Actualiza el estado del lemming y del juego si hay un nuevo lemming que sale por la puerta
	public void exit() {
	    if (this.isAlive) {
	        game.incrementLemmingsExit(); 
	        this.isAlive = false; 
	    }
    }
	
	//Verifica si el lemming esta en la posicion pos 
	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos);
	}
	
	public boolean receiveInteraction(GameItem other) {
	    return other.interactWith(this);
	}
	
	@Override
	public boolean interactWith(Wall wall) {
		return rol.interactWith(wall,this);
		
	}
	
	@Override
	public boolean interactWith(ExitDoor door) {
		if(this.pos.equals(door.pos)) { 
			exit();
        	return true;
    	}
    	return false;	}
	
	@Override
	public boolean interactWith(Lemming lemming) {
	   
	    return false;
	}
	
	public void increaseFallDistance() {
		this.fallDistance++;
	}
	
	public void changePreviousDir(Direction dir) {
		
		this.dir_anterior = dir;
	}
	
    public void changeDir(Direction dir) {
		
		this.dir = dir;
	}
	
	public Direction getDirection() {
		
		return this.dir;
	}
	
    public Direction getPreviousDirection() {
		
		return this.dir_anterior;
	}
    
     public void changeFall(int newFall) {
 		
 		this.fallDistance = newFall;
 	}
     
     //Para el fichero de salida del comando save
     @Override
     public String toString() {
    	 if(dir == Direction.DOWN) {
    		 dir = dir_anterior;
    	 }
         return String.format("(%d,%d) Lemming %s %d %s", 
             pos.getRow(),         
             pos.getCol(),         
             dir.toString(),       
             fallDistance,         
             rol.getName()        
         );
     }

}