package dijkstra;

public interface ASetInterface {

	//ajouter un sommet (un VertexInterface) à l'ensemble A
	public void add(VertexInterface vertex);

	//tester si un sommet appartient à A
	public boolean contains(VertexInterface vertex);
}

