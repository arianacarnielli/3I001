import java.util.Random;

public class TestSalon {

	public static void main(String[] args) {

		Salon s = new Salon(5);
		
		Barbier b = new Barbier(s);
		
		Random gen = new Random();
		int quant = gen.nextInt(20) + 1;
		
		System.out.println("quantité de clients : " + quant );
		
		Client [] clients = new Client[quant];
		
		for (int i = 0; i < quant; i++) {
			clients[i] = new Client(s);
		}
		
		Thread barb = new Thread(b);
		
		Thread [] cl = new Thread[quant];
		for (int i = 0; i < quant; i++) {
			cl[i] = new Thread(clients[i]);
		}
		
		barb.start();
		
		for (int i = 0; i < quant; i++) {
			cl[i].start();
		}
		
		for (int i = 0; i < quant; i++) {
			try {
				cl[i].join();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		
		barb.interrupt();
	}
}
