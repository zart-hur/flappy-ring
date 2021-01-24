package Main;




import javax.swing.JFrame;

import Controler.Avancer;
import Controler.Voler;
import Modele.Etat;
import Vue.Affichage;
import Vue.Parcours;




    
public class Main {
	
	public static void main(String [] args) {
	
		JFrame fenetre = new JFrame("Flappy Ring");
		Etat etat = new Etat();
		Affichage affichage = new Affichage(etat);
		Parcours parcours = new Parcours();
	    fenetre.add(affichage);
		fenetre.pack(); 
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		new Thread(new Voler(etat,affichage)).start();
		new Thread(new Avancer(parcours,affichage)).start();
		
	  }

	
	
}
