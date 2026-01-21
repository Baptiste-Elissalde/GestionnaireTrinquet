package agenda;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestAgenda {
	
	public static void main(String[] args) {
        //main pour tester si mes fonctions du package agenda fonctionne corretement
        Date d1 = new Date(2020,11,25);
        Date d2 = new Date(2025,12,31);
        Date d3 = new Date(2025,2,29);
        Date d4 = new Date(2020,2,29);
        Jour j1 = new Jour(d1);
        Jour j2 = new Jour(d2);
        Calendrier c1 = new Calendrier(j2,14);
        c1.afficherPlaning();
        c1.getJour(5).afficherReservation();
    }
}

