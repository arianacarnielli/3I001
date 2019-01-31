import java.util.Random;

public class TestReservation2 {

	public static void main(String[] args) {

		int quant = 8;
		int rang = 10;
		int placesRang = 10;
		
		//quantite aleatoire de personnes en chaque groupe				
		Random gen = new Random();
		
		Groupe2[] g = new Groupe2[quant];
		Salle2 s = new Salle2 (rang, placesRang);
		Thread[] th = new Thread[quant]; 
		
		System.out.println("avant les reservations : " + "\n" + s.toString());
		
		for (int i = 0; i < quant; i++) {
			//entre 1 et 10 personnes par groupe
			g[i] = new Groupe2(gen.nextInt(10) + 1, s);
			System.out.println(g[i].toString());
		}


		for(int i = 0; i < quant; i++) {
			th[i] = new Thread(g[i]);
			th[i].start();
		}

		//pour que l'affichage e et l'annuler soient correctes
		for(int i = 0; i < quant; i++) {
			try {
				th[i].join();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

		System.out.println("après les reservations : \n" + s.toString());
		
		//test fonction annuler(n)
		for(int i = 0; i < quant; i++) {
			s.annuler(3, g[i]);
		}
		
		System.out.println("Après les annulations : \n" + s.toString());
		
		//on refait la reservation pour voir si le comportament est ok
		th[0] = new Thread(g[0]);
		th[0].start();
		
		try {
			th[0].join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		System.out.println("Après la re-reservation pour le groupe 0 : \n" + s.toString());
	
	}

}

