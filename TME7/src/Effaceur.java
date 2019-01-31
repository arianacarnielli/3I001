//avec Lisa Kacel 3702697 

import java.util.Random;

public class Effaceur implements Runnable {
	private static int cpt = 1;
	private final int id;
	private EnsembleDonnees ed;
	private Random gen; 
	private int tailleMax;
	private static final Object obj = new Object();
	
	public Effaceur(EnsembleDonnees ed, int td) {
		synchronized(obj) {
			this.id = cpt;
			cpt++;
		}
		this.ed = ed;
		this.gen = new Random();
		this.tailleMax = td + 1;
	}
	
	public void run() {
		trace("je commence");
		int i = 0;
		while(i < 3) {
			try {
				int alea = gen.nextInt(tailleMax);
				trace("J'essaie de effacer " + alea);
				ed.effacer(alea);
			}catch(PasOcurrence e) {
				System.out.println(e.getMessage());
			}finally {
				i++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		trace("J'ai fini");
	}
	
	public void trace(String s) {
		System.out.println("Je suis l'Effaceur " + id + " et " + s);
	}
}
