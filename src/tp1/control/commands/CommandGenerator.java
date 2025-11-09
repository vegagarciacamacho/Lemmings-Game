package tp1.control.commands;

import java.util.Arrays; 
import tp1.exceptions.*;
import tp1.view.Messages;

import java.util.List;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
			new SetRoleCommand(),
			new UpdateCommand(),
			new ResetCommand(),
			new LoadCommand(),
			new SaveCommand(), 
			new HelpCommand(),
			new ExitCommand()
	);

	public static Command parse(String[] commandWords) throws CommandParseException{		
		for (Command c: availableCommands) {
			if(commandWords.length == 0 ||commandWords.length == 1 || commandWords.length == 2 ||commandWords.length == 4) {
				Command matchedCommand = c.parse(commandWords);
				if (matchedCommand != null) { 
					return matchedCommand;   
				}
			} else {
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER + Messages.LINE_SEPARATOR); 			}			
		}
		throw new CommandParseException(Messages.UNKNOWN_COMMAND.formatted(commandWords[0]));  
	}
	
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		for (Command c: availableCommands) {
			commands.append(c.helpText());
		}
		
		return commands.toString();
	}

}
