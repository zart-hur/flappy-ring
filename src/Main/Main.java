package Main;




import javax.swing.JFrame;

import Controler.Avancer;
import Controler.Voler;
import Modele.Etat;
import Modele.Parcours;
import Vue.Affichage;

    
public class Main {
	
	public static void main(String [] args) {
	
		/**Creation d'instances de Classes*/
		JFrame fenetre = new JFrame("Flappy Ring");//instance de fenetre
		Parcours parcours = new Parcours();//instance de
		Etat etat = new Etat(parcours);//instance de
		Affichage affichage = new Affichage( etat,parcours);//instance de
	
		/**activation de la fenetre et ajout de contenu (affichage)*/
	    fenetre.add(affichage);
		fenetre.pack(); 
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		/** creation des Threads*/
		new Thread(new Voler(etat,affichage)).start();
		new Thread(new Avancer(parcours,affichage)).start();
				
	  }	
	
}
