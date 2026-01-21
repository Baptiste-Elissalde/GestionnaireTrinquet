package dao;

public class TestDAO {
	public static void main(String[] args) {
	    System.out.println("Démarrage du test du tableau...");
	    
	    GestionnaireCSV gestionnaire = new GestionnaireCSV();
	    
	    // Appel de la méthode qui transforme le texte en String[][]
	    String[][] donnees = gestionnaire.lireFichiersCsv("trinquets.csv");
	    
	    // Vérification
	    if (donnees.length == 0) {
	        System.out.println("Échec : Le tableau est vide.");
	    } else {
	        System.out.println("Succès ! Nombre de trinquets trouvés : " + (donnees.length));
	        System.out.println("--------------------------------------------------");
	        
	        // On affiche les données ligne par ligne pour vérifier le découpage
	        for (int i = 0; i < donnees.length; i++) {
	            System.out.println(gestionnaire.obtenirIdTrinquets()[i]);
	            System.out.println(gestionnaire.obtenirNomsTrinquets()[i]);
	            System.out.println(gestionnaire.obtenirVillesTrinquets()[i]);
	            System.out.println(gestionnaire.obtenirAdressesTrinquets()[i]);
	        }
	    }
	    System.out.println("fini d afficher les trinquets");
	    int nbClient = gestionnaire.lireFichiersCsv("clients.csv").length;
	    System.out.println("Succès ! Nombre de trinquets trouvés : " + (donnees.length));
        System.out.println("--------------------------------------------------");
        
        // On affiche les données ligne par ligne pour vérifier le découpage
        for (int i = 0; i < nbClient; i++) {
            System.out.println(gestionnaire.obtenirIdClient()[i]);
            System.out.println(gestionnaire.obtenirNomClient()[i]);
            System.out.println(gestionnaire.obtenirPrenomClient()[i]);
        }
        int nbReservations = gestionnaire.lireFichiersCsv("reservations.csv").length;
        for (int i = 0; i < nbReservations; i++) {
        	System.out.println(gestionnaire.obtenirIdReservation()[i]);
            System.out.println(gestionnaire.obtenirDatesReservation()[i]);
            System.out.println(gestionnaire.obtenirIdClientReservation()[i]);
            System.out.println(gestionnaire.obtenirIdTrinquetReservation()[i]);
            System.out.println(gestionnaire.obtenirHeuresDebut()[i]);
        }
	    System.out.println("--------------------------------------------------");
	    System.out.println("Fin du test.");
	}
}
