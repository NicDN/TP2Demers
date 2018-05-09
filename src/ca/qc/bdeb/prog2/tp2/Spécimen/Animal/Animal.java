package ca.qc.bdeb.prog2.tp2.Spécimen.Animal;

import ca.qc.bdeb.prog2.tp2.Personne;
import ca.qc.bdeb.prog2.tp2.Spécimen.Spécimen;
import java.io.Serializable;

/**
 * Classe Animal
 *
 * @author Hadrien Guimond et Nicolas Demers
 * @version finale
 */
public abstract class Animal extends Spécimen implements Serializable {

    protected boolean estMale/*ou femmelle*/;
    protected String cri;

    /**
     * Constructeur de la classe Animal
     *
     * @param estMale true si l'animal est un mâle
     * @param cri le cri de l'animal
     * 
     * @see classe Spécimen pour les attributs suivants
     * @param dateObservation
     * @param nom
     * @param couleur
     * @param quantiéObservé
     * @param taille
     * @param observateur
     */
    public Animal(boolean estMale, String cri, String dateObservation, String nom, String couleur, int quantiéObservé, double taille, Personne observateur) {
        super(dateObservation, nom, couleur, quantiéObservé, taille, observateur);
        this.estMale = estMale;
        this.cri = cri;
    }

    public void cri() {
        System.out.println("Le cris de l'animal " + nom + " est " + cri);
    }

    public boolean isEstMale() {
        return estMale;
    }

    public String getCri() {
        return cri;
    }

    @Override
    public String toString() {
        return super.toString() + "\tmale: " + estMale + "\tson cri: " + cri;
    }

}
