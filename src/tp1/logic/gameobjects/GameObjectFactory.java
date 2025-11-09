package tp1.logic.gameobjects;
	
	import java.util.Arrays; 
	import java.util.List;
	import tp1.exceptions.*;
	import tp1.logic.GameObjectContainer;
	import tp1.logic.GameWorld;
	import tp1.logic.Position;
	import tp1.logic.lemmingRoles.LemmingRole;
import tp1.view.Messages;
	
	
	public class GameObjectFactory {
		
		private static final Position pos = null;
		private static final GameWorld game = null;
		private static final LemmingRole role = null;
		
		
		private static final List<GameObject> availableObjects = Arrays.asList(
				new Lemming(game, pos, role),
				new Wall(game, pos),
				new ExitDoor(game, pos),
				new MetalWall(game, pos) 
		);
		
	    public static GameObject parse(String line, GameWorld game)  throws ObjectParseException, OffBoardException {	
			
				 try {
					 for(GameObject object : availableObjects) {
		                GameObject parsedObject = object.parse(line, game);
		                if (parsedObject != null) {
		                    return parsedObject; 
		                }
					 }
					 return null;
		            } catch (ObjectParseException | OffBoardException e) { 
		    			throw new ObjectParseException(e.getMessage());
		            }
			
		}
	}
