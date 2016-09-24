package vigenere.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static vigenere.java.Vigenere.MAX;

/**
 *
 * @author Alexis
 */
public class Dechiffrage {

    private String[] lignes;
    private String messageDecrypte = "";

    //TO DO FIX
    public Dechiffrage(String mot, String cle) {
        int longueur;
        longueur = mot.length();
        
        final char alpha1[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char alpha2[] = new char[26];

        for (int i = 0; i < longueur; i++) {
            String newligne = "";
            for (int j = 0; j < lignes[i].length(); j++) {
                newligne += (char) ((lignes[i].charAt(j) - cle.charAt(i) + MAX) % MAX + 'a');
            }
            lignes[i] = newligne;
            System.out.println(lignes[i]);
        }
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
