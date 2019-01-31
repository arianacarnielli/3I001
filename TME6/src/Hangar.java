//avec 3702697

public class Hangar {
	private static int cpt = 0;
	private int id;
	private int loco;
	
	public Hangar() {
		this.id = cpt;
		cpt++;
		this.loco = -1;
	}
	
	public void entrer (int idLoco) {
		this.loco = idLoco;
		System.out.println("Le train " + this.loco + " est gare au hangar " + (this.id + 1));
	}
	
	public boolean estOccupe() {
		return this.loco != -1;
	}
	
}
