package dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import maze.MBox;

/*permet d'obtenir le prédécesseur d'un sommet dans le plus court chemin
établit une correspondance entre des objets de type VertexInterface
*/
public class Previous implements PreviousInterface {
	
	//Pour chaque sommet du graphe, on indique aussi son prédécesseur
	private HashMap<VertexInterface,VertexInterface> table;
	
	//constructeur
	public Previous() {
		table = new HashMap<VertexInterface, VertexInterface>();
	}
	
	//Permet de retourner le prédécesseur du sommet en argument
	public VertexInterface getValue(VertexInterface vertex) {
		return table.get(vertex);
	}

	//Permet de définir le prédécesseur du sommet en premier argument
	public void setValue(VertexInterface vertex, VertexInterface value) {
		table.put(vertex, value);
	}

	//Permet de retourner le chemin le plus court entre le sommet en argument et la racine
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex) {
		ArrayList<VertexInterface> path = new ArrayList<VertexInterface>();
		while (getValue(vertex) != null) {
			
			//transtypage
			MBox temporary = (MBox)vertex;
			if (temporary.getSymbol().equals("A")) {
				vertex = getValue(vertex);
			}
			
			else {
				path.add(vertex);
				vertex = getValue(vertex);
			}
		}
		return path;
	}
}