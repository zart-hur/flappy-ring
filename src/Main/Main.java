package Main;




import javax.swing.JFrame;

import Controler.Avancer;
import Controler.Voler;
import Modele.Etat;
import Modele.Parcours;
import Vue.Affichage;




    
public class Main {
	
	public static void main(String [] args) {
	
		JFrame fenetre = new JFrame("Flappy Ring");
		Parcours parcours = new Parcours();
		Etat etat = new Etat(parcours);
		Affichage affichage = new Affichage( etat,parcours);
	
	    fenetre.add(affichage);
		fenetre.pack(); 
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		
		new Thread(new Voler(etat,affichage)).start();
		new Thread(new Avancer(parcours,affichage)).start();
		
		
	  }

	
	
}
