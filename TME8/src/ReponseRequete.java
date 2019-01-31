//avec Lisa Kacel 3702697

import java.util.Random;

public class ReponseRequete {
	public final Client cl;
	private final int requete;
	private int res;
	private static Random gen = new Random(); 
	
	public ReponseRequete(Client cl, int req) {
		this.requete = req;
		this.cl = cl;
		res = gen.nextInt();
	}
	
	public String toString() {
		String s = "id = " + cl.id +" Requete = " + requete + " Resultat = " + res;
		return s;
	}
}
