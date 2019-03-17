package generateuralea;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import objets.Noeud;
import objets.Produit;
import objets.Usine;

public class Productionalea {
	
	static int maxproduction = 6;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Random ra = new Random();
		
		ArrayList<Produit> listproduits;
		ArrayList<Noeud> listnoeuds;
		
		listproduits = new ArrayList<Produit>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("nodes_properties.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			String line = br.readLine();
				
		while ((line = br.readLine()) != null) {
			String[] nodeInfo = line.split(",");
					
			if (nodeInfo[1].equals("usine")) {
				listproduits.add(new Produit(nodeInfo[0],1));
			}
		}
		br.close();
		
		FileWriter fileWriter = new FileWriter("Liste Produits.csv",false);
		for (int i=0;i<listproduits.size();i++){
			fileWriter.append(listproduits.get(i).getName() + ",1").append("\n");
		}
		fileWriter.close();
		
		//noeuds
		listnoeuds = new ArrayList<Noeud>();
		try {
			br = new BufferedReader(new FileReader("nodes_properties.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line = br.readLine();
		int lignelue = 0;
		while ((line = br.readLine()) != null) {
			lignelue++;
			String[] nodeInfo = line.split(",");
			lignelue++;
			if (!nodeInfo[1].equals("hub")){
				line = br.readLine();
				String[] nodeInfo2 = line.split(",");	
				if (nodeInfo2[1].equals("usine")) {
					listnoeuds.add(new Usine(nodeInfo2[0],listproduits));
					line = br.readLine();
					lignelue++;
				} else {
					listnoeuds.add(new Noeud(nodeInfo[0],listproduits));
				}
			}
		}
		br.close();
		
		fileWriter = new FileWriter("Productions.csv",false);
		fileWriter.append("Usine,Produit,quantite").append("\n");
		for (int i=0;i<listnoeuds.size();i++){
			if (listnoeuds.get(i) instanceof Usine) {
				fileWriter.append(listnoeuds.get(i).getName() + "," + listnoeuds.get(i).getName() + "," + (ra.nextInt(maxproduction)+1)).append("\n");
			}
		}
		fileWriter.close();

	}

}
