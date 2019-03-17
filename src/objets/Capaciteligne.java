package objets;

import java.util.Date;

public class Capaciteligne {

	Date datecapa;
	int capacite;
	public Capaciteligne(Date datecapa, int capacite) {
		super();
		this.datecapa = datecapa;
		this.capacite = capacite;
	}
	public Date getDatecapa() {
		return datecapa;
	}
	public void setDatecapa(Date datecapa) {
		this.datecapa = datecapa;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	
	
	
}
