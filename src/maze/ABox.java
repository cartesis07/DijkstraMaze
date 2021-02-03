package maze;

public class ABox extends MBox {
	
	//constructeur
	public ABox(int line, int column) {
		super(line,column, "A");
	}

	//le symbole correspond au type de case (A,D,W,E)
	public String getSymbol() {
		return this.Symbol;
	}
}
