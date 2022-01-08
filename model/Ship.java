/**
 * @author Rayane Chelihi Chelouche 
 * @author 51257925X
 */
package model;

import java.util.ArrayList;
import java.util.Objects;
//import java.util.Optional;

import model.exceptions.NoFighterAvailableException;
import model.fighters.*;

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
	protected ArrayList<Fighter> fleet;

	/** The position. */
	Coordinate position;

	/**
	 * Instantiates a new ship.
	 *
	 * @param name the name
	 * @param side the side
	 */
	public Ship(String name, Side side) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(side);
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
	public ArrayList<Fighter> getFleetTest() {
		return fleet;
	}

	/**
	 * Adds the fighters.
	 *
	 * @param fd the fd
	 */
	// PENDIENTE DE REVISION(HAZ MUCHAS PRUEBAS UNITARIAS QUE PUEDE FALLAR FACIL)
	public void addFighters(String fd) {
		Objects.requireNonNull(fd);

		String[] sep = fd.split(":");
		for (int i = 0; i < sep.length; i++) {
			String[] sep2 = sep[i].split("/");
			for (int j = 0; j < Integer.valueOf(sep2[0]); j++) {
				switch (sep2[1]) {
				case "AWing":
					Fighter f = new AWing(this);
					fleet.add(f);
					break;
				case "XWing":
					Fighter f1 = new XWing(this);
					fleet.add(f1);
					break;
				case "YWing":
					Fighter f2 = new YWing(this);
					fleet.add(f2);
					break;
				case "TIEBomber":
					Fighter f3 = new TIEBomber(this);
					fleet.add(f3);
					break;
				case "TIEFighter":
					Fighter f4 = new TIEFighter(this);
					fleet.add(f4);
					break;
				case "TIEInterceptor":
					Fighter f5 = new TIEInterceptor(this);
					fleet.add(f5);
					break;
				}
			}
		}
	}

	/**
	 * Update results.
	 *
	 * @param r the r
	 * @param luchador the luchador
	 */
	public void updateResults(int r, Fighter luchador) {
		switch (r) {
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
	 * Gets the first available fighter, escribir notablero para conseguir que no
	 * este en el tablero.
	 *
	 * @param type the type
	 * @return the first available fighter
	 * @throws NoFighterAvailableException the no fighter available exception
	 */
	public Fighter getFirstAvailableFighter(String type) throws NoFighterAvailableException {
		Objects.requireNonNull(type);
		//boolean comp = false;
		//Fighter f = null;
		/*
		 * for (int i = 0; i < fleet.size(); i++) { // Si el tipo es igual al parametro,
		 * no esta destruido y no hemos cogido antes a // ninguno if
		 * (!fleet.get(i).isDestroyed() && !comp) { if (fleet.get(i).getPosition() ==
		 * null) { if (type.isEmpty()) { comp = true; f = fleet.get(i); break; } else
		 * if(!type.equals("notablero")) { if (fleet.get(i).getType().equals(type)) { f
		 * = fleet.get(i); comp = true; break; } } } } } if (comp) { return f; } else {
		 * throw new NoFighterAvailableException(); }
		 */
		for (int i = 0; i < fleet.size(); i++) {
			if (fleet.get(i).getPosition() == null && !fleet.get(i).isDestroyed()) {
				if (type == "") {
					return fleet.get(i);
				} else {
					if (fleet.get(i).getType().equals(type)) {
						return fleet.get(i);
					}
				}
			}
		}
		throw new NoFighterAvailableException();
	}

	/**
	 * Purge fleet.
	 */
	public void purgeFleet() {
		for (int i = 0; i < fleet.size(); i++) {
			if (fleet.get(i).isDestroyed()) {
				fleet.remove(i);
				i--;
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
		if (fleet.size() != 0) {
			for (int i = 0; i < fleet.size(); i++) {

				resultado += fleet.get(i);
				if (fleet.get(i).isDestroyed()) {
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

		ArrayList<String> cazas = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();

		int cuenta = 0;

		for (int i = 0; i < fleet.size(); i++) {

			if (!cazas.contains(fleet.get(i).getType())) {

				cazas.add(fleet.get(i).getType());
			}

		}

		for (int i = 0; i < cazas.size(); i++) {

			for (int j = 0; j < fleet.size(); j++) {

				if (cazas.get(i).equals(fleet.get(j).getType()) && !fleet.get(j).isDestroyed()) {

					cuenta++;
				}
			}
			if (cuenta != 0) {

				if (i != 0) {
					sb.append(":");
				}

				sb.append(cuenta);
				sb.append("/");
				sb.append(cazas.get(i));

				cuenta = 0;

			}

		}

		String cadena = sb.toString();

		return cadena;

	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Ship" + " [" + name + " " + wins + "/" + losses + "] " + myFleet();

	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(fleet, losses, name, position, side, wins);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
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
