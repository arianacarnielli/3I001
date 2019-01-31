//avec 3702697

public class PoolHangars {
	private Hangar[] pHangars;
	
	public PoolHangars(int n) {
		this.pHangars = new Hangar[n];
		for (int i = 0; i < n; i++) {
			this.pHangars[i] = new Hangar();
		}
	}
	
	public Hangar getHangar(int i) {
		return this.pHangars[i];
	}
	
	public int hangarLibre() {
		for(int i = 0; i < this.pHangars.length; i++) {
			if(!pHangars[i].estOccupe()) {
				return i;
			}
		}
		System.out.println("ne doit pas arriver : pas de hangar disponible!");
		return -1;
	}
}
