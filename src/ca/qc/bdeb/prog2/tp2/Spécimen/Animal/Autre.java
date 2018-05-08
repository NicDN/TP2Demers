package ca.qc.bdeb.prog2.tp2.Spécimen.Animal;

import ca.qc.bdeb.prog2.tp2.Personne;
import java.io.Serializable;

/**
 * Classe Autre
 * 
 * @author Hadrien Guimond et Nicolas Demers
 * @version finale
 */
public class Autre extends Animal implements Serializable{

    /**
     * Constructeur de la classe Autre
     * 
     * @see classe Animal pour les attributs suivants
     * @param estMale
     * @param cri
     * 
     * @see classe Spécimen pour les attributs suivants
     * @param dateObservation
     * @param nom
     * @param couleur
     * @param quantiéObservé
     * @param taille
     * @param observateur 
     */
    public Autre ( boolean estMale, String cri, String dateObservation, String nom, String couleur, int quantiéObservé, double taille,Personne observateur) {
        super(estMale, cri, dateObservation, nom, couleur, quantiéObservé, taille,observateur);
        this.type="autre";
    }

    @Override
    public String toString() {
        return super.toString(); 
    }
    
    
}
