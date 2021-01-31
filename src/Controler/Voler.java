package Controler;

import Modele.Etat;
import Vue.Affichage;


public class Voler implements Runnable {

	private Etat etat;
	private Affichage affichage;
	
	private final int TIME=10; //c'est le temps que l'on veut entre chaque mise a jour de la fenetre quand l'ovale tombe.
	
	private static boolean flagDeFin=false;

	/** Constructeur */
	public Voler(Etat eta,Affichage aff) {
		this.etat=eta;
		this.affichage = aff;
		
	}

	public static void setfin() {
		flagDeFin=true;
	}

 
	/**methode de l'interface de Thread permettant de creer et executer un thread
	 * 
	 */
	@Override
	  public void run() {
	    while(flagDeFin==false) {
	    	this.etat.moveDown() ;   //la gravite agis sur l'ovale depuis la classe Etat
	    	this.affichage.revalidate();
	    	this.affichage.repaint(); //on reactualise l'image depuis l'instance affichage
	      try { Thread.sleep(TIME); }//on utilise Thread.sleep pour qu'il se passe un temps entre chaque Etat.moveDown().
	      catch (Exception e) { e.printStackTrace(); }
	    }
	  }

	
}



