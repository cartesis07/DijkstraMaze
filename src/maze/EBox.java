package maze;

public class EBox extends MBox {
	
	//constructeur
	public EBox(int line, int column) {
		super(line,column, "E");
	}

	//le symbole correspond au type de case (A,D,W,E)
	public String getSymbol() {
		return this.Symbol;
	}
}