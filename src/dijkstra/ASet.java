package dijkstra;

import java.util.HashSet;

/** Interface of the set A occurring in the Dijkstra algorithm
 * as presented in the INF 101 SDA course at Telecom ParisTech.
 * The set A is a set of vertices characterized by the
 * VertexInterface interface
 */

public class ASet implements ASetInterface {

	private HashSet<VertexInterface> Aset;
	//la classe HashSet implémente la notion d'ensemble en utilisant une table de hachage
	
	//constructeur
	public ASet() {
		Aset = new HashSet<VertexInterface>();
	}
	
	//ajouter un sommet dans l'ensemble A
	public void add(VertexInterface vertex) {
		Aset.add(vertex);
	}
	
	//tester si un sommet est dans l'ensemble A
	public boolean contains(VertexInterface vertex) {
		for (VertexInterface sommet : Aset) {
			if (vertex.getLabel().equals(sommet.getLabel()))
				return true;
		}
		
		return false;
	}
}