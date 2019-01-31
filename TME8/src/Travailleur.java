//avec Lisa Kacel 3702697

import java.util.Random;

public class Travailleur implements Runnable{
	private ReponseRequete rr;
	private int type;
	private static Random gen = new Random();
	
	public Travailleur(ReponseRequete rr, int type) {
		this.rr = rr;
		this.type = type;
	}
	
	public void run() {
		try {
			if (type == 1) {
				Thread.sleep(gen.nextInt(gen.nextInt(5000)));	
			}
			if(type == 2) {
				while(true) {
					Thread.sleep(gen.nextInt(1000));		
				}
			}
			rr.cl.requeteServie(rr);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	

	}
	

}


