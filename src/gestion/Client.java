package gestion;


public class Client {
	
	private int idClient ;
	private String nom;
	private String prenom;
	private int nbReservation;
	private Reservation[] reservation;
	
	public Client(int id,String nom,String prenom) {
		this.idClient = id;
		this.nom = nom;
		this.prenom = prenom;
		this.nbReservation = 0;
		this.reservation = new Reservation[20];
	}
	
	//Fonction pour reserver une horaire
	public int reserverHoraire(Trinquet trinquet, int ihoraire,int ijour) {
		if(trinquet.getPlanning().getJour(ijour).getHoraire(ihoraire).estDisponible())
		{
			this.reservation[nbReservation] = new Reservation(this,trinquet,ihoraire,ijour);
			this.nbReservation ++;
			return 0;
		}
		else {
			System.out.println("l horaire est deja prise");
			return 1;
		}
	}
	
	//Fonctions getter
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
	
	//Fonctions affichage
	public void afficherIdentifiant() {
		System.out.println("Vote identifiant est "+getId());
	}
	public void afficherNomPrenom() {
		System.out.println(getNom() +" "+getPrenom());
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

