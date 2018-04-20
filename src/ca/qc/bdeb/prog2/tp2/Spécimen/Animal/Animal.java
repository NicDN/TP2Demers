/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2.Spécimen.Animal;


import ca.qc.bdeb.prog2.tp2.Personne;
import ca.qc.bdeb.prog2.tp2.Spécimen.Spécimen;

/**
 *
 * @author Nicolas
 */
public abstract class Animal extends Spécimen{
    
    protected boolean estMale/*ou femmelle*/;
    protected String cri;

    public Animal(boolean estMale, String cri, String dateObservation, String nom, String couleur, int quantiéObservé, double taille,Personne observateur) {
        super(dateObservation, nom, couleur, quantiéObservé, taille,observateur);
        this.estMale = estMale;
        this.cri = cri;
    }
   
    public void cri(){
        System.out.println("Le cris de l'animal "+nom+" est "+cri);
    }

    public boolean isEstMale() {
        return estMale;
    }

    public String getCri() {
        return cri;
    }
    
    
}
