package vigenere.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Alexis
 */
public class Chiffrage {

    private String msgCrypte;

    public Chiffrage(String message, String cle) {
        //Creation des variables :
        String messageCrypte = "";
        int compteur = 0;
        final char alpha1[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char alpha2[] = new char[26];
        boolean rep = false;

        //Verification de la cle :
        for (int i = 0; i < cle.length(); i++) {
            if (cle.charAt(i) != ' ') {
                rep = true;
            }
        }

        if (rep == false || cle == null) {

            //Initialisation de la clé :
            for (int i = 0; i < message.length(); i++) {
                int j = (int) (Math.random() * 25 + 1);
                cle += alpha1[j];
            }

            System.out.println("Clé générée : " + cle + "\n");
        }

        if (cle.length() < message.length()) {
            for (int i = cle.length(); i < message.length(); i++) {
                cle += cle.charAt(i % cle.length());
            }
        }

        //Cryptage du message
        while (compteur < message.length()) {

            for (int i = 0; i < 26; i++) {
                if (cle.charAt(compteur) == alpha1[i]) {
                    alpha2[0] = alpha1[i];
                    int j = 0;

                    for (int k = 1; k < 26; k++) {
                        alpha2[k] = (char) (alpha2[0] + k);

                        if (alpha2[k] > 'z') {
                            alpha2[k] = alpha1[0 + j];
                            j++;
                        }
                    }

                    j = alpha1.length;
                }
            }

            //application du principe de vegenere :
            for (int i = 0; i < 26; i++) {
                if (message.charAt(compteur) == alpha1[i]) {
                    messageCrypte += alpha2[i];
                    i = alpha1.length;
                }
            }

            compteur++;

//          //Test 
//          System.out.print("VigenereDeux num "+compteur+" :\n"); for(int i=0; i<26; i++){System.out.print(alpha2[i]);} System.out.print("\n\n");
        }

//      //Test
//      System.out.print("VigenereUn :\n"); for(int i=0; i<26; i++){System.out.print(alpha1[i]);} System.out.print("\n\n");
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
            System.out.println("Erreur : " + err);

        } catch (Exception err) {
            System.out.println("Erreur Gnérale : " + err);
        }

    }
}
