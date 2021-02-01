package Modele;

import java.util.Random;

import Vue.Affichage;

import java.awt.Point;
import java.util.ArrayList;

public class Parcours {


	/** position et AVANCE sont les variables qui permettent de faire "avancer" le parcours*/
	private  int position =0; // position s'incremente tant que la partie continue
	private final static int AVANCE = 2;//valeur d'incrementation de la position

	/** Creation des Arraylist pour contenir les Points des Ligne haut et Bas */
	private  ArrayList<Point> ligneBas= new ArrayList<Point>();//la liste de points que l'on relira pour faire le parcours du bas.
	private  ArrayList<Point> ligneHaut= new ArrayList<Point>();//la liste de points que l'on relira pour faire le parcours du haut.

	/** Constantes pour borner et aider a definir les abscisses et ordonnees des points de lignes generes aleatoirement */
	private static final int BORNE_MIN =(Affichage.getTailleOvale()*6); //ordonnee la + en bas de la fenetre possible (pour ligneBas)
	private static final int BORNE_MAX =(Affichage.getHauteurFenetre()/2)-(Affichage.getTailleOvale()/6);//ordonnee la + en haut de la fenetre pour ligneBas
	private static final int ESPACE_MIN = 45; //on veut que chaques abscisses des points soit au minimum espaces de 100.
	private static final int LARGEUR_CAVERNE = 300;

	/** Constantes pour les premiers points des lignes (pour un effet d'entree de caverne au demarrage)  */
	private static final int DEPARTBAS = Affichage.getOrdOvale()+200;//le depart du parcoursBas sera en dessous de l'ovale
	private static final int DEPARTHAUT = Affichage.getOrdOvale()+100-LARGEUR_CAVERNE;//le depart du parcoursHaut sera au dessus de l'ovale
	private static final int  ENTRANCE=ESPACE_MIN*5;

	private static final Random rand = new Random(); //variable aleatoire pour pouvoir utiliser la bibliothèque java.util.Random.

	/**Constructeur*/
	public Parcours(){
		initLignes(); //on initialise les lignes a la creation d'une l'instance de Parcours
	}

	/***************METHODES GET ******************/

	public int getLargeurCaverne(){
		return LARGEUR_CAVERNE;
	}

	public ArrayList<Point> getLigneBas() {

		return this.ligneBas;
	}

	public ArrayList<Point> getLigneHaut() {

		return this.ligneHaut;
	}

	public int getPosition() {
		return this.position;
	}

	public static int getAvance() {
		return AVANCE;
	}

	/**
	 * Cette methode get est un peu speciale car elle permet de recuperer l'index du point le plus proche a gauche du cercle 
	 * (marche pour les points de ligneHaut et ligneBas)
	 * @return l'index du premier point dont l'abscisse est a gauche du cercle
	 */
	public int getPointProches(){
		int i = 0;
		while(this.ligneBas.get(i).x <= Affichage.getAbsOvale()) { //marcherai aussi avec ligneHaut puis qu'elles ont le meme abscisse
			i++;
		}
		return i-1;// -1 car pour sortir du while ont a pris le premier point a droite du cercle, et ont veut celui de gauche.
	}

	/***************AUTRES METHODES******************/

	/**
	 * setPosition() permet de mettre a jour la position (la faire "avancer")
	 * en meme temps que de mettre a jour l'abscisse des points de ligneHaut et ligneBas
	 */
	public  void setPosition() {
		this.ligneBas.forEach(p -> p.x -= AVANCE); //modifie l'abscisse des points dans l'arraylist ligneBas
		this.ligneHaut.forEach(p -> p.x -= AVANCE); //modifie l'abscisse des points dans l'arraylist ligneHaut
		this.position = this.position+AVANCE;		
	}

	/**
	 * MaJLignes() permet sous un certaine condition de supprimer les points qui sortent de la fenetre et d'appeller a en rajouter en bout de liste
	 * cette methode est  appellee a chaque utilisation du thread de la Classe Avancer
	 */
	public void MaJLignes() {		
		int ByeByeX =ligneBas.get(1).x;//on recup l'absisse du deuxieme point de ligneBas (c'est la meme abscisse que pour le point de ligneHaut)				
		if(ByeByeX<=0) { //si l'abscisse ByeByeX (du deuxieme point de la ligne) est sorti de la fenetre (a gauche),		
			this.ligneBas.remove(0); // on supprime (le premier point) celui a la gauche de ByeByeX pour ligneBas et ligneHaut
			this.ligneHaut.remove(0); 
			ajouteFindeListesRandomP();//et on rajoute un point au bout des 2 listes
		}		
	}

	/**
	 * ajouteFindeListesRandomP() agit exactement de la meme maniere qu'initLignes pour creer des points aleatoires aux ligneHaut et ligneBas
	 * a ceci pret que cette methode potentiellement appellee depuis la methode MaJLignes() a chaque utilisation du thread de la Classe Avancer
	 */
	private void ajouteFindeListesRandomP() {
		int x= (ligneBas.get(ligneBas.size()-1).x)+rand.nextInt(100)+ESPACE_MIN;//abscisse du dernier point de ligneBas + une random val(+ESPACE_MIN)
		//ligne suivante: l'ordonnee aleatoire du point est bornee en fonction de la taille de la fenetre et de l'ovale.(/!\ borne 2fois utilisee)
		int y=rand.nextInt(BORNE_MAX)+BORNE_MIN;  
		Point pB = new Point(x,y);
		Point pH = new Point(x,(y-LARGEUR_CAVERNE));
		ligneBas.add(ligneBas.size(),pB); // on ajoute le point aux coordonees "aleatoires" a la fin de l'arraylist ligne
		ligneHaut.add(ligneHaut.size(),pH); // on ajoute le point aux coordonees "aleatoires" a la fin de l'arraylist ligne
	}


	/** initLigne rempli l'Arraylist<point> ligneBas et ligneHaut de points ayant:
	 *  une absisse croisssante(mais qui "avance" de valeur aleatoire en valeur aleatoire avec un ESPACE_MIN au minimum)
	 *  et une ordonnee aleatoire bornee
	 *  pour ligne haut on "ajoute" LARGEUR_CAVERNE a l'ordonnee
	 *  //pour les 2 premiers points de chaques lignes, ont choisit nous meme les coordonnees pour donner un effet d'entree de caverne.
	 */
	private void initLignes() {
		Point startB = new Point(0,DEPARTBAS); //on cree le premier point de ligneBas (et ligneHaut) pour qu'il se situe au bas (en haut) du cercle
		Point startH = new Point(0,DEPARTHAUT);
		ligneBas.add(startB); //on ajoute les points aux Arraylist ligneBas et ligneHaut
		ligneHaut.add(startH);
		Point suiteStartB = new Point(ENTRANCE,DEPARTBAS-30); //on choisit le deuxieme point de ligneBas pour faire une entree plus 'entonoir'
		Point suiteStartH = new Point(ENTRANCE,DEPARTHAUT+30); //on choisit le deuxieme point de ligneBas pour faire une entree plus 'entonoir'
		ligneBas.add(suiteStartB); //on ajoute les points aux Arraylist ligneBas et ligneHaut
		ligneHaut.add(suiteStartH);
		int x=ENTRANCE; //le premier x Random va commencer apres cet abscisse
		while(x<(Affichage.getLargeurFenetre()*2)) { //on veut creer des point jusqu'a 2 fois la largeur de la fenetre
			x+=rand.nextInt(100)+ESPACE_MIN;//creation d'une abscisse random commun aux deux lignes avec un espace minimum entre deux abscisses
			//ligne suivante: les ordonnees aleatoires des points sont bornees en fonction de la taille de la fenetre et de l'ovale.
			int y=rand.nextInt(BORNE_MAX)+BORNE_MIN; 
			Point pB = new Point(x,y);
			Point pH = new Point(x,y-LARGEUR_CAVERNE); //on décale l'ordonnee pour correspondre a un point au-dessus (pour ligneHaut)
			ligneBas.add(pB); // on ajoute les point aux coordonees "aleatoires" a l'arraylist ligneBas et l'arraylist ligneHaut
			ligneHaut.add(pH); 
		}
	}

	/**Pseudo-code de initLigneV1 (avec une seule ligne) :
	 * void initLigne(){
	 * Point start = Point(0,ordonneOvale)
	 * ligne.add(0,start)
	 * int x=0
	 * int index=1;
	 * while(x<largeur fenetre * 2)
	 * 	x+=RandomValue
	 * 	y= RandomValue <- bornée entre 0(plus quelques pixels..) et la hauteur de la fenetre (moins quelques pixels)
	 * 	Point p = new Point()
	 * 	ligne.add(index,p)
	 */


}
