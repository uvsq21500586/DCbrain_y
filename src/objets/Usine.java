package objets;

import java.util.ArrayList;

public class Usine extends Noeud {
	
	private int production;
	private String produitgenere;

	public Usine(String name, ArrayList<Produit> listproduits) {
		super(name, listproduits);
		// TODO Auto-generated constructor stub
	}


	public void setProduction(int produstion) {
		this.production = produstion;
	}

	

	public int getProduction() {
		return production;
	}

	public String getProduitgenere() {
		return produitgenere;
	}

	public void setProduitgenere(String produitgenere) {
		this.produitgenere = produitgenere;
	}
	
}
