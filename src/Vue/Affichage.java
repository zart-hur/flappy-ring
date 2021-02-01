package Vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controler.Avancer;
import Controler.Controls;
import Controler.Voler;
import Modele.Etat;
import Modele.Parcours;


//import java.awt.Point;


@SuppressWarnings("serial")   // parce que je n'ai pas compris dans notre exercice l'interet d'avoir un serial ID number .
public class Affichage extends JPanel {

	/** pour les interactions avec la classe Etat*/
	private  Etat etat;
	private Controls clicks; 
	private Parcours parcours;

	/* Constantes */

	/** taille de la fenetre */
	private static final int LARG = 1000;
	private static final int HAUT = 600;

	/**coordonnées du cercle*/
	private static final int ABS_OVAL = 100;
	private static final int ORD_OVAL = 200; //la hauteur initiale. C'est Etat.hauteur qui se base sur cet 'y' que l'on utilisera en pratique.

	/**dimmensions du cercle*/
	private static final int WIDTH = 50; //largeur cercle
	public static final int HEIGHT = 50; //hauteur cercle


	/** Constructeur */
	public Affichage(Etat eta, Parcours parc) {

		this.etat = eta;
		this.parcours = parc;
		setPreferredSize(new Dimension(LARG, HAUT));
		this.clicks = new Controls(this,etat);
		addMouseListener(new Controls(this,etat));
	}
	
	/**************METHODES GET *******************/
	
	public static int getHauteurFenetre(){
		return HAUT;
	}

	public static int getLargeurFenetre(){
		return LARG;
	}

	public static int getOrdOvale(){
		return ORD_OVAL;
	}

	public static int getAbsOvale(){
		return ABS_OVAL;
	}

	public static int getTailleOvale() {
		return HEIGHT;
	}

	/**************METHODES DE DESSIN *******************/
		
	/**
	 * methode qui dessine l'ovale/cercle
	 * @param g
	 */
	private void dessineOval(Graphics g) {
		g.setColor(Color.BLACK);//couleur du cercle/oval
		//g.drawOval((ABS_OVAL-WIDTH/2), this.etat.getHauteur(), WIDTH, HEIGHT);//dessine le contours du cercle/oval (ligne de code remplacee par la suivante)
		g.fillOval((ABS_OVAL-WIDTH/2), this.etat.getHauteur(), WIDTH, HEIGHT);//dessine le contours et l'interieur du cercle/oval
	}


	/**cette methode dessine le parcours ( deux lignes brisees (haute et basse) remplies par des polygones (au dessus et en dessous)):
	 * pour donner l'effet de cet ligne brisee, on veut dessiner plusieurs lignes de differentes directions reliees les unes aux autres
	 *  pour cela on recupere  une liste de Points cree et mise a jour dans la classe parcours .
	 *  puisque l'on cree finalement des polygones, cette liste de points sera sous la forme de 2 tableaux,
	 *   contenant respectivement les abscisses et les ordonnees des points
	 *   puisque l'on cree finalement des polygones on laisse en commentaire les partie referant a la creation des deux lignes brisees
	 * @param g
	 */
	private void dessineParcours(Graphics g) {
		int taille = this.parcours.getLigneHaut().size();
		int[] tabXH= new int[taille+2];//tableaux X et Y pour fillPolygon haut
		int[] tabYH= new int[taille+2];	
		int[] tabXB= new int[taille+2];//tableaux X et Y pour fillPolygon bas
		int[] tabYB= new int[taille+2];

		tabYH[0]=0;//Prend un abscisse et un ordonne pour l'angle en haut a gauche de la fenetre
		tabXH[0]=0;
		tabYB[0]=HAUT;//Prend un abscisse et un ordonne pour l'angle en bas a gauche de la fenetre
		tabXB[0]=0;
		g.setColor(Color.darkGray);	//on choisit la couleur de la caverne (et avant des lignes  : voir commentaires ci-dessous)
		for(int i=0;i<taille-1;i++) {//entre chaques paire de points ( d'arraylist ligne on remplie les tableaux)
			/*
			Point pB1=this.parcours.getLigneBas().get(i);   //les points pour les lignes (de-commenter aussi l'import pour les Points)
			Point pB2=this.parcours.getLigneBas().get(i+1);
			Point pH1=this.parcours.getLigneHaut().get(i);
			Point pH2=this.parcours.getLigneHaut().get(i+1);
			 */
			tabXH[i+1]=this.parcours.getLigneHaut().get(i).x;//recupere l'abscisse puis l'ordonné des points du parcours pour fillPolygon haut
			tabYH[i+1]=this.parcours.getLigneHaut().get(i).y;
			tabXB[i+1]=this.parcours.getLigneBas().get(i).x;//recupere l'abscisse puis l'ordonné des points du parcours pour fillPolygon bas
			tabYB[i+1]=this.parcours.getLigneBas().get(i).y;
			//g.drawLine(pB1.x,pB1.y,pB2.x,pB2.y);// dessine la ligne du bas entre deux points de de l'arraylist<Point> ligneBas
			//g.drawLine(pH1.x,pH1.y,pH2.x,pH2.y);// dessine la ligne du bas entre deux points de de l'arraylist<Point> ligneBas
		}
		//on cree le dernier point des polygones en haut a droite (pour celui du haut) et en bas a droite (pour celui du bas)
		tabYH[taille]=0;
		tabXH[taille]=LARG;
		tabYB[taille]=HAUT;
		tabXB[taille]=LARG;

		g.fillPolygon(tabXH, tabYH, (taille+1)); ////pour colorier en dessus de la ligne du haut
		g.fillPolygon(tabXB, tabYB, (taille+1)); ////pour colorier en dessous de la ligne du bas
	}


	/**
	 * methode qui dessine/affiche le score au centre en haut de la fenetre
	 * @param g
	 */
	private void dessineScore(Graphics g) {
		g.setColor(Color.YELLOW);//choix de la couleur des lettres et chiffres du score
		g.setFont(new Font( "Cambria" ,Font.BOLD,30));// choix de la police et de la taille des lettres et chiffres du score
		g.drawString("Score :"+(this.parcours.getPosition()/Parcours.getAvance())/10, LARG/2, HAUT/16);//on place et dessine un score se basant sur la position
	}
	/**
	 * methode qui dessine/affiche un message de fin avec le score final et termine les thread par la meme occasion
	 * @param g
	 */
	private void dessineFin(Graphics g) {
		Avancer.setfin(); //termine le thread de la Classe Avancer
		Voler.setfin();  //termine le thread de la Classe Voler
		//ligne suivante : affiche un message indiquant que la partie est finie est indique un score final en se basant sur la position (voir dessineScore())
		JOptionPane.showMessageDialog(this, "Fin de partie !  Score : "+(this.parcours.getPosition()/Parcours.getAvance())/10);
	}
	
	/**
	 * Override de la methode paint de l'interface de JComponent qui:
	 *  dessine le cercle, la "caverne et le score dans la fenetre.
	 *  et verifie si la partie est finie auquel cas arrete un MouseListener et affiche le message de fin
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g); //permet de raffraichir la fenetre en appelant repaint
		if (this.etat.getflagTestperdu()) {//si la partie est finie
			removeMouseListener(clicks);//enleve le MouseListener cest a dire l'action de saut produite par un clique
			if(this.etat.getPerduDuHaut()) {dessineOval(g);} //tentative de rectification de l'ordonnee du cercle en cas de saut qui depasse la ligne
			dessineFin(g);	
			//g.clearRect(0, 0, LARG, HAUT);// pour plutot mettre un image genre de batman a la fin.. (pas eu le temps)  	  

		}		
		g.clearRect(0, 0, LARG, HAUT); //pour aider a redessiner la fenetre, efface le precedent affichage sur celle ci		
		dessineOval(g);
		dessineParcours(g);
		dessineScore(g);
	}
}