/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2;

import ca.qc.bdeb.prog2.tp2.Spécimen.Spécimen;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class GestionPokedex {

    ArrayList<Spécimen> listeSpécimen = new ArrayList();
    ArrayList<Personne> listePersonne = new ArrayList();

    Scanner clavier = new Scanner(System.in);
    int choix;
    //La classe Doit contenir une liste d'entrées, besoin de plus d'Explication. 
    //On a déjà fait une liste de toutes le entrées dans la classe spécimen...

    public void afficherMenu() {
        boolean erreur = true;

        while (erreur) {

            System.out.println("Sassissez une des options suivantes:\n");
            System.out.println("1- Consulter les spécimens déjà saisis\n2- Saisir "
                    + "un nouveau spécimen\n3- Modifier un spécimen\n4- Statistiques"
                    + "\n5- Quitter");

            //Sasir une donné et essayer de la convertir en int
            try {
                choix = Integer.parseInt(clavier.nextLine());
                //Validation de la donnée une fois convertie
                if (choix <= 0 || choix >= 6) {
                    System.out.println("Le numéro que vous avez saisie n'est pas valide, veuillez réessayer.");
                } else {
                    erreur = false;
                }
                //Exception d'une string vers un int
            } catch (NumberFormatException e) {
                System.out.println("Vous n'avez pas rentrer un nombre, veuillez réessayer.");
                //Exception autre
            } catch (Exception e) {
                System.out.println("Exeption autre dans classe GestionPokedex, méthode afficherMenu");
            }
        }

        optionMenu(choix);
    }

    private void optionMenu(int choix) {
        switch (choix) {
            case 1:
                ajouterSpécimen();
                break;
            case 2:
                break;
            case 3:
                sousMenuModifier();
                break;
            case 4:
                statistiques();
                break;
            case 5:
                quitter();
                break;

        }
    }

    private void sousMenuModifier() {
        boolean erreur = true;

        while (erreur) {

            System.out.println("Sassissez une des options suivantes:\n");
            System.out.println("1-Supprimer un spécimen\n2-Modifier la quantité "
                    + "aperçue d'un spécimen donné\n3-Retourner au menu principal");

            //Sasir une donné et essayer de la convertir en int
            try {
                choix = Integer.parseInt(clavier.nextLine());
                //Validation de la donnée une fois convertie
                if (choix <= 0 || choix >= 4) {
                    System.out.println("Le numéro que vous avez saisie n'est pas valide, veuillez réessayer.");
                } else {
                    erreur = false;
                }
                //Exception d'une string vers un int
            } catch (NumberFormatException e) {
                System.out.println("Vous n'avez pas rentrer un nombre, veuillez réessayer.");
                //Exception autre
            } catch (Exception e) {
                System.out.println("Exeption autre dans classe GestionPokedex, méthode sousMenuModifier");
            }
        }
        optionMenuModifier(choix);
    }

    private void optionMenuModifier(int choix) {
        switch (choix) {
            case 1:
                supprimerSpecimen();
                break;
            case 2:
                modifierQuantitéApercu();
                break;
            case 3:
                afficherMenu();
                break;

        }
    }

    private void supprimerSpecimen() {

    }

    private void modifierQuantitéApercu() {

    }

    private void statistiques() {
        afficherNbEntréesTypes();
        afficherNbEntréesPersonnes();
        afficherInfosPersonnes();
    }

    private void afficherNbEntréesTypes() {

    }

    private void afficherNbEntréesPersonnes() {

    }

    private void afficherInfosPersonnes() {

    }

    private void quitter() {

        System.exit(choix);
    }

    private void ajouterSpécimen() {
        System.out.println("Bienvenue dans la section pour ajouter un spécimen");

        System.out.println("Saisissez la date d'observation");
        String dateObservation = clavier.nextLine();
        System.out.println("Saisissez le type d'entrée");
        String type = clavier.nextLine();
        type.toLowerCase();
        System.out.println("Saisissez son nom");
        String nom = clavier.nextLine();
        System.out.println("Saississez sa couleur");
        String couleur = clavier.nextLine();
        boolean erreur = false;
        do {
            System.out.println("Saississez la quantité observé");

            try {
                int quantitéObservé = Integer.parseInt(clavier.nextLine());
                erreur = false;
            } catch (NumberFormatException e) {
                System.out.println("Erreur de conversion");
                erreur = true;
            }
        } while (erreur == true);

        switch (type) {
            case "poisson":
                System.out.println("Eau salée ou Eau douce?");
                String eau=clavier.nextLine();
                if(eau.equals("salée")){
                    
                }else if(eau.endsWith("douce")){
                    
                }
               //Rendu là
                break;
            case "mammifère marin":
                
                break;
            case "plante aquatique":
                break;
            case "minéral":
                break;

        }

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

    }

}
