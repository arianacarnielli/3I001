import java.util.ArrayList;

public class Salon {
	private ArrayList<Client> clients;
	private int nbElements;
	private int nbPlaces;

	
	public Salon(int nbPlaces) {
		
		this.clients =new ArrayList<Client>();
		this.nbElements = 0;
		this.nbPlaces = nbPlaces;
	} 
	
	synchronized public void getClient() throws InterruptedException {
		while(true) {
			
			if (this.nbElements > 0) {
				this.nbElements--;
				Client c = this.clients.remove(0);
				System.out.println("Je suis occupe avec le client :" + c.getId());
				
				synchronized(c) {
					c.setEstCoif();
					c.notifyAll();
				}
			}else {
				System.out.println("J'attend un client");
				this.wait();
			}
		}
	}
	
	public void addClient(Client c) throws InterruptedException {
		synchronized(this) { 
			if (this.getNbPlaces() <= this.getNbElements()) {
				System.out.println("Je suis le client " + c.getId() + " et je ne vais pas attendre");
				return;
			}			
			this.clients.add(c);
			this.nbElements++;
			this.notifyAll();
		
			System.out.println("Je suis le client " + c.getId() + " et je vais attendre le barbier");
		}
		synchronized(c){
			while (!c.getEstCoif()) {
				c.wait();
			}
		}
		
		System.out.println("Je suis le client " + c.getId() + " et j'ai ete coiffe");
	}

	public int getNbElements() {
		return nbElements;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}
	
}
