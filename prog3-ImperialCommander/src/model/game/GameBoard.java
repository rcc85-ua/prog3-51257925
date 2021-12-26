/*
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */

package model.game;
import model.*;
import model.exceptions.InvalidSizeException;

/**
 * The Class GameBoard.
 */
public class GameBoard extends Board{

	/** The size. */
	public int size = 0;
	
	/**
	 * Instantiates a new game board.
	 *
	 * @param size the size
	 * @throws InvalidSizeException the invalid size exception
	 */
	public GameBoard(int size) throws InvalidSizeException{
		super(size);
		this.size = size;
		
		
	}
	
	/**
	 * Num fighters.
	 *
	 * @param side the side
	 * @return the int
	 */
	public int numFighters(Side side) {
		int contador = 0;
		//for(int i = 0; i < board.size();i++) {
			for(Coordinate coord : board.keySet()) {
				if(board.get(coord) != null && board.get(coord).getSide() == side) {
					contador++;
					System.out.println("Ha sido aceptado");
				}	
			}
		//}
		return contador;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String resultado = "  ";
		
		for(int i = 0; i<size;i++) {
			//System.out.println(i);
			resultado += i;
		}
		resultado += "\n";
		resultado += "  ";
		for(int i = 0; i<size;i++) {
			//System.out.println("-");
			resultado += "-";
		}
		
		resultado += "\n";
		//i == y
		for(int i = 0; i<size; i++) {
			//System.out.print(i + "|");
			resultado += i+"|";
			//j == x
			for(int j = 0; j<size;j++) {
				Coordinate c = new Coordinate(j,i);
				if(board.containsKey(c) && board.get(c) != null) {
				resultado += board.get(c).getSymbol();
					//char tipo = board.get(c).getSymbol();
				//System.out.print(tipo);
				//resultado += tipo;
				}else {
					resultado += " ";
				}
			}
			//System.out.println();
			if(i != size-1)
			resultado += "\n";
		}
		return resultado;
	}
}
