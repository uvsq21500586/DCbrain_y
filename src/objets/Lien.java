package objets;

import java.util.ArrayList;

public class Lien {

	private String name;
	private Noeud source;
	private Noeud dest;
	private ArrayList<Capaciteligne> capa;
	private double cout;
	private int capavehicule;
	
	
	
	public Lien(String name, Noeud source, Noeud dest) {
		super();
		this.name = name;
		this.source = source;
		this.dest = dest;
		capa = new ArrayList<Capaciteligne>();
		cout = 0;
		capavehicule = 24;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Noeud getSource() {
		return source;
	}
	public void setSource(Noeud source) {
		this.source = source;
	}
	public Noeud getDest() {
		return dest;
	}
	public void setDest(Noeud dest) {
		this.dest = dest;
	}
	public ArrayList<Capaciteligne> getCapa() {
		return capa;
	}
	public void setCapa(ArrayList<Capaciteligne> capa) {
		this.capa = capa;
	}
	public double getCout() {
		return cout;
	}
	public void setCout(double cout) {
		this.cout = cout;
	}
	public int getCapavehicule() {
		return capavehicule;
	}
	public void setCapavehicule(int capavehicule) {
		this.capavehicule = capavehicule;
	}
	
	
	
}
