/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2.Spécimen.Animal;

import ca.qc.bdeb.prog2.tp2.Personne;

/**
 *
 * @author Nicolas
 */
public class MammifèreMarin extends Animal{

    private boolean estDansEauSalee/*ou dans l'eau douce*/, estCarnivore/*ou végétarien*/;

    public MammifèreMarin(boolean estDansEauSalee, boolean estCarnivore, boolean estMale, String cri, String dateObservation, String nom, String couleur, int quantiéObservé, double taille, Personne observateur) {
        super(estMale, cri, dateObservation, nom, couleur, quantiéObservé, taille, observateur);
        this.estDansEauSalee = estDansEauSalee;
        this.estCarnivore = estCarnivore;
    }

    

    
    
}
