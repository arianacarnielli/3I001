
public class Barbier implements Runnable{
	private Salon salon;
	
		
	public Barbier(Salon salon) {
		this.salon = salon;
	}


	@Override
	public void run() {
		try {
			salon.getClient();
		} catch (InterruptedException e) {
			System.out.println("Je suis le barbier et je m'en vais");

		}
	}

}
