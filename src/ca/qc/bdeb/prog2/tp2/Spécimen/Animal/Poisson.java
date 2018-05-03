/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2.Spécimen.Animal;

import ca.qc.bdeb.prog2.tp2.Personne;
import java.io.Serializable;


/**
 *
 * @author Nicolas
 */
public class Poisson extends Animal implements Serializable{

    private boolean estDansEauSalee/*ou dans l'eau douce*/;

    public Poisson(boolean estDansEauSalee, boolean estMale, String cri, String dateObservation, String nom, String couleur, int quantiéObservé, double taille,Personne observateur) {
        super(estMale, cri, dateObservation, nom, couleur, quantiéObservé, taille,observateur);
        this.estDansEauSalee = estDansEauSalee;
        this.type="poisson";
    }

    public boolean getisEstDansEauSalee() {
        return estDansEauSalee;
    }

    @Override
    public String toString() {
        return super.toString()+" eau salée: "+estDansEauSalee; 
    }
    
    
}
