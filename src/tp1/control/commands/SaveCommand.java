package tp1.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import tp1.exceptions.*;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameLoadException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class SaveCommand extends Command{
	
	 	private static final String NAME = "save";  
	    private static final String SHORTCUT = "s";   
	    private static final String DETAILS = "[s]ave <fileName>";
	    private static final String HELP = "save the actual configuration in text file <fileName>";
	    
	    private String fileName;

	    public SaveCommand() { 
	        super(NAME, SHORTCUT, DETAILS, HELP);  
	    }


	    @Override
	    public void execute(GameModel game, GameView view) throws CommandExecuteException  { 
	    	
	    	try {
	    		game.save(fileName);
	    	} catch (GameLoadException e) {
	            throw new CommandExecuteException ("Error while saving the game to the file: " + fileName, e);
	        }
	    	
	    	view.showMessage(Messages.FILE_SAVED.formatted(fileName) + Messages.LINE_SEPARATOR);
	    }

	    @Override
	    public Command parse(String[] commandWords) throws CommandParseException {
	    	
	        if (commandWords.length == 2 && matchCommandName(commandWords[0])) {
	            fileName = commandWords[1];                 //guarda el nombre del archivo para usarlo en execute
	            return this;
	            
	        } else if (matchCommandName(commandWords[0])) {
	            //no se proporciona el nombre del archivo, asiq lanza excepción
	            throw new CommandParseException("No se ha proporcionado un nombre de archivo");
	        }
	        
	        return null; //no se corresponde con este comando
	    }
}
