/*@author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model;

import model.fighters.*;


/**
 * A factory for creating Fighter objects.
 */
public class FighterFactory {
	
	/**
	 * Creates a new Fighter object.
	 *
	 * @param type the type
	 * @param Mother the mother
	 * @return the fighter
	 */
	public static Fighter createFighter(String type, Ship Mother){
		switch(type) {
		case "XWing":
			return new XWing(Mother);
		case "YWing":
			return new YWing(Mother);
		case "AWing":
			return new AWing(Mother);
		case "TIEBomber":
			return new TIEBomber(Mother);
		case "TIEFighter":
			return new TIEFighter(Mother);
		case "TIEInterceptor":
			return new TIEInterceptor(Mother);
		default:
			return null;
		}
	}
}
