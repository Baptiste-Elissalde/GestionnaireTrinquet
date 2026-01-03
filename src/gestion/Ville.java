package gestion;

public class Ville {
	private String nom;
	private int nbtrinquet ;
	private Trinquet[] trinquets;
	private boolean plein;
	
	public Ville(String nom) {
		this.nom = nom;
		this.nbtrinquet = 0;
		this.plein = false;
		this.trinquets = new Trinquet[10];
	}
	
	public void ajouterTrinquet(Trinquet trinquet) {
		this.trinquets[nbtrinquet] = trinquet;
		this.nbtrinquet++;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getNbtrinquet() {
		return nbtrinquet;
	}
	public Trinquet getTrinquet(int i) {
		assert i>0 && i<= nbtrinquet;
		return trinquets[i];
	}
	
	public void afficherTrinquet() {
		for(int i =0;i<this.nbtrinquet;i++) {
			trinquets[i].afficherNom();
			trinquets[i].afficherAdresse();
		}
	}
}
