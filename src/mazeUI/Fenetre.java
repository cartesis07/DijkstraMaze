package mazeUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.Maze;
import maze.MazeReadingException;

public class Fenetre extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	//tous les boutons que l'utilisateur peut utiliser
	private Button start = new Button("Start");
	private Button arrival = new Button("Arrival");
	private Button upload = new Button("Upload");
	private Button edit = new Button("Edit");
	private Button save = new Button("Save");
	private Button run = new Button("Run");
	private Button quit = new Button("Quit");
	private Button newmaze = new Button("New Maze");
	private Button wall = new Button("Wall");
	private Button empty = new Button("Empty");
	private Button back = new Button("Back");
	
	//les dimensions du labyrinthe en cours d'utilisation
	private int HEIGHT;
	private int WIDTH;
	
	//variables pour savoir si les cases de départ et d'arrivée existent
	public static int startBoxAlreadyExists = 0;
	public static int arrivalBoxAlreadyExists = 0;
	
	//variables pour savoir si les boutons wall, empty, start et arrival sont pressés ou non
	private int wallButtonPressed = 0;
	private int emptyButtonPressed = 0;
	private int startButtonPressed = 0;
	private int arrivalButtonPressed = 0;
	
	//variable pour savoir quelle interface graphique utiliser
	public static int UIbasicOrNot = 1;
	
	//Menu2 est un sous-menu de Menu1
	private JLabel Menu1 = new JLabel("Menu :");
	private JLabel Menu2 = new JLabel("Menu :");
		
	//le fichier defaultt est un fichier ne contenant que des cases vides
	File default_file = new File("private_data/Default.txt");
	
	//le fichier current contient le labyrinthe actuel manipulé par l'utilisateur
	File current_file = new File("private_data/Default.txt");
	
	//le fichier temporary est un fichier de sauvegarde temporaire
	File temporary_file = new File("private_data/temporary.txt");
	
	//le fichier current contient le résultat d'une exécution de Dijkstra
	File result_file = new File("private_data/result.txt");
	
	private int displayedOrNot = 0;
	private int uploadOrNot = 0;
	private int alreadyRun = 0;
	
	private Save SAVE;
	
	//constructeur de la fenêtre
	public Fenetre(String title, int display_test) throws IOException, MazeReadingException  {
		super(title);
		displayedOrNot = display_test;
		
		// Configurer les composants de la fenêtre
		setupInputPanel();
		setupButtonsPanel();
		setupMazePanel(default_file);
		setupUI();
	}
	
	//Configuration des deux menus
	private void setupInputPanel() {
	    Menu1.setLayout(new BoxLayout(Menu1, BoxLayout.LINE_AXIS));
	    Menu1.add(start);
	    Menu1.add(arrival);
	    Menu1.add(upload);
	    Menu1.add(edit);
	    Menu1.add(save);
	    Menu1.add(run);
	    Menu1.add(newmaze);
	    Menu1.add(quit);
	    
	    Menu2.setLayout(new BoxLayout(Menu2, BoxLayout.LINE_AXIS));
	    Menu2.add(wall);
	    Menu2.add(empty);
	    Menu2.add(back);
	}

	private void setupUI() throws IOException, MazeReadingException {
		
		this.setResizable(false);   //empêche le redimensionnement de la fenêtre
		
		// Configurer la taille optimale de la fenêtre en fonction des dimensions désirées par l'utilisateur
		if (HEIGHT < 15 && WIDTH < 15 && WIDTH > 10) {
			this.setSize(new Dimension(WIDTH*75,HEIGHT*75));
		}
		else if (WIDTH < 10) {
			this.setSize(new Dimension(700,HEIGHT*90));
		}
		else {
			if (HEIGHT < WIDTH) {
				this.setSize(new Dimension(1000,700));
			}
			else if (HEIGHT > WIDTH) {
				this.setSize(new Dimension(700,800));
			}
			else {
				this.setSize(new Dimension(800,800));
			}
		}
		
		//Configurer position de la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);		
	}
	
	//setup des boutons des deux menus
	private void setupButtonsPanel() {
		
		//Actions si le bouton Start est pressé
		start.addActionListener(e -> startButtonPressed = 1);
		start.addActionListener(e -> {
			if (startBoxAlreadyExists == 1){
				JOptionPane.showMessageDialog(null, "Départ déjà séléctionné", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

			else {
				JOptionPane.showMessageDialog(null, "Sélectionnez une case de départ", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//Actions si le bouton Arrival est pressé
		arrival.addActionListener(e -> arrivalButtonPressed = 1);
		arrival.addActionListener(e -> {
			if (arrivalBoxAlreadyExists == 1){
				JOptionPane.showMessageDialog(null, "Arrivée déjà séléctionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

			else {
				JOptionPane.showMessageDialog(null, "Sélectionnez une case d'arrivée", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});	
		
		//Actions si le bouton Upload est pressé
		upload.addActionListener(e -> {
			
			//on utilise un JFileChooser pour permettre à l'utilisateur de choisir un fichier
			JFileChooser dialogue = new JFileChooser(new File("data"));
			dialogue.setDialogTitle("Choisissez un fichier à charger de format .txt");
			File fichier;	
			if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				fichier = dialogue.getSelectedFile();
				String path = fichier.getAbsolutePath();
				String extension = path.substring(path.lastIndexOf("."));
				
				//on vérifie l'extension du fichier choisi
				if (extension.equals(".txt")) {
					uploadOrNot = 1;
					current_file = fichier;
					try {
						//on effectue un nouveau setup du labyrinthe à partir du fichier choisi s'il est valide
						setupMazePanel(fichier);
						setupUI();
					} catch (IOException e2) {
						e2.printStackTrace();
					} catch (MazeReadingException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Vous devez utiliser le format .txt", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		//Actions si le bouton Edit est pressé
		edit.addActionListener(e -> {

			//on bascule dans le sous-menu : Menu2
		    this.getContentPane().remove(Menu1);
			this.getContentPane().add(Menu2, BorderLayout.NORTH);
			repaint();
			revalidate();

			//Actions si le bouton Wall est pressé
			wall.addActionListener(e1 -> {
				wallButtonPressed = 1;
				emptyButtonPressed = 0;
			});
			
			//Actions si le bouton Empty est pressé
			empty.addActionListener(e1 -> {
				emptyButtonPressed = 1;
				wallButtonPressed = 0;
			});

			//Actions si le bouton Back est pressé
			back.addActionListener(e1 -> {
				emptyButtonPressed = 0;
				wallButtonPressed = 0;

				//on rebascule dans le Menu1
				this.getContentPane().remove(Menu2);
				this.getContentPane().add(Menu1, BorderLayout.NORTH);
				repaint();
				revalidate();
			});
		});
		
		//Actions si le bouton Save est pressé
		save.addActionListener(e -> {
			SAVE.filesave(temporary_file);	
			
			//on utilise un JFileChooser pour permettre à l'utilisateur de choisir un fichier
			JFileChooser dialogue = new JFileChooser(new File("data"));
			dialogue.setDialogTitle("Choisissez un fichier de sauvegarde de format .txt");
			File fichier;	
			if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				fichier = dialogue.getSelectedFile();
				String path = fichier.getAbsolutePath();
				String extension = path.substring(path.lastIndexOf("."));

				//on vérifie l'extension du fichier choisi par l'utilisateur
				if (extension.equals(".txt")) {
					
					//si l'extension correspond, on sauvegarde le fichier grâce à la classe SAVE
					SAVE.filesave(fichier);
					JOptionPane.showMessageDialog(null, "Labyrinthe sauvegardé dans le fichier " + fichier.getName(), "Sauvegarde", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Vous devez utiliser le format .txt", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}	
		});
		
		//Actions si le bouton Run est pressé
		run.addActionListener(e -> {
			
			//on vérifie qu'il y a bien une case départ
			if (startBoxAlreadyExists == 0) {
				JOptionPane.showMessageDialog(null, "Il manque la case Départ");
				JPanel newMazePanel = new JPanel();
				newMazePanel.setBackground(Color.black);
				this.getContentPane().add(newMazePanel, BorderLayout.CENTER);
				this.getContentPane().add(Menu1, BorderLayout.NORTH);
				this.repaint();
				try {
					setupUI();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (MazeReadingException e2) {
					e2.printStackTrace();
				}
			}
			
			//on vérifie qu'il y a bien une case arrivée
			if (arrivalBoxAlreadyExists == 0) {
				JOptionPane.showMessageDialog(null, "Il manque la case Arrivée");
				JPanel newMazePanel = new JPanel();
				newMazePanel.setBackground(Color.black);
				this.getContentPane().add(newMazePanel, BorderLayout.CENTER);
				this.getContentPane().add(Menu1, BorderLayout.NORTH);
				this.repaint();
				try {
					setupUI();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (MazeReadingException e2) {
					e2.printStackTrace();
				}
			}
			
			//s'il y a une case départ et une case arrivée, on réalise l'exécution
			if (startBoxAlreadyExists == 1 && arrivalBoxAlreadyExists == 1) {
				SAVE.filesave(temporary_file);
				try {
					//on utilise la classe Execute
					Execute.execute("private_data/temporary.txt",HEIGHT,WIDTH);
				} catch (MazeReadingException e3) {
					e3.printStackTrace();
				}
				
				//si le chemin n'est pas vide, on affiche le résultat contenu dans le fichier result
				if (Execute.pathEmptyOrNot == 0) {
					alreadyRun = 1;
					this.getContentPane().removeAll();
					try {
						setupMazePanel(result_file);
						setupUI();
					} catch (IOException e3) {
						e3.printStackTrace();
					} catch (MazeReadingException e3) {
						e3.printStackTrace();
					}
					 SAVE.filesave(current_file);
				}
				
				//si le chemin est vide, on affiche un message et on relance la fenêtre
				else if (Execute.pathEmptyOrNot == 1) {
						alreadyRun = 1;
						JPanel newMazePanel = new JPanel();
						newMazePanel.setBackground(Color.black);
						this.getContentPane().add(newMazePanel, BorderLayout.CENTER);
						this.getContentPane().add(Menu1, BorderLayout.NORTH);
						this.repaint();
						try {
							setupUI();
						} catch (IOException e2) {
							e2.printStackTrace();
						} catch (MazeReadingException e2) {
							e2.printStackTrace();
						}
						Execute.pathEmptyOrNot = 0;
					}
			}
			});
	
		//Actions si le bouton New Maze est pressé
			newmaze.addActionListener(e1 -> {
				this.dispose();
				try {
					uploadOrNot = 0;
					@SuppressWarnings("unused")
					Fenetre new_LABY = new Fenetre("Labyrinthe Dijkstra",0);
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (MazeReadingException e2) {
					e2.printStackTrace();
				}
			});
		
		//Action si le bouton Quit est pressé
		quit.addActionListener(e -> System.exit(0));
	}

	private void setupMazePanel(File fichier) throws IOException, MazeReadingException {
		
		//on ne demande l'interface qu'au premier lancement
		if (displayedOrNot == 0) {
			String[] Affichage = {"Interface champêtre", "Interface basique"};
			JOptionPane.showMessageDialog(null, "Bienvenue dans le projet Java de 1ère année", "Information", JOptionPane.INFORMATION_MESSAGE);			
			UIbasicOrNot = JOptionPane.showOptionDialog(null, "Veuillez choisir l'affichage du labyrinthe", "Choix de l'affichage", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, Affichage, Affichage[1]);
			displayedOrNot = 1;
		}
		
		//si le setup n'est pas demandé par le bouton upload, on demande les dimensions à l'utilisateur
		if (uploadOrNot == 0) {
			if (alreadyRun == 0) {
			GetHeightandWidth User = new GetHeightandWidth();
			HEIGHT = User.height;
			WIDTH = User.width;
			}
			//sinon on détecte directement les dimensions du labyrinthe uploadé
			else {
				GetHeightandWidth User = new GetHeightandWidth(fichier);
				HEIGHT = User.height;
				WIDTH = User.width;
			}
		}
		
		else if (uploadOrNot == 1) {
			GetHeightandWidth User = new GetHeightandWidth(fichier);
			HEIGHT = User.height;
			WIDTH = User.width;
			uploadOrNot = 1;
		}

//		Initialisation par défaut : une interface avec des cases 
//		vierges où l'utilisateur peut construire le labyrinthe qu'il souhaite
		Maze default_maze = new Maze(HEIGHT,WIDTH);
		default_maze.saveToTextFile(default_file);
		
		SAVE = new Save(HEIGHT,WIDTH);
		
		arrivalBoxAlreadyExists = 0;
		startBoxAlreadyExists = 0;
		
		startButtonPressed = 0;
		arrivalButtonPressed = 0;
		
		this.getContentPane().removeAll();
		JPanel mazePanel = new JPanel();
		mazePanel.setBackground(Color.black);
		this.getContentPane().add(mazePanel, BorderLayout.CENTER);
		this.getContentPane().add(Menu1, BorderLayout.NORTH);
		GridLayout grid = new GridLayout(HEIGHT, WIDTH, 1 ,1);
		mazePanel.setLayout(grid);

			FileReader in = null;
			BufferedReader br = null;
			String temporary = new String(); // on crée une variable temporaire qui va contenir une ligne du fichier texte
			int compteur = 0;		
			try {
				in = new FileReader(fichier.getPath());
				br = new BufferedReader(in); 
				for (int i = 0; i < HEIGHT ; i++) {
					temporary = br.readLine();	
					compteur = compteur + 1;
					for (int j = 0; j < WIDTH ; j++) {		
						
						//pour chaque case du labyrinthe, on crée un objet MazeButton
						MazeButton mazeButton = new MazeButton(i,j);
						mazeButton.setMargin(new Insets(0,0,0,0));
						mazeButton.setBorder(null);
						mazePanel.add(mazeButton);

						//Actions si un bouton du labyrinthe est pressé :
						mazeButton.addActionListener(e -> {

							//Si le bouton start est pressé et il n'y pas encore de départ dans le labyrinthe
							if (startBoxAlreadyExists == 0 && startButtonPressed == 1) {

								//Si la case cliquée n'est pas celle de l'arrivée (pour ne pas superposer les cases de départ et d'arrivée
								if (!(SAVE.save.getBoxSymbol(mazeButton.line, mazeButton.column).equals("A"))) {
									mazeButton.changeIcon_D();
									startBoxAlreadyExists = 1;
									SAVE.changeToD(mazeButton.line,mazeButton.column);
								}
							}
						});
						mazeButton.addActionListener(e -> {

							//Si le bouton arrival est pressé et il n'y pas encore d'arrivée dans le labyrinthe
							if (arrivalBoxAlreadyExists == 0 && arrivalButtonPressed == 1) {
								
								//Si la case cliquée n'est pas celle du départ (pour ne pas superposer les cases de départ et d'arrivée
								if(!(SAVE.save.getBoxSymbol(mazeButton.line, mazeButton.column).equals("D"))) {
									mazeButton.changeIcon_A();
									SAVE.changeToA(mazeButton.line,mazeButton.column);
									arrivalBoxAlreadyExists = 1;
								}
							}
						});

						mazeButton.addActionListener(e -> {

							//pour rajouter un mur dans le labyrinthe
							if (wallButtonPressed == 1) {

								//si on recouvre la case d'arrivée, il faut pouvoir la remettre dans le labyrinthe
								if (SAVE.save.getBoxSymbol(mazeButton.line, mazeButton.column).equals("A")) {
									mazeButton.changeIcon_W();
									SAVE.changeToW(mazeButton.line,mazeButton.column);
									arrivalBoxAlreadyExists = 0;
									arrivalButtonPressed = 0;
								}

								//si on recouvre la case de départ, il faut pouvoir la remettre dans le labyrinthe
								else if (SAVE.save.getBoxSymbol(mazeButton.line, mazeButton.column).equals("D")) {
									mazeButton.changeIcon_W();
									SAVE.changeToW(mazeButton.line,mazeButton.column);
									startBoxAlreadyExists = 0;
									startButtonPressed = 0;
								}

								else {
									mazeButton.changeIcon_W();
									SAVE.changeToW(mazeButton.line,mazeButton.column);
								}
							}	
						});

						mazeButton.addActionListener(e -> {

							//pour rajouter une case vide dans le labyrinthe
							if (emptyButtonPressed == 1) {

								//si on recouvre la case d'arrivée, il faut pouvoir la remettre dans le labyrinthe
								if (SAVE.save.getBoxSymbol(mazeButton.line, mazeButton.column).equals("A")) {
									mazeButton.changeIcon_E();
									SAVE.changeToE(mazeButton.line,mazeButton.column);
									arrivalBoxAlreadyExists = 0;
									arrivalButtonPressed = 0;
								}

								//si on recouvre la case de départ, il faut pouvoir la remettre dans le labyrinthe
								else if(SAVE.save.getBoxSymbol(mazeButton.line, mazeButton.column).equals("D")) {
									mazeButton.changeIcon_E();
									SAVE.changeToE(mazeButton.line,mazeButton.column);
									startBoxAlreadyExists = 0;
									startButtonPressed = 0;
								}
								else {
									mazeButton.changeIcon_E();
									SAVE.changeToE(mazeButton.line,mazeButton.column);
								} 
							}	
						});
						switch (String.valueOf(temporary.charAt(j))) {
						case "E" :
							mazeButton.changeIcon_E();
							SAVE.changeToE(mazeButton.line,mazeButton.column);
							break;
						case "W" :
							mazeButton.changeIcon_W();
							SAVE.changeToW(mazeButton.line,mazeButton.column);
							break;
						case "D" :
							mazeButton.changeIcon_D();
							SAVE.changeToD(mazeButton.line,mazeButton.column);
							startBoxAlreadyExists = 1;
							break;
						case "A" :
							mazeButton.changeIcon_A();
							SAVE.changeToA(mazeButton.line,mazeButton.column);
							arrivalBoxAlreadyExists = 1;
							break;
						case "." :
							mazeButton.changeIcon_S();
							break;
						default :
							//exception si un autre caractère est dans le fichier
							mazeButton.changeIcon_E();
							SAVE.changeToE(mazeButton.line,mazeButton.column);
							JOptionPane.showMessageDialog(null,"Mauvais caractère inséré ligne " + i + ", colonne " + j + "\nIl a été remplacé par une case vide");	
						}
					}
				}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Le fichier demandé n'existe pas");
			} catch (IOException e) {
				e.printStackTrace();
			} 
			finally {

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