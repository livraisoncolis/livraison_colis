package Gestion_de_livraison2;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Societe {

    private String listeColis[][];
    private String listeClient[][];
    private String listeCoursier[][];
    private double caisse;
    private String zoneGeographique[];
    public String[][] listeCaisse;

    //Getters()
    public String[][] getListeColis() {
        return listeColis;
    }

    public String[][] getListeClient() {
        return listeClient;
    }

    public String[][] getListeCoursier() {
        return listeCoursier;
    }

    public double getCaisse() {
        return caisse;
    }

    public String[] getZoneGeographique() {
        return zoneGeographique;
    }

    //Setters()
    public void setListeColis(String listeColis[][]) {
        this.listeColis = listeColis;
    }

    public void setListeClient(String listeClient[][]) {
        this.listeClient = listeClient;
    }

    public void setListeCoursier(String listeCoursier[][]) {
        this.listeCoursier = listeCoursier;
    }

    public void setCaisse(double caisse) {
        this.caisse = caisse;
    }

    public void setZoneGeographique(String[] zoneGeographique) {
        this.zoneGeographique = zoneGeographique;
    }
    
    // Les fonctions : 
    
    // Elle transformr les fichiers en matrices
    private String[][] fromFileToTab(int type) throws IOException {
        switch (type) {
            case 0:// liste des clients   : Nom,Prenom,CIN,Identifiant,Password,PonitDeFidélité,ListeDesColis (5:6:9)

                List<String> list0 = Files.readAllLines(Paths.get("src/Resources/client.txt")); // Transfomer chaque ligne du fichier en une case de type String 
                String[][] result0 = new String[list0.size()][7]; // La matrice resutat de taille nbr de ligne du fichier * nbr de case 
                int i = 0;
                for (String s : list0) {
                    result0[i] = s.split(","); // Convertir Chaque ligne en un tableau de String en utilisant le délimiteur ","
                    i++;
                }
                return result0;
            case 2:// liste des colis    : Identifiant,Poids,Destination,Position,DateDepart

                List<String> list1 = Files.readAllLines(Paths.get("src/Resources/colis.txt")); // Transfomer chaque ligne du fichier en une case de type String 
                String[][] result1 = new String[list1.size()][5]; // La matrice resutat de taille nbr de ligne du fichier * nbr de case 
                int j = 0;
                for (String s : list1) {
                    result1[j] = s.split(","); // Convertir Chaque ligne en un tableau de String en utilisant le délimiteur ","
                    j++;
                }
                return result1;

            case 1:// liste des coursiers : Nom,Prenom,CIN,Identifiant,Disponibilité,Capacité,Destination,ListedesColis
                List<String> list2 = Files.readAllLines(Paths.get("src/Resources/coursier.txt")); // Transfomer chaque ligne du fichier en une case de type String 
                String[][] result2 = new String[list2.size()][5]; // La matrice resutat de taille nbr de ligne du fichier * nbr de case 
                int e = 0;
                for (String s : list2) {
                    result2[e] = s.split(","); // Convertir Chaque ligne en un tableau de String en utilisant le délimiteur ","
                    e++;
                }
                return result2;

            case 3:// liste du caisse : Date,montant
                List<String> list3 = Files.readAllLines(Paths.get("src/Resources/caisse.txt")); // Transfomer chaque ligne du fichier en une case de type String 
                String[][] result3 = new String[list3.size()][2]; // La matrice resutat de taille nbr de ligne du fichier * nbr de case 
                int z = 0;
                for (String s : list3) {
                    result3[z] = s.split(","); // Convertir Chaque ligne en un tableau de String en utilisant le délimiteur ","
                    z++;
                }
                return result3;
            case 4:// liste du zoneGeo : prédécesseur,Ville
                List<String> list4 = Files.readAllLines(Paths.get("src/Resources/zoneGeo.txt")); // Transfomer chaque ligne du fichier en une case de type String 
                String[][] result4 = new String[list4.size()][2]; // La matrice resutat de taille nbr de ligne du fichier * nbr de case 
                int x = 0;
                for (String s : list4) {
                    result4[x] = s.split(","); // Convertir Chaque ligne en un tableau de String en utilisant le délimiteur ","
                    x++;
                }
                return result4;
            default:
                System.out.println("erreur");
                break;
        }
        return null;
    }

    // Elle transforme les matrices en fichiers 
    public void fromTabToFile(int type, String[][] result) throws IOException {

        String FileContent = "";// Construire une chaine à partir du matrice qui sépare les colonnes par "," et les lignes par "\n"
        for (String[] ch : result) {
            for (String s : ch) {
                FileContent = FileContent.concat(s + ",");
            }
            FileContent = FileContent.concat("\n");
        }

        switch (type) {

            case 0:// Modifier le contenu de fichier clien.txt
                FileWriter File0 = new FileWriter("src/Resources/client.txt");
                File0.write(FileContent);
                File0.close();
                break;

            case 1:// Modifier le contenu de fichier coursier.txt
                FileWriter File1 = new FileWriter("src/Resources/coursier.txt");
                File1.write(FileContent);
                File1.close();
                break;

            case 2:// Modifier le contenu de fichier colis.txt
                FileWriter File2 = new FileWriter("src/Resources/colis.txt");
                File2.write(FileContent);
                File2.close();
                break;

            case 3:// Modifier le contenu de fichier caisse.txt
                FileWriter File3 = new FileWriter("src/Resources/caisse.txt");
                File3.write(FileContent);
                File3.close();
                break;
            case 4:// Modifier le contenu de fichier zoneGeo.txt
                FileWriter File4 = new FileWriter("src/Resources/zoneGeo.txt");
                File4.write(FileContent);
                File4.close();
                break;

            default:
                System.out.println("erreur");
                break;
        }
    }

    // Elle donne la somme de la caisse entre 2 date
    public String calculCaisseFromDate1ToDate2(String t[][], String D1, String D2) {
        double s = 0;
        LocalDate date;
        LocalDate d1 = LocalDate.parse(D1); // recuperer la date1
        LocalDate d2 = LocalDate.parse(D2); // recuperer la date2
        for (String[] t1 : t) {//parcourir le fichier caisse en comparant les différent dates
            date = LocalDate.parse(t1[0]);
            if (date.isBefore(d2) && date.isAfter(d1)) {//si date est entre date1 et date2
                s += Double.parseDouble(t1[1]); // ajouter la valeur à la somme
            }
        }
        return s+" DT";
    }

    // Elle donne la somme totale de la caisse 
    public double calculCaisse(String t[][]) {
        double s = 0;
        for (String[] t1 : t) {
            s += Double.parseDouble(t1[1]);
        }
        return s;
    }
    
    // Elle transforme la matrice des zones en un tableau
    private String[] listerLesZoneGeo() throws IOException {
        String mat[][] = fromFileToTab(4);
        String[] tab = new String[mat.length];
        for (int i = 0; i < mat.length; i++) {
            tab[i] = mat[i][0];
        }
        return tab;
    }

    // Elle prend en parametre la destination ,et retourne un tableau de villes qui se trouvent dans le trajectoire
    public String[] getTrajet(String dest) throws IOException {
        String mat[][] = fromFileToTab(4);// lire le contenu de fichier zoneGeo.txt
        String[] tab = new String[mat.length];// preparer le tableau qui va contenir la liste des villes
        int i = 0, e = 0;
        String pos = dest;// initialiser la positon par la valeur de destination
        while (!pos.equals("Tunis")) {// tant que la position != de tunis
            if (pos.equals(mat[i][0])) { // rechercher la position dans le fichier
                tab[e] = pos; //ajouter la ville à notre tableau
                e++;
                pos = mat[i][1];// changer la valeur de la position par la valeur qui précede notre fichier 
            }
            i = (i + 1) % mat.length;
        }
        tab[e] = "Tunis";
        // inverser le tableau pour obtenir l'ordre correcte
        String t[];
        t = Arrays.copyOfRange(tab, 0, e + 1);
        for ( i = 0; i < t.length / 2; i++) {
            String temp = t[i];
            t[i] = t[t.length - 1 - i];
            t[t.length - 1 - i] = temp;
        }
        return t;
    }
    
    // cette méthode permet de preparer une matrice qui contient les information sur la caisse entre 2 dates
    public String[][] filterCaisse(String d1, String d2) {
        ArrayList<String[]> filtre_caisse = new ArrayList<String[]>(); //preparer notre matrice
        String[][] listcaisse = this.listeCaisse;// recuperer la matrice totale de la caisse
        LocalDate date1 = LocalDate.parse(d1); // recuperer la date1
        LocalDate date2 = LocalDate.parse(d2); // recuperer la date2
        for (String[] pay : listcaisse) { //parcourir le fichier caisse en comparant les différent dates
            LocalDate d = LocalDate.parse(pay[0]);
            if (d.isBefore(date2) && d.isAfter(date1)) { // si date entre date1 et date2
                filtre_caisse.add(pay); // ajouter à notre matrice
            }
        }
        // preparer la matrice qui va etre retourner par la fonction => cette matrice est plus organisée
        String[][] list = new String[filtre_caisse.size()][2];
        int i = 0;
        for (String[] cai : filtre_caisse) {
            list[i] = cai;
            i++;
        }
        return list;
    }

    // 0 = Client , 1 = Couriser , 2 = Colis , 3 = Caisse , 4 = zoneGeo
    // Constructeur
    public Societe() throws IOException {
        this.listeClient = fromFileToTab(0);
        this.listeCoursier = fromFileToTab(1);
        this.listeColis = fromFileToTab(2);
        this.listeCaisse = fromFileToTab(3);
        this.caisse = calculCaisse(listeCaisse);
        this.zoneGeographique = this.listerLesZoneGeo();
    }

}
