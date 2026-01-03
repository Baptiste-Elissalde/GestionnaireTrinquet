package gestion;
import java.util.Scanner;

import dao.OcamlCaller;

public class BaseDeDonnee {
	private Ville[] villes;
    private Trinquet[] trinquets;
    private int nbVilles;
    private int nbTrinquets;

    public BaseDeDonnee() {
        this.villes = new Ville[20];
        this.trinquets = new Trinquet[100];
        this.nbVilles = 0;
        this.nbTrinquets = 0;

        initialiserVilles();
        initialiserTrinquets();
    }
    public void initialiserVilles() {
    	Ville mouguerre = new Ville("Mouguerre");
    	Ville bayonne = new Ville("Bayonne");
    	Ville lahonce = new Ville("Lahonce");
    	Ville villefranque = new Ville("villefranque");
    	Ville ustaritz = new Ville("Ustaritz");
    	Ville biarritz = new Ville("Biarritz");
    	Ville anglet = new Ville("Anglet");
    	Ville arcangues = new Ville("Arcangues");
    	Ville bassussarry = new Ville("Bassussarry");
    	Ville hasparen = new Ville("Hasparren");
    	Ville helette = new Ville("Helette");
    	
    	ajouterVille(anglet);
        ajouterVille(arcangues);
        ajouterVille(bassussarry);
        ajouterVille(bayonne);
        ajouterVille(biarritz);
        ajouterVille(hasparen);
        ajouterVille(helette);
        ajouterVille(lahonce);
        ajouterVille(mouguerre);
        ajouterVille(ustaritz);
        ajouterVille(villefranque);    	
    }
    
    
    public void initialiserTrinquets() {

        // Mouguerre
        Trinquet ibar = new Trinquet("Trinquet Ibar", chercherVille("Mouguerre"));
        ibar.setAdresse("380 Route Départementale 357, 64990 Mouguerre, France");
        ajouterTrinquet(ibar);

        // Bayonne
        Trinquet saintAndre = new Trinquet("Trinquet Saint-André", chercherVille("Bayonne"));
        saintAndre.setAdresse("18 Rue du Trinquet, 64100 Bayonne, France");
        ajouterTrinquet(saintAndre);

        Trinquet moderne = new Trinquet("Trinquet Moderne", chercherVille("Bayonne"));
        moderne.setAdresse("60 Avenue Dubrocq, 64100 Bayonne, France");
        ajouterTrinquet(moderne);
        
        Trinquet aviron = new Trinquet("Trinquet aviron bayonnais",chercherVille("Bayonne"));
        aviron.setAdresse("1831 Avenue Chanoine Jean Lamarque, 64100 Bayonne, France");
        ajouterTrinquet(aviron);

        // Biarritz
        Trinquet aguilera = new Trinquet("Trinquet Aguilera", chercherVille("Biarritz"));
        aguilera.setAdresse("Rue d'Aguilera, Biarritz");
        ajouterTrinquet(aguilera);

        // Anglet
        Trinquet elHogar = new Trinquet("Trinquet El Hogar", chercherVille("Anglet"));
        elHogar.setAdresse("Rue de Jouanetote, Anglet");
        ajouterTrinquet(elHogar);

        // Ustaritz
        Trinquet hiribehere = new Trinquet("Trinquet Hiribehere", chercherVille("Ustaritz"));
        hiribehere.setAdresse("Place du Trinquet, Ustaritz");
        ajouterTrinquet(hiribehere);

        // Hasparren
        Trinquet municipalHasparren = new Trinquet("Trinquet Municipal", chercherVille("Hasparren"));
        municipalHasparren.setAdresse("Rue du Trinquet, Hasparren");
        ajouterTrinquet(municipalHasparren);

        // Arcangues
        Trinquet arcanguesCentre = new Trinquet("Trinquet d'Arcangues", chercherVille("Arcangues"));
        arcanguesCentre.setAdresse("Centre du village, Arcangues");
        ajouterTrinquet(arcanguesCentre);

        // Bassussarry
        Trinquet bassussarryCentre = new Trinquet("Trinquet de Bassussarry", chercherVille("Bassussarry"));
        bassussarryCentre.setAdresse("Route de l'Église, Bassussarry");
        ajouterTrinquet(bassussarryCentre);

        // Lahonce
        Trinquet lahonceCentre = new Trinquet("Trinquet de Lahonce", chercherVille("Lahonce"));
        lahonceCentre.setAdresse("Bourg de Lahonce");
        ajouterTrinquet(lahonceCentre);

        // Helette
        Trinquet heletteCentre = new Trinquet("Trinquet d'Helette", chercherVille("Helette"));
        heletteCentre.setAdresse("Centre du village, Helette");
        ajouterTrinquet(heletteCentre);

        // Villefranque
        Trinquet villefranqueCentre = new Trinquet("Trinquet de Villefranque", chercherVille("Villefranque"));
        villefranqueCentre.setAdresse("Place du Fronton, Villefranque");
        ajouterTrinquet(villefranqueCentre);
    }
    
    private void ajouterVille(Ville v) {
        villes[nbVilles++] = v;
    }

    private void ajouterTrinquet(Trinquet t) {
        trinquets[nbTrinquets++] = t;
        t.getVille().ajouterTrinquet(t);
    }

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
    
    public Trinquet saisiTrinquet(Scanner scanner,Client client) {
        Trinquet trinquet = null;
        while (trinquet == null) {
        	System.out.print("Selectionner un trinquet(faite un copier colle pour ne pas vous trompez) : ");
            String nomTrinquet = scanner.nextLine(); 
            trinquet = chercherTrinquet(nomTrinquet);
        }
        return trinquet;
    }
    
    public void saisiReserver(Trinquet trinquet,Scanner scanner,Client client) {
        // Affichage des jours
        System.out.println("\nJours disponibles :");
        trinquet.getPlanning().afficherPlaning();
        int jour = 0; 
        while(jour < 0 && jour >trinquet.getPlanning().getTaille()-1) {
        	System.out.print("Dans combien de jour voulais vous reserver (min 0 max 13) : ");
            jour = scanner.nextInt();
            System.out.println("\n");
        }
        trinquet.afficherJour(jour);
        System.out.println("\n");
        
        // Horaire
        System.out.print("Choisissez une heure (ex: 8 pour 8h-9h) : ");
        int heure = 0;

        // "Tant que l'heure est inférieure à 8 OU supérieure à 22"
        while(heure < 8 || heure > 22) {
        	System.out.print("Choisissez une heure (entre 8 et 22) : ");
        	heure = scanner.nextInt();
        	scanner.nextLine();
         
        	if (heure < 8 || heure > 22) {
        		System.out.println("Heure invalide ! Veuillez recommencer.");
        	}
        }
        client.reserverHoraire(trinquet, heure, jour);
        System.out.println("\n");
        
        System.out.println("=== RESERVATION CONFIRMEE ===");
        client.afficherReservation();
	}
}
