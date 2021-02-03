package mazeUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import maze.Maze;

//permet d'enregistrer le labyrinthe modifié par l'utilisateur grâce à un objet Maze(i,j)
public class Save {
	
	private int height;
	private int width;
	public Maze save;

	public Save(int i, int j) {
		save = new Maze(i,j);
		height = i;
		width = j;
	}
	
	public void filesave(File fichier) {
		PrintWriter pw = null ;
		try {
			pw = new PrintWriter(fichier.getPath()) ;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (j != width - 1 || (i == height - 1 && j == width - 1)) {
						pw.print(save.getBoxSymbol(i,j));
					}
					else {
						pw.println(save.getBoxSymbol(i,j)) ;
					}
				}
			}
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"Le fichier demandé n'existe pas");
		} finally {
			try {
			pw.close();
			} catch (Exception e1) {}
		}
	}
	
	public void changeToE(int i, int j) {
		save.setBoxSymbol(i, j, "E");;
	}
	
	public void changeToW(int i, int j) {
		save.setBoxSymbol(i, j, "W");
	}
	
	public void changeToD(int i, int j) {
		save.setBoxSymbol(i, j, "D");
	}
	
	public void changeToA(int i, int j) {
		save.setBoxSymbol(i, j, "A");
	}
	
	public String getSymbol(int i, int j) {
		String result = new String();
		result = save.getBoxSymbol(i, j);
		return result;
	}
}