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
            for (String arg : args) {
                commande.add(arg);
            }

            ProcessBuilder pb = new ProcessBuilder(commande);
            Process p = pb.start();

            BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));

            return reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void creerExecutable() {
        try {
            ProcessBuilder pb = new ProcessBuilder("wsl", "make");

            pb.directory(new File(
                "C:/Users/Baptiste Elissalde/Desktop/Licence_math_info/S3/Ilu1/" +
                "Baptiste_elissalde_projet_ilu1/worskspace/partieocaml"
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
    
    

