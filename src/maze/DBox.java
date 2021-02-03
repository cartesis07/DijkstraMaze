package maze;

public class DBox extends MBox {

	//constructeur
	public DBox(int line, int column) {
		super(line,column, "D");
	}

	//le symbole correspond au type de case (A,D,W,E)
	public String getSymbol() {
		return this.Symbol;
	}
}