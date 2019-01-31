public class Groupe implements Runnable{
	private static int cpt = 0;  
	private int id;
	private int nb_pers;
	private Salle s1;
	
	public Groupe (int nb, Salle s1) {
		this.id = cpt;
		cpt++;
		this.nb_pers = nb;		
		this.s1 = s1;
	}
	
	public void run() {
		boolean s = s1.reserver(this);
		System.out.println("g"+this.id + " : " + s);
	}
	
	public String toString() {
		String s = "g";
		s += this.id;
		s += " : ";
		s += this.nb_pers;
		s += " personnes";
		return s;
	}
	
	public int getNbPers() {
		return this.nb_pers;
	}
	
	


}
