/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2.Spécimen.Animal;


/**
 *
 * @author Nicolas
 */
public class Poisson extends Animal{

    private boolean estDansEauSalee/*ou dans l'eau douce*/;

    public Poisson(boolean estDansEauSalee, boolean estMale, String cri, String dateObservation, String nom, String couleur, int quantiéObservé, double taille) {
        super(estMale, cri, dateObservation, nom, couleur, quantiéObservé, taille);
        this.estDansEauSalee = estDansEauSalee;
    }

    public boolean getisEstDansEauSalee() {
        return estDansEauSalee;
    }
    
    
}
