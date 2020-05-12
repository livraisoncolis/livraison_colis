package Gestion_de_livraison2;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;

public class Livreur {
	
	private long id;
	private boolean disponibilite;
	private static int capacite = 3;
	private String[] listColis ;
	private String destination ;
	private static LocalTime [] tempsDepart = {
			LocalTime.of(8,0),
			LocalTime.of(13,45),
			LocalTime.of(23,00),
	};

	
	// constructeur
	Livreur(){
		this.id = new Date().getTime();
		this.disponibilite = true;
	}
	
	//getters & setters
	
	public long getId() {
		return id;
	}
	public static LocalTime[] getTempsDepart() {
		return tempsDepart;
	}

	public static void setTempsDepart(LocalTime[] tempsDepart) {
		Livreur.tempsDepart = tempsDepart;
	}

	public void setId(long id) {
		this.id = id;
	}
	public boolean isDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

	public static int getCapacite() {
		return capacite;
	}

	public static void setCapacite(int capacite) {
		Livreur.capacite = capacite;
	}

	public String[] getListColis() {
		return listColis;
	}

	public void setListColis(String[] listColis) {
		this.listColis = listColis;
	}
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	// methodes 
	
	

	public static boolean checkAvailability(Colis colis) throws IOException {
		Societe s = new Societe();
		boolean check = false; // variable booleene pour tester si le livreur est disponible ou non
		String[][] listCoursier = s.getListeCoursier(); // la liste des coursiers
		for(String [] cours:listCoursier) {   // iterer chaque coursier
			if(cours[3].equals("true")) { // tester si le coursier est disponible
				if(cours[4].equals("0")) {    // tester si le coursier a deja une colis
					cours[4] = Long.toString(colis.getIdentifiant());  // s'il n'y a pas : l'ecrase de 0 et l'ajout de l'id du 1er colis  
					cours[5] = colis.getDestination(); // ajout de la destination du 1er colis a la destination du coursier 
					check = true;   
					break;
				}
				else if(cours[5].equals(colis.getDestination())) {  // s'il y a deja des colis et cette colis a la meme destination que la colis precedente
					cours[4] = cours[4]+ ":" + Long.toString(colis.getIdentifiant());  // ajout du colis
					check = true;
					if(cours[4].split(":").length == Livreur.getCapacite()) { // si le livreur est satur�
						cours[3] = "false"; // devient indisponible
					}
					break;
				}
				
				           // si ce coursier n'est pas valable on commence le 2eme check du coursier suivant
			}
		}
		s.fromTabToFile(1, listCoursier); // mise à jour du coursier dans le fichier
		return check;
	}
}

