package vigenere.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static vigenere.java.Vigenere.MAX;

/**
 *
 * @author Alexis
 */
public class Decryptage {

    private String[] lignes;
    private Scanner sc;
    private String[] mots;
    private String mot;
    //Collection<String> list = new ArrayList<String>();
    public final char alpha[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public Decryptage(String file) {

        lire(file);
//        System.out.println(mot);
//        CalculCoincidence();
//        for (String l :lignes){
//		System.out.println(ordonne(l));
//        }

    }

    //Retourne le nombre d'occurence
    private int[] compte(String ligne) {
        int[] occ = new int[MAX]; //Nombre d'occurences
        for (int i = 0; i < ligne.length(); i++) {
            occ[ligne.charAt(i) - (int) 'a']++;
        }
        return occ;
    }

    //Calcule l'indice de coincidence par rapport au nombre d'occurence
    public float[] CalculCoincidence() {
        float[] ic = new float[lignes.length];
        for (int i = 0; i < ic.length; i++) {

            int[] occ = compte(lignes[i]);
            float sum = 0;
            for (int j = 0; j < MAX; j++) {
                sum += occ[j] * (occ[j] - 1);
            }
            ic[i] = sum / (lignes[i].length() * (lignes[i].length() - 1));
        }

        return ic;
    }

    //Méthode de 
    public String ordonne(String ligne) {
        int[] occ = compte(ligne); //On get le nombre d'occurence
        String ord = "";

        for (int i = 0; i < 26; i++) {

            /* Recherche du max */
            int max = 0;
            int pos = -1;
            for (int j = 0; j < MAX; j++) {
                if (occ[j] > max) {
                    max = occ[j];
                    pos = j;
                }
            }

            if (max > 0) {
                occ[pos] = 0;
                ord += (char) ('a' + pos);
            } else {
                break;
            }
        }
        return ord;
    }

    public void lire(String file) {
        int i = 1;
        String s = "e";//Pour rechercher la lettre dans le fichier

        try {
            File fichier = new File(file);
            sc = new Scanner(fichier);

            while (sc.hasNextLine()) {
                
                try {
                    mot = sc.next();
                    System.out.println(mot);

                    //list.add(mot.toString().toLowerCase());
                    
                    //System.out.println(compte(mot));
                    
                    i++;

//                    System.out.println(mot + " : " + i + " ");
                } catch (NoSuchElementException exception) {
                    System.out.println("Erreur : " + exception);
                    break;
                }
            }

//            System.out.println("votre recherche : "+s+" apparait "+mot.(s)+" fois.");
        } catch (IOException err) {
            System.out.println("Erreur : " + err);
        }
        sc.close();
    }
}
