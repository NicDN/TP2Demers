/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2;

import ca.qc.bdeb.prog2.tp2.Spécimen.Spécimen;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public class main {

    public static void main(String[] args) {
        ArrayList<Spécimen> listeSpécimen = new ArrayList();

        boolean erreur = false;

        erreur = demanderIdentité();

        if (erreur) {
            GestionPokedex pokedex = new GestionPokedex(); //Créer un pokedex
            pokedex.afficherMenu(); //Call le menu

        } else if (!erreur) {
            System.out.println("Vous avez échoué vos 3 tentatives!");
            System.exit(0);
        }

    }

    public static void charcherListePersonnes(String fichier) throws IOException {
        String codeAcces = null;
        String mdp = null;
        String nom = null;
        String age = null;
        BufferedReader lecture = null;
        try {
            lecture = new BufferedReader(new FileReader(fichier));
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fichier + " n'a pas été trouver dans la méthode charcherListePersonnes du main");
        } catch (IOException e) {
            System.out.println("Erreur entrée-sortie avec " + fichier + " dans la méthode charcherListePersonnes du main");
        }
        String ligne = lecture.readLine();

        while (ligne != null) {
            String[] contenuLigne = ligne.split(";");
            contenuLigne[0] = codeAcces;
            contenuLigne[1] = mdp;
            contenuLigne[2] = nom;
           
            //Personne personne=new Personne();
        }

    }

    public static boolean demanderIdentité() {
        boolean vérifier = false;
        //Si après 3 tentatives de demande de mot de passe raté: mettre le boolean true
        return vérifier;
    }

    public void chargerFichierPokedex(String fichier, ArrayList<Spécimen> listeSpécimen) throws ClassNotFoundException {
        try {
            FileInputStream fos = new FileInputStream(fichier);
            ObjectInputStream oos = new ObjectInputStream(fos);
            listeSpécimen = (ArrayList<Spécimen>) oos.readObject();
            System.out.println("Fichier chargé");
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fichier + " n'a pas été trouver dans la méthode charcherListePersonnes du main");
        } catch (IOException e) {
            System.out.println("Erreur entrée-sortie avec " + fichier + " dans la méthode charcherListePersonnes du main");
        }
        System.out.println("essai a enlever");
    }

    public void sauvegarderFichierPokedex(String fichier, ArrayList<Spécimen> listeSpécimen) {
        try {
            FileOutputStream fos = new FileOutputStream(fichier);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listeSpécimen);
            oos.flush();
            oos.close();
            System.out.println("Fichier sauvegardé");
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fichier + " n'a pas été trouver dans la méthode charcherListePersonnes du main");
        } catch (IOException e) {
            System.out.println("Erreur entrée-sortie avec " + fichier + " dans la méthode charcherListePersonnes du main");
        }
    }

}
