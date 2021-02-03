package dijkstra;

import java.util.ArrayList;

public interface GraphInterface {

	//retourne le poids d'un arc de deux sommets voisins 
	public int getWeight(VertexInterface src, VertexInterface dst);
	
	//retourne la liste de tous les sommets
	public ArrayList<VertexInterface> getAllVertices();
	
	//retourne la liste de tous les successeurs d'un sommet
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);	
	
}