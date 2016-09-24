package vigenere.java;

import java.util.Scanner;

/**
 *
 * @author Alexis
 */
public class Vigenere {

    public static int MAX = 26;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int choose;
        Scanner sc = new Scanner(System.in);

        System.out.println("Tapez 1 pour chiffrer");
        System.out.println("Tapez 2 pour déchiffrer");
        System.out.println("Tapez 3 pour décrypter un fichier");
        String choix = sc.nextLine();

        choose = Integer.parseInt(choix);

        if (choose == 1) {
            System.out.println("Veuillez saisir un mot à chiffrer :");
            String mot = sc.nextLine();
            System.out.println("Veuillez saisir la clé de chiffrage :");
            String cle = sc.nextLine();

            Chiffrage chf = new Chiffrage(mot, cle);
            chf.ecrire();

            System.out.println("Fin du programme");

            //TO DO !!!
        } else if (choose == 2) {

            System.out.println("Veuillez saisir un mot à déchiffrer :");
            String mot = sc.nextLine();
            System.out.println("Veuillez saisir la clé de déchiffrage :");
            String cle = sc.nextLine();

            Dechiffrage dec = new Dechiffrage(mot, cle);

            System.out.println("Fin du programme");

        } else {

            System.out.println("Veuillez saisir le fichier à décrypter :");
            String file = sc.nextLine();
            Decryptage decr = new Decryptage(file);

            /*
             float[] ic = decr.CalculCoincidence();
             for (float i : ic) {
             System.out.println(i);
             }
             */
            System.out.println("Fin du programme");
        }

    }
}
