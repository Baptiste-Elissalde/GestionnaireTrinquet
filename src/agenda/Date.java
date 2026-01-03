package agenda;
import dao.OcamlCaller;


public class Date {
	private int annee;
	private int mois;
	private int jour;
	
	public Date(int annee, int mois, int jour) {
		assert 0 < mois ;
		assert mois <= 12; 
		this.annee = annee;
		this.mois = mois;
		this.jour = jour;
	}
	
	public boolean estBissextile() {
		String chemin =
        		"/mnt/c/Users/Baptiste Elissalde/Desktop/"
        		+ "Licence_math_info/S3/Ilu1/Baptiste_elissalde_projet_ilu1/"
        		+ "worskspace/GestionnaireTrinquet/crud/bissextile.exe";
		String parametre = Integer.toString(this.annee);
		int retour = Integer.parseInt(OcamlCaller.appelerOCaml(chemin,parametre));
		return (retour == 1);
	}
	
	public int nbjour() {
		String chemin =
        		"/mnt/c/Users/Baptiste Elissalde/Desktop/"
        		+ "Licence_math_info/S3/Ilu1/Baptiste_elissalde_projet_ilu1/"
        		+ "worskspace/GestionnaireTrinquet/crud/nbjour.exe";
		return Integer.parseInt(OcamlCaller.appelerOCaml(chemin, String.valueOf(this.annee), String.valueOf(this.mois)));
	}
	
	public boolean estValide() {
		int retour = this.nbjour();
		if (retour == -1) return false;
		if (this.jour > 0 && this.jour <= retour)return true;
		else return false;
	}
	
	public Date dateSuivant() {
		int nbjour = this.nbjour();
		int j = this.jour;
		int m = this.mois;
		int a = this.annee;
		
		if(j<nbjour) {
			j ++;
		}
		else {
			j = 1;
			if (m <12) {
				m++;
			}
			else {
				m = 1;
				a ++;
			}
		}
		return new Date(a,m,j);
	}
	
	public String afficherDate() {
	    return this.jour + "/" + this.mois + "/" + this.annee;
	}
}


