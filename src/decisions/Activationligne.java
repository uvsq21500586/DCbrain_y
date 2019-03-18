package decisions;

import java.util.ArrayList;
import java.util.Date;

import objets.*;

public class Activationligne {
	private Lien choixlien;
	private Date dateact;
	private ArrayList<Produit> listproduit;
	private ArrayList<Integer> quantites;
	
	public Activationligne(Lien choixlien, Date dateact, ArrayList<Produit> listproduit, ArrayList<Integer> quantites) {
		super();
		this.choixlien = choixlien;
		this.dateact = dateact;
		this.listproduit = listproduit;
		this.quantites = quantites;
	}

	public Lien getChoixlien() {
		return choixlien;
	}

	public void setChoixlien(Lien choixlien) {
		this.choixlien = choixlien;
	}

	public Date getDateact() {
		return dateact;
	}

	public void setDateact(Date dateact) {
		this.dateact = dateact;
	}

	public ArrayList<Produit> getListproduit() {
		return listproduit;
	}

	public void setListproduit(ArrayList<Produit> listproduit) {
		this.listproduit = listproduit;
	}

	public ArrayList<Integer> getQuantites() {
		return quantites;
	}

	public void setQuantites(ArrayList<Integer> quantites) {
		this.quantites = quantites;
	}
	
	
}
