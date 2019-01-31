//avec 3702697
public class TesteGarage {

	public static void main(String[] args) {

		int n = 5;
		
		SegAccueil sA = new SegAccueil();
		PoolHangars pH = new PoolHangars(n);
		SegTournant sT = new SegTournant(pH);
		
		Loco[] tabL = new Loco[n];
		Thread[] tabTL = new Thread[n];
		Thread tsT = new Thread(sT);
		tsT.start();
		
		for(int i = 0; i < n; i++) {
			tabL[i] = new Loco(sA, sT, pH);
			tabTL[i] = new Thread(tabL[i]);
			tabTL[i].start();

		}
	}

}
