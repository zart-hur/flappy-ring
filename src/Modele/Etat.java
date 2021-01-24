package Modele;


import java.awt.Point;
import java.util.ArrayList;

import Vue.Affichage;
import Vue.Parcours;

public class Etat {
private static int hauteur = Affichage.getOrdOvale();
private static Parcours parcours = new Parcours();
private final int SAUT=40;
private final static int GRAVITE=4;

public Etat() {
	//affichage=aff;
	//new Thread(new Voler(this,aff)).start();
	//new Thread(new Voler(this)).start();
}
/**
 * fonction qui recupere la valeur de hauteur
 **/
public int getHauteur(){ 
	return hauteur;
}

/** getParcours permet de recuperer l'Arraylist<Point> ligne */
public ArrayList<Point> getParcours() {
	return parcours.getLigne();
}


public void jump(){ 
	if((hauteur) > 0)  // pour que l'on ne puisse pas sortir de la fenettre
		hauteur=hauteur-this.SAUT;
}

public static void moveDown() {
	if((hauteur) < (Affichage.getHauteurFenetre()-Affichage.getTailleOvale())) {  // pour que l'on ne puisse pas sortir de la fenettre
		hauteur=hauteur+GRAVITE;
	}
}

}

