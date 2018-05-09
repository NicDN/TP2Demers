package ca.qc.bdeb.prog2.tp2.Spécimen.Animal;

import ca.qc.bdeb.prog2.tp2.Personne;
import java.io.Serializable;

/**
 * Classe Poisson
 *
 * @author Hadrien Guimond et Nicolas Demers
 * @version finale
 */
public class Poisson extends Animal implements Serializable {

    private boolean estDansEauSalee;

    /**
     * Contructeur de la classe Poisson
     *
     * @param estDansEauSalee vrai si le poisson vit dans l'eau salée
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
    public Poisson(boolean estDansEauSalee, boolean estMale, String cri, String dateObservation, String nom, String couleur, int quantiéObservé, double taille, Personne observateur) {
        super(estMale, cri, dateObservation, nom, couleur, quantiéObservé, taille, observateur);
        this.estDansEauSalee = estDansEauSalee;
        this.type = "poisson";
    }

    public boolean getisEstDansEauSalee() {
        return estDansEauSalee;
    }

    @Override
    public String toString() {
        return super.toString() + "\teau salée: " + estDansEauSalee;
    }

}
