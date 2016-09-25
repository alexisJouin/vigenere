package vigenere.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import static vigenere.java.Vigenere.MAX;
import static vigenere.java.Vigenere.alpha1;
import static vigenere.java.Vigenere.alpha2;

/**
 *
 * @author Alexis
 */
public class Chiffrage {

    private final String msgCrypte;

    public Chiffrage(String message, String cle) {
        
        String messageCrypte = "";
        int compteur = 0;

        if (cle == null || cle == "") {

            cle = "cle"; //Par défaut on met "cle" si rien n'est inscrit
            System.out.println("Clé par défaut : " + cle);
        }

        if (cle.length() < message.length()) {
            for (int i = cle.length(); i < message.length(); i++) {
                cle += cle.charAt(i % cle.length());
            }
        }

        //chiffrage du message
        while (compteur < message.length()) {

            for (int i = 0; i < MAX; i++) {
                if (cle.charAt(compteur) == alpha1[i]) {
                    alpha2[0] = alpha1[i];
                    int j = 0;

                    for (int k = 1; k < MAX; k++) {
                        alpha2[k] = (char) (alpha2[0] + k);

                        if (alpha2[k] > 'z') {
                            alpha2[k] = alpha1[0 + j];
                            j++;
                        }
                    }

                    j = alpha1.length;
                }
            }

            for (int i = 0; i < MAX; i++) {
                if (message.charAt(compteur) == alpha1[i]) {
                    messageCrypte += alpha2[i];
                    i = alpha1.length;
                }
            }
            compteur++;
        }
        this.msgCrypte = messageCrypte;
    }

    public void ecrire() {
        try {

            boolean cont = true;
            Scanner sc = new Scanner(System.in);

            //String numero = sc.nextLine();
            BufferedWriter bw = new BufferedWriter(new FileWriter("texteChiffre.txt"));
            PrintWriter pWriter = new PrintWriter(bw);
            pWriter.print(this.msgCrypte);
            pWriter.close();

            System.out.println("Le message a été chiffré dans le fichier texteChiffre.txt");

        } catch (IOException err) {
            System.out.println("Erreur IO : " + err);

        } catch (Exception err) {
            System.out.println("Erreur Gnérale : " + err);
        }

    }
}
