
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2.Spécimen;

import ca.qc.bdeb.prog2.tp2.Personne;
import java.io.Serializable;


/**
 *
 * @author Nicolas
 */
public abstract class Spécimen implements Serializable {

    private static int numTransac=0;
   protected  String type;
    

    protected String dateObservation, nom, couleur;
    protected int quantiéObservé;
    protected double taille/*taille du spécimen*/;
    protected Personne observateur;

    public Spécimen(String dateObservation, String nom, String couleur, int quantiéObservé, double taille, Personne observateur) {
        this.dateObservation = dateObservation;
        this.nom = nom;
        this.couleur = couleur;
        this.quantiéObservé = quantiéObservé;
        this.taille = taille;
        this.observateur=observateur;
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
        return "Date d'observation: "+dateObservation+" Nom: "+ nom+" Couleur: "+ couleur+" Quantité observé "+quantiéObservé+" Taille: "+taille+" Informations sur l'observateur: "+observateur+" Infomations particulières selon le type: ";
    }
public void ajouterQuantitéObservé(int quantité){
    quantiéObservé=quantiéObservé+quantité;
}

    public Personne getObservateur() {
        return observateur;
    }

    public String getType() {
        return type;
    }

}

