package ca.qc.bdeb.prog2.tp2;

import java.io.Serializable;

/**
 * Classe Personne
 *
 * @author Hadrien Guimond et Nicolas Demers
 * @version finale
 */
public class Personne implements Serializable {

    private String nom, codeAcces, mdp;
    private int age;
    private static int nbEntrées;

    /**
     * Constructeur de la classe Personne
     *
     * @param nom vrai nom de la personne
     * @param codeAcces pseudonyme de la personne
     * @param mdp mot de passe encrypté de la personne
     * @param age age de la personne
     */
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
        return "\tNom: " + nom + "\tAge: " + age + "\tCode accès: " + codeAcces;
    }

}
