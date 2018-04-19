/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2.Spécimen;

import ca.qc.bdeb.prog2.tp2.Personne;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public abstract class Spécimen implements Serializable {

    private static int numTransac=0;

    protected String dateObservation, nom, couleur;
    protected int quantiéObservé;
    protected double taille;
    protected Personne observateur;

    public Spécimen(String dateObservation, String nom, String couleur, int quantiéObservé, double taille, Personne observateur) {
        this.dateObservation = dateObservation;
        this.nom = nom;
        this.couleur = couleur;
        this.quantiéObservé = quantiéObservé;
        this.taille = taille;
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

    public Personne getObservateur() {
        return observateur;
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

    public void setObservateur(Personne observateur) {
        this.observateur = observateur;
    }

    public void setQuantiéObservé(int quantiéObservé) {
        this.quantiéObservé = quantiéObservé;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }


}
