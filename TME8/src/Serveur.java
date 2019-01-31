//avec Lisa Kacel 3702697

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serveur implements Runnable{
	private ArrayBlockingQueue<ReponseRequete> file;
	private final int MAX_SERV = 10;
	private ExecutorService executor = Executors.newFixedThreadPool(MAX_SERV);
	private int type;
	
	
	public Serveur() {
		file = new ArrayBlockingQueue<ReponseRequete>(1, true);
	}

	public void run(){
		try {
			while(true) {
				attendreRequete();
				traiterRequete();
			}
		}catch(InterruptedException e) {
			System.out.println("Serveur Interrompu");
		}
		
	}
	
	public void soumettre(Client cl, int nbReq, int type) throws InterruptedException {
		ReponseRequete rr = new ReponseRequete(cl, nbReq);
		
		file.put(rr);
		
		synchronized(this){
			this.type = type;
			notifyAll();
		}
		synchronized(cl) {
			cl.wait();
		}
	}
	
	private void attendreRequete() throws InterruptedException {
		synchronized(this) {
			if(file.peek() == null) {
				wait();
			}
		}
	}
	
	private void traiterRequete() throws InterruptedException {
		executor.execute(new Travailleur(file.take(), this.type)); 
	}
}
