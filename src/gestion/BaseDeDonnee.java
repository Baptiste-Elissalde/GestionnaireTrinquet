package gestion;
import java.util.Scanner;

import dao.OcamlCaller;
import dao.GestionnaireCSV;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import agenda.Date; 
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BaseDeDonnee {
	private Client[] clients;
	private Ville[] villes;
    private Trinquet[] trinquets;
    private int nbClients;
    private int nbVilles;
    private int nbTrinquets;
    
    //Constructeur de la bdd avec le csv
    public BaseDeDonnee(GestionnaireCSV gestionnaire) {
    	this.clients = new Client[20];
        this.villes = new Ville[20];
        this.trinquets = new Trinquet[20];
        this.nbClients = 0;
        this.nbVilles = 0;
        this.nbTrinquets = 0;

        initialiserClients(gestionnaire);
        initialiserVilles(gestionnaire);
        initialiserTrinquets(gestionnaire);
        initialiserReservations(gestionnaire);
    }
    //Fonctions pour initialiser la bdd a partir des csv
    public void initialiserVilles(GestionnaireCSV gestionnaire) {
    	String [] data = supprimerDoublonsManuel(gestionnaire.obtenirVillesTrinquets());
        for (int i = 0; i < data.length && i < villes.length; i++) {
    		Ville ville = new Ville(data[i]);
    		ajouterVille(ville);
    	}
    }
    public void initialiserTrinquets(GestionnaireCSV gestionnaire) {
        int[] data_id = gestionnaire.obtenirIdTrinquets();
        String[] data_nom = gestionnaire.obtenirNomsTrinquets();
        String[] data_ville = gestionnaire.obtenirVillesTrinquets();

        for (int i = 0; i < data_id.length && i < trinquets.length; i++) {
            Trinquet trinquet = new Trinquet(data_id[i],data_nom[i],chercherVille(data_ville[i]));
            trinquet.setAdresse(gestionnaire.obtenirAdressesTrinquets()[i]);
            ajouterTrinquet(trinquet);
        }
    }
    public void initialiserClients(GestionnaireCSV gestionnaire) {
    	int [] data_id = gestionnaire.obtenirIdClient();
    	String[] data_nom = gestionnaire.obtenirNomClient();
    	String[] data_prenom = gestionnaire.obtenirPrenomClient();
    	for(int i = 0;i<data_id.length &&  i < clients.length;i++) {
    		Client client  = new Client(data_id[i], data_nom[i], data_prenom[i]);
    		ajouterClients(client);
    	}
    }
    public void initialiserReservations(GestionnaireCSV gestionnaire) {
        int[] idsClients = gestionnaire.obtenirIdClientReservation();
        if(idsClients.length == 1) {
        	System.out.println("pas de donnée"); 
        	return;
        }
        int[] idsTrinquets = gestionnaire.obtenirIdTrinquetReservation();
        String[] dates = gestionnaire.obtenirDatesReservation();
        String[] heuresDebut = gestionnaire.obtenirHeuresDebut();
        
        LocalDate aujourdHui = LocalDate.now();
        int j = aujourdHui.getDayOfMonth();
		int m = aujourdHui.getMonthValue();
		int a = aujourdHui.getYear();
		Date aujourdhui = new Date(a,m,j); // on cree la date d aujourdui pour comparer l ecart
        for(int i =0; i < idsClients.length;i++) { //on parcours les reservations
        	Trinquet trinquet = chercherTrinquet(idsTrinquets[i]);
        	Client client = chercherClient(idsClients[i]);
        	if (client != null && trinquet != null) {
                Date date = Date.creerDate(dates[i]);
                int ecart = calculerEcartEntre(aujourdhui, date); //en recuperant l ecart on peut reserver 
                client.reserverHoraire(trinquet, Integer.parseInt(heuresDebut[i]), ecart);
            } else {
                System.out.println("Avertissement : Client ou Trinquet introuvable pour la réservation index " + i);
            }
        }
        
    }
    private void ajouterClients(Client c) {
    	clients[nbClients++] = c;
    }
    private void ajouterVille(Ville v) {
        villes[nbVilles++] = v;
    }
    private void ajouterTrinquet(Trinquet t) {
        trinquets[nbTrinquets++] = t;
        t.getVille().ajouterTrinquet(t);
    }
    
    //Fonctions de saisi
    /**
	 * fait la saisi pour creer un client
	 * @param scanner pour travailler avec le terminal et saisir les données
	 * @return 
	 */
    public Client saisiClient(Scanner scanner) {
        System.out.print("Entrez votre nom : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez votre prénom : ");
        String prenom = scanner.nextLine(); // on recupere le nom et prenom
        
        for(int i = 0 ;i <nbClients;i++) {
        	Client client = clients[i];
        	if(client.getNom().equalsIgnoreCase(nom) && client.getPrenom().equalsIgnoreCase(prenom)) {
        		System.out.println("vous avez deja un compte le voici");
        		client.afficherReservation();
        		return client;  // si le client est deja present dans le fichier csv on renvoie lui
        	}
        }
        int id;
        if (nbClients == 0) { //gestion d erreur si il n y pas de clients dans le csv
        	id = 1; 
        } else {
        	id = clients[nbClients - 1].getId() + 1;
        }
        Client client = new Client(id,nom, prenom);
        System.out.println("\nBonjour " + client.NomPrenom());
        String chemin = "/mnt/c/Users/Baptiste Elissalde/Desktop/"
        		+ "Licence_math_info/S3/Ilu1/Baptiste_elissalde_projet_ilu1/"
        		+ "worskspace/GestionnaireTrinquet/crud/main.exe";
        OcamlCaller.appelerOCaml(chemin, "-C","clients.csv","--",String.valueOf(id),nom,prenom); //on sauvegarde le clients dans le csv 
        client.afficherIdentifiant();
        return client;
    }
    public Trinquet saisiTrinquet(Scanner scanner,Client client) {
        Trinquet trinquet = null;
        while (trinquet == null) {
        	System.out.print("Selectionner un trinquet(faite un copier colle pour ne pas vous trompez) : "); //tant que le nom donne n est pas valide
            String nomTrinquet = scanner.nextLine(); 
            trinquet = chercherTrinquet(nomTrinquet); 
        }
        return trinquet;
    }
    
    public void saisiReserver(Trinquet trinquet,Scanner scanner,Client client,GestionnaireCSV gestionnaire) {
        // on affche les nombres de disponibilités du trinquets avec leur jour
        System.out.println("\nJours disponibles :");
        trinquet.getPlanning().afficherPlaning();
        int jour = -1; 
        // tant que le jour n est pas valide
        while(jour < 0 || jour > trinquet.getPlanning().getTaille() - 1) {
        	System.out.print("Dans combien de jour voulez-vous reserver (min 0 max 13) : ");
        	if (scanner.hasNextInt()) { // Sécurité pour éviter un plantage si l'utilisateur tape du texte
        		jour = scanner.nextInt();
        		if (jour < 0 || jour > trinquet.getPlanning().getTaille() - 1) {
        			System.out.println("Saisie invalide, veuillez recommencer.");
        		}
        	} 
        	else {
        		System.out.println("Veuillez entrer un nombre entier.");
        		scanner.next(); // Consomme la mauvaise saisie
        	}
        }
        trinquet.afficherJour(jour);
        System.out.println("\n");
        // Horaire
        System.out.print("Choisissez une heure (ex: 8 pour 8h-9h) : ");
        int heure = 0;
        // tant que l heure n est pas valide
        while(heure < 8 || heure > 22) {
        	System.out.print("Choisissez une heure (entre 8 et 22) : ");
        	heure = scanner.nextInt();
        	scanner.nextLine();
         
        	if (heure < 8 || heure > 22) {
        		System.out.println("Heure invalide ! Veuillez recommencer.");
        	}
        }
        
        int retour = client.reserverHoraire(trinquet, heure, jour);
        if(retour == 0) { //gestion d erreur si la reservation existe deja
        	String chemin = "/mnt/c/Users/Baptiste Elissalde/Desktop/"
            		+ "Licence_math_info/S3/Ilu1/Baptiste_elissalde_projet_ilu1/"
            		+ "worskspace/GestionnaireTrinquet/crud/main.exe";
        	int id_reservation;
        	int[] idsExistants = gestionnaire.obtenirIdReservation();
        	if (idsExistants == null || idsExistants.length == 0) {
        	    id_reservation = 1; // gestiond erreur si le csv est vide
        	} else {
        	    id_reservation = idsExistants[idsExistants.length - 1] + 1;
        	}
            int id_client = client.getId();
            int id_trinquet = trinquet.getId();
            String dateStr = trinquet.getPlanning().getJour(jour).getDate().afficherDate();
            String heureDebut = String.valueOf(heure);

            //on sauvegarde la reservation dans le csv
            OcamlCaller.appelerOCaml(chemin, "-C", "reservations.csv", "--",
                    String.valueOf(id_reservation),
                    String.valueOf(id_client),
                    String.valueOf(id_trinquet),
                    dateStr,
                    heureDebut);
            System.out.println("=== RESERVATION CONFIRMEE ===");
        }
	}
    
    //Fonctions pour aider a la saisi
    public static int calculerEcartEntre(Date d1, Date d2) {
        // 1. On détermine laquelle est la plus petite pour commencer la boucle
        
        int compteur = 0;
        
        // 2. On avance jour par jour avec ta méthode dateSuivant()
        while (d1.getJour() != d2.getJour() || 
               d1.getMois() != d2.getMois() || 
               d1.getAnnee() != d2.getAnnee()) {
            d1 = d1.dateSuivant(); 
            compteur++;
        }
        
        return compteur;
    }
    public Trinquet chercherTrinquet(int id) {
    	for (int i = 0; i < nbTrinquets; i++) {
            if (trinquets[i].getId()==id) {
                return trinquets[i];
            }
        }
        return null;
    }
    private Client chercherClient(int id) {
        for (int i = 0; i < nbClients; i++) {
            if (clients[i].getId() == id) {
                return clients[i];
            }
        }
        return null;
    }   
    public String[] supprimerDoublonsManuel(String[] tableau) {
        if (tableau.length == 0) return tableau;

        // On utilise une liste temporaire car on ne connaît pas la taille finale
        java.util.ArrayList<String> listeUnique = new java.util.ArrayList<>();

        for (String s : tableau) {
            String ville = s.trim();
            // Si la liste ne contient pas encore cette ville, on l'ajoute
            if (!listeUnique.contains(ville)) {
                listeUnique.add(ville);
            }
        }

        return listeUnique.toArray(new String[0]);
    }

    
    //Fonctions getter
    public Ville[] getVilles() {
        return villes;
    }

    public Trinquet[] getTrinquets() {
        return trinquets;
    }
    public Ville chercherVille(String nom) {
        for (int i = 0; i < nbVilles; i++) {
            if (villes[i].getNom().equalsIgnoreCase(nom)) {
                return villes[i];
            }
        }
        return null;
    }
    
    
    //Fonctions d affichage
    public void afficherToutesLesVilles() {
        for (int i = 0; i < nbVilles; i++) {
            System.out.println(villes[i].getNom());
        }
    }
    
    public void afficherVillesTries() {
    	StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nbVilles; i++) {
            sb.append(villes[i].getNom());
            if (i < nbVilles - 1) sb.append(";");
        }

        // 2. Appeler OCaml
        String chemin = "/mnt/c/Users/Baptiste Elissalde/Desktop/"
        		+ "Licence_math_info/S3/Ilu1/Baptiste_elissalde_projet_ilu1/"
        		+ "worskspace/GestionnaireTrinquet/crud/trierVille.exe";
        String resultat = dao.OcamlCaller.appelerOCaml(chemin, sb.toString());

        // 3. Traiter le résultat
        if (resultat != null) {
            String[] nomsTries = resultat.split(";");
            System.out.println("=== Liste des villes ===");
            for (String nom : nomsTries) {
                System.out.println("- " + nom);
            }
        }
        else {
            System.out.println("DEBUG : Le résultat OCaml est NULL !");
        }
    }
    public void afficherTrinquetsTries() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nbTrinquets; i++) {
            sb.append(trinquets[i].getNom());
            if (i < nbTrinquets - 1) sb.append(";");
        }
        
        // 2. Appeler OCaml
        String chemin = "/mnt/c/Users/Baptiste Elissalde/Desktop/"
        		+ "Licence_math_info/S3/Ilu1/Baptiste_elissalde_projet_ilu1/"
        		+ "worskspace/GestionnaireTrinquet/crud/trierVille.exe";
        String resultat = dao.OcamlCaller.appelerOCaml(chemin, sb.toString());

        // 3. Traiter le résultat
        if (resultat != null) {
            String[] nomsTries = resultat.split(";");
            System.out.println("=== Liste des trinquets ===");
            for (String nom : nomsTries) {
                System.out.println("- " + nom);
                System.out.println(chercherTrinquet(nom).getVille().getNom());
            }
        }
        else {
            System.out.println("DEBUG : Le résultat OCaml est NULL !");
        }
    }
    
    public Trinquet chercherTrinquet(String nom) {
        for (int i = 0; i < nbTrinquets; i++) {
            if (trinquets[i].getNom().equalsIgnoreCase(nom)) {
                return trinquets[i];
            }
        }
        return null;
    }
    public void afficherTrinquets_csv(GestionnaireCSV gestionnaire) {
        // 1. Récupération des données brutes depuis le gestionnaire
        int[] ids = gestionnaire.obtenirIdTrinquets();
        String[] noms = gestionnaire.obtenirNomsTrinquets();
        String[] villes = gestionnaire.obtenirVillesTrinquets();
        String[] adresses = gestionnaire.obtenirAdressesTrinquets();

        // 2. En-tête du CSV
        System.out.println("id;nom;ville;adresse");

        // 3. Parcours des tableaux (on utilise la taille de l'un des tableaux, ex: ids.length)
        for (int i = 0; i < ids.length; i++) {
            System.out.println(
                ids[i] + ";" + 
                noms[i] + ";" + 
                villes[i] + ";" + 
                adresses[i]
            );
        }
    }    
    public void afficherClients_csv(GestionnaireCSV gestionnaire) {
        // 1. Récupérer les données brutes (tableau de tableaux)
        // On suppose que le gestionnaire a accès aux colonnes ID, NOM, PRENOM
        int[] ids = gestionnaire.obtenirIdClient();
        String[] noms = gestionnaire.obtenirNomClient();
        String[] prenoms = gestionnaire.obtenirPrenomClient();

        System.out.println("id;nom;prenom"); // En-tête

        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i] + ";" + noms[i] + ";" + prenoms[i]);
        }
    }
    public void afficherReservations_csv(GestionnaireCSV gestionnaire) {
        // 1. Récupérer les colonnes du fichier reservations.csv via le gestionnaire
        int[] idsClients = gestionnaire.obtenirIdClientReservation();
        int[] idsTrinquets = gestionnaire.obtenirIdTrinquetReservation();
        String[] dates = gestionnaire.obtenirDatesReservation();
        String[] heures = gestionnaire.obtenirHeuresDebut();

        System.out.println("id_client;id_trinquet;date;heure"); // En-tête

        for (int i = 0; i < idsClients.length; i++) {
            // On affiche chaque ligne du tableau de tableau simulé par les colonnes
            System.out.println(
                idsClients[i] + ";" + 
                idsTrinquets[i] + ";" + 
                dates[i] + ";" + 
                heures[i]
            );
        }
    }
    
    
}
