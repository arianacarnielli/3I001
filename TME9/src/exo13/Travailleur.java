//avec Lisa Kacel 3702697

import java.util.Random;
import java.util.concurrent.Callable;

public class Travailleur implements Callable<Requete>{
	private Requete r_in;
	private static Random gen = new Random();
	
	public Travailleur(Requete r_in) {
		this.r_in = r_in;
		
	}
	public Requete call() {
		this.trace("initialise");
		int res = gen.nextInt(1000);
		try {
			r_in.setRes(res);
			Thread.sleep(res);
		}catch(InterruptedException e) {
			System.out.println("Travailleur Interrompu, ne doit pas arriver");
		}
		this.trace("Je rend le resultat au serveur, res = " + res);
		return r_in;
	}
	
	public void trace(String s) {
		System.out.println("Servant " + r_in + " : " + s);
	}
}
