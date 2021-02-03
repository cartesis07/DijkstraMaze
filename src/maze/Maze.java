package maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

public class Maze implements GraphInterface {

	private int height;
	private int width;
	private MBox[][] maze;
	private DBox CaseDepart;
	private ABox CaseArrivee;
	
	//permet de définir la case départ du labyrinthe
	public VertexInterface Start() {
		for (int i=0; i < height; i++) {
			for (int j=0; j < width; j++) {
				if (this.getBoxSymbol(i,j).equals("D"))
					CaseDepart = new DBox(i,j);
			}
		}
		return CaseDepart;
	}
	
	//permet de définir la case arrivée du labyrinthe
	public VertexInterface Finish() {
		for (int i=0; i < height; i++) {
			for (int j=0; j < width; j++) {
				if (this.getBoxSymbol(i,j).equals("A"))
					CaseArrivee = new ABox(i,j);
			}
		}
		return CaseArrivee;
	}
	
	//retourne la hauteur du labyrinthe
	public int getHeight() {
		return height;
	}

	//retourne la largeur du labyrinthe
	public int getWidth() {
		return width;
	}
	
	//obtenir le symbole (A,D,W,E) en fonction de la case spécifiée (getter)
	public String getBoxSymbol(int line, int column) {
		return maze[line][column].getSymbol();
	}
	
	//fixe le symbole (A,D,W,E) de la case spécifiée (setter)
	public void setBoxSymbol(int line, int column, String Symbol) {
		maze[line][column].changeSymbol(Symbol);
	}
	
	//constructeur
	public Maze(int hauteur, int largeur) {
		this.height = hauteur;
		this.width = largeur;
		maze = new MBox[height][width];
		for (int i=0;i<hauteur;i++) {
			for (int j=0;j<width;j++) {
				maze[i][j]= new EBox(i,j);
			}
		}
	}

	// lire les lignes d'un fichier et ajouter chaque caractère au tableau maze en gérant les exceptions
	public final void initFromTextFile(String filename) throws MazeReadingException {		
		FileReader in = null;
		BufferedReader br = null;
		String temporary = new String(); // on crée une variable temporaire qui va contenir une ligne du fichier texte
		int compteur = 0;		
		try {
			in = new FileReader(filename);
			br = new BufferedReader(in); 

				for (int i=0; i<height; i++) {
					temporary = br.readLine();	
					compteur = compteur +1;

					for (int j=0; j<width; j++) {
						switch (String.valueOf(temporary.charAt(j))) {
							case "E" :
								break;
							case "W" :
								maze[i][j].changeSymbol("W");
								break;
							case "D" :
								maze[i][j].changeSymbol("D");
								break;
							case "A" :
								maze[i][j].changeSymbol("A");
								break;

							//exception si un mauvais caractère a été inséré
							default :
								System.out.println("Mauvais caractère inséré ligne " + i + ", colonne " + j);
						}
					}
				}	
		
		//exception si le fichier n'existe pas
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier demandé n'existe pas");
		} catch (IOException e) {
			e.printStackTrace();

		//exception s'il manque des lignes dans le fichier
		} catch (NullPointerException e) {
			if (temporary == null)
				throw new MazeReadingException(filename, compteur, " Il manques des lignes dans le fichier");	
			
		//exception s'il manque des caractères dans le fichier
		} catch (IndexOutOfBoundsException e) {
			if (temporary.length() != width) { 
				throw new MazeReadingException(filename, compteur, " Il manque des caractères sur la ligne");
			}
		
		//fermeture des entrées
		} finally {
			try {
				if (in != null) 
					in.close();
				else if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//sauvegarder le labyrinthe dans un fichier texte
	public final void saveToTextFile(File fichier) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fichier);
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {

					if (j != width-1 | (i == height-1 && j == width-1)) {
						pw.print(maze[i][j].getSymbol());
					}

					else {
						pw.println(maze[i][j].getSymbol());  //println permet de faire le retour de ligne 
					}
				}
			}
			
		//exception si le fichier n'existe pas
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier demandé n'existe pas");

		//fermeture de la sortie
		} finally {
			try {
			pw.close();
			} catch (Exception e) {}
		}
	}

	// La distance entre deux cases voisines est fixée à 1
	public int getWeight(VertexInterface src, VertexInterface dst) {
		return 1;
	}

	//retourne la liste de tous les sommets
	public ArrayList<VertexInterface> getAllVertices() {
		ArrayList<VertexInterface> allVertices = new ArrayList<VertexInterface>();
		for(int i = 0 ; i < height ; i++) {
			for (int j = 0 ; j < width ; j++) {
				allVertices.add(maze[i][j]);
			}
		}
		return allVertices;
	}

	//retourne la liste de tous les successeurs d'un sommet
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
		ArrayList<VertexInterface> SuccessorsList = new ArrayList<VertexInterface>();
		
		//transtypage
		MBox vertex_mbox = (MBox)vertex;
		int i = vertex_mbox.getLabel_line();
		int j = vertex_mbox.getLabel_column();
		
		//tester si le voisin du haut est un mur
		if (i != 0) {
			MBox up = maze[i-1][j];
			if (up.getSymbol() != "W") {
				SuccessorsList.add(up);
			}
		}
		
		//tester si le voisin de gauche est un mur
		if (j != 0) {
			MBox left = maze[i][j-1];
			if (left.getSymbol() != "W") {
				SuccessorsList.add(left);
			}
		}
		
		//tester si le voisin d'en bas est un mur
		if (i != height-1) {
			MBox down = maze[i+1][j];
			if (down.getSymbol() != "W") {
				SuccessorsList.add(down);
			}
		}
		
		//tester si le voisin de gauche est un mur
		if (j != width-1) {
			MBox right = maze[i][j+1];
			if (right.getSymbol() != "W") {
				SuccessorsList.add(right);
			}
		}
		return SuccessorsList;
	}
}