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

/**
 * Classe gestion du Pokedex
 *
 * @author Hadrien Guimond et Nicolas Demers
 * @version finale
 */
public class GestionPokedex {

    ArrayList<Spécimen> listeSpécimen = new ArrayList();
    ArrayList<Personne> listePersonne = new ArrayList();

    Scanner clavier = new Scanner(System.in);
    int choix;
    Personne observateur;

    /**
     * Méthode mère qui organise le déroulement du programe, du début jusqu'a la
     * fin
     */
    public void démarrer() {

        charcherListePersonnes("personnes.txt");

        chargerFichierPokedex("pokedex.bin");

        demanderIdentité();

    }

    /**
     * Méthode de chargement de la liste de personne selon un fichier texte, qui
     * les ajoutent dans une liste de Personnes
     *
     * @param fichier Nom du fichier que l'on veut accéder
     */
    public void charcherListePersonnes(String fichier) {
        String codeAcces, mdp, nom, age, ligne;
        BufferedReader lecture = null;
        int ageint = 0;
        String[] contenuLigne;

        try {
            lecture = new BufferedReader(new FileReader(fichier));

            ligne = lecture.readLine();

            while (ligne != null) {

                contenuLigne = ligne.split(";");
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
                try {
                    ligne = lecture.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(GestionPokedex.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            lecture.close();

        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fichier + " n'a pas été trouver dans la méthode charcherListePersonnes du main");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Erreur entrée-sortie avec " + fichier + " dans la méthode charcherListePersonnes du main");
        }

    }

    /**
     * Méthode de chargement du fichier binaire qui contient la liste de
     * spécimen utilisée
     *
     * @param fichier Nom du fichier que l'on veut accéder
     */
    public void chargerFichierPokedex(String fichier) {
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
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe non trouvée");
        }

    }

    /**
     * Méthode qui vérifie si le nom d'utilisateur existe et si son mot de passe
     * est valide
     */
    public void demanderIdentité() {
        boolean erreurUtilisateur, erreur = true;
        int positionUtilisateur = 0;
        String nomUtilisateur;

        String mdpDécrypté, mdp;

        do {
            erreurUtilisateur = true;
            System.out.println("Entrez votre nom d'utilisateur");
            nomUtilisateur = clavier.nextLine();
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
        mdpDécrypté = décrypterMotDePasses(positionUtilisateur);

        for (int i = 0; i < 3 && erreur; i++) {
            System.out.println("Entrez votre mot de passe");
            mdp = clavier.nextLine();
            erreur = vérifierIdentité(mdpDécrypté, mdp);
        }
        if (erreur) {
            System.exit(0);

        }

        afficherMenu();
    }

    /**
     *
     * @param position Position de la personne dans la liste de Personnes
     * @return le mot de passe décrypter
     */
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

    /**
     * Sous méthode de vérification du mot de passe de la personne
     *
     * @param mdpDécrypté Vrai mot de passe de la personne
     * @param mdp Mot de passe saisi par la personne
     * @return vrai si mdpDécrypté est pareil a mdp
     */
    private boolean vérifierIdentité(String mdpDécrypté, String mdp) {
        boolean erreur;
        if (mdpDécrypté.equalsIgnoreCase(mdp)) {
            erreur = false;
        } else {
            erreur = true;
        }
        return erreur;
    }

    /**
     * Méthode qui affiche le menu principal et qui call le menu
     */
    public void afficherMenu() {
        boolean continuer = true;
        boolean erreur = true;
        while (continuer) {

            while (erreur) {

                System.out.println("Saisissez une des options suivantes:\n");
                System.out.println("1- Consulter les spécimens déjà saisis\n2- Saisir "
                        + "un nouveau spécimen\n3- Modifier un spécimen\n4- Statistiques"
                        + "\n5- Quitter");

                try {
                    choix = Integer.parseInt(clavier.nextLine());

                    if (choix <= 0 || choix >= 6) {
                        System.out.println("Le numéro que vous avez saisie n'est pas valide, veuillez réessayer.");
                    } else {

                        erreur = false;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Vous n'avez pas rentrer un nombre, veuillez réessayer.");

                } catch (Exception e) {
                    System.out.println("Exeption autre dans classe GestionPokedex, méthode afficherMenu");
                }
            }
            erreur = true;

            continuer = optionMenu(choix);
        }
    }

    /**
     * Méthode qui gère le choix de l'utilisateur
     *
     * @param choix Choix d'action de l'utilisateur
     * @return vrai si l'utilisateur n'a pas quitter
     */
    private boolean optionMenu(int choix) {

        boolean recommencer = false;
        switch (choix) {
            case 1:
                sousMenuAfficher();

                recommencer = true;
                break;
            case 2:
                listeSpécimen.add(ajouterSpécimen());
                recommencer = true;
                break;
            case 3:
                sousMenuModifier();
                recommencer = true;
                break;
            case 4:
                statistiques();
                recommencer = true;
                break;
            case 5:
                quitter();
                recommencer = false;
                break;

        }
        return recommencer;
    }

    /**
     * Méthode qui demande a l'utilisateur de quelle façon il veut voir les
     * spécimens
     */
    private void sousMenuAfficher() {
        boolean erreur = true;
        int choix = 0;
        boolean recommencer = true;
        while (recommencer) {
            while (erreur) {

                System.out.println("Saisissez une des options suivantes:\n");
                System.out.println("1- Afficher en ordre croissant et décroissante\n2- Afficher par type de spécimen "
                        + "\n3- Afficher les animaux\n4-Retour au menu\nFaites votre choix");

                try {
                    choix = Integer.parseInt(clavier.nextLine());
                    if (choix <= 0 || choix >= 5) {
                        System.out.println("Le numéro que vous avez saisie n'est pas valide, veuillez réessayer.");
                    } else {
                        erreur = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Vous n'avez pas rentrer un nombre, veuillez réessayer.");
                } catch (Exception e) {
                    System.out.println("Exeption autre dans classe GestionPokedex, méthode afficherMenu");
                }
            }
            erreur = true;
            recommencer = optionMenuAfficher(choix);
        }
    }

    /**
     * Méthode qui gère le choix de l'utilisateur
     *
     * @param choix Choix de l'utilisateur
     * @return false si l'utilisateur veut retourné au menu principal
     * @see sousMenuAfficher
     */
    private boolean optionMenuAfficher(int choix) {
        boolean recommencer = false;
        switch (choix) {
            case 1:
                triCroissantEtDécroissant();
                recommencer = true;
                break;

            case 2:
                String type = demanderType();

                triType(type);
                recommencer = true;
                break;
            case 3:
                triAnimaux();
                recommencer = true;
                break;
            case 4:
                recommencer = false;
                break;
        }
        return recommencer;

    }

    /**
     * Méthode qui tri de façon croissante puis décroissante la liste de
     * spécimen actuelle
     */
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

        for (int i = 0; pileSpécimen.size() != 0; i++) {
            System.out.println(pileSpécimen.pop());
        }

    }

    /**
     * Méthode qui tri la liste actuelle de spécimen selon un type précis
     *
     * @param type Type de spécimen choisi par l'utilisateur
     */
    private void triType(String type) {
        boolean erreur = true;
        for (int i = 0; i < listeSpécimen.size() && erreur; i++) {
            if (listeSpécimen.get(i).getType().equals(type)) {
                erreur = false;
            } else {
                erreur = true;
            }
        }
        if (erreur) {
            System.out.println("La liste ne contient pas d'élément du type demandé");
        }

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

    /**
     * Méthode qui tri les animaux de la liste de spécimen actuelle
     */
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

    /**
     * Méthode qui demande a l'utilisateur quel spécimen veut-il ajouter
     *
     * @return le spécimen qu'il a ajouter
     */
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

            case "mammifere marin":
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

            case "mineral":
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

    /**
     * Méthode qui demande le choix d'action pour modifier un spécimen
     */
    private void sousMenuModifier() {
        boolean erreur = true;
        boolean continuer = true;
        while (continuer) {
            while (erreur) {

                System.out.println("Sassissez une des options suivantes:\n");
                System.out.println("1-Supprimer un spécimen\n2-Modifier la quantité "
                        + "aperçue d'un spécimen donné\n3-Retourner au menu principal");

                try {
                    choix = Integer.parseInt(clavier.nextLine());
                    if (choix <= 0 || choix >= 4) {
                        System.out.println("Le numéro que vous avez saisie n'est pas valide, veuillez réessayer.");
                    } else {
                        erreur = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Vous n'avez pas rentrer un nombre, veuillez réessayer.");
                } catch (Exception e) {
                    System.out.println("Exeption autre dans classe GestionPokedex, méthode sousMenuModifier");
                }
            }
            erreur = true;
            continuer = optionMenuModifier(choix);
        }
    }

    /**
     * Méthode qui gère le choix de l'utilisateur
     *
     * @param choix Choix saisi par l'utilisateur
     * @return false si l'utilisateur veut retourner au menu principal
     */
    private boolean optionMenuModifier(int choix) {
        boolean recommencer = false;
        switch (choix) {
            case 1:
                supprimerSpecimen();
                recommencer = true;
                break;
            case 2:
                modifierQuantitéApercu();
                recommencer = true;
                break;
            case 3:
                recommencer = false;
                break;

        }
        return recommencer;
    }

    /**
     * Méthode qui suprime un spécimen choisi par l'utilisateur
     */
    private void supprimerSpecimen() {
        boolean erreur = true;
        String type;
        do {
            if (listeSpécimen.size() == 0) {
                System.out.println("La liste est vide");
                return;
            }

            type = demanderType();
            for (int i = 0; i < listeSpécimen.size() && erreur; i++) {
                if (listeSpécimen.get(i).getType().equals(type)) {
                    erreur = false;
                } else {
                    erreur = true;

                }
            }
            if (erreur) {
                System.out.println("Il n'y a pas de spécimen de ce type");
            }
        } while (erreur);

        afficherListeType(type);

        int élément;

        do {

            élément = tryCatchInt("Quel élément voulez-vous supprimer? Saississez la position");
            try {
                listeSpécimen.get(élément);
                erreur = false;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Mauvaise coordonnée");
                erreur = true;
            }

        } while (erreur || élément > listeSpécimen.size() || !listeSpécimen.get(élément).getType().equals(type));

        listeSpécimen.remove(élément);
        System.out.println("Élément supprimé");

    }

    /**
     * Méthode qui permet a l'utilisateur de modifier la quantité apercu d'un
     * spécimen par l'utilisateur
     */
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

    /**
     * Méthode qui affiche le nomdre de spécimen selon chaque type ainsi que le
     * nombre de spécimen crée selon chaque utilisateur
     */
    private void statistiques() {
        afficherNbEntréesTypes();
        System.out.println("");
        afficherNbEntréesPersonnes();
        System.out.println("");
        afficherInfosPersonnes();
    }

    /**
     * Méthode qui affiche le nombre d'entrée selon les types de spécimen
     */
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

    /**
     * Méthode qui affiche le nombre d'entrée selon les utilisateurs
     */
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

    /**
     * Méthode qui affiche les information sans les mots de passes des
     * utilisateurs
     */
    private void afficherInfosPersonnes() {
        System.out.println("Informations sur les personnes: ");
        for (int i = 0; i < listePersonne.size(); i++) {
            System.out.println(listePersonne.get(i));
        }
    }

    /**
     * Méthode qui quitte le programe en sauvegardant la liste de spécimen
     */
    private void quitter() {
        sauvegarderFichierPokedex("pokedex.bin");
    }

    /**
     * Méthode qui sauvegarde la liste de spécimen dans un fichier binaire
     *
     * @param fichier Nom du fichier à accéder
     */
    public void sauvegarderFichierPokedex(String fichier) {
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

    /**
     * Méthode qui demande a l'utilisateur un boolean
     *
     * @param booltrue String qui est vrai
     * @param stringFalse String qui est fausse
     * @return le choix (vrai ou faux) de l'utilisateur
     */
    private boolean demanderBoolean(String stringTrue, String stringFalse) {
        System.out.println(stringTrue + ":true ou " + stringFalse + ":autre caractères  ?");

        boolean bool = Boolean.parseBoolean(clavier.nextLine());
        return bool;
    }

    /**
     * Méthode qui demande le cri de l'animal à l'utilisateur
     *
     * @return le cris saisi par l'utilisateur
     */
    private String demanderCri() {
        System.out.println("Saississez son cri");
        String cri = clavier.nextLine();
        return cri;
    }

    /**
     * Méthode qui demande quel type de spécimen a l'utilisateur
     * 
     * @return le spécimen en lowercase saisi par l'utilisateur
     */
    private String demanderType() {
        String type;
        boolean erreur;
        do {
            erreur = false;
            System.out.println("Saisissez le type d'entrée");
            type = clavier.nextLine();
            type.toLowerCase();

            if (type.equals("poisson") || type.equals("mammifere marin") || type.equals("plante aquatique") || type.equals("mineral") || type.equals("autre")) {
                erreur = false;
            } else {
                System.out.println("Vous n'avez pas saisi un type valide!");
                erreur = true;
            }
        } while (erreur);
        return type;
    }

    /**
     *Méthode qui affiche tous les spécimen d'un certain type
     * 
     * @param type Type de spécimen à afficher
     */
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

    /**
     * Méthode qui transforme un String saisi au clavier en int
     * @param messageAUtilisateur Message de saisi pour l'utilisateur
     * @return le int transormer du string
     */
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

}
