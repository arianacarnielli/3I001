public class TestAvion{

	private static final int nbRangs=5;
	private static final int nbSieges=4;

	public static void main(String args[]){
		Avion avion = new Avion(nbRangs, nbSieges);
		
		ChefDeBord chef = new ChefDeBord(avion);
		Thread t_chef = new Thread(chef);
		t_chef.start();
		
		Thread[] t_pass = new Thread[nbRangs * nbSieges];
		
		for(int i = 0; i < nbRangs; i++) {
			for(int j = 0; j < nbSieges; j++) {
				Passager p = new Passager(avion, i, j);
				t_pass[i * j] = new Thread(p);
				t_pass[i * j].start();
			}
		}
		
		try {
			t_chef.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("L'embarquement est fini, main dit adieu");
		
	}
}