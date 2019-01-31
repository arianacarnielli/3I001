//avec 3702697

public class SegAccueil {

	private boolean estReserve;
	
	public SegAccueil() {
		this.estReserve = false;
	}
	
	public synchronized void reserver(int id) throws InterruptedException {
		System.out.println("Le train : " + id + " veut reserver le segment d'accueil");
		while(this.estReserve) {
			wait();
		}
		this.estReserve = true;
		System.out.println("Le train : " + id + " a reserve le segment d'accueil");
	}
	
	public synchronized void liberer(int id) {
		System.out.println("Le train : " + id + " a libere le segment d'accueil");
		this.estReserve = false;
		notifyAll();
	}
}
