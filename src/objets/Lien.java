package objets;

public class Lien {

	String name;
	Noeud source;
	Noeud dest;
	public Lien(String name, Noeud source, Noeud dest) {
		super();
		this.name = name;
		this.source = source;
		this.dest = dest;
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
	
	
	
}
