package Gestion_de_livraison2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gerant {


    // cette m√©thode permet d'ajouter un coursier
    public void ajoutCoursier(String nom, String prenom) {
        String coursier = new Date().getTime() + "," + nom + "," + prenom + ",true,0,no,\n"; //crier un nouveau coursier
        try {
            FileWriter myWriter = new FileWriter("src/Resources/coursier.txt", true);// creation d'un objet FileWriter permettant l'√©criture dans le fichier
            myWriter.append(coursier);// ajouter le coursier √† la fin de fichier
            myWriter.close();// fermer le fichier
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //supprimer un Couriser
    // retourne 0 si tous va bien (le coursier est supprim√©), 1 si coursier non trouv√©, 2 si coursier √† des colis √† transporter
    public int supprimerCoursier(String nom, String prenom) {
        String nouvelleListe = "";
        int testSupp = 1;
        File file = new File("src/Resources/coursier.txt");
        Scanner reader;
        try {
            reader = new Scanner(file); // creation d'un objet FileWriter permettant l'Ècriture dans le fichier
            while (reader.hasNextLine()) {//lire une ligne
                String data = reader.nextLine();
                String[] tab = data.split(",");
                if (tab[1].equals(nom) && tab[2].equals(prenom) && !tab[4].equals("0")) {
                    testSupp = 2;// c'est juste pour informer le gÈrant que la supprission est impossible car le coursier a des colis ‡ livrer

                }
                if (tab[1].equals(nom) && tab[2].equals(prenom) && tab[4].equals("0")) {// si le coursier trouvÈ on ne l'ajoute pas ‡ notre nouvelle liste
                    testSupp = 0;

                } else {
                    nouvelleListe += data + "\n";// ajouter le coursier ‡ notre nouvelle liste
                }
            }
            try {
                FileWriter myWriter = new FileWriter("src/Resources/coursier.txt", false);// creation d'objet FileWriter pertmttant la lecture ‡ partir de fichier
                myWriter.write(nouvelleListe);// ecrire dans le fichier
                myWriter.close();// fermer le fichier
            } catch (IOException ex) {
                Logger.getLogger(Gerant.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Gerant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return testSupp;
    }
    

}
