package modeles;



public class Client {
	private int id_client; 
    private String nom_client; 
    private String adresse_client; 
    private String telephone_client; 
    private float credit_client;
	public Client(int id_client, String nom_client, String adresse_client, String telephone_client,
			float credit_client) {
		super();
		this.id_client = id_client;
		this.nom_client = nom_client;
		this.adresse_client = adresse_client;
		this.telephone_client = telephone_client;
		this.credit_client = credit_client;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getNom_client() {
		return nom_client;
	}
	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}
	public String getAdresse_client() {
		return adresse_client;
	}
	public void setAdresse_client(String adresse_client) {
		this.adresse_client = adresse_client;
	}
	public String getTelephone_client() {
		return telephone_client;
	}
	public void setTelephone_client(String telephone_client) {
		this.telephone_client = telephone_client;
	}
	public float getCredit_client() {
		return credit_client;
	}
	public void setCredit_client(float credit_client) {
		this.credit_client = credit_client;
	} 
    

}
