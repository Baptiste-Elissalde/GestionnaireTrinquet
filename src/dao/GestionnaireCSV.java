package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireCSV {
	private String[][] clients_csv;
	private String[][] trinquets_csv;
	private String[][] reservations_csv;
	
	public GestionnaireCSV() {
		this.clients_csv = lireFichiersCsv("clients.csv");
		this.trinquets_csv = lireFichiersCsv("trinquets.csv");
		this.reservations_csv  = lireFichiersCsv("reservations.csv");
		
	}
	public String[][] getClients_csv() {
		return clients_csv;
	}
	public String[][] getTrinquets_csv() {
		return trinquets_csv;
	}
	public String[][] getReservations_csv() {
		return reservations_csv;
	}
	
	/**
	 * lis le fichier et renvoie un tableau de tableau de strings 
	 * @param fichier que l on soihaite lire
	 * @return renvoie le fichiers csv sous forme d un tableau de tableau de string
	 */
	public String[][] lireFichiersCsv(String fichier) {
	    String chemin = "/mnt/c/Users/Baptiste Elissalde/Desktop/Licence_math_info/S3/Ilu1/Baptiste_elissalde_projet_ilu1/worskspace/GestionnaireTrinquet/crud/main.exe";
	    String resultatBrut = OcamlCaller.appelerOCaml(chemin, "-R", fichier);

	    if (resultatBrut == null || resultatBrut.trim().isEmpty()) {
	        return new String[0][0];
	    }

	    // On découpe en lignes et on enlève les lignes vides éventuelles à la fin
	    String[] lignes = resultatBrut.split("\n");
	    
	    // Si on a 15 lignes (1 header + 14 trinquets), le tableau doit faire 14 de taille
	    int nbTrinquets = lignes.length - 1;
	    if (nbTrinquets < 0) return new String[0][0];

	    String[][] tableau = new String[nbTrinquets][];

	    for (int i = 0; i < nbTrinquets; i++) {
	        // i + 1 car on veut commencer à lire la ligne 1 (le premier trinquet)
	        // et on remplit la case i du tableau (la case 0)
	        String ligneCourante = lignes[i + 1].trim();
	        if (!ligneCourante.isEmpty()) {
	            tableau[i] = ligneCourante.split(";");
	        }
	    }

	    return tableau;
	}
	
	public int[] obtenirIdTrinquets() {
	    // 1. On récupère toutes les données
	    String[][] donnees = trinquets_csv;
	    
	    // 2. On prépare un tableau de int de la même taille
	    int[] ids = new int[donnees.length];
	    
	    try {
	        for (int i = 0; i < donnees.length; i++) {
	            // La colonne 0 contient l'ID (ex: "1", "2"...)
	            // On le transforme en int
	            ids[i] = Integer.parseInt(donnees[i][0].trim());
	        }
	    } catch (NumberFormatException e) {
	        System.err.println("Erreur de conversion d'un ID : " + e.getMessage());
	        return new int[0];
	    }
	    
	    return ids;
	}
	
	// Récupère uniquement les noms (Colonne 1)
	public String[] obtenirNomsTrinquets() {
		String[][] donnees = trinquets_csv;
	    String[] noms = new String[donnees.length];
	    for (int i = 0; i < donnees.length; i++) {
	        noms[i] = donnees[i][1];
	    }
	    return noms;
	}

	// Récupère uniquement les villes (Colonne 2)
	public String[] obtenirVillesTrinquets() {
		String[][] donnees = trinquets_csv;
	    String[] villes = new String[donnees.length];
	    for (int i = 0; i < donnees.length; i++) {
	        villes[i] = donnees[i][2];
	    }
	    return villes;
	}

	// Récupère uniquement les adresses (Colonne 3)
	public String[] obtenirAdressesTrinquets() {
		String[][] donnees = trinquets_csv;
	    String[] adresses = new String[donnees.length];
	    for (int i = 0; i < donnees.length; i++) {
	        // Sécurité au cas où une ligne n'aurait pas d'adresse
	        adresses[i] = (donnees[i].length > 3) ? donnees[i][3] : "Non renseignée";
	    }
	    return adresses;
	}
	
	public int[] obtenirIdClient() {
	    // 1. On récupère toutes les données
	    String[][] donnees = clients_csv;
	    
	    // 2. On prépare un tableau de int de la même taille
	    int[] ids = new int[donnees.length];
	    
	    try {
	        for (int i = 0; i < donnees.length; i++) {
	            // La colonne 0 contient l'ID (ex: "1", "2"...)
	            // On le transforme en int
	            ids[i] = Integer.parseInt(donnees[i][0].trim());
	        }
	    } catch (NumberFormatException e) {
	        System.err.println("Erreur de conversion d'un ID : " + e.getMessage());
	        return new int[0];
	    }
	    
	    return ids;
	}
	
	// Récupère uniquement les nom (Colonne 2)
	public String[] obtenirNomClient() {
		String[][] donnees = clients_csv;
		String[] nom = new String[donnees.length];
	    for (int i = 0; i < donnees.length; i++) {
	        nom[i] = donnees[i][1];
	    }
	    return nom;
	}
	// recupere les prenom
	public String[] obtenirPrenomClient() {
		String[][] donnees = clients_csv;
		String[] prenom = new String[donnees.length];
	    for (int i = 0; i < donnees.length; i++) {
	        prenom[i] = donnees[i][2];
	    }
	    return prenom;
	}
	
	// Récupère les IDs de réservation (Colonne 0)
	public int[] obtenirIdReservation() {
	    String[][] donnees = reservations_csv;
	    int[] ids = new int[donnees.length];
	    for (int i = 0; i < donnees.length; i++) {
	        ids[i] = Integer.parseInt(donnees[i][0].trim());
	    }
	    return ids;
	}

	// Récupère les IDs clients liés aux réservations (Colonne 1)
	public int[] obtenirIdClientReservation() {
	    String[][] donnees = reservations_csv;
	    int[] ids = new int[donnees.length];
	    for (int i = 0; i < donnees.length; i++) {
	        ids[i] = Integer.parseInt(donnees[i][1].trim());
	    }
	    return ids;
	}

	// Récupère les IDs trinquets liés aux réservations (Colonne 2)
	public int[] obtenirIdTrinquetReservation() {
	    String[][] donnees = reservations_csv;
	    int[] ids = new int[donnees.length];
	    for (int i = 0; i < donnees.length; i++) {
	        ids[i] = Integer.parseInt(donnees[i][2].trim());
	    }
	    return ids;
	}

	// Récupère les dates (Colonne 3)
	public String[] obtenirDatesReservation() {
	    String[][] donnees = reservations_csv;
	    String[] dates = new String[donnees.length];
	    for (int i = 0; i < donnees.length; i++) {
	        dates[i] = donnees[i][3];
	    }
	    return dates;
	}

	// Récupère les heures de début (Colonne 4)
	public String[] obtenirHeuresDebut() {
	    String[][] donnees = reservations_csv;
	    String[] heures = new String[donnees.length];
	    for (int i = 0; i < donnees.length; i++) {
	        heures[i] = donnees[i][4];
	    }
	    return heures;
	}
	
	
}
