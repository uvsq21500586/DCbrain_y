package objets;

import java.util.ArrayList;

public class Client extends Noeud {
	
	ArrayList<Commande> listcom;

	public Client(String name, ArrayList<Produit> listproduits) {
		super(name, listproduits);
		// TODO Auto-generated constructor stub
		listcom = new ArrayList<Commande>();
	}

}
