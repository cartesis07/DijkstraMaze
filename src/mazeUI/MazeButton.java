package mazeUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

//Implémentation des boutons représentant le labyrinthe
public class MazeButton extends JButton implements MouseMotionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	public int line;
	public int column;
	
	//constructeur
	public MazeButton(int i, int j) throws IOException{
		super();
		line = i;
		column = j;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	//Changer le visuel du bouton en case vide
	public void changeIcon_E() {
		
		//si l'interface sélectionnée est l'interface basique
		if (Fenetre.UIbasicOrNot == 1) {
			this.setOpaque(true);
			this.setBackground(Color.WHITE);
			repaint();
		}
		
		//si l'interface sélectionnée est l'interface champêtre
		else {
			ImageIcon img_Grass = new ImageIcon(new ImageIcon("Images/MazeButton_images/Grass.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	    	this.setIcon(img_Grass);
		}
	}
	
	//Changer le visuel du bouton en mur
	public void changeIcon_W() {
		
		//si l'interface sélectionnée est l'interface basique
		if (Fenetre.UIbasicOrNot == 1) {
			this.setOpaque(true);
			this.setBackground(Color.BLACK);
			repaint();
		}
		
		//si l'interface sélectionnée est l'interface champêtre
		else {
			ImageIcon img_Wall = new ImageIcon(new ImageIcon("Images/MazeButton_images/Wall.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	    	this.setIcon(img_Wall);
		}
	}
	
	//Changer le visuel du bouton en arrivée
	public void changeIcon_A() {
		
		//si l'interface sélectionnée est l'interface basique
		if (Fenetre.UIbasicOrNot == 1) {
			this.setOpaque(true);
			this.setBackground(Color.RED);
			repaint();
		}
		
		//si l'interface sélectionnée est l'interface champêtre
		else {
			ImageIcon img_Arrival = new ImageIcon(new ImageIcon("Images/MazeButton_images/Arrival.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	    	this.setIcon(img_Arrival);
		}
	}
	
	//Changer le visuel du bouton en départ
	public void changeIcon_D() {
		
		//si l'interface sélectionnée est l'interface basique
		if (Fenetre.UIbasicOrNot == 1) {
			this.setOpaque(true);
			this.setBackground(Color.BLUE);
			repaint();
		}
		
		//si l'interface sélectionnée est l'interface champêtre
		else {
			ImageIcon img_Start = new ImageIcon(new ImageIcon("Images/MazeButton_images/Start.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	    	this.setIcon(img_Start);
		}
    }
	
	public void changeIcon_S() {
		
		//si l'interface sélectionnée est l'interface basique
		if (Fenetre.UIbasicOrNot == 1) {
			this.setOpaque(true);
			this.setBackground(Color.LIGHT_GRAY);
			repaint();
		}
		
		//si l'interface sélectionnée est l'interface champêtre
		else {
			ImageIcon img_Solution = new ImageIcon(new ImageIcon("Images/MazeButton_images/Solution.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	    	this.setIcon(img_Solution);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}