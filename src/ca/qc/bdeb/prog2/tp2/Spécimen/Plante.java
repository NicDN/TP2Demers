/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2.Spécimen;

import ca.qc.bdeb.prog2.tp2.Personne;

/**
 *
 * @author Nicolas
 */
public class Plante extends Spécimen{
    
    private boolean estFlottante/*ou immergée*/;

    public Plante(boolean estFlottante, String dateObservation, String nom, String couleur, int quantiéObservé, int taille, Personne observateur) {
        super(dateObservation, nom, couleur, quantiéObservé, taille, observateur);
        this.estFlottante=estFlottante;
    }
    
    
}
