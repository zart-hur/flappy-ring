package Controler;

import Vue.Parcours;
import Vue.Affichage;

public class Avancer extends Parcours implements Runnable {

	private Affichage affichage;
	private final int TIME=65; //c'est le temps que l'on veut entre chaque mise a jour de la fenetre quand le parcours avance.
	
	/** Constructeur */
	public Avancer(Parcours parc,Affichage aff) {
		this.affichage = aff;
		
	}

	

 
	/**methode de l'interface de Thread permettant de creer et executer un thread
	 * 
	 */
	@Override
	  public void run() {
	    while(true) {
	    	Parcours.setPosition() ;   //la position referente du parcours augmente
	    	affichage.revalidate();
	    	affichage.repaint(); //on reactualise l'image depuis l'instance affichage
	      try { Thread.sleep(TIME); }//on utilise Thread.sleep pour qu'il se passe un temps entre chaque Parcours.setPosition().
	      catch (Exception e) { e.printStackTrace(); }
	    }
	  }

}
