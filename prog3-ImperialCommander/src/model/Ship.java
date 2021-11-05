/**
 * @author Rayane Chelihi Chelouche 
 * @author 51257925X
 */
package model;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
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
		if(fleet.size() != 0) {
		for(int i=0; i < fleet.size(); i++) {
			
			resultado += fleet.get(i);
			if(fleet.get(i).isDestroyed()) {
				resultado += " (X)";
			}
			resultado += "\n";
		}
		return resultado;
		}
		return "";
	}
	/**
	 * My fleet.
	 *
	 * @return the string
	 */
	public String myFleet() {
		//IDEA FUNCION A PARTE QUE COMPRUEBA QUE NO ESTA EL STRING EN EL ARRAY, OTRO QUE CUENTA CUANTOS HAY DE UN TIPO
		String resultado = "";
		ArrayList <String> hecho = new ArrayList <String>();
		for(int i=0;i<fleet.size();i++) {
			if(!estadentro(hecho, fleet.get(i).getType())) {
				//Si no lo hemos cogido
				hecho.add(fleet.get(i).getType());
				resultado+=contador(fleet, fleet.get(i).getType()) + "/" + fleet.get(i).getType() +  ":";
			}
		}
		resultado = Optional.ofNullable(resultado)
				.filter(s -> s.length() != 0)
				.map(s -> s.substring(0,s.length()-1))
				.orElse(resultado);
		return resultado;
	}
	
	private int contador(ArrayList<Fighter> fleet,String tipo) {
		int resultado = 0;
		for(int i=0;i<fleet.size();i++) {
			if(tipo.equals(fleet.get(i).getType())) {
				resultado++;
			}
		}
		return resultado;
	}
	
	private boolean estadentro(ArrayList <String> hecho, String type) {
		boolean resultado = false;
		for(int i=0;i<hecho.size();i++) {
			if(type.equals(hecho.get(i)))
				resultado = true;
		}
		return resultado;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Ship" + " [" + name + " " + wins + "/" + losses + "] " + myFleet();
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(fleet, losses, name, position, side, wins);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		return Objects.equals(fleet, other.fleet) && losses == other.losses && Objects.equals(name, other.name)
				&& Objects.equals(position, other.position) && side == other.side && wins == other.wins;
	}
}
