package generateuralea;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import objets.Client;
import objets.Commande;
import objets.Lien;
import objets.Noeud;
import objets.Produit;
import objets.Usine;

public class Commandealea {

	static int maxcommandes = 5;//commandes maximum par client
	static int maxquantitees = 10;//quantité maximum par commande
	static String datedebut = "2018-01-01 00:00:00";
	static String datefin = "2018-06-30 23:59:59";
	
	public static void main(String[] args) throws IOException, ParseException {
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
		
		//Création de commandes
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long dureejours;
		Date vraidatedebut = formater.parse(datedebut);
		Date vraidatefin = formater.parse(datefin);
		dureejours = (vraidatefin.getTime() - vraidatedebut.getTime())/86400;
		
		ArrayList<Commande> com = new ArrayList<Commande>();
		
		fileWriter = new FileWriter("Commandes Clients.csv",false);
		StringBuilder sb = new StringBuilder();
		sb.append("Client,Date,Produit,Quantite").append("\n");
		for (int i=0;i<listnoeuds.size();i++) {
			if (listnoeuds.get(i) instanceof Client){
				com = new ArrayList<Commande>();
				int nbcommandes = ra.nextInt(maxcommandes);
				int posprod = ra.nextInt(listproduits.size());
				String nameprod = listproduits.get(posprod).getName();
				for (int j=0;j<nbcommandes;j++){
					Date d = ajouterJour(vraidatedebut,ra.nextInt((int) dureejours));
					for (int k=0;k<com.size();k++){
						if (com.get(k).getDate().getTime()>d.getTime()){
							com.add(k,new Commande(d, nameprod, ra.nextInt(maxquantitees)+1));
							break;
						}
					}
					if (com.size()==0 || com.get(com.size()-1).getDate().getTime()<=d.getTime()){
						com.add(new Commande(d, nameprod, ra.nextInt(maxquantitees)+1));
					}
					
				}
				for (int j=0;j<nbcommandes;j++){
					sb.append(listnoeuds.get(i).getName() + "," + formater.format(com.get(j).getDate()) + "," 
							+ com.get(j).getProduitcommande() + "," + com.get(j).getQuantite()).append("\n");
				}
				
				
			}
		}
		fileWriter.append(sb);
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
	
	public static Date ajouterJour(Date date, int nbJour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, nbJour);
		return cal.getTime();
	}
	
	
	
}
