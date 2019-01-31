import java.util.Random;

public class TesteCharge1 {
	public static void main(String[] args) {
		Random gen = new Random();
		AleaStock stock = new AleaStock(150);
		
		for (int i = 0; i < stock.getTaille(); i++) {
			stock.ajouterStock(new AleaObjet(gen.nextInt(20)+10, 50));
		}

		stock.affichage();
		
		Chariot chariot = new Chariot(1000, 50);
		
		Chargeur chargeur = new Chargeur(stock, chariot);
		
		Thread th = new Thread(chargeur);
		
		th.start();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	
		stock.affichage();
		
		chariot.affichage();
		
		
		
	}
}
