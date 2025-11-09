package tp1.control.commands;

import tp1.exceptions.*;
import tp1.logic.GameModel;
import tp1.view.GameView;

public class LoadCommand extends Command{

    private static final String NAME = "load";  
    private static final String SHORTCUT = "l";   
    private static final String DETAILS = "[l]oad <fileName>";
    private static final String HELP = "load the game configuration from text file <fileName>";
    
    private String fileName;

    public LoadCommand() { 
        super(NAME, SHORTCUT, DETAILS, HELP);  
    }


    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
    	
    	try {
        	game.load(fileName);   
        	game.reset();

    	} catch(GameLoadException e) {
    		throw new CommandExecuteException("Invalid file \"%s\" configuration".formatted(fileName), e); 
    	}
    	
        view.showGame(); 
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
    	
        if (commandWords.length == 2 && matchCommandName(commandWords[0])) {
            fileName = commandWords[1];                //guarda el nombre del archivo para usarlo en execute
            return this;
            
        } else if (matchCommandName(commandWords[0])) {
            //no se proporciona el nombre del archivo, asiq lanza excepción
            throw new CommandParseException("No se ha proporcionado un nombre de archivo");
        }
        
        return null; //no se corresponde con este comando
    }
}

