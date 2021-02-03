package maze;

public class MazeReadingException extends Exception {

	private static final long serialVersionUID = 1L;

	//permet de distinguer une erreur de lecture d'un autre type d'erreur
	public MazeReadingException(String filename, int lineNumber, String errorMessage) {
		super("Erreur détéctée pendant la lecture du labyrinthe dans :" + filename +"(ligne : " + lineNumber + ")" + errorMessage);
	}

}
