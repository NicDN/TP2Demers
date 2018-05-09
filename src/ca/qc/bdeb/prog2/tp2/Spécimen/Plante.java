package ca.qc.bdeb.prog2.tp2.Spécimen;

import ca.qc.bdeb.prog2.tp2.Personne;
import java.io.Serializable;

/**
 * Classe Plante
 *
 * @author Hadrien Guimond et Nicolas Demers
 * @version finale
 */
public class Plante extends Spécimen implements Serializable {

    private boolean estFlottante/*ou immergée*/, estDansEauSalee/*ou dans l'eau douce*/;

    /**
     * Contructeur de la classe Plante
     *
     * @param estFlottante true si la plante flotte
     * @param estDansEauSalee true si la plante vie dans l'eau salée
     *
     * @see classe Spécimen pour les attributs suivants
     * @param dateObservation
     * @param nom
     * @param couleur
     * @param quantiéObservé
     * @param taille
     * @param observateur
     */
    public Plante(boolean estFlottante, boolean estDansEauSalee, String dateObservation, String nom, String couleur, int quantiéObservé, double taille, Personne observateur) {
        super(dateObservation, nom, couleur, quantiéObservé, taille, observateur);
        this.estFlottante = estFlottante;
        this.estDansEauSalee = estDansEauSalee;
        this.type = "plante";
    }

    public boolean isEstDansEauSalee() {
        return estDansEauSalee;
    }

    public boolean isEstFlottante() {
        return estFlottante;
    }

    @Override
    public String toString() {
        return super.toString() + "\tflottante: " + estFlottante + "\teauSalée: " + estDansEauSalee;
    }

}
