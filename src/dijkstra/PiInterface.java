package dijkstra;

public interface PiInterface {

	//retourne la distance entre le sommet r et le sommet pris en argument
	public int getValue(VertexInterface vertex);
	
	//fixe la distance pour un sommet
	public void setValue(VertexInterface vertex, int value);
	
}
