package generateuralea;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import objets.Client;
import objets.Noeud;
import objets.Produit;
import objets.Usine;

public class CapaTraitementalea {
	
	static int maxcapatraitement = 3;

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
		
		//liens
		try {
			br = new BufferedReader(new FileReader("topologie.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line = br.readLine();
		lignelue = 0;
		while ((line = br.readLine()) != null) {
			lignelue++;
			String[] nodeInfo = line.split(",");
			lignelue++;
			if (!nodeInfo[1].contains("Stock") && !nodeInfo[0].contains("Prod")){
				if (getposnoeud(nodeInfo[0],listnoeuds)==-1) {
					listnoeuds.add(new Client(nodeInfo[0],listproduits));
				}
				if (getposnoeud(nodeInfo[1],listnoeuds)==-1) {
					listnoeuds.add(new Client(nodeInfo[1],listproduits));
				}
				//listliens.add(new Lien(nodeInfo[2],d,s));
			}
		}
		br.close();
		
		//capatraitement
		fileWriter = new FileWriter("Capacites Traitement.csv",false);
		fileWriter.append("Noeud,CapaTraitement").append("\n");
		for (int i=0;i<listnoeuds.size();i++){
			fileWriter.append(listnoeuds.get(i).getName() + "," + (ra.nextInt(maxcapatraitement)+1)).append("\n");
		}
		fileWriter.close();
		
		
		

	}
	
	public static int getposnoeud( String nomnoeud , ArrayList<Noeud> listnoeuds) {
		int pos = -1;
		for (int i=0;i<listnoeuds.size();i++){
			if (listnoeuds.get(i).getName().equals(nomnoeud)){
				pos = i;
			}
		}
		return pos;
	}

}
