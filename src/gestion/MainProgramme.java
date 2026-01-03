package gestion;

import agenda.Calendrier;

import agenda.Date;
import agenda.Jour;
import java.util.Scanner;
public class MainProgramme {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        BaseDeDonnee base = new BaseDeDonnee();

        System.out.println("=== BIENVENUE AU SERVICE DE RESERVATION === ");
        System.out.println("Paul Sabatier unibertsitateko Baptiste Elissalde ikasleak egina\n\n");
        System.out.println("ATTENTION si le logiciel vous ddemande un nombre et que vous donnez autre chose par ");
        System.out.println("exemple des caracteres le logiciel plantera, j en decline toute responsabilité \n");

        // Client
        System.out.print("Entrez votre nom : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez votre prénom : ");
        String prenom = scanner.nextLine();

        Client client = new Client(nom, prenom);
        System.out.println("\nBonjour " + client.NomPrenom());
        client.afficherIdentifiant();
        int menu = 0;
         //choix de l option
        while (menu != 4) {
        	System.out.print("Que voulez vous faire ? \n");
            System.out.print("1 - Reserver un trinquet par son nom \n");
            System.out.print("2 - Regarder les trinquets d une ville \n");
            System.out.print("3 - Regarder vos reservations \n");
            System.out.print("4 - Quitter \n");
            String saisie = scanner.nextLine(); 
            menu = Integer.parseInt(saisie.trim());
            if(menu == 1) {
            	base.afficherTrinquetsTries();
            	Trinquet trinquet = base.saisiTrinquet(scanner, client);
            	base.saisiReserver(trinquet, scanner, client);
            	menu = 0;
            }
            if(menu == 2) {
            	base.afficherToutesLesVilles();
            	Ville ville = null;
                while (ville == null) {
                	System.out.print("Selectionner une ville(faite un copier colle pour ne pas vous trompez) : ");
                    String nomVille = scanner.nextLine(); 
                    ville = base.chercherVille(nomVille);
                }
                System.out.println("Voici les trinquets de la ville");
                ville.afficherTrinquet();
                int iTrinquet = -1;
                while(iTrinquet < 0 || iTrinquet >= ville.getNbtrinquet()) {
                    System.out.print("Sélectionner un trinquet par indice (1 à " + (ville.getNbtrinquet()) + ") : ");
                    try {
                        iTrinquet = Integer.parseInt(scanner.nextLine())-1;
                    } catch (NumberFormatException e) {
                        System.out.println("Veuillez entrer un nombre valide.");
                    }
                }
                Trinquet trinquet = ville.getTrinquet(iTrinquet);
                base.saisiReserver(trinquet,scanner, client);
            }
            if(menu == 3) {
            	client.afficherReservation();
            	menu = 0;
            }
        }
        System.out.println("Au revoir !");
        scanner.close();
    }
}
