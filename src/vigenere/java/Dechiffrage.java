package vigenere.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static vigenere.java.Vigenere.MAX;
import static vigenere.java.Vigenere.alpha1;

/**
 *
 * @author Alexis
 */
public final class Dechiffrage {

    private String[] lignes;
    private String messageDecrypte = "";
    private int position = 0;
    private final char[][] vigenere = new char[MAX][MAX];

    //TO DO FIX
    public Dechiffrage(String message, String cle) {
        int longueur;
        longueur = message.length();

        cle = cle.toUpperCase();

        int row;
        int col = 0;
        int compteur = 0;

        if (position == cle.length()) {
            position = 0;
        }
        /*
         String msgDecrypte = "";

         if (cle.length() < message.length()) {
         for (int j = cle.length(); j < message.length(); j++) {
         cle += cle.charAt(j % cle.length());
         }
         }

         //Déchiffrement du message
         while (compteur < message.length()) {

         for (int j = 0; j < MAX; j++) {
         if (cle.charAt(compteur) == alpha1[j]) {
         alpha2[0] = alpha1[j];
         int l = 0;

         for (int k = 1; k < MAX; k++) {
         alpha2[k] = (char) (alpha2[0] + k);

         if (alpha2[k] > 'z') {
         alpha2[k] = alpha1[0 + j];
         l++;
         }
         }

         l = alpha1.length;
         }
         }

         for (int j = 0; j < MAX; j++) {
         if (cle.charAt(compteur) == alpha1[j]) {
         msgDecrypte += alpha2[j];
         j = alpha1.length;
         }
         }
         compteur++;
         }
         this.messageDecrypte = msgDecrypte;
         */

        
        row = (int) cle.charAt(position) - alpha1[i];
        for (int j = 0; j < MAX; j++) {
            if (vigenere[row][j] == Character.toLowerCase(message.charAt(i))) {
                col = j;
            }
        }

        if ((int) message.charAt(i) > alpha1[i]) {
            decode += (char) (col + alpha1[i]);
            position++;
        } else if ((int) message.charAt(i) > alpha1[i] && (int) message.charAt(i) <= 122) {
            decode += (char) (col + alpha1[i]);
            position++;
        } else {
            decode += message.charAt(i);
        }
    }

         //        this.msgDecrypte = msgDecrypte;
    decode += "\n" ;
     
    this.msgDecrypte  = decode;
    

    ecrire();

    /*
     for (int i = 0; i < longueur; i++) {
     String newligne = "";
     for (int j = 0; j < lignes[i].length(); j++) {
     newligne += (char) ((lignes[i].charAt(j) - cle.charAt(i) + MAX) % MAX + 'a');
     }
     lignes[i] = newligne;
     System.out.println(lignes[i]);
     }
        
     */
}

public void ecrire() {

        try {

            //String numero = sc.nextLine();
            BufferedWriter bw = new BufferedWriter(new FileWriter("texteClair.txt"));
            PrintWriter pWriter = new PrintWriter(bw);
            pWriter.print(this.messageDecrypte);

            pWriter.close();
            System.out.println("Le message a été déchiffré dans le fichier texteClair.txt");

        } catch (IOException err) {
            System.out.println("Erreur : " + err);

        } catch (Exception err) {
            System.out.println("Erreur Gnérale : " + err);
        }

    }

}
