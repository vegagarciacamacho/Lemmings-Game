package tp1.logic;

import tp1.logic.gameobjects.GameObject;
import tp1.exceptions.*;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.view.Messages;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileGameConfiguration implements GameConfiguration {
	
	public static final GameConfiguration NONE = new FileGameConfiguration();

    private FileGameConfiguration() {
        //No hace nada, representa una configuración vacía (NONE)
    }
	
	
    private int cycle;
    private int numLemmingsInBoard;
    private int numLemmingsDead;
    private int numLemmingsExit;
    private int numLemmingsToWin;
    private GameObjectContainer container;

    
    public FileGameConfiguration(String fileName, GameWorld game) throws GameLoadException {   
        container = new GameObjectContainer();
        loadConfiguration(fileName, game); 
    }

    private void loadConfiguration(String fileName, GameWorld game) throws GameLoadException {
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"))) {

            line = br.readLine();
            if (line == null) {
                throw new GameLoadException("El fichero de configuración está vacío.");
            } 
            parseGameStatus(line);

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; 
                }
                GameObject obj = GameObjectFactory.parse(line, game);
                if (obj != null) {
                    container.add(obj);
                } else {
                	throw new ObjectParseException(Messages.UNKNOWN_GAME_OBJECT.formatted((line))); 
                }
            }

        }catch(FileNotFoundException e) {
        	throw new GameLoadException(Messages.FILE_NOT_FOUND.formatted(fileName) + Messages.LINE_SEPARATOR);
        } catch(IOException e) {
            throw new GameLoadException(e.getMessage());
        }catch(OffBoardException e) {
        	throw new GameLoadException("Object position is off board: \"%s\"".formatted(line) + Messages.LINE_SEPARATOR);
        } catch (ObjectParseException e) { 
            throw new GameLoadException(e.getLocalizedMessage());
        }
    }

    private void parseGameStatus(String line) throws GameLoadException {
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 5) {
            throw new GameLoadException(Messages.INVALID_GAME_STATUS.formatted(line) + Messages.LINE_SEPARATOR);
        }
        try {
            cycle = Integer.parseInt(parts[0]);
            numLemmingsInBoard = Integer.parseInt(parts[1]);
            numLemmingsDead = Integer.parseInt(parts[2]);
            numLemmingsExit = Integer.parseInt(parts[3]);
            numLemmingsToWin = Integer.parseInt(parts[4]);
        } catch (NumberFormatException e) {
            throw new GameLoadException(e.getMessage());
        }
    }

    @Override
    public int getCycle() {
        return cycle;
    }

    @Override
    public int numLemmingsInBoard() {
        return numLemmingsInBoard;
    }

    @Override
    public int numLemmingsDead() {
        return numLemmingsDead;
    }

    @Override
	   public int numLemingsExit() {
        return numLemmingsExit;
    }

    @Override
    public int numLemmingToWin() {
    	return numLemmingsToWin;
    }

    @Override
    public GameObjectContainer getGameObjects() {
   	
        return container;
    }

}

