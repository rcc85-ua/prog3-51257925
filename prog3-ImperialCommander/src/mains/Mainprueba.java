package mains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.Coordinate;
import model.Ship;
import model.Fighter;
import model.Side;

public class Mainprueba {
	public static void main(String[] args) {
		Ship barco = new Ship("barco", Side.IMPERIAL);
		final String kFleet2 = "40/XWing:10/AWing:30/YWing:25/XWing:35/TIEFighter:55/TIEBomber:45/TIEShuttle:100/ZWing";
		barco.addFighters(kFleet2);
		String resultado = "";
		Map<String, Integer> flota = new HashMap<String, Integer>();
		int aux;
		int contador = 0;
			//IDEA=HACER COPIA DEFENSIVA Y EN UN BUCLE IR ELIMINANDO LOS DE LA MISMA CLASE CON CONTADOR
			ArrayList<Fighter> copia = new ArrayList<>(barco.getFleetTest());
			//Recorre el vector si tiene algo
			if(barco.getFleetTest().size() != 0) {
				do {
					//Recorre el vector copiado
				for(int i = 0; i<copia.size(); i++) {
					
					//Si son iguales
					if(copia.get(i).getType().equals(copia.get(0).getType())){
						contador++;
						System.out.println(copia.get(i).getType());
						copia.remove(i);
						if(i>0)
						i--;
					}
				}
				resultado += contador + "/" + copia.get(0).getType();
				if(copia.size() >= 1)
					resultado += ":"; 
					copia.remove(0);
					contador = 0;
				}while(copia.size()!=0);
			}
		System.out.println(resultado);
	}
}
