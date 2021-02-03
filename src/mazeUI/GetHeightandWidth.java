package mazeUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*cette classe permet soit de demander les dimensions du labyrinthe à l'utilisateur
 * soit de détecter les dimensions du labyrinthe pour un fichier texte donné
 */

public class GetHeightandWidth extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public int height;
	public int width;

	//constructeur
	//demande les dimensions du labyrinthe à l'utilisateur
	public GetHeightandWidth() {
		try {
		String reponse1 = JOptionPane.showInputDialog(null, "Quelle est la hauteur de labyrinthe que vous souhaitez utiliser ?");
		String reponse2 = JOptionPane.showInputDialog(null, "Quelle est la largeur de labyrinthe que vous souhaitez utiliser ?");
		height = Integer.parseInt(reponse1);
		width = Integer.parseInt(reponse2);
		
		//Exception si les dimensions données sont négatives
		if (height <= 0 || width <= 0) {
			JOptionPane.showMessageDialog(null, "Les dimensions doivent être strictement positives");
			System.exit(0);
		}
		
		//Exception si le format est incorrect
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Les dimensions du labyrinthes sont incorrectes");
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	//détecte les dimensions d'un labyrinthe pour un ficher texte donné
	public GetHeightandWidth(File fichier) {
		FileReader in = null;
		BufferedReader br = null;
		int hauteur = 0;
		try {
			in = new FileReader(fichier);
			br = new BufferedReader(in); 
			String temporary = br.readLine();

			//Exception si le fichier est vide
			if (temporary == null) {
				JOptionPane.showMessageDialog(null, "Le fichier demandé est vide, impossible de charger un labyrinthe");
			}
			else {
				width = temporary.length();
			}
			while(temporary != null) {
				hauteur = hauteur + 1;
				temporary = br.readLine();
			height = hauteur;
			}
		//exception si le fichier n'existe pas
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Le fichier demandé n'existe pas");
		}catch (IOException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally {
			
			//fermeture des entrées
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
}