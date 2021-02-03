package mazeUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

//cette classe décrit le fonctionnement des boutons des menus
public class Button extends JButton implements MouseMotionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	public String name;
	private Image img;

	//constructeur
	public Button(String str){
		super(str);
		this.name = str;

		try {
			img = ImageIO.read(new File("Images/Button_images/default.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		g2d.setColor(Color.black);
		g2d.drawString(this.name, 3, (this.getHeight() / 2) + 5);
	}

	@Override
	//Méthode appelée lors du clic de souris
	public void mousePressed(MouseEvent event) { 
		try {
			img = ImageIO.read(new File("Images/Button_images/clicked.png"));
			repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Méthode appelée lorsque la souris entre dans la zone du bouton
	@Override
	public void mouseEntered(MouseEvent event) {
		try {
			img = ImageIO.read(new File("Images/Button_images/entered.png"));
			repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Méthode appelée lorsque la souris sort de la zone du bouton
	@Override
	public void mouseExited(MouseEvent event) {
		try {
			img = ImageIO.read(new File("Images/Button_images/default.png"));
			repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Méthode appelée lorsque la souris relâche le clic
	@Override
	public void mouseReleased(MouseEvent e) {
		try {
			img = ImageIO.read(new File("Images/Button_images/default.png"));
			repaint();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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