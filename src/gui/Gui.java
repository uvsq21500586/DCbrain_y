package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import objets.*;

public class Gui extends JFrame {
	private ArrayList<Produit> listproduits;
	private ArrayList<Noeud> listnoeuds;
	private ArrayList<Lien> listliens;

	public Gui() throws IOException{
		//générer la liste des produits
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
		
		FileWriter fileWriter = new FileWriter("Liste Produits.csv",true);
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
		for (int i=0;i<listnoeuds.size();i++) {
			System.out.print(listnoeuds.get(i).getName() + " ");
		}
		System.out.println();
		br.close();
		
		//liens
		listliens = new ArrayList<Lien>();
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
				Noeud s = null;
				Noeud d = null;
				if (getposnoeud(nodeInfo[0])==-1) {
					listnoeuds.add(new Client(nodeInfo[0],listproduits));
					s = listnoeuds.get(listnoeuds.size()-1);
				} else {
					s = listnoeuds.get(getposnoeud(nodeInfo[0]));
				}
				if (getposnoeud(nodeInfo[1])==-1) {
					listnoeuds.add(new Client(nodeInfo[1],listproduits));
					d = listnoeuds.get(listnoeuds.size()-1);
				} else {
					d = listnoeuds.get(getposnoeud(nodeInfo[1]));
				}
				listliens.add(new Lien(nodeInfo[2],d,s));
			}
		}
		br.close();
		for (int i=0;i<listliens.size();i++) {
			System.out.print(listliens.get(i).getName() + " ");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Gui gui = new Gui();
		
		
	}

	public ArrayList<Produit> getListproduits() {
		return listproduits;
	}

	public void setListproduits(ArrayList<Produit> listproduits) {
		this.listproduits = listproduits;
	}

	public ArrayList<Noeud> getListnoeuds() {
		return listnoeuds;
	}

	public void setListnoeuds(ArrayList<Noeud> listnoeuds) {
		this.listnoeuds = listnoeuds;
	}
	
	public int getposnoeud( String nomnoeud) {
		int pos = -1;
		for (int i=0;i<listnoeuds.size();i++){
			if (listnoeuds.get(i).getName().equals(nomnoeud)){
				pos = i;
			}
		}
		return pos;
	}

}
