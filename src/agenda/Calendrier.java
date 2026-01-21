package agenda;

public class Calendrier {
	private Jour debut;
	private Jour[] planning;
	private int taille;
	private boolean plein;
	
	//Constructeur
	/**
	 * Creer un calendrier 
	 * @param premier jour du calendrier, nombre de jour que le calendrier a ( dans le projet ça sera 14).
	 * @return calendrier initialiser avec tout les jours et tout les horaire disponibles.
	 */
	public Calendrier(Jour debut,int x) {
		this.debut = debut;
		this.planning = creerPlaning(debut, x);
		this.taille = x;
		this.plein = false;
	}
	/**
	 * creation de l attribut planning
	 * @param premier jour du planning, nombre de jour que le calendrier a ( dans le projet ça sera 14).
	 * @return tableau de jour vierge.
	 */
	private Jour[] creerPlaning(Jour jour,int taille) {
		Jour[] retour = new Jour[taille]; 
		Jour nouveau = jour;
		retour[0] = jour;
		for(int i = 1;i <taille;i++) {
			nouveau = nouveau.jSuivant(); // on rempli le tableau avec les jour suivants
			retour[i] = nouveau;	
		}
		return retour;
	}
	
	//Fonction d affichage
	/**
	 * Affiche le planing tel que affiche la date puis affiche le nombre d horaire dispo a cette date
	 * @param 
	 * @return 
	 */
	public void afficherPlaning() {
		for(int i =0;i<this.taille;i++) {
			System.out.println(planning[i].getDate().afficherDate()); 
			planning[i].afficherNbDispo();
		}
	}
	
	//Fonction getter
	/**
	 * accede a l attrbut planning
	 * @param 
	 * @return renvoie l attribut planning
	 */
	public Jour[] getPlanning() {
		return this.planning;
	}
	/**
	 * accede au ieme jour du planning
	 * @param indice du jour dans le planning
	 * @return le jour a l indice i
	 */
	public Jour getJour(int i) {
		return this.planning[i];
	}
	/**
	 * renvoie l horaire du ieme jour
	 * @param l heure demandé et l indice du ieme jour demandé
	 * @return l horaire au jour i
	 */
	public Horaire getHoraireJour(int heure,int ijour) {
		return getJour(ijour).getHoraire(heure);
	}
	/**
	 * accede a l attribut taille
	 * @param 
	 * @return attrbut taille
	 */
	public int getTaille() {
		return this.taille;
	}
	
	//Fonctions manipulations de la classe Calendrier
	/**
	 * donne le nombre de jour qui sont disponible dans le planning
	 * @param 
	 * @return le nombre de jour disponible
	 */
	public int nbJourDispo() {
		int cpt = 0;
		for(int i =0;i<this.taille;i++) {
			if(!getJour(i).getPlein()) {
				cpt ++;
			}
		}
		return cpt;
	}
	/**
	 * donne le nombre d horaire dispo du planning
	 * @param 
	 * @return nombre d horare dispo dans le planing
	 */
	public int nbHoraireDispo() {
		int cpt = 0;
		for(int i =0;i<this.taille;i++) {
			cpt = cpt + planning[i].nbHoraireDispo();
		}
		return cpt;
	}
	/**
	 * met a true l attribut plein si il y a plus de jour disponible
	 * @param 
	 * @return 
	 */
	public void setPlein() {
		if(nbJourDispo()==0) {
			this.plein = true;
		}
	}

}
