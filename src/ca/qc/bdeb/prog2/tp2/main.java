/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.tp2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Nicolas
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
       boolean erreur =false;
       
        charcherListePersonnes();
        chargerFichierPokedex();
        
        erreur=demanderIdentité();
        
        if(erreur){
            //Créer objet GestionPokedex
            //Caller le menu
        }else if(!erreur){
            System.out.println("Vous avez échoué vos 3 tentatives!");
            System.exit(0);
        }
        
    }

    private static void charcherListePersonnes() throws FileNotFoundException {
//       Va créer les personnes à partir du fichier texte 
BufferedReader lecture=new BufferedReader(new FileReader("personnes.txt"));

    }

    private static void chargerFichierPokedex() {
//        Va charger le contenu du fichier et le mettre dans la liste de spécimen?? À vérifier
//        Si fichier inexistant, on va devoir catcher filenotfound et ensuite créer
    }

    private  static boolean demanderIdentité() {
       boolean vérifier = false;
       //Si après 3 tentatives de demande de mot de passe raté: mettre le boolean true
       return vérifier;
    }
    
}
