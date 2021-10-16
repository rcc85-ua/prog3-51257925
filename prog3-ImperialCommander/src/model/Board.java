package model;

import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;

/**
 * The Class Board.
 */
/*NO SE HA HECHO NADA, VUELVE A MIRAR LA PRACTICA MANIN*/
public class Board {
	
	/** The size. */
	private int size;
	private HashMap<Coordinate, Fighter> board = new HashMap<Coordinate, Fighter>();
	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 */
	public Board(int size) {
		Objects.requireNonNull(size);
		board  = new HashMap<Coordinate, Fighter>();
		this.size = size;
		
	}
	
	/**
	 * Gets the fighter.
	 *
	 * @param c the c
	 * @return the fighter
	 */
	public boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
		Coordinate c = new Coordinate(f.getPosition());
		if(board.get(c).equals(f)) {
			board.remove(f.getPosition(),f);
			return true;
		}else {
			return false;
		}
		
	}
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		Fighter copia;
		if(board.get(c) != null) {
			copia = new Fighter(board.get(c));
			return copia;
		}else {
			return null;
		}

		}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Inside.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		if(board.containsKey(c)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Gets the neighborhood.
	 *
	 * @param c the c
	 * @return the neighborhood
	 */
	public TreeSet<Coordinate> getNeighborhood(Coordinate c){
		Objects.requireNonNull(c);
		TreeSet<Coordinate> vecinas = new TreeSet<Coordinate>();
		for(int i = -1; i<2;i++) {
			for(int j = -1;j<2;j++) {
				if(c.getX()+i>=0 && c.getY()+j>=0) {
					vecinas.add(new Coordinate(c.getX()+i,c.getY()+j));
				}
			}
		}
		
		return vecinas;
	}
	
	/**
	 * Launch.
	 *
	 * @param c the c
	 * @param f the f
	 * @return the int
	 */
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		int result = 0;
		//Si board no tiene fighter en la coordenada c
		if(!board.containsKey(c) && c.getX()>0 && c.getY()>0) {
			board.put(c, f);
		}else {
			//Si el fighter que esta en la coordenada es del otro bando
			if(board.get(c).getSide() != f.getSide()) {
				//Se pelean
				result =f.fight(board.get(c));
				//Actualizamos los resultados de la madre
				Ship madre = f.getMotherShip();
				Ship madre2 = board.get(c).getMotherShip();
				f.getMotherShip().updateResults(result);
				board(c).getMotherShip().updateResults(-result);
				if(result == 1) {
					//borramos al barco que hemos derrotado
					board.remove(c);
					//ponemos el nuevo barco
					board.put(c, f);
					f.setPosition(c);
				}
			}
		}
		return result;
	}
	
	/**
	 * Patrol.
	 *
	 * @param f the f
	 */
	public void patrol(Fighter f) {
		if(board.containsValue(f)) {
			Coordinate position = f.getPosition();
			TreeSet <Coordinate> vecinas = position.getNeighborhood();
			for(Coordinate coord: vecinas) {
				if(board.containsKey(coord)) {
					int result = f.fight(board.get(coord));
					Ship madre= f.getMotherShip();
					madre.updateResults(result);
					Ship madre2= board.get(coord).getMotherShip();
					madre2.updateResults(-result);
				}
				
			}
		}
		
	}
	
}
