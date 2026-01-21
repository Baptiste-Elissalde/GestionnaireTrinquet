package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class OcamlCaller {

	public static String appelerOCaml(String chemin, String... args) {
	    try {
	        List<String> commande = new ArrayList<>();
	        commande.add("wsl");
	        commande.add(chemin);
	        for (String arg : args) { commande.add(arg); }

	        ProcessBuilder pb = new ProcessBuilder(commande);
	        // Important pour que OCaml trouve les fichiers .csv
	        pb.directory(new File("C:/Users/Baptiste Elissalde/Desktop/Licence_math_info/S3/Ilu1/Baptiste_elissalde_projet_ilu1/worskspace/GestionnaireTrinquet/crud"));
	        
	        Process p = pb.start();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        
	        StringBuilder result = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            result.append(line).append("\n");
	        }
	        
	        p.waitFor();
	        return result.toString().trim(); // .trim() enlève le dernier saut de ligne inutile

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	/**
	 * lance la commande make dans le dossier crud et crée les executables ocaml
	 * @param 
	 * @return 
	 */
    public static void creerExecutable() {
        try {
            ProcessBuilder pb = new ProcessBuilder("wsl", "make");

            pb.directory(new File(
            		"C:/Users/Baptiste Elissalde/Desktop/Licence_math_info/S3/Ilu1/Baptiste_elissalde_projet_ilu1/worskspace/GestionnaireTrinquet/crud"
            ));

            pb.inheritIO();

            Process p = pb.start();
            p.waitFor();

            System.out.println("Compilation OCaml terminée.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la compilation OCaml");
            e.printStackTrace();
        }
    }


}
    
    

