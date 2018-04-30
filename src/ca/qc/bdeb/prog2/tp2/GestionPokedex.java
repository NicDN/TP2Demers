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
    Personne observateur;

    public void démarrer() {
        try {
            //les try catch ne sont pas bien fait, ils sont automatique

            charcherListePersonnes("personnes.txt", listePersonne);
        } catch (IOException ex) {

        }

        try {
            chargerFichierPokedex("pokedex.bin");
        } catch (ClassNotFoundException ex) {

        }
        demanderIdentité();
        //demander identité si réussi à trouver mdp on call la menu
        //le menu doit être dans un while
    }

    public void afficherMenu() {

        boolean erreur = true;

        //boucle de validation du choix du menu
        while (erreur) {

            System.out.println("Saisissez une des options suivantes:\n");
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

        //étapes selon le choix
        switch (choix) {
            case 1://Consulter les spécimens déjà saisis
                sousMenuAfficher();

                afficherMenu();
                break;
            case 2://saisir un nouveau spécimen
                listeSpécimen.add(ajouterSpécimen());
                afficherMenu();
                break;
            case 3://modifier un spécimen
                sousMenuModifier();
                afficherMenu();
                break;
            case 4://statistiques
                statistiques();
                afficherMenu();
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
                sousMenuModifier();
                break;
            case 2:
                modifierQuantitéApercu();
                sousMenuModifier();
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
        int élément;
        boolean erreur = false;

        do {

            élément = tryCatchInt("Quel élément voulez-vous supprimer? Saississez la position");
            try {
                listeSpécimen.get(élément);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Mauvaise coordonnée");
                erreur = true;
            }

        } while (erreur || élément > listeSpécimen.size() || !listeSpécimen.get(élément).getType().equals(type));

        listeSpécimen.remove(élément);
        System.out.println("Élément supprimé");

    }

    private void modifierQuantitéApercu() {
        String type = demanderType();

        afficherListeType(type);
        int élément;
        System.out.println(listeSpécimen.size());
        do {
            élément = tryCatchInt("Quel élément voulez-vous modifier? Saississez la position");
        } while (élément > listeSpécimen.size());

        int quantité = tryCatchInt("Quelle quantité de spécimen voulez-vous ajouter? ");

        listeSpécimen.get(élément).ajouterQuantitéObservé(quantité);

        System.out.println("La quantité observé à été modifié");

    }

    private void statistiques() {
        afficherNbEntréesTypes();
        System.out.println("");
        afficherNbEntréesPersonnes();
        System.out.println("");
        afficherInfosPersonnes();
    }

    private void afficherNbEntréesTypes() {
        int cptMinéral = 0, cptPoisson = 0, cptMammifère = 0, cptAutre = 0, cptPlante = 0;
        for (int i = 0; i < listeSpécimen.size(); i++) {
            if (listeSpécimen.get(i) instanceof Plante) {
                cptPlante++;
            } else if (listeSpécimen.get(i) instanceof Minéral) {
                cptMinéral++;
            } else if (listeSpécimen.get(i) instanceof Poisson) {
                cptPoisson++;
            } else if (listeSpécimen.get(i) instanceof MammifèreMarin) {
                cptMammifère++;
            } else if (listeSpécimen.get(i) instanceof Autre) {
                cptAutre++;
            }
        }
        System.out.println("Nombre d'entrées de plante " + cptPlante + "\nNombre d'"
                + "entrées de poisson: " + cptPoisson + "\nNombre d'entrées de minér"
                + "al: " + cptMinéral + "\nNombre d'entrées de autre: "
                + cptAutre + "\nNombre d'entrées de mammifère marin: " + cptMammifère);
    }

    private void afficherNbEntréesPersonnes() {

        int cptMitaine = 0, cptBieber = 0, cptJohn = 0, cptConseil = 0, cptCrunch = 0;
        for (int i = 0; i < listeSpécimen.size(); i++) {
            if (listeSpécimen.get(i).getObservateur().getCodeAcces().equals("mitaine")) {
                cptMitaine++;
            } else if (listeSpécimen.get(i).getObservateur().getCodeAcces().equals("bieber")) {
                cptBieber++;
            } else if (listeSpécimen.get(i).getObservateur().getCodeAcces().equals("john")) {
                cptJohn++;
            } else if (listeSpécimen.get(i).getObservateur().getCodeAcces().equals("crunch")) {
                cptCrunch++;
            } else if (listeSpécimen.get(i).getObservateur().getCodeAcces().equals("conseil")) {
                cptConseil++;
            }
        }
        System.out.println("Nombre d'entrées de Capitaine Mitaine: " + cptMitaine + "\nNombre d'"
                + "entrées de Justin Bieber: " + cptBieber + "\nNombre d'entrées de John Deere"
                + ": " + cptJohn + "\nNombre d'entrées de Conseil: "
                + cptConseil + "\nNombre d'entrées de Capitaine Crunch: " + cptCrunch);

    }

    private void afficherInfosPersonnes() {
        System.out.println("Informations sur les personnes: ");
        for (int i = 0; i < listePersonne.size(); i++) {
            System.out.println(listePersonne.get(i));
        }
    }

    private void quitter() {
        sauvegarderFichierPokedex("pokedex.bin", listeSpécimen);

        System.exit(0);

    }

    private Spécimen ajouterSpécimen() {
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
        } catch (java.io.IOException e) {
            System.out.println("Erreur entrée-sortie avec " + fichier + " " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void chargerFichierPokedex(String fichier) throws ClassNotFoundException {
        try {
            FileInputStream fos = new FileInputStream(fichier);
            ObjectInputStream oos = new ObjectInputStream(fos);
            listeSpécimen = (ArrayList<Spécimen>) oos.readObject();
            System.out.println("Fichier chargé");
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fichier + " n'a pas été trouver "
            );

        } catch (IOException e) {
            System.out.println("Erreur entrée-sortie avec " + fichier + " " + e.getMessage());
            e.printStackTrace();
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
        int ageint = 0;
        while (ligne != null) {

            String[] contenuLigne = ligne.split(";");
            codeAcces = contenuLigne[0];
            mdp = contenuLigne[1];
            nom = contenuLigne[2];
            age = contenuLigne[3];
            try {
                ageint = Integer.parseInt(age); //try catch à faire
            } catch (NumberFormatException e) {
                System.out.println("Erreur de conversion de l'âge");
            }
            Personne personne = new Personne(nom, codeAcces, mdp, ageint);
            listePersonne.add(personne);
            ligne = lecture.readLine();
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
//            String typeObjet=type.charAt(0)

            if (type.equals("poisson") || type.equals("mammifère marin") || type.equals("plante aquatique") || type.equals("minéral") || type.equals("autre")) {
                erreur = false;
            } //else if (listeSpécimen.contains(typeObjet) ==false) {} 
            else {
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
        int positionUtilisateur = 0;
        do {
            erreurUtilisateur = true;
            System.out.println("Entrez votre nom d'utilisateur");
            String nomUtilisateur = clavier.nextLine();
            for (int position = 0; position < listePersonne.size(); position++) {

                if (nomUtilisateur.equals(listePersonne.get(position).getCodeAcces())) {
                    observateur = listePersonne.get(position);
                    erreurUtilisateur = false;
                    positionUtilisateur = position;

                }

            }
            if (erreurUtilisateur) {
                System.out.println("Votre nom d'utilisateur est inexistant");
            }
        } while (erreurUtilisateur);
        String mdpDécrypté = décrypterMotDePasses(positionUtilisateur);
        boolean erreur = true;

        for (int i = 0; i < 3 && erreur; i++) {
            System.out.println("Entrez votre mot de passe");
            String mdp = clavier.nextLine();
            erreur = vérifierIdentité(mdpDécrypté, mdp);
        }
        if (erreur) {
            System.exit(0);

        }

        afficherMenu();
        return erreur;
    }

    private String décrypterMotDePasses(int position) {
        Encryption encryption = null;
        try {
            encryption = new Encryption();
        } catch (Exception ex) {
            System.exit(choix);
        }

        String mdpDécrypté = encryption.decrypt(listePersonne.get(position).getMdp());
        return mdpDécrypté;
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

        String type = demanderType();

        triType(type);
        triAnimaux();
    }

    private void triCroissantEtDécroissant() {
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
            System.out.println(listeSpécimen.get(i));
            pileSpécimen.push(listeSpécimen.get(i));
        }

        System.out.println("\n\nTous les spécimens enregistrés, classés en ordre décroissant\n");

        for (int i = 0; i < pileSpécimen.size(); i++) {
            System.out.println(pileSpécimen.pop());
        }

    }

    private void triType(String type) {
        Spécimen temp;
        boolean lettrePlusGrande = true;

        for (int position = listeSpécimen.size() - 1; position >= 0; position--) {

            for (int recherche = 0; recherche <= position - 1; recherche++) {

                for (int k = 0; k < listeSpécimen.get(recherche).getNom().length()
                        && k < listeSpécimen.get(position).getNom().length() && lettrePlusGrande; k++) {

                    if (listeSpécimen.get(recherche).getNom().charAt(k)
                            > listeSpécimen.get(recherche + 1).getNom().charAt(k)) {
                        temp = listeSpécimen.get(recherche);
                        listeSpécimen.set(recherche, listeSpécimen.get(recherche + 1));
                        listeSpécimen.set(recherche + 1, temp);
                        lettrePlusGrande = false;
                    }
                }
            }
        }

        for (int i = 0; i < listeSpécimen.size(); i++) {
            if (type.equals("poisson") && ((listeSpécimen.get(i)) instanceof Poisson)) {
                System.out.println(listeSpécimen.get(i));

            } else if (type.equals("mammifère marin") && ((listeSpécimen.get(i)) instanceof MammifèreMarin)) {
                System.out.println(listeSpécimen.get(i));

            } else if (type.equals("plante aquatique") && ((listeSpécimen.get(i)) instanceof Plante)) {
                System.out.println(listeSpécimen.get(i));

            } else if (type.equals("minéral") && ((listeSpécimen.get(i)) instanceof Minéral)) {
                System.out.println(listeSpécimen.get(i));

            } else if (type.equals("autre") && ((listeSpécimen.get(i)) instanceof Autre)) {
                System.out.println(listeSpécimen.get(i));

            }

        }
    }

    private void triAnimaux() {
        Spécimen temp;
        int positionMin;
        boolean chiffrePlusGrande = true;

        for (int i = 0; i < listeSpécimen.size() - 1; i++) {

            positionMin = i;

            for (int j = i + 1; j < listeSpécimen.size(); j++) {

                for (int k = 0; k < listeSpécimen.get(j).getDateObservation().length()
                        && k < listeSpécimen.get(i).getDateObservation().length() && chiffrePlusGrande; k++) {

                    if (listeSpécimen.get(j).getDateObservation().charAt(k)
                            < listeSpécimen.get(positionMin).getDateObservation().charAt(k)) {
                        positionMin = j;
                        chiffrePlusGrande = false;
                    }
                }

            }
            temp = listeSpécimen.get(positionMin);
            listeSpécimen.set(positionMin, listeSpécimen.get(i));
            listeSpécimen.set(i, temp);
        }

        for (int i = 0; i < listeSpécimen.size(); i++) {
            if (((listeSpécimen.get(i)) instanceof Poisson) || ((listeSpécimen.get(i)) instanceof MammifèreMarin) || ((listeSpécimen.get(i)) instanceof Autre)) {
                System.out.println(listeSpécimen.get(i));
            }

        }
    }

    private boolean vérifierIdentité(String mdpDécrypté, String mdp) {
        boolean erreur;
        if (mdpDécrypté.equalsIgnoreCase(mdp)) {
            erreur = false;
        } else {
            erreur = true;
        }
        return erreur;
    }

    private void sousMenuAfficher() {
        boolean erreur = true;
        int choix = 0;
        while (erreur) {

            System.out.println("Saisissez une des options suivantes:\n");
            System.out.println("1- Afficher en ordre croissant et décroissante\n2- Afficher par type de spécimen "
                    + "\n3- Afficher les animaux\n4-Retour au menu\nFaites votre choix");

            //Sasir une donné et essayer de la convertir en int
            try {
                choix = Integer.parseInt(clavier.nextLine());
                //Validation de la donnée une fois convertie
                if (choix <= 0 || choix >= 5) {
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

        optionMenuAfficher(choix);
    }

    private void optionMenuAfficher(int choix) {
        switch (choix) {
            case 1:
                triCroissantEtDécroissant();
                break;
            case 2:
                String type = demanderType();

                triType(type);
                break;
            case 3:
                triAnimaux();
                break;
            case 4:
                afficherMenu();
                break;
        }

    }

}
