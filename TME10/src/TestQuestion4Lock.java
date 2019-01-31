import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class TestQuestion4Lock {
	static int nbBab;
	public static void main(String[] args) {
		try {
			nbBab = Integer.parseInt(args[0]);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Arguments requis, quantite de babouins");
			return;
		}
		
		Corde c = new CordeLock5();
		
		//Cree le groupe des Babouins (pour s'entrainer ;D)
		ThreadGroup gBab = new ThreadGroup("babouins");
		UsineThread uBab = new UsineThread(gBab);
		ExecutorService eBab = Executors.newFixedThreadPool(nbBab, uBab);
				
		for(int i = 0; i < nbBab; i++) {
			eBab.execute(new Babouin(c));
		}
		 eBab.execute(new Kong(c));
			
		eBab.shutdown();
	}
}
