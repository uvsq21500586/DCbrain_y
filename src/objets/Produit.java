package objets;

public class Produit {
	private String name;
	private int volume;
	
	public Produit(String name, int volume) {
		super();
		this.name = name;
		this.volume = volume;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	
	
}
