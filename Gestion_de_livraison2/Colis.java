package Gestion_de_livraison2;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Ala
 *
 */
public class Colis {

    private long identifiant;
    private float poids;
    private String destination;
    private String position;
    private LocalDate dateDepart;
    private LocalTime timeDepart;
    private LocalTime timeArrive;
    private String duree;
    private String status;

    // Les Getters et Setters
    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }


    public long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(long identifiant) {
        this.identifiant = identifiant;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalTime getTimeArrive() {
        return timeArrive;
    }

    public void setTimeArrive(LocalTime timeArrive) {
        this.timeArrive = timeArrive;
    }

    public LocalTime getTimeDepart() {
        return timeDepart;
    }

    public void setTimeDepart(LocalTime timeDepart) {
        this.timeDepart = timeDepart;
    }

    // Les fonctions :
    // Constructeur
    Colis() {
        this.identifiant = new Date().getTime();
        this.position = "tunis";
        this.dateDepart = LocalDateTime.now().toLocalDate();
        this.status = "En cours";
        this.timeDepart = LocalTime.now();
    }

    // Elle calcule les frais du transport
    public double calculPrix(Societe S) throws IOException {
        String trajet[] = S.getTrajet(this.destination);
        double prix;

        //on va prendre en consideration le trajet dans le prix : il y a un prix pour chaque trajet et il y a un prix pour chaque ville du tajet
        prix = (double) (1.2 * (trajet.length / 2) + 0.8 * (trajet.length));

        //On va prendre le poids en consideration dans le prix : chaque gramme on paie 10 frank
        prix = (double) (prix + this.poids * 0.01);

        return prix;
    }

    // Elle calcule la durée Total du transport (du Tunis vers destination)
    public String calculDureeTotal(Societe S) throws IOException {

        //chercher le niveau (position) de la destination
        String trajet[] = S.getTrajet(this.destination);
        int ville = trajet.length;

        //la durée pour chaque niveau
        int heure, minute;
        switch (ville) {
            case 1:
                heure = 0;
                minute = 0;
                break;
            case 2:
                heure = 1;
                minute = 30;
                break;
            case 3:
                heure = 2;
                minute = 45;
                break;
            case 4:
                heure = 3;
                minute = 38;
                break;
            case 5:
                heure = 5;
                minute = 27;
                break;
            case 6:
                heure = 6;
                minute = 12;
                break;
            case 7:
                heure = 7;
                minute = 03;
                break;
            case 8:
                heure = 8;
                minute = 17;
                break;
            case 9:
                heure = 10;
                minute = 0;
                break;
            default:
                heure = 12;
                minute = 0;
        }
        String time = heure + "h:" + minute + "min";
        setDuree(time);
        return time;
    }

    // ajouter le colis dans le fichier colis.txt
    public void update() {
        // preparer la format du colis qui va etre ajouté au fichier colis.txt
        String newColis = this.identifiant + "," + this.poids + "," + this.destination + "," + this.position + "," + this.dateDepart + "," + this.duree + "," + this.timeArrive + "," + this.status + "," + this.timeDepart + "\n";
        try {
            FileWriter myWriter = new FileWriter("src/Resources/colis.txt", true);// creation d'un objet FileWriter permettant l'écriture dans le fichier
            myWriter.append(newColis);// Ajouter le colis à la fin de fichier
            myWriter.close();// fermer le fichier
        } catch (IOException e) {
            System.out.println("erreur");
        }
    }

    // preciser le temps de depart :
    // attacher la date de depart correspondante aux dates des livreur pour chaque colis 
    public boolean preciseDepartureTime() {     
        for (LocalTime time : Livreur.getTempsDepart()) {
            if (LocalTime.now().isBefore(time)) {
                setTimeDepart(time);
                return true;
            }
        }
        return false;
    }

    // calculer le temps d'arrivé de la colis
    public void calculArrivalTime() throws IOException {
        String[] time = this.duree.split(":"); 
        int heure = Integer.parseInt(time[0].replace("h", ""));
        int minute = Integer.parseInt(time[1].replace("min", ""));
        LocalTime currentTime = getTimeDepart();
        LocalTime tempsarrive = currentTime.plusMinutes(minute);
        tempsarrive = tempsarrive.plusHours(heure);
        setTimeArrive(tempsarrive);
    }

    // ajouter le payement du colis dans le fichier caisse.txt
    public void updateCaisse() throws IOException {
        Societe s = new Societe();
        double colisPrice = this.calculPrix(s);// calculer les frais de transport
        try {
            FileWriter myWriter = new FileWriter("src/Resources/caisse.txt", true);// creation d'un objet FileWriter permettant l'écriture dans le fichier
            myWriter.append(LocalDate.now() + "," + colisPrice + "\n"); // ajouter la valeur à la fin du fichier
            myWriter.close();// fermer le fichier
        } catch (IOException e) {
            System.out.println("erreur");
        }
    }
    
    
}
