import java.util.Random;

public class TestReservation1 {

	public static void main(String[] args) {

		int quant = 8;
		int rang = 10;
		int placesRang = 10;
		
		//quantite aleatoire de personnes en chaque groupe
		Random gen = new Random();
		
		Groupe[] g = new Groupe[quant];
		Salle s = new Salle (rang, placesRang);
		Thread[] th = new Thread[quant]; 
		
		System.out.println(s.toString());
		
		for (int i = 0; i < quant; i++) {
			//entre 1 et 10 personnes par groupe
			g[i] = new Groupe(gen.nextInt(10) + 1, s);
			System.out.println(g[i].toString());
		}


		for(int i = 0; i < quant; i++) {
			th[i] = new Thread(g[i]);
			th[i].start();
		}
		// pour que l'affichage soit correcte
		for(int i = 0; i < quant; i++) {
			try {
				th[i].join();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

		System.out.println(s.toString());
	}

}
