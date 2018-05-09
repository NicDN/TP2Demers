package ca.qc.bdeb.prog2.tp2.Spécimen;

import ca.qc.bdeb.prog2.tp2.Personne;
import java.io.Serializable;

/**
 * Classe Spécimen
 *
 * @author Hadrien Guimond et Nicolas Demers
 * @version finale
 */
public abstract class Spécimen implements Serializable {

    private static int numTransac = 0;
    protected String type;

    protected String dateObservation, nom, couleur;
    protected int quantiéObservé;
    protected double taille;
    protected Personne observateur;

    /**
     * Contructeur de la classe Spécimen
     *
     * @param dateObservation numéro à 8 chiffres de la date observée
     * année/mois/jour
     * @param nom nom du spécimen
     * @param couleur couleur du spécimen
     * @param quantiéObservé quantité observé d'un même spécimen
     * @param taille taille du spécimen
     * @param observateur personne qui à observée/entrée dans le pokedex le
     * spécimen
     */
    public Spécimen(String dateObservation, String nom, String couleur, int quantiéObservé, double taille, Personne observateur) {
        this.dateObservation = dateObservation;
        this.nom = nom;
        this.couleur = couleur;
        this.quantiéObservé = quantiéObservé;
        this.taille = taille;
        this.observateur = observateur;
        numTransac++;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getDateObservation() {
        return dateObservation;
    }

    public String getNom() {
        return nom;
    }

    public static int getNumTransac() {
        return numTransac;
    }

    public int getQuantiéObservé() {
        return quantiéObservé;
    }

    public double getTaille() {
        return taille;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setDateObservation(String dateObservation) {
        this.dateObservation = dateObservation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantiéObservé(int quantiéObservé) {
        this.quantiéObservé = quantiéObservé;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return "Date d'observation: " + dateObservation + "\tType : " + type + 
                "\tNom: " + nom + "\tCouleur: " + couleur + "\tQuantité observé"
                + " " + quantiéObservé + "\tTaille: " + taille + "\tInformations "
                + "sur l'observateur: " + observateur + "\tInfomations particul"
                + "ières selon le type: ";
    }

    public void ajouterQuantitéObservé(int quantité) {
        quantiéObservé = quantiéObservé + quantité;
    }

    public Personne getObservateur() {
        return observateur;
    }

    public String getType() {
        return type;
    }

}
