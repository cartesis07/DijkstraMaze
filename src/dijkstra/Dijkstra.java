package dijkstra;

import java.util.ArrayList;

/** Implementation of the Dijkstra algorithm of INF 101 SDA course 
 * using object interfaces only
 */

public class Dijkstra {
	
	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r) {
		ASet Aset = new ASet(); //ensemble des sommets visités
		Previous previous = new Previous(); //fonction père
		Pi pi = new Pi(); //fonction pi
		
		return dijkstra(g,r,Aset,pi,previous);
	}
	
	private static final PreviousInterface dijkstra(GraphInterface g, VertexInterface r, ASetInterface a, PiInterface pi, PreviousInterface previous) {
		ArrayList<VertexInterface> vertexList = g.getAllVertices(); //liste de tous les sommets
		int n = vertexList.size();
		int infinity = Integer.MAX_VALUE;
		int temporary1 = 0;
		
		a.add(r); //ajouter sommet de départ à A
		VertexInterface pivot = r; //pivot = sommet de départ
		pi.setValue(r, 0);
		
		//première boucle permettant d'initialiser les valeurs de la fonction pi à l'infini
		for (VertexInterface x : vertexList ) {
			if (! x.getLabel().equals(r.getLabel()))
				pi.setValue(x, infinity);
		}
		
		//deuxième boucle
		
		for (int j=1; j<n; j++) { //pour tout j variant de 1 à n-1
			ArrayList<VertexInterface> pivotSuccessors = g.getSuccessors(pivot);
			
			for (VertexInterface y1 : pivotSuccessors) {
				
				if (!a.contains(y1)) { //pour tout sommet y non encore dans A et successeur de pivot, faire :
					temporary1 = pi.getValue(pivot) + g.getWeight(pivot, y1);
					
					if (temporary1  < pi.getValue(y1)) {
						pi.setValue(y1, temporary1);
						previous.setValue(y1, pivot);
					}
				}
			}
			int temporary2 = infinity;
			for (VertexInterface y2 : vertexList) {
				if (!a.contains(y2)) { //chercher parmi les sommets non dans A, un sommet y2 tel que pi(y2) soit minimum
					int y1_value = pi.getValue(y2);
					
					if (y1_value < temporary2 ) {
						temporary2 = y1_value;
						pivot = y2;
					}
				}
			}
			a.add(pivot);
		}
		return previous;
	}
}