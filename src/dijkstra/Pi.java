package dijkstra;

import java.util.Hashtable;

//calcule la longueur du plus court chemin entre le sommet et la racine
public class Pi implements PiInterface {
	
	private Hashtable<VertexInterface, Integer> table;
	
	//constructeur
	public Pi() {
		table = new Hashtable<VertexInterface, Integer>();
	}
	
	//définit pour un sommet sa distance au sommet de départ
	@SuppressWarnings("deprecation")
	public void setValue(VertexInterface vertex, int value) {
		table.put(vertex, new Integer(value));
	}
	
	//retourne la distance entre le sommet en argument et le sommet de départ 
	public int getValue(VertexInterface vertex) {
		return table.get(vertex).intValue();
	}

}