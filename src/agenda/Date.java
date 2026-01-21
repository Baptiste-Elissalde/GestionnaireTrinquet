package agenda;
import dao.OcamlCaller;


public class Date {
	private int annee;
	private int mois;
	private int jour;
	
	//Constructeur
	/**
	 * constructeur de la classe Date
	 * @param int annee,int mois , int jour.
	 * @return la date crée.
	 */
	public Date(int annee, int mois, int jour) {
		assert 0 < mois ; //on verifie que le mois est valide, pour le jour ça se fera ulterieurement
		assert mois <= 12; 
		this.annee = annee;
		this.mois = mois;
		this.jour = jour;
	}
	
	//Fonctions getter
	/**
	 * getter pour l attribut annee
	 * @param .
	 * @return attrbut annee.
	 */
	public int getAnnee() {
		return annee;
	}
	/**
	 * getter pour l attribut jour
	 * @param .
	 * @return attribut jour .
	 */
	public int getJour() {
		return jour;
	}
	/**
	 * getter pour l attribut mois
	 * @param .
	 * @return attribut mois.
	 */
	public int getMois() {
		return mois;
	}
	
	//Fonction verifications date valide
	/**
	 * verifie que l année de la date est bissextile
	 * @param .
	 * @return true si oui , false sinon.
	 */
	public boolean estBissextile() {
		String chemin =
        		"/mnt/c/Users/Baptiste Elissalde/Desktop/"
        		+ "Licence_math_info/S3/Ilu1/Baptiste_elissalde_projet_ilu1/"
        		+ "worskspace/GestionnaireTrinquet/crud/bissextile.exe";
		String parametre = Integer.toString(this.annee);
		int retour = Integer.parseInt(OcamlCaller.appelerOCaml(chemin,parametre));
		return (retour == 1);
	}
	/**
	 * calcule le nombre de jour dans un mois
	 * @param .
	 * @return le nombre de jour dans le mois sous forme d un int .
	 */
	public int nbjour() {
		String chemin =
        		"/mnt/c/Users/Baptiste Elissalde/Desktop/"
        		+ "Licence_math_info/S3/Ilu1/Baptiste_elissalde_projet_ilu1/"
        		+ "worskspace/GestionnaireTrinquet/crud/nbjour.exe";
		return Integer.parseInt(OcamlCaller.appelerOCaml(chemin, String.valueOf(this.annee), String.valueOf(this.mois)));
	}
	/**
	 * verifie si la date est valide
	 * @param .
	 * @return true si oui, false sinon.
	 */
	public boolean estValide() {
		int retour = this.nbjour();
		if (retour == -1) return false;
		if (this.jour > 0 && this.jour <= retour)return true;
		else return false;
	}
	
	// Fonctions de creation de date
	/**
	 * creer la date du jour suivant
	 * @param .
	 * @return Date datejsuivant.
	 */
	public Date dateSuivant() {
		int nbjour = this.nbjour();
		int j = this.jour;
		int m = this.mois;
		int a = this.annee;
		
		if(j<nbjour) {
			j ++; // si j < au nombre de jour possible alors on l incremente
		}
		else {
			j = 1; // sinon il passe a un et on passe au moins suivant
			if (m <12) {
				m++; // pareil qu avec jour si il est plus petit que 12 on l incremente
			}
			else {
				m = 1; // sinon on passe a l annes suivante et le mois devient janvier
				a ++;
			}
		}
		return new Date(a,m,j);
	}
	/**
	 * Creer une date a partir d un string
	 * @param string date.
	 * @return la date correspondante.
	 */
	public static Date creerDate(String date) {
	    if (date == null) return null;

	    String[] parties = date.trim().split("/"); //on decoupe le string en enlevant les /

	    if (parties.length != 3) return null; // si la date n est pas au bon format renvoie null

	    try {
	        int jour = Integer.parseInt(parties[0]); //on convertie les parties obtenu
	        int mois = Integer.parseInt(parties[1]);
	        int annee = Integer.parseInt(parties[2]);

	        Date d = new Date(annee, mois, jour); 
	        if (d.estValide()) {
	            return d; // si la date est valide on la renvoie 
	        }
	    } catch (NumberFormatException e) {
	        return null; // sinon on renvoie null
	    }

	    return null;
	}
	
	//Fonctions d affichage
	/**
	 * affiche la date sous forme j/m/a
	 * @param .
	 * @return .
	 */
	public String afficherDate() {
	    return this.jour + "/" + this.mois + "/" + this.annee;
	}
}


