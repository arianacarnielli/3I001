
public class Client implements Runnable{
	private static int cpt=0;
	private int id ;
	private Salon salon;
	private boolean estCoif;

	public Client(Salon salon) {
		this.id=cpt;
		this.salon = salon;
		cpt++;
		this.estCoif = false;
	}
	
	public int getId() {
		return id;
	}
	
	public void setEstCoif() {
		this.estCoif = true;
	}
	
	public boolean getEstCoif() {
		return this.estCoif;
	}
	
	@Override
	public void run() {
		try {
			salon.addClient(this);
		} catch (InterruptedException e) {
			System.out.println("Je m'en vais");
			e.printStackTrace();
		}
	}


}
