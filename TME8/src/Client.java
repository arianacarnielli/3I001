//avec Lisa Kacel 3702697

import java.util.Random;

public class Client implements Runnable{
	private Serveur serv;
	private int numReq = 5;
	public final int id;
	private static int cpt = 1;
	public boolean requeServ = false; 
	private static Object obj = new Object();
	private static Random gen = new Random(); 
	
	public Client (Serveur s) {
		this.serv = s;
		synchronized(obj) {
			this.id = cpt;
			cpt++;
		}
	}
	
	public void run() {
		
		int type;
		if(this.id % 3 == 0) {
			type = 2; 
		}else {
			type = 1;
		}		
		try {
			for(int i = 0; i < numReq; i++) {
				System.out.println("id : " + id + " requete : " + i);
				serv.soumettre(this, i, type);
				synchronized(this) {			
					if (this.requeServ == false) {
						this.wait();	
					}
				}	
				Thread.sleep(gen.nextInt(5000));
				requeServ = false;
			}
		System.out.println("id: " + id + " est fini");	
		}catch(InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	void requeteServie(ReponseRequete r) {
		System.out.println(r);
		synchronized(this){
			requeServ = true;
			this.notifyAll();
		}
	}	
}
