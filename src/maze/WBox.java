package maze;

public class WBox extends MBox {
	
	//constructeur
	public WBox(int line, int column) {
		super(line,column, "W");
	}

	//le symbole correspond au type de case (A,D,W,E)
	public String getSymbol() {
		return this.Symbol;
	}
}
