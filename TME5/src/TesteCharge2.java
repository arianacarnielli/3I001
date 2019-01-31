import java.util.Random;
public class TesteCharge2 {
	public static void main(String[] args) {
		Random gen = new Random();
		AleaStock stock = new AleaStock(50);
		
		for (int i = 0; i < stock.getTaille(); i++) {
			stock.ajouterStock(new AleaObjet(gen.nextInt(20)+10, 50));
		}

		stock.affichage();
		
		Chariot chariot = new Chariot(1000, 10);
		
		Chargeur chargeur = new Chargeur(stock, chariot);
		Dechargeur dechar = new Dechargeur(chariot);
		
		Thread th1 = new Thread(chargeur);
		Thread th2 = new Thread(dechar);
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	
		stock.affichage();
		
		chariot.affichage();
		
		
		
	}
}


