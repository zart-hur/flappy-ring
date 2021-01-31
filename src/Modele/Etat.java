package Modele;


import java.awt.Point;


import Vue.Affichage;

public class Etat {
	private  int hauteur = Affichage.getOrdOvale();
	private  Parcours parcours ;
	private final int SAUT=50;
	private  int sautDuPerdu;
	private final static int GRAVITE=2;
	public boolean flagTestPerdu = false;
	public boolean perduHaut = false;
	


	public Etat(Parcours parc) {
		this.parcours =parc;

	}
	/**
	 * fonction qui recupere la valeur de hauteur
	 **/
	public int getHauteur(){ 
		return hauteur;
	}


	public  boolean getflagTestperdu() {
		return this.flagTestPerdu;
	}
	
	public  boolean getPerduDuHaut() {
		return this.perduHaut;
	}


	public boolean testPerdu() {
		int indexP1= parcours.getPointProches();
		int indexP2= indexP1+1;
		Point p1 =parcours.getLigneBas().get(indexP1);
		Point p2 =parcours.getLigneBas().get(indexP2);	
	
	
		
		float pente = ((p1.y) - (p2.y) )/ ((float)(p1.x) - (float)(p2.x)); //calcul de la pente de la droite, la meme pour la ligne du bas et la ligne du haut 
		float pointyDeLaDroiteBas = ( - pente *(p1.x-Affichage.getAbsOvale()) + p1.y ); 
		float pointyDeLaDroiteHaut = ( - pente *(p1.x-Affichage.getAbsOvale()) + (p1.y-parcours.getLargeurCaverne()) );// cest  ca normalement ...
		
	
		
		if(pointyDeLaDroiteBas <= (hauteur+Affichage.getTailleOvale())){	
			return true;
		}
		else if(pointyDeLaDroiteHaut >= hauteur){
			this.sautDuPerdu=(int) pointyDeLaDroiteHaut;
			return true;
		}
		else {	
			return false;
		}
	}

	
	
	public void jump(){ 	
		if(testPerdu()) {
			this.hauteur=this.sautDuPerdu;		
			this.perduHaut = true;
			this.flagTestPerdu = true;
		}
		else
			hauteur=hauteur-this.SAUT;
	}
	

	public  void moveDown() {
		if(testPerdu()) {
			this.flagTestPerdu = true;
		}
		if((hauteur) < (Affichage.getHauteurFenetre()-Affichage.getTailleOvale())) {  // pour que l'on ne puisse pas sortir de la fenettre
			hauteur=hauteur+GRAVITE;
		}
	}

}

