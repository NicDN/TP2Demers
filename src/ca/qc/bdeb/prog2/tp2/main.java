/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Nicolas
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean erreur = false;

        charcherListePersonnes();
        chargerFichierPokedex();

        erreur = demanderIdentité();

        if (erreur) {
            GestionPokedex pokedex = new GestionPokedex(); //Créer un pokedex
            pokedex.afficherMenu(); //Call le menu

        } else if (!erreur) {
            System.out.println("Vous avez échoué vos 3 tentatives!");
            System.exit(0);
        }

    }

    public static void charcherListePersonnes() {

    }

    public static void chargerFichierPokedex() {
//        Va charger le contenu du fichier et le mettre dans la liste de spécimen?? À vérifier
//        Si fichier inexistant, on va devoir catcher filenotfound et ensuite créer


    }

    public static boolean demanderIdentité() {
        boolean vérifier = false;
        //Si après 3 tentatives de demande de mot de passe raté: mettre le boolean true
        return vérifier;
    }

    public static void ouvertureFichierTexte(String fichier) {
        // Va créer les personnes à partir du fichier texte
        // Gère les exeptions aussi
        try {
            BufferedReader lecture = new BufferedReader(new FileReader(fichier));
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fichier + " n'a pas été trouver dans la méthode charcherListePersonnes du main");
        } catch (IOException e) {
            System.out.println("Erreur entrée-sortie avec " + fichier + " dans la méthode charcherListePersonnes du main");
        }
    }
    
    public static void ouvertureFichierBinaire(String fichier){
        try{
            FileOutputStream fos=new FileOutputStream(fichier);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            
        }catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fichier + " n'a pas été trouver dans la méthode charcherListePersonnes du main");
        } catch (IOException e) {
            System.out.println("Erreur entrée-sortie avec " + fichier + " dans la méthode charcherListePersonnes du main");
        }
    }
}
