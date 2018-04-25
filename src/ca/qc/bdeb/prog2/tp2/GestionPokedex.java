/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2;

import ca.qc.bdeb.prog2.tp2.Spécimen.Animal.Autre;
import ca.qc.bdeb.prog2.tp2.Spécimen.Animal.MammifèreMarin;
import ca.qc.bdeb.prog2.tp2.Spécimen.Animal.Poisson;
import ca.qc.bdeb.prog2.tp2.Spécimen.Minéral;
import ca.qc.bdeb.prog2.tp2.Spécimen.Plante;
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
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionPokedex {

    ArrayList<Spécimen> listeSpécimen = new ArrayList();
    ArrayList<Personne> listePersonne = new ArrayList();

    Scanner clavier = new Scanner(System.in);
    int choix;

    public void démarrer() {
        //les try catch ne sont pas bien fait, ils sont automatique
        try {
            charcherListePersonnes("personnes.txt", listePersonne);
        } catch (IOException ex) {
            Logger.getLogger(GestionPokedex.class.getName()).log(Level.SEVERE, null, ex);
        }
        décrypterMotDePasses();
        try {
            chargerFichierPokedex("pokedex.bin", listeSpécimen);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionPokedex.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean confirmer = demanderIdentité();
        //demander identité si réussi à trouver mdp on call la menu
        //le menu doit être dans un while
    }

    public void afficherMenu() {
        boolean erreur = true;

        //boucle de validation du choix du menu
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
                    //tout est beau, on sort de la boucle
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

        //méthode qui trète l'obtion de l'utilisateur
        optionMenu(choix);
    }

    private void optionMenu(int choix) {
        Personne observateur = new Personne("", "", "", 1);//création d'une personne temporaire temporaire

        //étapes selon le choix
        switch (choix) {
            case 1://Consulter les spécimens déjà saisis
                consulterSpécimensExistants();
                break;
            case 2://saisir un nouveau spécimen
                listeSpécimen.add(ajouterSpécimen(observateur));
                break;
            case 3://modifier un spécimen
                sousMenuModifier();
                break;
            case 4://statistiques
                statistiques();
                break;
            case 5://quitter
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
        //variable type représente le type de spécimen
        //méthode demander type demande a l'utilisateur de saisir un type de spécimen
        String type = demanderType();

        //affichage de la liste du type de spécimen choisi
        afficherListeType(type);

        //demande de la position de l'élément que l'utilisateur veut supprimmer
        int élément = tryCatchInt("Quel élément voulez-vous supprimer? Saississez la position");

        listeSpécimen.remove(élément);
        System.out.println((listeSpécimen.get(élément)).getNom() + " a été supprimé.");

    }

    private void modifierQuantitéApercu() {
        String type = demanderType();

        afficherListeType(type);

        int élément = tryCatchInt("Quel élément voulez-vous modifier? Saississez la position");

        int quantité = tryCatchInt("Quelle quantité de spécimen voulez-vous ajouter? ");

        listeSpécimen.get(élément).ajouterQuantitéObservé(quantité);
        System.out.println("La quantité observé à été modifié");

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
//Ne pas oublier de ré enregistrer
//Créer un fichier pokedex.bin si il n'y en avait pas au départ
        System.exit(choix);
    }

    private Spécimen ajouterSpécimen(Personne observateur) {
        boolean erreur, estMale, estDansEauSalee, estCarnivore, estFlottante;
        String dateObservation, type, nom, couleur, cri;
        double taille = 0;
        int quantitéObservé;
        Spécimen spécimen = null;

        System.out.println("Bienvenue dans la section pour ajouter un spécimen");

        do {
            erreur = false;
            System.out.println("Saisissez la date d'observation (8 carctères)");
            dateObservation = clavier.nextLine();
            if ((dateObservation.length()) != 8) {
                System.out.println("Vous n'avez pas saisi 8 caractères!");
                erreur = true;
            } else {
                try {
                    Integer.parseInt(dateObservation);
                } catch (NumberFormatException e) {
                    System.out.println("Vos caractères ne sont pas des entiers! Recommencez");
                    erreur = true;
                }
            }

        } while (erreur);

        type = demanderType();

        System.out.println("Saisissez son nom");
        nom = clavier.nextLine();

        System.out.println("Saississez sa couleur");
        couleur = clavier.nextLine();

        do {
            erreur = false;
            System.out.println("Saissisez sa taille");
            try {
                taille = Double.parseDouble(clavier.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erreur de conversion");
                erreur = true;
            }
        } while (erreur);

        quantitéObservé = tryCatchInt("Saississez la quantité observé");

        switch (type) {
            case "poisson":
                estMale = demanderBoolean("male", "femelle");
                cri = demanderCri();
                estDansEauSalee = demanderBoolean("Eau salée", "eau douce");
                spécimen = new Poisson(estDansEauSalee, estMale, cri, dateObservation, nom, couleur, quantitéObservé, taille, observateur);
                break;

            case "mammifère marin":
                estMale = demanderBoolean("male", "femelle");
                cri = demanderCri();
                estDansEauSalee = demanderBoolean("Eau salée", "eau douce");
                estCarnivore = demanderBoolean("Carnivore", "végétarien");
                spécimen = new MammifèreMarin(estDansEauSalee, estCarnivore, estMale, cri, dateObservation, nom, couleur, quantitéObservé, taille, observateur);
                break;

            case "plante aquatique":
                estDansEauSalee = demanderBoolean("Eau salée", "eau douce");
                estFlottante = demanderBoolean("Flottante", "Immergé");
                spécimen = new Plante(estFlottante, estDansEauSalee, dateObservation, nom, couleur, quantitéObservé, taille, observateur);
                break;

            case "minéral":
                spécimen = new Minéral(dateObservation, nom, couleur, quantitéObservé, taille, observateur);
                break;

            case "autre":
                estMale = demanderBoolean("male", "femelle");
                cri = demanderCri();
                spécimen = new Autre(estMale, cri, dateObservation, nom, couleur, quantitéObservé, taille, observateur);
                break;
        }
        return spécimen;
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
            //CRÉER LE FICHIER À CET EMPLACEMENT!
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

    public static void charcherListePersonnes(String fichier, ArrayList<Personne> listePersonne) throws IOException {
        String codeAcces = null;
        String mdp = null;
        String nom = null;
        String age = null;
        BufferedReader lecture = null;
        try {
            lecture = new BufferedReader(new FileReader(fichier));
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fichier + " n'a pas été trouver dans la méthode charcherListePersonnes du main");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Erreur entrée-sortie avec " + fichier + " dans la méthode charcherListePersonnes du main");
        }
        String ligne = lecture.readLine();

        while (ligne != null) {
            String[] contenuLigne = ligne.split(";");
            contenuLigne[0] = codeAcces;
            contenuLigne[1] = mdp;
            contenuLigne[2] = nom;
            contenuLigne[3] = age;
            int ageint = Integer.parseInt(age); //try catch à faire

            Personne personne = new Personne(nom, codeAcces, mdp, ageint);
            listePersonne.add(personne);
        }
        lecture.close();

    }

    private boolean demanderBoolean(String male, String femelle) {
        System.out.println(male + ":true ou " + femelle + ":autre caractères  ?");

        boolean bool = Boolean.parseBoolean(clavier.nextLine());
        return bool;
    }

    private String demanderCri() {
        System.out.println("Saississez son cri");
        String cri = clavier.nextLine();
        return cri;
    }

    private String demanderType() {
        String type;
        boolean erreur;
        do {
            erreur = false;
            System.out.println("Saisissez le type d'entrée");
            type = clavier.nextLine();
            type.toLowerCase();

            if (type.equals("poisson") || type.equals("mammifère marin") || type.equals("plante aquatique") || type.equals("minéral") || type.equals("autre")) {
                erreur = false;
            } else {
                System.out.println("Vous n'avez pas saisi un type valide!");
                erreur = true;
            }
        } while (erreur);
        return type;
    }

    private void afficherListeType(String type) {

        for (int i = 0; i < listeSpécimen.size(); i++) {
            if (type.equals("poisson") && ((listeSpécimen.get(i)) instanceof Poisson)) {
                System.out.println(listeSpécimen.get(i));
                System.out.println("Élément numéro " + i + " dans la liste");
            } else if (type.equals("mammifère marin") && ((listeSpécimen.get(i)) instanceof MammifèreMarin)) {
                System.out.println(listeSpécimen.get(i));
                System.out.println("Élément numéro " + i + " dans la liste");
            } else if (type.equals("plante aquatique") && ((listeSpécimen.get(i)) instanceof Plante)) {
                System.out.println(listeSpécimen.get(i));
                System.out.println("Élément numéro " + i + " dans la liste");
            } else if (type.equals("minéral") && ((listeSpécimen.get(i)) instanceof Minéral)) {
                System.out.println(listeSpécimen.get(i));
                System.out.println("Élément numéro " + i + " dans la liste");
            } else if (type.equals("autre") && ((listeSpécimen.get(i)) instanceof Autre)) {
                System.out.println(listeSpécimen.get(i));
                System.out.println("Élément numéro " + i + " dans la liste");
            }
        }
    }

    public boolean demanderIdentité() {
        boolean vérifier = false;
        boolean erreurUtilisateur;
        do {
            erreurUtilisateur = false;
            System.out.println("Entrez votre nom d'utilisateur");
            String nomUtilisateur = clavier.nextLine();
            if (nomUtilisateur.equalsIgnoreCase((listePersonne.get(0)).getCodeAcces()) || nomUtilisateur.equalsIgnoreCase((listePersonne.get(1)).getCodeAcces()) || nomUtilisateur.equalsIgnoreCase((listePersonne.get(2)).getCodeAcces()) || nomUtilisateur.equalsIgnoreCase((listePersonne.get(3)).getCodeAcces())) {
                erreurUtilisateur = false;
            } else {
                System.out.println("Le nom d'utilisateur entré est non existant"); //Pas sur que do while fonctionne
                erreurUtilisateur = true;
            }
        } while (erreurUtilisateur);

        //Si après 3 tentatives de demande de mot de passe raté: mettre le boolean true
        return vérifier;
    }

    private void décrypterMotDePasses() {
//        for (int i = 0; i < listePersonne.size(); i++) {
//
//        }
    }

    //métode qui converti un string en int avec une boucle de validation
    private int tryCatchInt(String messageAUtilisateur) {
        boolean erreur;
        int choixUtilisateur = 0;
        do {
            System.out.println(messageAUtilisateur);
            erreur = false;
            try {
                choixUtilisateur = Integer.parseInt(clavier.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erreur de comversion");
                erreur = true;
            }
        } while (erreur);

        return choixUtilisateur;
    }

    private void consulterSpécimensExistants() {
        triCroissantetDécroissant();

        String type = demanderType();
        
        triType();//pas fait
        triAnimaux();//pas fait
    }

    private void triCroissantetDécroissant() {
        Spécimen temp;
        int positionMin;
        boolean lettrePlusGrande = true;
        Stack<Spécimen> pileSpécimen = new Stack();

        for (int i = 0; i < listeSpécimen.size() - 1; i++) {

            positionMin = i;

            for (int j = i + 1; j < listeSpécimen.size(); j++) {

                for (int k = 0; k < listeSpécimen.get(j).getNom().length()
                        && k < listeSpécimen.get(i).getNom().length() && lettrePlusGrande; k++) {

                    if (listeSpécimen.get(j).getNom().charAt(k)
                            < listeSpécimen.get(positionMin).getNom().charAt(k)) {
                        positionMin = j;
                        lettrePlusGrande = false;
                    }
                }

            }
            temp = listeSpécimen.get(positionMin);
            listeSpécimen.set(positionMin, listeSpécimen.get(i));
            listeSpécimen.set(i, temp);
        }
        
        System.out.println("Tous les spécimens enregistrés, classés en ordre croissant\n");

        for (int i = 0; i < listeSpécimen.size(); i++) {
            System.out.println(listeSpécimen.get(i).getNom());
            pileSpécimen.push(listeSpécimen.get(i));
        }
        
        System.out.println("\n\nTous les spécimens enregistrés, classés en ordre décroissant\n");
        
        for (int i = 0; i < pileSpécimen.size(); i++) {
            System.out.println(pileSpécimen.pop().getNom());
        }
        
        
    }

    private void triType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void triAnimaux() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
