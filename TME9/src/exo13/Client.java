//avec Lisa Kacel 3702697

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Client implements Runnable{

	public final int id;
	private static int cpt = 1;
	private static Object obj = new Object();
	private static Random gen = new Random(); 
	public boolean requeServ = false; 
	private ArrayBlockingQueue<Requete> file_req; //file des requetes
	
	public Client (ArrayBlockingQueue<Requete> file_req) {
		synchronized(obj) {
			this.id = cpt;
			cpt++;
		}
		this.file_req = file_req;
	}
	
	public void run() {
		try {
			this.trace(" initialisé");
			Requete req_in = new Requete(this, gen.nextInt(1000));
			this.trace(" envoie la " + req_in);
			file_req.put(req_in);
			this.trace(" j'attends le serveur");
			synchronized(this) {
				if (this.requeServ == false) {
					wait();
				}
			}
			this.trace(" ma requete a ete traitee");
		}catch(InterruptedException e) {
			e.printStackTrace();
		}		
	}
	public void trace(String s) {
		System.out.println("Client id " + id + s);
	}
}