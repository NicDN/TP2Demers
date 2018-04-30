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
public class Plante extends Spécimen implements Serializable{
    
    private boolean estFlottante/*ou immergée*/, estDansEauSalee/*ou dans l'eau douce*/;

    public Plante(boolean estFlottante, boolean estDansEauSalee, String dateObservation, String nom, String couleur, int quantiéObservé, double taille,Personne observateur) {
        super(dateObservation, nom, couleur, quantiéObservé, taille,observateur);
        this.estFlottante = estFlottante;
        this.estDansEauSalee = estDansEauSalee;
        this.type="plante";
    }

    public boolean isEstDansEauSalee() {
        return estDansEauSalee;
    }

    public boolean isEstFlottante() {
        return estFlottante;
    }

    @Override
    public String toString() {
        return super.toString()+"flottante: "+estFlottante+" eauSalée: "+estDansEauSalee; 
    }

    
    

    
    
    
}
