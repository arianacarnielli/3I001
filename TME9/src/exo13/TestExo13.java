import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

//avec Lisa Kacel 3702697

public class TestExo13 {
	static int tailleFile;
	static int nbClients;
	public static void main(String[] args) {
		
		try {
			tailleFile = Integer.parseInt(args[0]);
			nbClients = Integer.parseInt(args[1]);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Arguments requis, taille de la file, nombre de clients");
			return;
		}
		//File de requetes
		ArrayBlockingQueue<Requete> file = new ArrayBlockingQueue<Requete>(tailleFile, true);
		
		//Cree le groupe du Serveur
		ThreadGroup gServeur = new ThreadGroup("serveur");
		UsineThread uServeur = new UsineThread(gServeur);
		ExecutorService eServeur = Executors.newSingleThreadExecutor(uServeur);
		
		//Cree le groupe des Clients
		ThreadGroup gClients = new ThreadGroup("clients");
		UsineThread uClients = new UsineThread(gClients);
		ExecutorService eClients = Executors.newFixedThreadPool(5, uClients);
		
		//Lancer le Serveur
		eServeur.execute(new Serveur(file, eClients));
		
		//Lancer les Clients
		for(int i = 0; i < nbClients; i++) {
			eClients.execute(new Client(file));
		}
		
		eClients.shutdown();
		eServeur.shutdown();
		
	}
}
