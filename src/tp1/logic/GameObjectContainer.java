package tp1.logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.*;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.view.Messages;

public class GameObjectContainer {
	private List<GameObject> objects;
	
	
	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}
	
	public void add(GameObject object) {
		objects.add(object);
	}
	
	
	//Recorre la lista de lemmings y los va actualizando
    public void update() {
    	for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            receiveInteractionsFrom(object); 
            object.update(); 
            
        }
    	removeDead();
	}
    
  
    
    public void write(BufferedWriter writer) throws IOException { 
    	for (GameObject obj : objects) {
            writer.write(obj.toString());
            writer.newLine();
        }
    }
    
    
    public boolean setRoleAtObject(Position position, LemmingRole role) {
		
    	for (GameObject obj: objects)
	       
			if (obj.isInPosition(position)) {  // Si hay un lemming en esa posición
	            return obj.setRole(role);  // Asignamos el nuevo rol
	        }
		        
		return false;
	}
    
    
    //Convierte los distintos elementos del juego a simbolos string
    public String positionToString(Position p) {
        StringBuilder respuesta = new StringBuilder();
        int contador = 0; // Contador para los elementos encontrados

        // Recorremos la lista de objetos
        for (GameObject obj : objects) {
            if (obj.isInPosition(p)) {
            	respuesta.append(obj.getIcon());
            	contador++;
            	if(contador == 3){
            		break;
            	}
            }          
        }

        // Si no hemos encontrado nada, devolvemos un espacio
        if (contador == 0) {
            return Messages.SPACE;
        }

        return respuesta.toString();
    }

	
	//Quitamos los lemmings muertos
    private void removeDead() {
    	List<GameObject> objectsToRemove = new ArrayList<>();

        for (GameObject obj : objects) {

            if (!obj.isAlive()) {
                objectsToRemove.add(obj);
            
            }
        }

        for (GameObject lemming : objectsToRemove) {
            objects.remove(lemming); 
        }
        
    }
    
    public void clear() {
    	objects.clear();
    }
    
    //Comprueba si el objeto es una pared
    public boolean isSolidAt(Position pos, GameObject sourceObject) {
        for (GameObject obj : objects) {
            if (obj.isInPosition(pos) && obj.isSolid()) {
                return true; 
            }
        }
        return false; 
    }
    
    //Devuelve el índice del lemming que ocupa la posición en la lista de objetos (si lo hay), sino devuelve -1
    public int isLemming(Position p, int ind) {
        for (int i = ind; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj.isInPosition(p)) {
                return i; 
            }
        }
        return -1; 
    }
   
    
	public boolean receiveInteractionsFrom(GameItem obj) {
		boolean interactionOccurred = false;

        for (GameObject gameObject : objects) {
            if (gameObject.receiveInteraction(obj)) {
                interactionOccurred = true;
            }
        }  
		return interactionOccurred;
				  
	}


}