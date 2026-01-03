package agenda;

public class Calendrier {
	private Jour debut;
	private Jour[] planning;
	private int taille;
	private boolean plein;
	
	public Calendrier(Jour debut,int x) {
		this.debut = debut;
		this.planning = creerPlaning(debut, x);
		this.taille = x;
		this.plein = false;
	}
	
	private Jour[] creerPlaning(Jour jour,int taille) {
		Jour[] retour = new Jour[taille];
		Jour nouveau = jour;
		retour[0] = jour;
		for(int i = 1;i <taille;i++) {
			nouveau = nouveau.jSuivant();
			retour[i] = nouveau;
			
		}
		return retour;
	}
	
	public void afficherPlaning() {
		for(int i =0;i<this.taille;i++) {
			System.out.println(planning[i].getDate().afficherDate()); 
			planning[i].afficherNbDispo();
		}
	}
	
	public Jour[] getPlanning() {
		return this.planning;
	}
	
	public Jour getJour(int i) {
		return this.planning[i];
	}
	
	public Horaire getHoraireJour(int ihoraire,int ijour) {
		return getJour(ijour).getHoraire(ihoraire);
	}
	
	public int getTaille() {
		return this.taille;
	}
	
	public int nbJourDispo() {
		int cpt = 0;
		for(int i =0;i<this.taille;i++) {
			if(!getJour(i).getPlein()) {
				cpt ++;
			}
		}
		return cpt;
	}
	
	public int nbHoraireDispo() {
		int cpt = 0;
		for(int i =0;i<this.taille;i++) {
			cpt = cpt + planning[i].nbHoraireDispo();
		}
		return cpt;
	}
	
	public void setPlein() {
		if(nbJourDispo()==0) {
			this.plein = true;
		}
	}
}
