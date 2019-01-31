//avec Lisa Kacel 3702697

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Serveur implements Runnable{
	private ArrayBlockingQueue<Requete> file_req; //file des requetes
	
	private final int MAX_SERV = 5; //quantité max de travailleurs
	private ExecutorService pool_travailleurs; //pool de travailleurs
	
	private ExecutorService pool_clients; //pool de clients
	
	private CompletionService<Requete> cs; //file des reponses
	
	public Serveur(ArrayBlockingQueue<Requete> file_req, ExecutorService pool_clients) {
		this.file_req = file_req;
		this.pool_clients = pool_clients;
		this.pool_travailleurs = Executors.newFixedThreadPool(MAX_SERV);
		this.cs = new ExecutorCompletionService<Requete>(pool_travailleurs);		
	}

	public void run(){
		Requete r_in = null;
		Future<Requete> fr_out = null;
		Requete r_out = null;
		trace("commence!");
		while(!pool_clients.isTerminated()) {
			try {
				if((r_in = file_req.poll()) != null || (fr_out = cs.poll()) != null){
					if(r_in != null) {
						cs.submit(new Travailleur(r_in)); 
					}
					if(fr_out != null) {
						r_out = fr_out.get();
						r_out.requeteTraitee();
					}
				}else {
					Thread.sleep(200);
				}
			}catch(InterruptedException | ExecutionException e) {
				System.out.println("Serveur Interrompu");
			}
		}
		pool_travailleurs.shutdown();	
		trace("fini!");
	}
	
	public void trace(String s) {
		System.out.println("Serveur " + s);
	}
}