public class Dechargeur implements Runnable{
	private Chariot chariot;
	
	public Dechargeur(Chariot chariot) {
		this.chariot = chariot;
	}
	
	public void run() {
		try {
			chariot.retirerToutMarch();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
