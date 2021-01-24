package Controler;

import Modele.Etat;
import Vue.Affichage;


public class Voler extends Etat implements Runnable {

	private Affichage affichage;
	private final int TIME=50; //c'est le temps que l'on veut entre chaque mise a jour de la fenetre quand l'ovale tombe.


	/** Constructeur */
	public Voler(Etat etat,Affichage aff) {
		this.affichage = aff;
		
	}


	
	

 
	/**methode de l'interface de Thread permettant de creer et executer un thread
	 * 
	 */
	@Override
	  public void run() {
	    while(true) {
	    	Etat.moveDown() ;   //la gravite agis sur l'ovale depuis la classe Etat
	    	affichage.revalidate();
	    	affichage.repaint(); //on reactualise l'image depuis l'instance affichage
	      try { Thread.sleep(TIME); }//on utilise Thread.sleep pour qu'il se passe un temps entre chaque Etat.moveDown().
	      catch (Exception e) { e.printStackTrace(); }
	    }
	  }

	
}



