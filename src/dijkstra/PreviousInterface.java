package dijkstra;

import java.util.ArrayList;

public interface PreviousInterface {

	//Permet de retourner le prédécesseur du sommet en argument
	public VertexInterface getValue(VertexInterface vertex);
	
	//Permet de définir le prédécesseur du sommet en premier argument
	public void setValue(VertexInterface vertex, VertexInterface value);
	
	//Permet de retourner le chemin le plus court entre le sommet en argument et la racine
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex);
	
}