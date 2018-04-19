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

    ArrayList<Spécimen> listeSpécimen = new ArrayList<>();

    protected String dateObservation, nom, couleur;
    protected int quantiéObservé, taille;
    protected Personne observateur;

    public Spécimen(String dateObservation, String nom, String couleur, int quantiéObservé, int taille, Personne observateur) {
        this.dateObservation = dateObservation;
        this.nom = nom;
        this.couleur = couleur;
        this.quantiéObservé = quantiéObservé;
        this.taille = taille;
        numTransac++;
    }


    
    
    

}
