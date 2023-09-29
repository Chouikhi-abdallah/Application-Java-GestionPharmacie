package modeles;

public class Utilisateur {
	private int id_utilisateur ;
    private String nom_utilisateur ;
    private String mot_de_passe;
    private String type_utilisateur ;
	public Utilisateur(int id_utilisateur, String nom_utilisateur, String mot_de_passe, String type_utilisateur) {
		super();
		this.id_utilisateur = id_utilisateur;
		this.nom_utilisateur = nom_utilisateur;
		this.mot_de_passe = mot_de_passe;
		this.type_utilisateur = type_utilisateur;
	}
	public int getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public String getNom_utilisateur() {
		return nom_utilisateur;
	}
	public void setNom_utilisateur(String nom_utilisateur) {
		this.nom_utilisateur = nom_utilisateur;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	public String getType_utilisateur() {
		return type_utilisateur;
	}
	public void setType_utilisateur(String type_utilisateur) {
		this.type_utilisateur = type_utilisateur;
	}
    
    

}
