//avec Lisa Kacel 3702697 

import java.util.Random;

public class Producteur implements Runnable {
	private static int cpt = 1;
	private final int id;
	private EnsembleDonnees ed;
	private Random gen; 
	private int tailleMax;
	private static final Object obj = new Object();
	
	public Producteur(EnsembleDonnees ed, int td) {
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
		try {
			for (int i = 0; i < 3; i++) {
				trace("je commence la serie d'ajouts "+ (i+1));
				for(int j = 0; j < 4; j++) {
					int alea = gen.nextInt(tailleMax);
					ed.ajouter(alea);
					trace("J'ajoute " + alea);
					Thread.yield();
				}
				Thread.sleep(1000);
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		trace("Je termine");
	}
	
	public void trace(String s) {
		System.out.println("Je suis le Producteur " + id + " et " + s);
	}
	public void trace2(String s) {
		System.out.println("Je suis le Producteur " + id + " et " + s);
		Thread.yield();
	}
}
