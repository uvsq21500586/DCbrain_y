package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

import objets.*;

public class Gui extends JFrame {
	private ArrayList<Produit> listproduits;
	private ArrayList<Noeud> listnoeuds;
	private ArrayList<Lien> listliens;
	static String datedebut = "2018-01-01 00:00:00";
	static String datefin = "2018-06-30 23:59:59";

	public Gui() throws IOException, ParseException{
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
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
		System.out.println();
		
		//productions
		try {
			br = new BufferedReader(new FileReader("Productions.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line = br.readLine();
				
		while ((line = br.readLine()) != null) {
			String[] nodeInfo = line.split(",");		
			((Usine)listnoeuds.get(getposnoeud(nodeInfo[0]))).setProduction(Integer.parseInt(nodeInfo[2])); 
			((Usine)listnoeuds.get(getposnoeud(nodeInfo[0]))).setProduitgenere(nodeInfo[1]);
		}
		br.close();
		
		//Commandes
		try {
			br = new BufferedReader(new FileReader("Commandes Clients.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line = br.readLine();
				
		while ((line = br.readLine()) != null) {
			String[] nodeInfo = line.split(",");
			Date d = formater.parse(nodeInfo[1]);
			((Client)listnoeuds.get(getposnoeud(nodeInfo[0]))).getListcom().add(new Commande(d, nodeInfo[2],Integer.parseInt(nodeInfo[3])));
		}
		br.close();
		
		//Capatraitement
		try {
			br = new BufferedReader(new FileReader("Capacites Traitement.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line = br.readLine();
				
		while ((line = br.readLine()) != null) {
			String[] nodeInfo = line.split(",");
			listnoeuds.get(getposnoeud(nodeInfo[0])).setCapaTraitement(Integer.parseInt(nodeInfo[1]));
		}
		br.close();
		
		//capacite
		//par défaut
		for (int i=0;i<listnoeuds.size();i++){
			listnoeuds.get(i).setCapaStock(50);
		}
		try {
			br = new BufferedReader(new FileReader("costSites.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line = br.readLine();
				
		while ((line = br.readLine()) != null) {
			String[] nodeInfo = line.split(",");
			listnoeuds.get(getposnoeud(nodeInfo[0])).setCapaStock(Double.parseDouble(nodeInfo[1]));
			listnoeuds.get(getposnoeud(nodeInfo[0])).setCoutstock(Double.parseDouble(nodeInfo[2]));
			listnoeuds.get(getposnoeud(nodeInfo[0])).setCouttraitement(Double.parseDouble(nodeInfo[5]));
		}
		br.close();
		
		for (int i=0;i<listnoeuds.size();i++){
			System.out.print(listnoeuds.get(i).getName() + "->" + listnoeuds.get(i).getCapaStock() + " ");
		}
		System.out.println();
		
		//capacité des lignes
		try {
			br = new BufferedReader(new FileReader("measures_capacities.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] nodeInfo = line.split(",");
			
			if (getposlien( nodeInfo[0])!=-1) {
				Date d = formater.parse(nodeInfo[2]);
				double numb = Double.parseDouble( nodeInfo[5]);
				listliens.get(getposlien( nodeInfo[0])).getCapa().add(new Capaciteligne(d,(int)numb));
			}
			
		}
		br.close();
		
		for (int i=0;i<listliens.size();i++){
			System.out.print(listliens.get(i).getName() + ":");
			for (int j=0;j<listliens.get(i).getCapa().size();j++){
				System.out.print(formater.format(listliens.get(i).getCapa().get(j).getDatecapa()) + "->" + listliens.get(i).getCapa().get(j).getCapacite() + "; ");
			}
			System.out.println();
		}
		
		//cout lien
		try {
			br = new BufferedReader(new FileReader("costByLink.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line = br.readLine();
		lignelue = 0;
		while ((line = br.readLine()) != null) {
			String[] nodeInfo = line.split(",");
			
			if (getposlien( nodeInfo[0])!=-1) {
				if (!nodeInfo[1].equals("")) {
					listliens.get(getposlien( nodeInfo[0])).setCout(Double.parseDouble(nodeInfo[1]));
				} else {
					listliens.get(getposlien( nodeInfo[0])).setCout(0);
				}
			}
			
		}
		br.close();
		
		//cout lien
		try {
			br = new BufferedReader(new FileReader("edges_properties.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] nodeInfo = line.split(",");
					
			if (getposlien( nodeInfo[0])!=-1) {
				if (nodeInfo.length<3) {
					listliens.get(getposlien( nodeInfo[0])).setCapavehicule(24);
				} else {
					double numb = Double.parseDouble( nodeInfo[2]);
					listliens.get(getposlien( nodeInfo[0])).setCapavehicule((int)numb);
				}
			}
					
		}
		br.close();
		
		for (int i=0;i<listliens.size();i++){
			System.out.print(listliens.get(i).getName() + "->" + listliens.get(i).getCapavehicule() + "; ");
		}
		System.out.println();
		
		
		
	}
	
	public static void main(String[] args) throws IOException, ParseException {
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
	
	public int getposlien( String nomlien) {
		int pos = -1;
		for (int i=0;i<listliens.size();i++){
			if (listliens.get(i).getName().equals(nomlien)){
				pos = i;
			}
		}
		return pos;
	}

}
