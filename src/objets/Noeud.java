package objets;

import java.util.ArrayList;

public class Noeud {

	private String name;
	private ArrayList<Produit> listproduits;
	private ArrayList<Integer> stockinitial;
	private ArrayList<Integer> stockactuel;
	
	private double coutstock;
	private double capaStock;
	private int capaTraitement;
	private double couttraitement;
	public Noeud(String name, ArrayList<Produit> listproduits, ArrayList<Integer> stockinitial,
			double coutstock, double capaStock, int capaTraitement, double couttraitement) {
		super();
		this.name = name;
		this.listproduits = listproduits;
		this.stockinitial = stockinitial;
		stockactuel = new ArrayList<Integer>();
		for (int i=0;i<listproduits.size();i++) {
			stockactuel.add(stockinitial.get(i));
		}
		this.coutstock = coutstock;
		this.capaStock = capaStock;
		this.capaTraitement = capaTraitement;
		this.couttraitement = couttraitement;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Produit> getListproduits() {
		return listproduits;
	}
	public void setListproduits(ArrayList<Produit> listproduits) {
		this.listproduits = listproduits;
	}
	public ArrayList<Integer> getStockinitial() {
		return stockinitial;
	}
	public void setStockinitial(ArrayList<Integer> stockinitial) {
		this.stockinitial = stockinitial;
	}
	public ArrayList<Integer> getStockactuel() {
		return stockactuel;
	}
	public void setStockactuel(ArrayList<Integer> stockactuel) {
		this.stockactuel = stockactuel;
	}
	public double getCoutstock() {
		return coutstock;
	}
	public void setCoutstock(double coutstock) {
		this.coutstock = coutstock;
	}
	public double getCapaStock() {
		return capaStock;
	}
	public void setCapaStock(double capaStock) {
		this.capaStock = capaStock;
	}
	public int getCapaTraitement() {
		return capaTraitement;
	}
	public void setCapaTraitement(int capaTraitement) {
		this.capaTraitement = capaTraitement;
	}
	public double getCouttraitement() {
		return couttraitement;
	}
	public void setCouttraitement(double couttraitement) {
		this.couttraitement = couttraitement;
	}
	
	
	
	
}
