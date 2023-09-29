
package modeles;

import java.util.Date;


public class Ordonance  {
	
    private int id_ordonnance;
    private int id_client;
    private int id_medicament;
    private Date date_ordonnance;
    private int quantite;
	public Ordonance(int id_ordonnance, int id_client, int id_medicament, Date date_ordonnance, int quantite) {
		super();
		this.id_ordonnance = id_ordonnance;
		this.id_client = id_client;
		this.id_medicament = id_medicament;
		this.date_ordonnance = date_ordonnance;
		this.quantite = quantite;
	}
	public int getId_ordonnance() {
		return id_ordonnance;
	}
	public void setId_ordonnance(int id_ordonnance) {
		this.id_ordonnance = id_ordonnance;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public int getId_medicament() {
		return id_medicament;
	}
	public void setId_medicament(int id_medicament) {
		this.id_medicament = id_medicament;
	}
	public Date getDate_ordonnance() {
		return date_ordonnance;
	}
	public void setDate_ordonnance(Date date_ordonnance) {
		this.date_ordonnance = date_ordonnance;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
    
    

}
