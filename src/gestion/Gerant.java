package gestion;

public class Gerant {
	private String nom;
	private String prenom;
	private Trinquet[] trinquet;
	private String numero;
	
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
