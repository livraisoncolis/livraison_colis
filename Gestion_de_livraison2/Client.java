package Gestion_de_livraison2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;

public class Client {

	private long id;
	private String username;
	private String password;
	private String listcolis;
	private int pts;

	// constructor
	Client(String username, String password) {
		this.id = new Date().getTime();
		this.username = username;
		this.password = password;
		this.listcolis = "";
		this.pts = 0;
	}

	Client(long id, String username, String password, int pts, String listcolis) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.listcolis = listcolis;
		this.pts = pts;
	}

	// getter & setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getListcolis() {
		return listcolis;
	}

	public void setListcolis(String listcolis) {
		this.listcolis = listcolis;
	}

	public int getPts() {
		return pts;
	}

	public void setPts(int pts) {
		this.pts = pts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	// methods
	// cette methode crypte le mot de passe de chaque client
	public static String securemdp(String password) {
		MessageDigest md5;
		String myHash = "";
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes());
			byte[] digest = md5.digest();
			myHash = DatatypeConverter.printHexBinary(digest);
			// System.out.println(myHash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return myHash;

	}

	// ajouter un cliet dans le fichier client.txt a travers les champs
	// d'inscriptions
	public static void register(String username, String password) {
		String client = new Date().getTime() + "," + username + "," + password + ",0,0,\n"; // préparer la forme de la
																							// chaine à ajouter dans le
																							// fichier
		try {
			FileWriter myWriter = new FileWriter("src/Resources/client.txt", true); // creation d'un objet FileWriter
																					// permettant l'écriture dans le
																					// fichier
			myWriter.append(client); // ajouter la chaine à la fin du fichier
			myWriter.close(); // fermer le fichier
			// System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	// entrer dans la session de chaque client si le mot de passe et le nom sont
	// correctes

	public static Client login(String username, String password) {
		File file = new File("src/Resources/client.txt");
		try {
			@SuppressWarnings("resource")
			Scanner reader = new Scanner(file); // creation d'objet Scanner pertmttant la lecture à partir de fichier
			while (reader.hasNextLine()) { // lire un ligne de fichier => lire un client
				String data = reader.nextLine();
				String[] tab = data.split(",");// trasnformer la chaine en un tableau
				if (tab[1].equals(username) && tab[2].equals(Client.securemdp(password))) { // vérifier le nom et le
																							// mot de passe
					return (new Client(Long.parseLong(tab[0]), tab[1], tab[2], Integer.parseInt(tab[3]), tab[4]));// si
																													// client
																													// existant
																													// returner
																													// un
																													// objet
																													// Client
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;// si Client non trouvé retourner null
	}

	// attacher l'id de colis au client correspondant et payer ansi que l'ajout des
	// points pour chaque payement
	public void payer(Colis colis) throws IOException {
		Societe s = new Societe();
		String[][] listclient = s.getListeClient();
		for (String[] col : listclient) {

			if (Long.parseLong(col[0]) == getId()) {
				if (col[4].equals("0")) { // tester si le client a deja une colis
					col[4] = Long.toString(colis.getIdentifiant()); // s'il n'y a pas : l'ecrase de 0 et l'ajout de l'id
																	// du 1er colis
				} else { // s'il a deja des colis
					col[4] = col[4] + ":" + Long.toString(colis.getIdentifiant()); // ajouter un colis
				}
				int update_pts = (int) (this.pts + colis.calculPrix(s) * 2); // calculer le nouveau nombre de point de
																				// fidélité
				setPts(update_pts); // mettre à jour les ponits de fidélité
				col[3] = Integer.toString(getPts());
				break;
			}
		}
		// enregitrer les modifications dans les fichiers
		s.fromTabToFile(0, listclient);
		setListcolis(Long.toString(colis.getIdentifiant()));
		colis.update();

	}

	// payer avec les points de fidélité
	public void UtiliserLesPoints(Colis colis) throws IOException {
		Societe s = new Societe();
		String[][] listclient = s.getListeClient();
		for (String[] col : listclient) {

			if (Long.parseLong(col[0]) == getId()) {
				if (col[4].equals("0")) { // tester si le client a deja une colis
					col[4] = Long.toString(colis.getIdentifiant()); // s'il n'y a pas : l'ecrase de 0 et l'ajout de l'id
																	// du 1er colis
				} else { // s'il a deja des colis
					col[4] = col[4] + ":" + Long.toString(colis.getIdentifiant()); // ajout du colis
				}
				int update_pts = (int) (colis.calculPrix(s) * 100); // calculer le nombre de point de fidélité
																	// necessaire pour lr payement
				setPts(getPts() - update_pts); // mettre à jours les points de fidélité;
				col[3] = Integer.toString(getPts());
				break;
			}
		}
		// enregitrer les modifications dans les fichiers
		s.fromTabToFile(0, listclient);
		setListcolis(Long.toString(colis.getIdentifiant()));
		colis.update();

	}

	// elle va retourner une matrice bien détailler de tous les colis du client
	public String[][] getColisMatrice() throws IOException {
		Societe s = new Societe();
		String listCol[] = null;// preparer un tableau pour contenir les id des colis relative à ce client
		for (String[] client : s.getListeClient()) { // rechercher le client via id
			if (Long.parseLong(client[0]) == this.id) {// si existe
				listCol = client[4].split(":"); // on met dans le tableau listCol la liste des colis de ce client
			}
		}
		int j = 0;
		String mat[][] = new String[listCol.length][8]; // preparer une matrice pour contenir les informations relative
														// aux colis de ce client
		for (String[] col : s.getListeColis()) { // rechercher les colis de client dans la liste totale des colis via
													// l'id
			for (String listCol1 : listCol) {
				if (listCol1.equals(col[0])) {// si les id des colis sont équivalent
					mat[j] = col; // on recupere le coliset l'ajout à la matrice
					j++;
					break;
				}
			}
		}
		return mat;
	}

	// mise a jour du temps du colis et son etat
	public long updateColisTime(String[] colis) throws IOException {
		Societe s = new Societe();
		LocalTime arrialTime = LocalTime.parse(colis[6], DateTimeFormatter.ISO_LOCAL_TIME); // recuperer la date
																							// d'arrivé de colis
		LocalTime departTime = LocalTime.parse(colis[8], DateTimeFormatter.ISO_LOCAL_TIME); // recuperer la date de
																							// départ de colis
		LocalTime currentTime = LocalTime.now(); // recuperer le temps actuel
		if (currentTime.isAfter(departTime)) { // pour assurer que le calcul se fait apres le depart du colis avec le
												// livreur
			long newTime = currentTime.until(arrialTime, ChronoUnit.MINUTES); // calculer le temps restant jusqu'à
																				// arriver
			if (newTime < 0) { // si ce temps est négatif => colis delivré
				String[][] listcolis = s.getListeColis(); // recuperer la matrice des colis
				String[][] listcoursier = s.getListeCoursier(); // recuperer la matrice des coursiers
				for (String[] col : listcolis) { // rechercher le colis dans la liste des colis via id
					if (col[0].equals(colis[0])) {// si existe
						col[7] = "Delivree";// changer son etat en "Delivré"
						break;
					}
				}
				for (String[] col : listcoursier) { // rechercher le colis dans la liste des coursiers via id
					if (col[4].contains(colis[0])) { // si existe
						col[4] = "0";// mettre a zero la liste des colis de livreur
						col[5] = "no";// mettre a zero la destination de livreur
						col[3] = "true"; // rendre le livreur disponible
					}
				}
				// enregister les modifications dans les fichiers
				s.fromTabToFile(2, listcolis);
				s.fromTabToFile(1, listcoursier);
			}
			return newTime;
		}
		return 0;
	}

//	fomatter le temps en min
	public String formatTime(String[] colis) throws IOException {
		long time = this.updateColisTime(colis);
		long hour = time / 60;
		long minute = time - hour * 60;
		if (hour == 0 && minute == 0) {
			return "En cours de traitement"; // si ce n'est pas encore l'heure de depart
		} else if (hour >= 0 && minute > 0) {
			return hour + "h:" + minute + "min"; // s'il est en cours de livraison
		} else {
			return "Delivree"; // s'il est livrer
		}
	}

	// cette methode met à jour la position du colis
	public String updateColisPosition(String[] colis) throws IOException {
		Societe s = new Societe();
		String[] trajet = s.getTrajet(colis[2]);
		LocalTime arrialTime = LocalTime.parse(colis[6], DateTimeFormatter.ISO_LOCAL_TIME); // recuperer la date
																							// d'arrivé de colis
		LocalTime departTime = LocalTime.parse(colis[8], DateTimeFormatter.ISO_LOCAL_TIME);// recuperer la date de
																							// depart de colis
		LocalTime currentTime = LocalTime.now(); // recuperer le temps actuel
		if (currentTime.isAfter(departTime)) { // pour assurer que le calcul se fait apres le depart du colis avec le
												// livruer
			long newTime = currentTime.until(arrialTime, ChronoUnit.MINUTES); // calculer le temps restant jusqu'à
																				// arriver
			int position = 0;
			// mettre à jour la position la duré restante
			if (newTime > 600) {
				position = 10;
			} else if (newTime > 497) {
				position = 9;
			} else if (newTime > 423) {
				position = 8;
			} else if (newTime > 372) {
				position = 7;
			} else if (newTime > 327) {
				position = 6;
			} else if (newTime > 218) {
				position = 5;
			} else if (newTime > 165) {
				position = 4;
			} else if (newTime > 90) {
				position = 3;
			} else if (newTime > 0) {
				position = 2;
			} else {
				position = 1;
			}
			// enregistrer les mofication dans la matrice
			String[][] listcolis = s.getListeColis();
			for (String[] col : listcolis) {
				if (col[0].equals(colis[0])) {
					col[3] = trajet[trajet.length - position];
					break;
				}
			}
			// enregistrer les modifications dans le fichier
			s.fromTabToFile(2, listcolis);
			return trajet[trajet.length - position];
		}
		return "Tunis";

	}

	public void deleteColis(long id) {
		File file = new File("src/Resources/colis.txt");   // ouvrir le fichier colis.txt
		String nouvelleListe = "";   // initialiser le nouveau contenu du fichier colis.txt
		try {
			@SuppressWarnings("resource")
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {    // parcourir les lignes du fichier 
				String data = reader.nextLine();
				String[] tab = data.split(",");  // rendre la ligne un tableau 
				if (Long.parseLong(tab[0]) == id) {   // si oon trouve l'id du colis on pass
					System.out.println("fas5ou");
				} else { // sinon on l'ajoute dans la nouveau contenu 
					nouvelleListe += data + "\n";
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			FileWriter myWriter = new FileWriter("src/Resources/colis.txt", false);
			myWriter.write(nouvelleListe);   // ecraser l'ancien contenue par le nouveau
			myWriter.close();
		} catch (IOException ex) {
			Logger.getLogger(Gerant.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void deleteColisFromClient(Societe s, long id) {
		String[][] listClients = s.getListeClient();
		for (String[] client : listClients) {
			String[] listColis = client[4].split(":");
			int i = 0;
			for (String colis : listColis) {
				if (colis.equals(Long.toString(id))) { // si on trouve l'id du colis dans le client
					if (i != 0) {  // s'il ya plusieur id
						client[4] = client[4].replace(":" + Long.toString(id), "");  // on supprime l'id du colis de la liste 
						break;
					} else {   
						if (listColis.length == 1) { // s'il ya un seul id dans la liste
							client[4] = "0";
						} else    // on supprime le premier id de laliste
							client[4] = client[4].replace(Long.toString(id) + ":", "");
						break;
					}
				} else {
					i++;
				}
			}
		}
		try {
			s.fromTabToFile(0, listClients);   // mise a jour du fichier client.txt
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteColisFromCoursier(Societe s,long id) {
		String[][] listCoursier = s.getListeCoursier(); // liste 
		for (String[] coursier : listCoursier) {
			String[] listColis = coursier[4].split(":");
			int i = 0;
			for (String colis : listColis) {
				if (colis.equals(Long.toString(id))) {   // si on trouve l'id du colis dans le coursier
					if (i != 0) {  // si on est dans le 2eme colis ou plus
						coursier[4] = coursier[4].replace(":" + Long.toString(id), "");   // supprimer l'id de la liste 
						break;
					} else {  // si on est dans le 1er colis
						if (listColis.length == 1) {  // s'il est le seul
							coursier[5] = "no";   // remettre le coursier a son etat initial
							coursier[4] = "0";
						} else
							coursier[4] = coursier[4].replace(Long.toString(id) + ":", ""); // sinon supprimer le 1er colis de la liste
						break;
					}
				} else {
					i++;   // incrementer le compteur
				}
			}

		}
		try {
			s.fromTabToFile(1, listCoursier);   // mise a jour de fichier cousier.txt
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean annulerColis(Colis colis, long id) throws IOException {
		Societe s = new Societe();     // instance du societe
		String[][] listcolis = s.getListeColis();  // matrice des colis 
		String time = "";  
		for (String[] col : listcolis) {
			if (col[0].equals(Long.toString(id))) {
				time = col[8];    // recupere le temps de depart du colis
				break;
			}
		}
		LocalTime departTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
		if (LocalTime.now().isBefore(departTime)) {
			this.deleteColis(id);
			this.deleteColisFromClient(s, id);
			this.deleteColisFromCoursier(s, id);
			return true;
		} else {
			return false;
		}
	}
}
