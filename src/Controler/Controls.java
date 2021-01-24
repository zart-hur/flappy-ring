package Controler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Modele.Etat;
import Vue.Affichage;



public class Controls implements MouseListener {
	
	Affichage affichage ;
	private  Etat etat;
	
	public Controls(Affichage aff,Etat eta){
		this.affichage=aff;
		this.etat =eta;
		
	}
 
		/** on veut que la position du cercle soit modifi�e a chaques clicks (le cercle saute) */	
		 public void mouseClicked(MouseEvent e) {
			this.etat.jump();   //cest la hauteur du cercle dans etat que l'on modifie		      
			affichage.repaint();
			
		}

		 
		 
	/**autres methodes demandees par l'interface**/
		
		public void mouseEntered(MouseEvent arg0) {	
		}
	
		public void mouseExited(MouseEvent arg0) {
		}
	
		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}
		
}



	

