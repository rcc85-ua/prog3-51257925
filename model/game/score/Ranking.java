/*
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The Class Ranking.
 *
 * @param <ScoreType> the generic type
 */
public class Ranking<ScoreType extends Score<?>> {
	
	/** The score set. */
	private SortedSet<ScoreType> scoreSet;
	
	/**
	 * Instantiates a new ranking.
	 */
	public Ranking() {
		scoreSet = new TreeSet<>();
	}
	
	/**
	 * Adds the score.
	 *
	 * @param Type the type
	 */
	public void addScore(ScoreType Type) {
		scoreSet.add( Type);
	}
	
	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public ScoreType getWinner() {
		if(scoreSet.size() == 0) {
			throw new RuntimeException();
		}else {
		return scoreSet.first();
	
		}
	}
	
	/**
	 * Gets the sorted ranking.
	 *
	 * @return the sorted ranking
	 */
	public SortedSet<ScoreType> getSortedRanking(){
		
		return scoreSet;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String resultado = "";
		
		resultado+="|";
		
		for(ScoreType puntuacion : scoreSet) {
			resultado +=puntuacion.toString();
			resultado+="|";
		
		}
		return resultado;
	}
}
