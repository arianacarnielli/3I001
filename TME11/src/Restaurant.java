//avec Lisa Kacel 3702697

import java.util.concurrent.Semaphore;

public class Restaurant {
	private int nbTables;
	private Semaphore resTables;
	private static int reservation = 1;
	
	public Restaurant(int nbTables){
		this.nbTables = nbTables;
		this.resTables = new Semaphore(this.nbTables);
		System.out.println("le restaurant a " + nbTables + " tables");
	}
	
	public Integer reserver(int nbClients, int id) {
		Integer resultat = null;
		int tables  = (int)Math.ceil((double)nbClients / 2);
		System.out.println("un client du groupe " + id + " essaie de reserver " + tables + " tables");
		if(resTables.tryAcquire(tables)) {
			resultat = reservation;
			reservation++;
		}
		return resultat;
	}
}
