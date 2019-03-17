package objets;

import java.util.ArrayList;

public class Client extends Noeud {
	
	private ArrayList<Commande> listcom;

	public Client(String name, ArrayList<Produit> listproduits) {
		super(name, listproduits);
		// TODO Auto-generated constructor stub
		listcom = new ArrayList<Commande>();
	}

	public ArrayList<Commande> getListcom() {
		return listcom;
	}

	public void setListcom(ArrayList<Commande> listcom) {
		this.listcom = listcom;
	}

}
