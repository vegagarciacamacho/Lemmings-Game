package tp1.control.commands;

import tp1.exceptions.*; 
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends Command{
	
	private int level = -1;

	private static final String NAME = "reset";
	private static final String SHORTCUT = "r";
	private static final String DETAILS = "[r]eset [numLevel]";
	private static final String HELP = "reset the game to initial configuration if not numLevel else load the numLevel map";

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}

	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException{
		if(game.hasGameConfiguration()) {
			String filename = game.fileName();
			try {
	        	game.load(filename);   
	        	game.reset();

	    	} catch(GameLoadException e) {
	    		throw new CommandExecuteException("Invalid file \"%s\" configuration".formatted(filename), e); 
	    	}
		} else {
			if(level == -1) {
				game.reset(); 
			} else if(game.isValidLevel(level)) {
				game.setLevel(level);
				game.reset();
			} else {
				throw new CommandExecuteException("Not valid level number" + Messages.LINE_SEPARATOR);
			}
		}
		
		view.showGame();
		
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			
            //Reset sin argumentos
            if (commandWords.length == 1) {
                level = -1;
                return this;
                
            //Reset con argumentos
            } else if (commandWords.length == 2) {
                try {
                    level = Integer.parseInt(commandWords[1]); // Parsear el nivel
                    return this;
                } catch (NumberFormatException e) {
                    throw new CommandParseException("[ERROR] Error: Invalid level format, must be a number");
                }
             
            } else {
                throw new CommandParseException("[ERROR] Error: Too many arguments for reset command");
            }
        }
        return null; //no coincide con este comando
    }
	
	}

