//avec Lisa Kacel 3702697

public class Requete {
	private final Client cl;
	private final int req;
	private int res;

	public Requete(Client cl, int req) {
		this.cl = cl;
		this.req = req;
		this.res = 0;
	}
	
	public void setRes(int res) {
		this.res = res;
	}
	
	public int getRes() {
		return this.res;
	}
	
	public void requeteTraitee() {
		synchronized (cl) {
			cl.requeServ = true;
			cl.notifyAll();
			System.out.println("requete " + req + " resultat " + res);
		}
	}
	
	public String toString() {
		return "requete " + req;
	}
}
