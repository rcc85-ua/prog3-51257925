/*
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.game;

import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

/**
 * The Interface IPlayer.
 */
public interface IPlayer {

/**
 * Sets the board.
 *
 * @param gb the new board
 */
	

void setBoard(GameBoard gb);

/**
 * Gets the game ship.
 *
 * @return the game ship
 */
GameShip getGameShip();

/**
 * Inits the fighters.
 */
void initFighters();

/**
 * Checks if is fleet destroyed.
 *
 * @return true, if is fleet destroyed
 */
boolean isFleetDestroyed();

/**
 * Show ship.
 *
 * @return the string
 */
String showShip();

/**
 * Purge fleet.
 */
void purgeFleet();

/**
 * Next play.
 *
 * @return true, if successful
 */
boolean nextPlay();

/**
 * Gets the wins score.
 *
 * @return the wins score
 */
public WinsScore getWinsScore();	

/**
 * Gets the destroyed fighters score.
 *
 * @return the destroyed fighters score
 */
public DestroyedFightersScore getDestroyedFightersScore();
}
