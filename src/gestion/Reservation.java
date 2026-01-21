package gestion;
import agenda.Horaire;
import agenda.Date;

public class Reservation {
	private Horaire horaire;
	private Date date;
	private String nomTrinquet;
	private Client client;
	
	//Constructeur
	public Reservation(Client client,Trinquet trinquet,int ihoraire,int ijour) {
		trinquet.getPlanning().getHoraireJour(ihoraire, ijour).reserverHoraire(client);
		trinquet.getPlanning().getJour(ijour).setPlein();
		trinquet.getPlanning().setPlein();
		this.date = trinquet.getPlanning().getJour(ijour).getDate();
		this.client = client;
		this.horaire = new Horaire(ihoraire,ihoraire+1);
		this.nomTrinquet = trinquet.getNom() + " à " + trinquet.getVille().getNom();
	}
	
	//fonctions affichage
	public void afficherReservation() {
		System.out.println("Reservation au nom de " + this.client.NomPrenom() + " au trinquet " +this.nomTrinquet+" le "+ this.date.afficherDate());
		this.horaire.afficherHoraire();
	}
	
	//getter
	public Client getClient() {
		return client;
	}
	public Date getDate() {
		return date;
	}
	public Horaire getHoraire() {
		return horaire;
	}
	public String getNomTrinquet() {
		return nomTrinquet;
	}
}
