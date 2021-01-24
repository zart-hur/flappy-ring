package Vue;

import java.util.Random;

import java.awt.Point;
import java.util.ArrayList;

public class Parcours {
	
	private static final Random rand = new Random(); //variable aleatoire pour pouvoir utiliser la bibliothèque java.util.Random.
	
	private  ArrayList<Point> ligne= new ArrayList<Point>();//la liste de points que l'on relira pour faire le parcours.
	
	private final int ESPACE_MIN = 150; //on veut que chaques abscisses des points soit au minimum espaces de 100.
	private final int DEPART = Affichage.getOrdOvale()+50;//le depart du parcours sera au bas de l'ovale
	
	private final static int AVANCE = 5;
	private static  int position =0;
	
	
	
	
	/**Constructeur*/
	public Parcours() {
	initLigne();
	}
	
	/** cette methode permet (a Etat ) de recuperer l'arraylist ligne.
	 * @return ligne
	 */
	public ArrayList<Point> getLigne() {
		return this.ligne;
	}

	
	
	public int getPosition() {
		return position;
	}
	
	public int getEspaceMin() {
		return this.ESPACE_MIN;
	}
	
	public static void setPosition() {
		position = position+AVANCE;
	}
	
	public void AjouteFindeListeRandomP() {
		
		int x= (ligne.get(ligne.size()-1).x)+rand.nextInt(100)+ESPACE_MIN;//abscisse du dernier point de la liste auquel on rajoute une random val(+ESPACE_MIN)
		//ligne suivante: l'ordonnee aleatoire du point est bornee en fonction de la taille de la fenetre et de l'ovale.(/!\ borne 2fois utilisee)
		int y=rand.nextInt((Affichage.getHauteurFenetre()/2)+Affichage.getTailleOvale())+(Affichage.getTailleOvale()/4); 
		Point p = new Point(x,y);
		ligne.add(ligne.size(),p); // on ajoute le point aux coordonees "aleatoires" a la fin de l'arraylist ligne
		
		
		
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
		Point start = new Point(0,DEPART); //on choisit le premier point pour qu'il se situe au bas de l'ovale
		ligne.add(0,start);
		int x=0;
		int index=1;//a partir du deuxieme point, on incrementera l'index
		
		while(x<(Affichage.getLargeurFenetre()*2)) { //on veut creer des point jusqu'a 2 fois la largeur de la fenetre
			x+=rand.nextInt(100)+ESPACE_MIN;
			//ligne suivante: les ordonnees aleatoire des points sont bornees en fonction de la taille de la fenetre et de l'ovale.(/!\ borne 2fois utilisee)
			int y=rand.nextInt((Affichage.getHauteurFenetre()/2)+Affichage.getTailleOvale())+(Affichage.getTailleOvale()/4); 
			Point p = new Point(x,y);
			ligne.add(index,p); // on ajoute le point aux coordonees "aleatoires" a l'arraylist ligne
			index++;
		}
		
	}
	
	


	
	
}
