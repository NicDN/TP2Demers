/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2.Spécimen.Animal;

import ca.qc.bdeb.prog2.tp2.Spécimen.Spécimen;

/**
 *
 * @author Nicolas
 */
public abstract class Animal{
    
    public enum Sexe{
        MALE,
        FEMELLE;
    }
    public enum Eau{
        SALÉ,
        DOUCE;
    }
    protected Sexe sexe;
    protected Eau eau;
   
    public abstract void cri();
}
