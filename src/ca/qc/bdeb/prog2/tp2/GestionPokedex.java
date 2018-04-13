/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class GestionPokedex {

    //La classe Doit contenir une liste d'entrées, besoin de plus d'Explication. 
    //On a déjà fait une liste de toutes le entrées dans la classe spécimen...
    
    
    public static void afficherMenu() {
        Scanner clavier = new Scanner(System.in);
        int choix;
        System.out.println("Sassissez une des options suivantes:\n");
        System.out.println("1- Consulter les spécimens déjà saisis\n2- Saisir "
                + "un nouveau spécimen\n3- Modifier un spécimen\n4- Statistiques"
                + "\n5- Quitter");
        choix = Integer.parseInt(clavier.nextLine());
        //Il faudra Gérer exception+bon chiffre parmi ceux demandés
        optionMenu(choix);
    }

    private static void optionMenu(int choix) {
        switch (choix) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;

        }
    }

}
