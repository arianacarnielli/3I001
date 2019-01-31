//avec Lisa Kacel 3702697

import java.util.Random;

public class GroupeClients {
	private Restaurant resto;
	private Integer reservation;
	private int tGroupe;
	
	public final int id;
	private static int cpt = 1;
	
	private Thread [] clients;
	public int tousarrivees;

	private static Object obj = new Object();
	private static Random gen = new Random(); 
	
	
	public GroupeClients (Restaurant resto, int tGroupesMax){
		this.resto = resto;
		synchronized(obj) {
			this.id = cpt;
			cpt++;
		}
		reservation = null; 
		this.tGroupe = gen.nextInt(tGroupesMax) + 1;
		clients = new Thread[tGroupe];
		tousarrivees = tGroupe;
		System.out.println("le groupe " + this.id + " a " + this.tGroupe + " clients");
		for(int i = 0; i < tGroupe; i++) {
			clients[i] = new Thread(new Client(this, i));
			clients[i].start();
		}
	}
	
	public synchronized void reserver() {
		if (this.reservation == null) {
			this.reservation = resto.reserver(this.tGroupe, this.id);
			if(this.reservation == null) {
				for(int i = 0; i < this.tGroupe; i ++) {
					//if(this.clients[i] != null) {
					this.clients[i].interrupt();
					//}
				}
			}else {
				System.out.println("un client du groupe " + this.id + " a reserve des tables");
			}
		}		
	}

	

}
