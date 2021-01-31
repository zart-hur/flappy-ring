package Controler;

import Modele.Parcours;
import Vue.Affichage;

public class Avancer implements Runnable {
	
	private Parcours parcours;
	private Affichage affichage;
	
	private final int TIME=10; //c'est le temps que l'on veut entre chaque mise a jour de la fenetre quand le parcours avance.
	
	private static boolean flagDeFin=false;
	
	/** Constructeur */
	public Avancer(Parcours parc,Affichage aff) {
		
		this.parcours = parc;
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
	    	parcours.setPosition() ;   //la position referente du parcours augmente
	    	parcours.MaJLignes();
	    	affichage.revalidate();
	    	affichage.repaint(); //on reactualise l'image depuis l'instance affichage
	      try { Thread.sleep(TIME); }//on utilise Thread.sleep pour qu'il se passe un temps entre chaque Parcours.setPosition().
	      catch (Exception e) { e.printStackTrace(); }
	    }
	  }

}
