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
public class Minéral extends Spécimen implements Serializable{

    public Minéral(String dateObservation, String nom, String couleur, int quantiéObservé, double taille,Personne observateur) {
        super(dateObservation, nom, couleur, quantiéObservé, taille,observateur);
        this.type="minéral";
    }

    @Override
    public String toString() {
        return super.toString()+" Aucun"; 
    }


    
}
