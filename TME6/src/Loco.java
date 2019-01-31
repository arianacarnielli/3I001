//avec 3702697

public class Loco implements Runnable {
	private SegAccueil sAccueil;
	private SegTournant sTournant;
	private PoolHangars pHangars;
	private int id;
	private static int cpt = 1;
	
	public Loco(SegAccueil sA, SegTournant sT, PoolHangars pH) {
		this.sAccueil = sA;
		this.sTournant = sT;
		this.pHangars = pH;
		this.id = cpt;
		cpt++;
	}
	
	
	public void	run() {
		try	{
			sAccueil.reserver(this.id);
			sTournant.appeler(this.id);
			sTournant.attendrePositionOK(this.id);
			sTournant.entrer(this.id);
			sAccueil.liberer(this.id);
			sTournant.attendrePositionOK(this.id);
			pHangars.getHangar( sTournant.getPosition() ).entrer(this.id);
			sTournant.sortir(this.id);
		}catch(InterruptedException e) {
			System.out.println("Loco " + this.id + " interrompue (ne devrait pas arriver)");
		}
	}
}
