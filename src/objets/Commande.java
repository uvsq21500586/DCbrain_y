package objets;

import java.util.Date;

public class Commande {

	private Date date;
	private String produitcommande;
	private int quantite;
	public Commande(Date date, String produitcommande, int quantite) {
		super();
		this.date = date;
		this.produitcommande = produitcommande;
		this.quantite = quantite;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProduitcommande() {
		return produitcommande;
	}
	public void setProduitcommande(String produitcommande) {
		this.produitcommande = produitcommande;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
	
}
