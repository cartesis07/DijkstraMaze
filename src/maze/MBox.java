package maze;

import dijkstra.VertexInterface;

public abstract class MBox implements VertexInterface {
	
	private int line;
	private int column;
	protected String Symbol;
	
	//constructeur
	public MBox(int line, int column, String Symbol) {
		this.line = line;
		this.column = column;
		this.Symbol = Symbol;
	}
	
	//retourne les coordonées de la case
	public String getLabel() {
		return "(" + line + "," + column + ")";
 	}
	
	//retourne la ligne de la case
	public int getLabel_line() {
		return line;
 	}
	
	
	//retourne la colonne de la case
	public int getLabel_column() {
		return column;
 	}
	
	
	//permet de modifier le symbole d'une case
	public void changeSymbol(String symbol) {
		this.Symbol = symbol ;
	}
	
	//retourne le type de case E,D,W,A 
	public  String getSymbol() {
		return this.Symbol;
	}

	//retourne la ligne de la case
	public int getLine() {
		return line;
	}

	//retourne la colonne de la case
	public int getColumn() {
		return column;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int hashCode() {
		return new Integer(line).hashCode() ^ new Integer(column).hashCode();
	}

	//tester si un objet de type MBox est à la même position qu'un vertex
	@Override
	public boolean equals(Object vertex) {

		//transtypage
		MBox vertex_mbox = (MBox)vertex;

		//tester si les lignes et colonnes sont égales
		if (this.getLine() == vertex_mbox.getLine() && this.getColumn() == vertex_mbox.getColumn()) {
			return true;
		}

		return false;
	}

}
