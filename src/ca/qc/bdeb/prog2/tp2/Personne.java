/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2;

/**
 *
 * @author Nicolas
 */
public class Personne {
    private String nom,codeAcces,mdp;
    private int age;
private static int nbEntrées; 

    public Personne(String nom, String codeAcces, String mdp, int age) {
        this.nom = nom;
        this.codeAcces = codeAcces;
        this.mdp = mdp;
        this.age = age;
        
    }

    public int getAge() {
        return age;
    }

    public String getCodeAcces() {
        return codeAcces;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCodeAcces(String codeAcces) {
        this.codeAcces = codeAcces;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Nom: "+nom+" Age: "+age+" Code accès: "+codeAcces;
    }
    

    
    
    
    
    
    
}
