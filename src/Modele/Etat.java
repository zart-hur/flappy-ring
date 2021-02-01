package Modele;


import java.awt.Point;


import Vue.Affichage;

public class Etat {

	private  Parcours parcours; //pour recuperer l'instance parcours, neccesaire pour le testPerdu()

	private  int hauteur; //variable qui donne l'ordonnee du centre du cercle, initilalisee dans le constructeur

	private final int SAUT=55;	// constante definisant la taille d'un "saut" du cercle
	private final static int GRAVITE=2; // Constante definisant le nombre de pixels descendu l'ors de moveDown()

	private  int sautDuPerdu; //variable pour recuperer l'ordonnée de la ligne qui touche le haut du cercle dans un saut 
	public boolean flagTestPerdu = false; //flag qui permet de prevenir l'affichage quand testperdu() est vrai pour jump() ou moveDown() 
	public boolean perduHaut = false; //tentative de rectification (entre autres de la latence du click) dans jump pour redessiner le cercle au bon endroit dans affichage


	/** Constructeur */
	public Etat(Parcours parc) {
		this.parcours =parc;
		this.hauteur = Affichage.getOrdOvale(); //je prefere initialiser hauteur l'ors de l'instanciation de la Classe.

	}

	/**************METHODES GET *******************/

	public int getHauteur(){ 
		return hauteur;
	}

	public  boolean getflagTestperdu() {
		return this.flagTestPerdu;
	}

	public  boolean getPerduDuHaut() {
		return this.perduHaut;
	}

	/**************AUTRE METHODES *******************/

	/**
	 * testPerdu() recupere 2 points, un a gauche et un a droite de l'abscisse du cercle.
	 * ensuite elle calcule le "coefficient de pente entre les 2 points grace a leurs Ordonnees
	 * puis elle trouve en operant un equation a une inconnue l'ordonnee du point la pente correspondant a l'abscisse du centre du cercle
	 * et ceci pour l'ordonnee du point de la ligneHaut et celui de la ligneBas
	 * Enfin cette methode teste si le haut du cercle depasse l'ordonnee du point de ligne haut ou le bas du cercle l'ordonnee du point de ligneBas
	 * si cest le cas, elle renvoie true, sinon false.
	 * @return boolean indiquant si la partie est perdue ou non (true=oui)
	 */
	public boolean testPerdu() {
		int indexP1= parcours.getPointProches();//on recupere l'index du premier point a gauche du cercle
		int indexP2= indexP1+1; //on recupere l'index du premier point a droite
		Point p1 =parcours.getLigneBas().get(indexP1); // on recupere le p1 de ligneBas
		Point p2 =parcours.getLigneBas().get(indexP2);// on recupere le p2 de ligneBas 
		//a noter que la seule difference avec les points de ligneHaut est le (+ LARGEUR_CAVERNE) de leur abscisse

		float pente = ((p1.y) - (p2.y) )/ ((float)(p1.x) - (float)(p2.x)); //calcul de la pente entre deux points, la meme pour la ligneBas et ligneHaut 
		float pointyDeLaDroiteBas = ( -pente*(p1.x-Affichage.getAbsOvale())+p1.y );//calcul l'ordonnee du point de la pente de ligneBas (avec p.x=cercle.x)
		float pointyDeLaDroiteHaut = ( -pente *(p1.x-Affichage.getAbsOvale()) + (p1.y-parcours.getLargeurCaverne()) );//meme calcul pour ligneHaut

		if(pointyDeLaDroiteBas <= (hauteur+Affichage.getTailleOvale())){ // si le point de la ligne du bas touche ou depasse le bas du cercle	
			return true;
		}
		else if(pointyDeLaDroiteHaut >= hauteur){// si le point de la ligne du haut touche ou depasse le haut du cercle
			this.sautDuPerdu=(int) pointyDeLaDroiteHaut;//recuperation de la valeur d'ordonnee du point lignehaut pour tentative de rectification du dessin
			return true;
		}
		else {	
			return false;
		}
	}


	/**
	 * jump() "incremente" la hauteur du cercle, la methode est utilisee dans la Classe Controls (quand on clique)
	 * cette methode verifie avant tout testPerdu() pour en informer par la suite l'Affichage grace au flagTestPerdu
	 */
	public void jump(){ 	
		if(testPerdu()) { //si on touche la ligne (du haut logiquement) en sautant
			this.hauteur=this.sautDuPerdu;//tentative de rectification de la hauteur pour que le haut du cercle ne depasse pas la ligneHaut mais la touche	
			this.perduHaut = true;// valeur utilisé par Affichage pour savoir si il faut redessiner effectivement le cercle
			this.flagTestPerdu = true;
		}
		else
			//if (hauteur<0) // pour que hauteur ne sorte pas de la fenetre (inutile avec le parcours)
			hauteur=hauteur-this.SAUT;
	}

	/**
	 * moveDown() "decremente" la hauteur du cercle, la methode est utilisee dans la Classe Voler (dans un Thread)
	 * cela donne un effet de gravitee au cercle
	 * cette methode verifie avant tout testPerdu() pour en informer par la suite l'Affichage grace au flagTestPerdu
	 */
	public  void moveDown() {
		if(testPerdu()) {
			this.flagTestPerdu = true;
		}
		//if(hauteur>(Affichage.getHauteurFenetre()-Affichage.getTailleOvale())) //pour que hauteur ne sorte pas de la fenetre (inutile avec le parcours)
		hauteur=hauteur+GRAVITE;
	}

	
}

