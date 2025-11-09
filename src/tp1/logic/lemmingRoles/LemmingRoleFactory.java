package tp1.logic.lemmingRoles;

import java.util.Arrays; 
import tp1.exceptions.*;
import tp1.view.Messages;

import java.util.List;


public class LemmingRoleFactory {
	private static final List<LemmingRole> availableRoles = Arrays.asList(
			new DownCaverRole(),
			new ParachuterRole(),
			new WalkerRole()
	);
	
	public static LemmingRole parse(String commandWords) 
							throws RoleParseException{		
		for (LemmingRole r: availableRoles) {
			if (r.canParse(commandWords)) {
				return r;
			}
		}
		throw new RoleParseException(Messages.UNKNOWN_ROLE + commandWords + Messages.LINE_SEPARATOR);
	}
    
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		int cont = 0;
		
		for (LemmingRole c: availableRoles) {
			cont++;
			if(cont == 3) {
				commands.append("       " + c.helpText());
			} else {
				commands.append("       " + c.helpText()).append("\n");
			}
		}
		
		return commands.toString();
	}

	

}
 