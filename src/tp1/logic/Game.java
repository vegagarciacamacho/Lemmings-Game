package tp1.logic;

import tp1.logic.gameobjects.Lemming;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tp1.exceptions.*; 
import tp1.logic.gameobjects.Wall;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.lemmingRoles.*;
import tp1.view.Messages;
import tp1.logic.gameobjects.*;

public class Game implements GameWorld, GameStatus, GameModel{
     
	public static final int DIM_X = 10;   
	public static final int DIM_Y = 10;  

    private GameObjectContainer container;
    private GameConfiguration conf = FileGameConfiguration.NONE;
    private String filename;
	private int currentCycle;					
	private int numLemmings;
	private int remaining;
	private int lemmingsToWin;
	private int lemmingsDead;
	private int lemmingsExit;	
	
	private boolean finished;
	private int nivel;

	public Game() {
		initGame1();
	}
	
	/*
	//PLAY EXPECTED 1
	//3 lemmings termina player looses con 2 muertos y uno en exit door
	private void initGame1() {
		container = new GameObjectContainer();
		
		LemmingRole walkerRole = LemmingRoleFactory.parse("W"); 


		Lemming lemming1 = new Lemming(this, new Position(2, 3), container, walkerRole); 
		container.add(lemming1);
		Lemming lemming2 = new Lemming(this, new Position(0, 8), container, walkerRole);     
		container.add(lemming2);
		Lemming lemming3 = new Lemming(this, new Position(9, 0), container, walkerRole);    
		container.add(lemming3);
		

		container.add(new Wall(this,new Position(0,9)));
		container.add(new Wall(this,new Position(1,9)));
		container.add(new Wall(this,new Position(2,4)));
		container.add(new Wall(this,new Position(3,4)));
		container.add(new Wall(this,new Position(4,4)));
		container.add(new Wall(this,new Position(4,6)));
		container.add(new Wall(this,new Position(5,6)));
		container.add(new Wall(this,new Position(6,6)));
		container.add(new Wall(this,new Position(7,6)));
		container.add(new Wall(this,new Position(7,5)));
		container.add(new Wall(this,new Position(8,1)));
		container.add(new Wall(this,new Position(9,1)));
		container.add(new Wall(this,new Position(8,8)));
		container.add(new Wall(this,new Position(9,9)));
		container.add(new Wall(this,new Position(8,8)));
		container.add(new Wall(this,new Position(8,9)));

		ExitDoor exit = new ExitDoor(this, new Position(4,5), container);
		container.add(exit);
		//container.add(new ExitDoor(this,new Position(4,5)));

		numLemmings = 3;
		remaining = numLemmings;
		
		lemmingsToWin = 2;
	}
	
	//PLAY EXPECTED 2
	//empiezas con 4 palman dos sobreviven otros dos player wins
	private void initGame2() {
		container = new GameObjectContainer();
		
		LemmingRole walkerRole = LemmingRoleFactory.parse("W"); 
		LemmingRole parachuterRole = LemmingRoleFactory.parse("P"); 

		Lemming lemming1 = new Lemming(this, new Position(2, 3), container, walkerRole); 
		container.add(lemming1);
		Lemming lemming2 = new Lemming(this, new Position(0, 8), container, walkerRole);     
		container.add(lemming2);
		Lemming lemming3 = new Lemming(this, new Position(9, 0), container, walkerRole);    
		container.add(lemming3);
		Lemming lemming4 = new Lemming(this, new Position(3, 3), container, walkerRole);         
		container.add(lemming4);
		
		Lemming lemming5 = new Lemming(this, new Position (9, 4), container, walkerRole);  
	    container.add(lemming5);
	    

	    
		container.add(new Wall(this,new Position(0,9)));
		container.add(new Wall(this,new Position(1,9)));
		container.add(new Wall(this,new Position(2,4)));
		container.add(new Wall(this,new Position(3,4)));
		container.add(new Wall(this,new Position(4,4)));
		container.add(new Wall(this,new Position(4,6)));
		container.add(new Wall(this,new Position(5,6)));
		container.add(new Wall(this,new Position(6,6)));
		container.add(new Wall(this,new Position(7,6)));
		container.add(new Wall(this,new Position(7,5)));
		container.add(new Wall(this,new Position(8,1)));
		container.add(new Wall(this,new Position(9,1)));
		container.add(new Wall(this,new Position(8,8)));
		container.add(new Wall(this,new Position(9,9)));
		container.add(new Wall(this,new Position(8,8)));
		container.add(new Wall(this,new Position(8,9)));

		ExitDoor exit = new ExitDoor(this, new Position(4,5), container);
		container.add(exit);
		//container.add(new ExitDoor(this,new Position(4,5)));

		numLemmings = 5;
		remaining = numLemmings;
		lemmingsToWin = 2;
	}
	
	
	
	//NUESTRO
	//empiezas con 4 palman 0 player wins
	private void initGame3() {

		container = new GameObjectContainer();
		
		LemmingRole walkerRole = LemmingRoleFactory.parse("W"); 
		LemmingRole parachuterRole = LemmingRoleFactory.parse("P");
        
		Lemming lemming1 = new Lemming(this, new Position(1, 4), container, walkerRole); 
		container.add(lemming1);
		Lemming lemming2 = new Lemming(this, new Position(0, 5), container, walkerRole);     
		container.add(lemming2);
		Lemming lemming3 = new Lemming(this, new Position(9, 4), container, walkerRole);    
		container.add(lemming3);
		Lemming lemming4 = new Lemming(this, new Position(5, 1), container, parachuterRole);         
		container.add(lemming4);
		
		container.add(new Wall(this,new Position(1,5)));
		container.add(new Wall(this,new Position(2,5)));
		container.add(new Wall(this,new Position(1,7)));
		container.add(new Wall(this,new Position(3,5)));
		container.add(new Wall(this,new Position(2,7)));
		container.add(new Wall(this,new Position(3,7)));
		container.add(new Wall(this,new Position(0,7)));
		container.add(new Wall(this,new Position(3,6)));
		container.add(new Wall(this,new Position(4,6)));
		container.add(new Wall(this,new Position(9,5)));
		container.add(new Wall(this,new Position(4,9)));
		container.add(new Wall(this,new Position(7,7)));
		container.add(new Wall(this,new Position(6,7)));
		container.add(new Wall(this,new Position(5,7)));
		container.add(new Wall(this,new Position(8,7)));
		
		ExitDoor exit = new ExitDoor(this, new Position(6,6), container);
		container.add(exit);
		//container.add(new ExitDoor(this,new Position(6,6)));
		
		numLemmings = 4;
		lemmingsToWin = 3;
		remaining = numLemmings; 

	}
	*/
	
	private void initGame0() {
		
		LemmingRole walkerRole = new WalkerRole();

		container = new GameObjectContainer();

		numLemmings = 0;
		
		container.add(new Lemming(this, new Position(9,0), walkerRole));
		numLemmings++;

		
		container.add(new Lemming(this, new Position(2,3), walkerRole));
		numLemmings++;
		
		container.add(new ExitDoor(this,new Position(4,5)));


		container.add(new Lemming(this, new Position(0,8), walkerRole));
		numLemmings++;

		
		remaining = numLemmings;
		
		lemmingsToWin = 2;

		

		container.add(new Wall(this,new Position(2,4)));
		//container.add(new MetalWall(this,new Position(2,5)));
		//container.add(new MetalWall(this,new Position(3,6)));

		//container.add(new Wall(this,new Position(3,5)));

		container.add(new Wall(this,new Position(3,4)));

		container.add(new Wall(this,new Position(4,4)));
		
		container.add(new Wall(this,new Position(6,6)));

		container.add(new Wall(this,new Position(7,6)));
		
		container.add(new Wall(this,new Position(4,6)));

		container.add(new Wall(this,new Position(5,6)));
		
		container.add(new Wall(this,new Position(7,5)));

		container.add(new Wall(this,new Position(8,9)));
		container.add(new Wall(this,new Position(8,8)));
		container.add(new Wall(this,new Position(9,9)));

		container.add(new Wall(this,new Position(9,1)));

		container.add(new Wall(this,new Position(8,1)));		
		
		container.add(new Wall(this,new Position(0,9)));

		container.add(new Wall(this,new Position(1,9)));
		
		
		}
	
	
	private void initGame1(){
		
		LemmingRole walkerRole = new WalkerRole();
	
		container = new GameObjectContainer();

		numLemmings = 0;
		
		container.add(new Lemming(this, new Position(9,0), walkerRole));
		numLemmings++;

		
		container.add(new Lemming(this, new Position(2,3), walkerRole));
		numLemmings++;
		container.add(new ExitDoor(this,new Position(4,5)/*, container*/));


		container.add(new Lemming(this, new Position(0,8), walkerRole));
		numLemmings++;

		container.add(new Lemming(this, new Position(3,3), walkerRole));

		numLemmings++;
		
		remaining = numLemmings;
		
		lemmingsToWin = 2;

		

		container.add(new Wall(this,new Position(2,4)));
		

		container.add(new Wall(this,new Position(3,4)));

		container.add(new Wall(this,new Position(4,4)));
		
		container.add(new Wall(this,new Position(6,6)));

		container.add(new Wall(this,new Position(7,6)));
		
		container.add(new Wall(this,new Position(4,6)));

		container.add(new Wall(this,new Position(5,6)));
		
		container.add(new Wall(this,new Position(7,5)));

		container.add(new Wall(this,new Position(8,9)));
		container.add(new Wall(this,new Position(8,8)));
		container.add(new Wall(this,new Position(9,9)));

		container.add(new Wall(this,new Position(9,1)));

		container.add(new Wall(this,new Position(8,1)));		
		
		container.add(new Wall(this,new Position(0,9)));

		container.add(new Wall(this,new Position(1,9)));
		
		
		}

	
	private void initGame2(){

		container = new GameObjectContainer();
		
		LemmingRole walkerRole = new WalkerRole();
		LemmingRole parachuterRole = new ParachuterRole();
		
		
		numLemmings = 0;
		
		container.add(new Lemming(this, new Position(9,0), walkerRole));
		numLemmings++;
		
		container.add(new Lemming(this, new Position(2,3), walkerRole));
		numLemmings++;
		
		container.add(new ExitDoor(this,new Position(4,5)/*, container*/));


		container.add(new Lemming(this, new Position(0,8), walkerRole));
		numLemmings++;

		container.add(new Lemming(this, new Position(3,3), walkerRole));
		
		numLemmings++;
		
		container.add(new Lemming(this, new Position(6,0), walkerRole));
		numLemmings++;
		
		container.add(new Lemming(this, new Position(6,0), parachuterRole));
		numLemmings++;
		
		remaining = numLemmings;
		
		lemmingsToWin = 2;

		container.add(new Wall(this,new Position(2,4)));
		container.add(new MetalWall(this,new Position(3,6)));
		container.add(new Wall(this,new Position(3,5)));

		//container.add(new Wall(this,new Position(3,5)));

		container.add(new Wall(this,new Position(3,4)));

		container.add(new Wall(this,new Position(4,4)));
		
		container.add(new Wall(this,new Position(6,6)));

		container.add(new Wall(this,new Position(7,6)));
		
		container.add(new Wall(this,new Position(4,6)));

		container.add(new Wall(this,new Position(5,6)));
		
		container.add(new Wall(this,new Position(7,5)));

		container.add(new Wall(this,new Position(8,9)));
		container.add(new Wall(this,new Position(8,8)));
		container.add(new Wall(this,new Position(9,9)));

		container.add(new Wall(this,new Position(9,1)));

		container.add(new Wall(this,new Position(8,1)));		
		
		container.add(new Wall(this,new Position(0,9)));

		container.add(new Wall(this,new Position(1,9)));
			
			
			}
	//Gestiona los niveles
	public Game(int nLevel) {
		nivel = nLevel;
		if(nivel == 0) {
	    	initGame0();
	    } else if(nivel == 1) {
		    initGame1();  
	    } else if(nivel == 2){
	    	initGame2(); 
	    } else {
	    	initGame0();
	    }
	}	
	
	//Resetea el juego
	@Override
	public void reset() {

  	    currentCycle = 0;
        lemmingsDead = 0;
        lemmingsExit = 0;
        numLemmings = 0;
        remaining = 0;
        finished = false;

		
	    // Si no hay configuración inicial cargada desde un archivo, usar inicialización estándar.
	    if (conf == FileGameConfiguration.NONE) {
	      
	        if (nivel == 0) {
	            initGame0();
	        } else if (nivel == 1) {
	            initGame1();
	        } else if (nivel == 2) {
	            initGame2();
	        } else {
	            initGame0();
	        }
	    } else {
	    	
	    	
			//container.clear();

	        currentCycle = conf.getCycle();
	        numLemmings = conf.numLemmingsInBoard();
	        lemmingsDead = conf.numLemmingsDead();
	        lemmingsExit = conf.numLemingsExit();
	        lemmingsToWin = conf.numLemmingToWin();
	        remaining = numLemmings;
	        
	        container = conf.getGameObjects(); 
	        
	       // container.initialize(this);

	        
	        }
	}

	@Override 
	//Carga el juego desde un fichero
	public void load(String fileName) throws GameLoadException {
		try {
			conf = new FileGameConfiguration(fileName, this);
			this.filename = fileName;
		
		} catch(GameLoadException e){
			throw new GameLoadException(e.getMessage()); 
		}
	    
	}
	
	@Override
	public void save(String fileName) throws GameLoadException{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt"))) {
	        writer.write(String.format("%d %d %d %d %d%n", 
	            this.currentCycle,                      
	            this.numLemmings,      
	            this.lemmingsDead,        
	            this.lemmingsExit,     
	            this.lemmingsToWin    
	        ));
	        
	        container.write(writer); 
	    } catch (IOException e) {
	        throw new GameLoadException(e.getMessage());
	    }
	}

	
	//Actualiza el juego
	@Override
	public void update() {
		 container.update(); 
	}
	
	//Añade un lemming
	@Override
	public void addLemming(Lemming lemming) {
	    container.add(lemming); 
	}
	
	//Devuelve el ciclo actual
	@Override
	public int getCycle() {
		return this.currentCycle;
	}
	
	//Aumenta el ciclo del juego
	@Override
	public void nextCycle() {
		this.currentCycle++; 
	}

	//Devuelve el numero de lemmings que siguen en la partida
	@Override
	public int numLemmingsInBoard() {
		return this.remaining;
	}

	//Devuelve el numero de lemmings muertos
	@Override 
	public int numLemmingsDead() {
		return this.lemmingsDead; 
	}
	
	//Aumenta el numero de lemmings muertos
	@Override
	public void incrementDeadLemmings() {
	    this.lemmingsDead++;  
	    this.remaining--; 
	}

    //Devuelve el numero de lemmings que ha salido
	@Override 
	public int numLemmingsExit() {
		return lemmingsExit; 
	}
	
	@Override
	public int numLemmings() {
		return numLemmings;
	}
	
	//Aumenta el numero de lemmings que salen por la puerta y resta uno al numero de lemmings que quedan en la partida
	@Override
	public void incrementLemmingsExit() {
		this.lemmingsExit++;
		this.remaining--;
	}
	
	@Override
	public void incrementNumLemmings() {
		this.numLemmings++;
		this.remaining++;
	}
	
    //Devuelve el numero de lemmings necesarios para ganar
	@Override
	public int numLemmingsToWin() {
		return this.lemmingsToWin;
	}
	
	//Llama a la funcion que devuelve los elementos como string
	@Override
	public String positionToString(int col, int row) { 
		Position pos = new Position(col,row);
		return container.positionToString(pos);
	}
	
    //Devuelve true si el jugador ha ganado
	@Override
	public boolean playerWins() {
		return numLemmingsExit() >= numLemmingsToWin() && remaining == 0; 
	}
	
    //Devuelve true si el jugador ha perdido
	@Override 
	public boolean playerLooses() {
		return remaining == 0 && numLemmingsExit() < numLemmingsToWin(); 
	}
	
	//Aumenta el numero de lemmings muertos
	@Override
	public void lemmingHasDied() {
        incrementDeadLemmings();  
    }
	
	//Devuelve true si el juego ha terminado
	@Override	
	public boolean isFinished() {
		return finished || playerWins() || playerLooses();
	}
	
	//Pone el estado del juego a -> terminado (usado por exit)
	@Override
	public void setFinished(boolean finished) {
        this.finished = finished;
    }
	
	@Override
	public boolean setRoleAt(Position position, LemmingRole role) throws OffBoardException {
	    if (!isValidPosition(position)) {
	        throw new OffBoardException("Position (" + position.getRow()
            + "," + position.getCol() + ") is off board" + Messages.LINE_SEPARATOR);
	    }
	    return container.setRoleAtObject(position, role);
	}

	@Override
	public boolean isValidLevel(int level) {
		if(level >= 0 && level < 3) {
			return true;
		}
		return false;		
	}
	
	@Override
	public void setLevel(int level) {
		nivel = level;
	}


    public String help() {
		return "";
	}
    
	@Override 
	public boolean receiveInteractionsFrom(GameItem obj){
		return container.receiveInteractionsFrom(obj);
	}
    
	@Override
	public boolean isValidPosition(Position position) {
		if (position.getCol() >= Game.DIM_X || position.getRow() >= Game.DIM_Y || position.getCol() < 0 || position.getRow() < 0) {
			return false;
		}
		return true;
	}
    
	public boolean isSolidAt(Position pos_abajo, Lemming lemming) {
		return container.isSolidAt(pos_abajo, lemming);
		
	}
	
	@Override
	public boolean hasGameConfiguration() {
		if (conf == FileGameConfiguration.NONE) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public String fileName() {
		return this.filename;
	}

}