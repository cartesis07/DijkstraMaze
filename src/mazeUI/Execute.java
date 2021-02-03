package mazeUI;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import dijkstra.Dijkstra;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import maze.MBox;
import maze.Maze;
import maze.MazeReadingException;

//cette classe est appelée quand l'utilisateur souhaite éxecuter Dijkstra sur son labyrinthe
public class Execute {
	
	public static int pathEmptyOrNot = 0;
	
	//permet l'éxecution de Dijkstra à partir d'un fichier texte et des dimensions du fichier texte
	public static void execute(String filename, int i, int j) throws MazeReadingException {
		Maze labyrinthe = new Maze(i,j);
		VertexInterface start;
		VertexInterface arrival;
		PreviousInterface previous;
		ArrayList<VertexInterface> path;
		
		labyrinthe.initFromTextFile(filename);

		start = labyrinthe.Start();
		arrival = labyrinthe.Finish();
		
		MBox start_mbox = (MBox)start;
		MBox arrival_mbox = (MBox)arrival;
		
		int start_line = start_mbox.getLine();
		int start_column = start_mbox.getColumn();
		int arrival_line = arrival_mbox.getLine();
		int arrival_column = arrival_mbox.getColumn();
		
		//on vérifie si le départ et l'arrivée sont adjacents ou non
		if (Math.abs(start_line - arrival_line) <= 1 && Math.abs(start_column - arrival_column) <= 1 && !(Math.abs(start_line - arrival_line) == 1 && Math.abs(start_column - arrival_column) == 1)) {
			JOptionPane.showMessageDialog(null, "Le départ et l'arrivée sont adjacents.");
			
			previous = Dijkstra.dijkstra(labyrinthe,start);
			path = previous.getShortestPathTo(arrival);
			
			for (VertexInterface vertex : path) {
				MBox sommet = (MBox)vertex;
				int line = sommet.getLine();
				int column = sommet.getColumn();
				labyrinthe.setBoxSymbol(line, column, ".");
			}
			labyrinthe.saveToTextFile(new File("private_data/result.txt"));
		}

		//s'ils ne sont pas adjacents, on exécute dijkstra et on affiche le résultat
		else {
			previous = Dijkstra.dijkstra(labyrinthe,start);
			
			path = previous.getShortestPathTo(arrival);
			
			//si le chemin est vide, il n'y pas de solution au labyrinthe : au moins un mur bloque le chemin entre le départ et l'arrivée
			if (path.isEmpty()) {
				pathEmptyOrNot = 1;
				JOptionPane.showMessageDialog(null, "Il n'y a pas de solution au labyrinthe proposé," + " il faut le modifier.");
			}
			
			//on dessine le chemin grâce au caractère "."
			for (VertexInterface vertex : path) {
				MBox sommet = (MBox)vertex;
				int line = sommet.getLine();
				int column = sommet.getColumn();
				labyrinthe.setBoxSymbol(line, column, ".");
			}
			
			//on enregistre le résultat dans le fichier "result.txt"
			labyrinthe.saveToTextFile(new File("private_data/result.txt"));
		}
	}
}