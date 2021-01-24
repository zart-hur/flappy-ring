package Vue;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Controler.Controls;
import Modele.Etat;
import java.awt.Point;


@SuppressWarnings("serial")   // parce que je n'ai pas compris dans notre exercice l'interet d'avoir un serial ID number .
public class Affichage extends JPanel {

    /* Constantes */
	
	/** taille de la fenetre */
    private static final int LARG = 1000;
    private static final int HAUT = 600;
    
    /**coordonnées du cercle*/
    private static final int ABS_OVAL = 50;
    private static final int ORD_OVAL = 200; //la hauteur initiale. C'est Etat.hauteur qui se base sur 'y' que l'on utilisera en pratique.
    
    /**dimmentions du cercle*/
    private final int WIDTH = 50;
    private static final int HEIGHT = 200;
    
    /** pour les interactions avec la classe Etat*/
    Etat etat;

    /** Constructeur */
    public Affichage(Etat eta) {
    	this.etat = eta;
        setPreferredSize(new Dimension(LARG, HAUT));
        addMouseListener(new Controls(this,etat));
    }
    /**methodes get pour récuperer les valeurs de constantes de cette classe.
     */
    public static int getHauteurFenetre(){
    	return HAUT;
    }
    
    public static int getLargeurFenetre(){
    	return LARG;
    }
    
    public static int getOrdOvale(){
    	return ORD_OVAL;
    }
    
    public static int getAbsOvale(){
    	return ABS_OVAL;
    }
    
    public static int getTailleOvale() {
    	return HEIGHT;
    }
    /**methode qui dessine le cercle et le parcours dans la fenetre.
     */
    @Override
    public void paint(Graphics g) { 
    	super.paint(g);         //raffraichit la fenetre quand on appelle repaint
      g.drawOval(ABS_OVAL, etat.getHauteur(), WIDTH, HEIGHT);
     
     
      for(int i=0;i<this.etat.getParcours().size()-1;i++) {//entre chaques paire de points ( l'arraylist ligne on dessine une ligne)
    	   Point p1=this.etat.getParcours().get(i);
    	   Point p2=this.etat.getParcours().get(i+1);    
    	   g.drawLine(p1.x,p1.y,p2.x,p2.y);
    	   
      }
    }
    


}