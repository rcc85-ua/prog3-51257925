package mains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.Coordinate;
import model.Ship;
import model.Fighter;
import model.Side;
import model.Board;

public class Mainprueba {
	public static void main(String[] args) {
		
		Board board = new Board(10);
		Set<Coordinate> coord = board.getNeighborhood(new Coordinate(0,0));
		System.out.println("------------------------------------------------------");
		for(Coordinate corde : coord) {
			System.out.println(corde);
		}
	}
}
