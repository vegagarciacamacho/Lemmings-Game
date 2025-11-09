 package tp1.control.commands;

import tp1.logic.GameModel; 
import tp1.exceptions.*;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;

public class SetRoleCommand extends Command {

    private Position position; 
    private LemmingRole roleInput;  

    private static final String NAME = "setRole";  
    private static final String SHORTCUT = "sr";   
    private static final String DETAILS = "[s]et[R]ole ROLE ROW COL";
    private static final String HELP = "sets the lemming in position (ROW,COL) to role ROLE" + "\n" + LemmingRoleFactory.commandHelp();

    public SetRoleCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);  
    }

    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
        if (position == null || roleInput == null) {
            view.showError(Messages.ERROR_POSITION);
            return;
        }

        boolean success;
        try {
        	success = game.setRoleAt(position, roleInput); 
        } catch (OffBoardException e) {
        	throw new CommandExecuteException(Messages.COMMAND_EXECUTE_PROBLEM, e);
        } 
        
        if (!success) {
            throw new CommandExecuteException("No lemming in position (" + position.getRow()
            + "," + position.getCol() + ") admits role " + roleInput.getName() + Messages.LINE_SEPARATOR);
        }

        game.update();
        game.nextCycle();
        view.showGame(); 
        
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {

        if (commandWords.length != 4 || !matchCommandName(commandWords[0])) { //Necesitamos 4 palabras: "sr", "Parachuter", "3", "3"
            return null;
        }
               
        try {
			roleInput = LemmingRoleFactory.parse(commandWords[1]);
		} catch (RoleParseException e) {
			throw new CommandParseException(Messages.INVALID_PAR_COMMAND, e);
		}
               
        int row;
        char letra;
        int col;
             	
            
        letra = commandWords[2].charAt(0);
        
        try {
        	row = letra - 'A';
            col = Integer.parseInt(commandWords[3]);  //El segundo parámetro es la fila (num)
        	position = new Position(col-1,row);

        
        } catch (NumberFormatException e) {
            throw new CommandParseException(Messages.INVALID_POSITION.formatted(commandWords[2], commandWords[3]), e);
        }

        return this;
    }
}
