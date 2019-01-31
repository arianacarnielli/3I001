//avec Lisa Kacel 3702697 

public class Lecteur implements Runnable {
	private static int cpt = 1;
	private final int id;
	private EnsembleDonnees ed;
	private static final Object obj = new Object();
	
	public Lecteur(EnsembleDonnees ed) {
		synchronized(obj) {
			this.id = cpt;
			cpt++;
		}
		this.ed = ed;
	}
	
	public void run() {
		trace("Je commence");
		try {
			for(int i = 0; i < 3; i++) {
				trace("Je essaie de lire pour la " + (i+1) + " fois");
				ed.lire();
				Thread.sleep(1000); 
			}
			trace("J'ai terminé!");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void trace(String s) {
		System.out.println("Je suis le lecteur " + id + " et " + s);
	}
}
