package Modele;


import java.awt.Point;
import java.util.ArrayList;

import Vue.Affichage;
import Vue.Parcours;

public class Etat {
private static int hauteur = Affichage.getOrdOvale();
private static Parcours parcours = new Parcours();
private final int SAUT=60;
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

public int getPosition() {
	return parcours.getPosition();
}
/** 
 * getParcours permet de recuperer l'Arraylist<Point> ligne
 *  */
public ArrayList<Point> getParcours() {
	ArrayList<Point> ligne= parcours.getLigne();
	for(int i=0;i<ligne.size();i++) {
		double newX =ligne.get(i).getX()- parcours.getPosition();
		//si le point va pour sortir de la fenetre, on le supprime et on en cree un nouveau en bout de liste
		if(newX<(-(parcours.getEspaceMin())*2)) { //parce que 0 ne suffisait pas, en partie en raison de cet ESPACE_MIN (ce n'est pas tres clair non )
			ligne.remove(i);//on supprime le points (quand il va pour sortir de la fenetre)		
			parcours.AjouteFindeListeRandomP();//on rajoute un point a la fin de la liste
		}
		
	}
	return ligne;
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

