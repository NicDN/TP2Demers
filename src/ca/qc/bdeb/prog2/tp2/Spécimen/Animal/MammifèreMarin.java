package ca.qc.bdeb.prog2.tp2.Spécimen.Animal;

import ca.qc.bdeb.prog2.tp2.Personne;
import java.io.Serializable;

/**
 * Classe Mammifère Marin
 *
 * @author Hadrien Guimond et Nicolas Demers
 * @version finale
 */
public class MammifèreMarin extends Animal implements Serializable {

    private boolean estDansEauSalee, estCarnivore;

    /**
     * Constructeur de la classe MammifèreMarin
     *
     * @param estDansEauSalee true si le mammifère vit dans l'eau salée
     * @param estCarnivore true si le mammifère est carnivore
     *
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
    public MammifèreMarin(boolean estDansEauSalee, boolean estCarnivore, boolean estMale, String cri, String dateObservation, String nom, String couleur, int quantiéObservé, double taille, Personne observateur) {
        super(estMale, cri, dateObservation, nom, couleur, quantiéObservé, taille, observateur);
        this.estDansEauSalee = estDansEauSalee;
        this.estCarnivore = estCarnivore;
        this.type = "mammifere marin";
    }

    public boolean isEstCarnivore() {
        return estCarnivore;
    }

    public boolean isEstDansEauSalee() {
        return estDansEauSalee;
    }

    @Override
    public String toString() {
        return super.toString() + " eau salée: " + estDansEauSalee + " Carnivore: " + estCarnivore;
    }

}
