package agenda;
import gestion.Client;

public class Jour {
	private Date date;
	private Horaire [] creneau;
	private boolean plein;
	
	public Jour(Date date) {
		if (date.estValide()) {
			this.date = date;
			this.creneau = creerCreneau();
			this.plein = false;
		}
		else throw new IllegalArgumentException("Date invalide veuillez chnagez la date");
	}
	
	private Horaire[] creerCreneau() {
		Horaire [] reservation = new Horaire[15];
		for(int i = 8; i<23;i++) {
			reservation[i-8] = new Horaire(i,i+1) ;
		}
		return reservation;
	}
	
	public void afficherDisponibilité() {
		if(!this.plein) {
			afficherDateReservation();
			System.out.println(nbHoraireDispo() +" créneau disponible");
			afficherReservation();
		}
		else {
			afficherDateReservation();
			System.out.println("Pas de créneau disponible");
		}
	}
	
	private void afficherDateReservation() {
		System.out.println("Créneau du " +this.date.afficherDate());
	}
	
	public void afficherReservation() {
		afficherDateReservation();
		for(int i =0;i<15;i++) {
			this.creneau[i].afficherHoraire();
		}
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public Horaire getHoraire(int i) {
		int indice = i-8;
		if(indice>=0 && indice<creneau.length) {
			return this.creneau[indice];
		}
		else {
			 throw new IllegalArgumentException(
				        "Heure invalide : " + i + " (doit être entre 8 et 22)"
				    );
		}
	}
	
	public Jour jSuivant() {
		 return new Jour(this.date.dateSuivant());
		
	}
	
	public void reserverHoraire(Client client,int i) {
		assert i >= 8 && i <23 ;
		getHoraire(i-8).reserverHoraire(client);
	}
	
	public boolean getPlein() {
		return this.plein;
	}
	
	public int nbHoraireDispo() {
	    int cpt = 0;
	    for(int i = 8; i <= 22; i++) { 
	        if (getHoraire(i).estDisponible()) { 
	            cpt++;
	        }
	    }
	    return cpt;
	}
	
	public void setPlein() {
		this.plein = nbHoraireDispo() == 0;
	}
	
	public void afficherNbDispo() {
		System.out.println(nbHoraireDispo() +" créneau disponible");
	}
}
