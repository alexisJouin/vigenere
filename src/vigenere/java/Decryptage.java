package vigenere.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static vigenere.java.Vigenere.MAX;
import static vigenere.java.Vigenere.alpha1;

/**
 *
 * @author Alexis
 */
public class Decryptage {

    private String[] lignes;
//    public String cle;
    private Scanner sc;
    private String[] mots;
    private String msgCrypt;
    private float[] ks;
    private int[] ordre;
    private int longueurCle;
    private int[] cle;
    private double ic;

    public Decryptage(String file) {
        int index = 0;
        int car;
        int decalage;
        

        lire(file);

        System.out.println("\nTexte en clair : ");
        while (index < msgCrypt.length()) {
            car = (int) msgCrypt.charAt(index);
            decalage = cle[index % longueurCle];
            for (int i = 0; i < MAX; i++) {
                if (car >= decalage) {
                    System.out.print((char) (car - decalage + alpha1[i]));
                } else {
                    System.out.print((char) (car + MAX - decalage + alpha1[i]));
                }
            }
            index++;
        }

    }

    //Retourne le nombre d'occurence
    private int[] compteOccurence(String ligne) {
        int[] occ = new int[MAX]; //Nombre d'occurences
        for (int i = 0; i < ligne.length(); i++) {
            occ[ligne.charAt(i) - (int) 'a']++;
        }
        return occ;
    }

    //Calcule l'indice de coincidence par rapport au nombre d'occurence
    public float[] CalculCoincidence() {
        float sum = 0;
        float[] ic = new float[lignes.length];
        for (int i = 0; i < ic.length; i++) {

            int[] occ = compteOccurence(lignes[i]);

            for (int j = 0; j < MAX; j++) {
                sum += occ[j] * (occ[j] - 1);
            }
            ic[i] = sum / (lignes[i].length() * (lignes[i].length() - 1));
        }

        return ic;
    }

    //Recherche du max
    public String ordonne(String ligne) {
        int[] occ = compteOccurence(ligne); //On get le nombre d'occurence
        String ord = "";

        for (int i = 0; i < 26; i++) {

            // Recherche du max 
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

    public final void lire(String file) {

//        String s = "e";//Pour rechercher la lettre dans le fichier
        FileInputStream fichier;

        try {
            fichier = new FileInputStream(file);
            byte[] b;
            int retour = 0;
            msgCrypt = new String();
            String carUnique;
            while (retour != -1) {
                b = new byte[1];

                try {
                    retour = fichier.read(b);
                    carUnique = new String(b);
                    if ((carUnique.compareTo(" ") != 0)) {
                        msgCrypt = msgCrypt.concat(carUnique);
                    }
                } catch (IOException err) {
                    System.out.println("Erreur IO " + err);
                }
            }
            fichier.close();
        } //.println("votre recherche : "+s+" apparait "+mot.(s)+" fois."); //.println("votre recherche : "+s+" apparait "+mot.(s)+" fois.");
        catch (Exception err) {
            System.out.println("Erreur Générale : " + err);
        }
        sc.close();
    }

    private int trouverSuivant(float prec) {
        int indexMax = -1;
        float max = -1;
        for (int i = 0; i < ks.length; i++) {
            if ((ks[i] > max) && (ks[i] <= prec)) {

                max = ks[i];
                indexMax = i;

            }
        }
        return indexMax;
    }


    //Quand on a la longueur de la clé
    private void trouverCle() {

    }
    
    public String toString(){
        return "Indice de coincidence = " + this.ic;
    }
}
