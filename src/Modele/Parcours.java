package Modele;

import java.util.Random;

import Vue.Affichage;

import java.awt.Point;
import java.util.ArrayList;

public class Parcours {

	private static final Random rand = new Random(); //variable aleatoire pour pouvoir utiliser la bibliothèque java.util.Random.

	private  ArrayList<Point> ligneBas= new ArrayList<Point>();//la liste de points que l'on relira pour faire le parcours du bas.
	private  ArrayList<Point> ligneHaut= new ArrayList<Point>();//la liste de points que l'on relira pour faire le parcours du haut.

	private final int ESPACE_MIN = 45; //on veut que chaques abscisses des points soit au minimum espaces de 100.
	private final int DEPARTBAS = Affichage.getOrdOvale()+200;//le depart du parcours sera au bas de l'ovale
	private final int DEPARTHAUT = Affichage.getOrdOvale()+100-LARGEUR_CAVERNE;//le depart du parcours sera au bas de l'ovale

	public final static int AVANCE = 2;
	private  int position =0;

	private static final int BORNE_MIN =(Affichage.getTailleOvale()*6);
	private static final int BORNE_MAX =(Affichage.getHauteurFenetre()/2)-(Affichage.getTailleOvale()/6);
	
	private static final int LARGEUR_CAVERNE = 300;


	/**Constructeur*/
	public Parcours(){
		initLigne();
	}
	
	public int getLargeurCaverne(){
		return LARGEUR_CAVERNE;
	}

	public int getPointProches(){
		int i = 0;
		while(this.ligneBas.get(i).x <= Affichage.getAbsOvale()) { //marcherai aussi avec ligneHaut puis qu'elles ont le meme abscisse
			i++;
		}
		return i-1;
	}

	/** 
	 * getParcours permet de recuperer l'Arraylist<Point> ligne
	 *  */
	public void MaJLignes() {
		
		
		int newX =ligneBas.get(1).x;
		
		
		//si le point va pour sortir de la fenetre, on le supprime et on en cree un nouveau en bout de liste
		if(newX<=0) { //parce que 0 ne suffisait pas, en partie en raison de cet ESPACE_MIN (ce n'est pas tres clair non )
			//on supprime le points (quand il va pour sortir de la fenetre)	
			//System.out.println("coordonnées removées :"+(ligne.get(i).x- this.position)+" ligne :"+i);
			this.ligneBas.remove(0);
			this.ligneHaut.remove(0);
			//System.out.println("coordonnées de nwX :"+newX+" ligne :"+(i+1));	
			ajouteFindeListesRandomP();//on rajoute un point a la fin des 2 listes
		}		

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

	public int getEspaceMin() {
		return this.ESPACE_MIN;
	}

	public  void setPosition() {
		this.ligneBas.forEach(p -> p.x -= AVANCE); //modifie l'abscisse des p dans l'arraylist ligneBas
		this.ligneHaut.forEach(p -> p.x -= AVANCE); //modifie l'abscisse des p dans l'arraylist ligneHaut
		this.position = this.position+AVANCE;		
	}
	
	public void ajouteFindeListesRandomP() {

		int x= (ligneBas.get(ligneBas.size()-1).x)+rand.nextInt(100)+ESPACE_MIN;//abscisse du dernier point de la liste auquel on rajoute une random val(+ESPACE_MIN)
		//ligne suivante: l'ordonnee aleatoire du point est bornee en fonction de la taille de la fenetre et de l'ovale.(/!\ borne 2fois utilisee)
		int y=rand.nextInt(BORNE_MAX)+BORNE_MIN;  
		Point pB = new Point(x,y);
		Point pH = new Point(x,(y-LARGEUR_CAVERNE));
		ligneBas.add(ligneBas.size(),pB); // on ajoute le point aux coordonees "aleatoires" a la fin de l'arraylist ligne
		ligneHaut.add(ligneHaut.size(),pH); // on ajoute le point aux coordonees "aleatoires" a la fin de l'arraylist ligne
	}



	/**Pseudo-code de initLigne :
	 * void initLigne(){
	 * Point start = Point(0,ordonneOvale)
	 * ligne.add(0,start)
	 * int x=0
	 * int index=1;
	 * while(x<largeur fenetre * 2)
	 * 	x+=RandomValue
	 * 	y= RandomValue <- bornée entre 0(plus quelques pixels) et la hauteur de la fenetre (moins quelques pixels)
	 * 	Point p = new Point()
	 * 	ligne.add(index,p)
	 */

	/** initLigne rempli l'Arraylist<point> ligne de point ayant:
	 *  une absisse croisssante(mais qui "avance" de valeur aléatoire en valeur aleatoire)
	 *  et une ordonnee aleatoire bornée
	 */
	private void initLigne() {
		Point startB = new Point(0,DEPARTBAS); //on choisit le premier point de ligneBas pour qu'il se situe au bas de l'ovale
		Point startH = new Point(0,DEPARTHAUT); //on choisit le premier point de ligneBas pour qu'il se situe au bas de l'ovale
		ligneBas.add(startB);
		ligneHaut.add(startH);
		int  entrance=ESPACE_MIN*5;
		Point suiteStartB = new Point(entrance,DEPARTBAS-30); //on choisit le deuxieme point de ligneBas pour faire une entree plus 'entonoir'
		Point suiteStartH = new Point(entrance,DEPARTHAUT+30); //on choisit le deuxieme point de ligneBas pour faire une entree plus 'entonoir'
		ligneBas.add(suiteStartB);
		ligneHaut.add(suiteStartH);
		int x=entrance;
		while(x<(Affichage.getLargeurFenetre()*2)) { //on veut creer des point jusqu'a 2 fois la largeur de la fenetre
			x+=rand.nextInt(100)+ESPACE_MIN;
			//ligne suivante: les ordonnees aleatoire des points sont bornees en fonction de la taille de la fenetre et de l'ovale.(/!\ borne 2fois utilisee)
			int y=rand.nextInt(BORNE_MAX)+BORNE_MIN; 
			Point pB = new Point(x,y);
			Point pH = new Point(x,y-LARGEUR_CAVERNE); //on décale l'ordonnee pour correspondre a un point au-dessus
			ligneBas.add(pB); // on ajoute le point aux coordonees "aleatoires" a l'arraylist ligneBas
			ligneHaut.add(pH); // on ajoute le point aux coordonees "aleatoires" a l'arraylist ligneHaut


		}


	}






}
