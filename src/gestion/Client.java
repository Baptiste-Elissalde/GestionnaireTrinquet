package gestion;


public class Client {
    private static int compteurIdclient = 1; 
	
	private int idClient ;
	private String nom;
	private String prenom;
	private int nbReservation;
	private Reservation[] reservation;
	
	public Client(String nom,String prenom) {
		this.idClient = compteurIdclient;
		compteurIdclient ++;
		this.nom = nom;
		this.prenom = prenom;
		this.nbReservation = 0;
		this.reservation = new Reservation[28];
	}
	
	public void reserverHoraire(Trinquet trinquet, int ihoraire,int ijour) {
		this.reservation[nbReservation] = new Reservation(this,trinquet,ihoraire,ijour);
		this.nbReservation ++;
	}
	
	public String getNom() {
		return this.nom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public int getId() {
		return this.idClient;
	}
	
	public String NomPrenom() {
		return this.nom +" "+this.prenom ;
	}
	
	public void afficherIdentifiant() {
		System.out.println("Vote identifiant est "+getId());
	}
	
	public void afficherReservation() {
		if (nbReservation == 0) {
			System.out.println("Vous n avez pas encore de réservation, qu'est ce que vous attendez ???");
		}
		else {
			for(int i = 0;i<this.nbReservation;i++ ) {
				reservation[i].afficherReservation();
			}
		}
	}
	

}

