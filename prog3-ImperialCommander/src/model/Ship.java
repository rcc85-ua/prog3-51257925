/**
 * @author rayane Chelihi Chelouche 
 * @author 51257925X
 */
package model;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
/**
 * The Class Ship.
 */
public class Ship {
	
	/** The name. */
	private String name;
	
	/** The side. */
	private Side side;
	
	/** The wins. */
	private int wins;
	
	/** The losses. */
	private int losses;
	
	/** The fleet. */
	private ArrayList<Fighter> fleet;
	
	/** The position. */
	Coordinate position;
	
	/**
	 * Instantiates a new ship.
	 *
	 * @param name the name
	 * @param side the side
	 */
	public Ship(String name, Side side) {
		wins = 0;
		losses = 0;
		this.name = name;
		this.side = side;
		fleet = new ArrayList<Fighter>();
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the side.
	 *
	 * @return the side
	 */
	public Side getSide() {
		return side;
	}
	
	/**
	 * Gets the wins.
	 *
	 * @return the wins
	 */
	public int getWins() {
		return wins;
	}
	
	/**
	 * Gets the losses.
	 *
	 * @return the losses
	 */
	public int getLosses() {
		return losses;
	}
	
	/**
	 * Gets the fleet test.
	 *
	 * @return the fleet test
	 */
	public ArrayList<Fighter> getFleetTest(){
		return fleet;
	}
	
	/**
	 * Adds the fighters.
	 *
	 * @param fd the fd
	 */
	//PENDIENTE DE REVISION(HAZ MUCHAS PRUEBAS UNITARIAS QUE PUEDE FALLAR FACIL)
	public void addFighters(String fd) {
		
		String[] sep = fd.split(":");
		for(int i=0; i< sep.length;i++) {
			String[] sep2 = sep[i].split("/");
			for(int j=0; j < Integer.valueOf(sep2[0]); j++) {
				System.out.println(new Fighter(sep2[1], this));
				fleet.add(new Fighter(sep2[1], this ));
			}
		}
	}

	/**
	 * Update results.
	 *
	 * @param r the r
	 */
	public void updateResults(int r) {
		switch(r) {
		case 1:
			wins++;
			break;
		case -1:
			losses++;
			break;
		default:
			break;
		}
	}
	
	/**
	 * Gets the first available fighter.
	 *
	 * @param type the type
	 * @return the first available fighter
	 */
	public Fighter getFirstAvailableFighter(String type) {
		boolean comp = false;
		int resp = 0;
		//Recorremos todo el array
		for(int i = 0; i< fleet.size(); i++) {
			//Si el tipo es igual al parametro, no esta destruido y no hemos cogido antes a ninguno
			if(fleet.get(i).getType().equals(type) && !fleet.get(i).isDestroyed() && !comp) {
				resp = i;
				comp = true;
			}
		}
		if(comp) {
			return fleet.get(resp);
		}else {
			return null;
		}
	}
	
	/**
	 * Purge fleet.
	 */
	public void purgeFleet() {
		for(int i=0; i< fleet.size(); i++) {
			if(fleet.get(i).isDestroyed()) {
				fleet.remove(i);
			}
		}
	}
	
	/**
	 * Show fleet.
	 *
	 * @return the string
	 */
	public String showFleet() {
		String resultado = "";
		for(int i=0; i < fleet.size(); i++) {
			
			resultado += fleet.get(i);
			if(fleet.get(i).isDestroyed()) {
				resultado += " (X)";
			}
			resultado += "\n";
		}
		return resultado;
	}
	
	/**
	 * My fleet.
	 *
	 * @return the string
	 */
	public String myFleet() {
	String resultado ="";
	int contador = 0;
		//IDEA=HACER COPIA DEFENSIVA Y EN UN BUCLE IR ELIMINANDO LOS DE LA MISMA CLASE CON CONTADOR
		ArrayList<Fighter> copia = new ArrayList<>(fleet);
		//Recorre el vector si tiene algo
		if(fleet.size() != 0) {
			do {
				//Recorre el vector copiado
			for(int i = 0; i<copia.size(); i++) {
				
				//Si son iguales
				if(copia.get(i).getType().equals(copia.get(0).getType())){
					contador++;
					copia.remove(i);
					if(i>0)
					i--;
				}
			}
			resultado += contador + "/" + copia.get(0).getType();
			if(copia.size() >= 1)
				resultado += ":"; 
				copia.remove(0);
			}while(copia.size()!=0);
		}
	return resultado;
		//HACER UN ARRAY CON EL TIPO DE LOS SHIP Y OTRO CON LA CANTIDAD
		/*ArrayList<Integer> num = new ArrayList(); 
		ArrayList<String> type = new ArrayList();
		String resultado = "";
		boolean esta=false;
		for(int i=0;i<fleet.size();i++) {
			for(int j = 0; j<type.size();j++) {
				if(fleet.get(i).getType() == type.get(j)) {
					num.set(j,num.get(j)+1);
					esta = true;
				}
			}
			if(!esta) {
				type.add(fleet.get(i).getType());
				num.add(1);
			}
		}
		for(int i=0; i<type.size();i++) {
			resultado+= num.get(i) + "/" + type.get(i);
			if(type.size()>1)
				resultado+=":";
		}
		return resultado;*/
		//Hacer un HashMap donde clave sea el string
		/*String resultado = "";
		Map<String, Integer> flota = new HashMap<String, Integer>();
		int aux;
		for(int i=0; i<fleet.size();i++) {
			if(flota.containsKey(fleet.get(i).getType())) {
				aux = flota.get(fleet.get(i).getType());
				flota.put(fleet.get(i).getType(), aux+1);
			}
		}
		Set<String> tipo = flota.keySet();
		for(String tip : tipo) {
			int cantidad = flota.get(tip);
			resultado+= tipo + "/" + String.valueOf(cantidad) + ":";
		}
		if(resultado.length()>0)
		resultado=resultado.substring(0,resultado.length()-1);
		return resultado;*/
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Ship [" + name + wins + "/" + losses + "] " + myFleet();
		
	}
}
