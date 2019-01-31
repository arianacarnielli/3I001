//avec Lisa Kacel 3702697

import java.util.Random;

public class Client implements Runnable{
	private GroupeClients gc;
	private static Random gen = new Random(); 
	
	public final int id;
	
	public Client(GroupeClients gc, int cpt) {
		this.gc = gc;
		this.id = cpt;
	}

	public void run() {
		synchronized(gc) {
			try {
				if(Thread.interrupted()){
					throw new InterruptedException(); 
				}
				
				gc.reserver();
				
				if(Thread.interrupted()){ //pour rattraper le seul client qui a essaye de faire la reservation 
					throw new InterruptedException(); 
				}
	
				Thread.sleep(gen.nextInt(2000));
				gc.tousarrivees--;
				System.out.println("le client " + this.id + " du groupe " + gc.id + " vient d'arriver");
				if(gc.tousarrivees == 0) {
					System.out.println("Groupe " + gc.id + " complet, on est pret a passer la commande");
				}
				
			}catch(InterruptedException e) {
				System.out.println("le client " +this.id + " du groupe " + gc.id + " n'a pas reussi a reserver une table");
			}
		}
		
	}

}
