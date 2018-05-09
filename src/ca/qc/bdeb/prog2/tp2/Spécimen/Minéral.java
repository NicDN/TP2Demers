package ca.qc.bdeb.prog2.tp2.Spécimen;

import ca.qc.bdeb.prog2.tp2.Personne;
import java.io.Serializable;

/**
 *Classe Minérale
 * 
 * @author Hadrien Guimond et Nicolas Demers
 * @version finale
 */
public class Minéral extends Spécimen implements Serializable {

    /**
     * Constructeur de la classe Minéral
     *
     * @see classe Spécimen pour les attributs suivants
     * @param dateObservation
     * @param nom
     * @param couleur
     * @param quantiéObservé
     * @param taille
     * @param observateur
     */
    public Minéral(String dateObservation, String nom, String couleur, int quantiéObservé, double taille, Personne observateur) {
        super(dateObservation, nom, couleur, quantiéObservé, taille, observateur);
        this.type = "mineral";
    }

    @Override
    public String toString() {
        return super.toString() + "\tAucun";
    }

}
