//avec Lisa Kacel 3702697

public class TesteExo11 {
	static int NB_CLIENTS = 3;
	public static void main(String[] args) {
		Serveur serv = new Serveur();		
		Thread tr_s = new Thread(serv);
		tr_s.start();
		
		Client[] client = new Client[NB_CLIENTS]; 
		Thread[] tr_c = new Thread[NB_CLIENTS];
		
		
		for(int i = 0; i < NB_CLIENTS; i++) {
			client[i] = new Client(serv);
			tr_c[i] = new Thread(client[i]);
			tr_c[i].start();			
		}
				
		for(int i = 0; i < NB_CLIENTS; i++) {
			try {
				tr_c[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		tr_s.interrupt();
	}

}
