package modeles;

public class Medicament {
	private int id_medicament ;
    private String nom_medicament ;
    private String description_medicament ;
    private String stock_medicament;
    private double prix_medicament;
	
    public Medicament(int id_medicament, String nom_medicament, String description_medicament,
			String stock_medicament,double prix_medicament) {
		super();
		this.id_medicament = id_medicament;
		this.nom_medicament = nom_medicament;
		this.description_medicament = description_medicament;
		this.stock_medicament = stock_medicament;
		this.prix_medicament=prix_medicament;
	}
	public int getId_medicament() {
		return id_medicament;
	}
	public void setId_medicament(int id_medicament) {
		this.id_medicament = id_medicament;
	}
	public String getNom_medicament() {
		return nom_medicament;
	}
	public void setNom_medicament(String nom_medicament) {
		this.nom_medicament = nom_medicament;
	}
	public String getDescription_medicament() {
		return description_medicament;
	}
	public void setDescription_medicament(String description_medicament) {
		this.description_medicament = description_medicament;
	}
	public String getStock_medicament() {
		return stock_medicament;
	}
	public void setStock_medicament(String stock_medicament) {
		this.stock_medicament = stock_medicament;
	}
	public double getPrix_medicament() {
		return prix_medicament;
	}
	public void setPrix_medicament(double prix_medicament) {
		this.prix_medicament = prix_medicament;
	}
    

}
