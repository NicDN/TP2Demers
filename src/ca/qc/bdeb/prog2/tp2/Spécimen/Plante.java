/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2.Spécimen;



/**
 *
 * @author Nicolas
 */
public class Plante extends Spécimen{
    
    private boolean estFlottante/*ou immergée*/, estDansEauSalee/*ou dans l'eau douce*/;

    public Plante(boolean estFlottante, boolean estDansEauSalee, String dateObservation, String nom, String couleur, int quantiéObservé, double taille) {
        super(dateObservation, nom, couleur, quantiéObservé, taille);
        this.estFlottante = estFlottante;
        this.estDansEauSalee = estDansEauSalee;
    }

    public boolean isEstDansEauSalee() {
        return estDansEauSalee;
    }

    public boolean isEstFlottante() {
        return estFlottante;
    }

    
    
    
}
