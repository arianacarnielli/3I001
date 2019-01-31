public class GestionnaireCapote2 {
	public static void main(String[] args) {
		
		
		MoteurVitre2 v1 = new MoteurVitre2(Cote.GAUCHE);
		MoteurVitre2 v2 = new MoteurVitre2(Cote.DROITE);
		Object verrou = MoteurVitre2.cap;
		
		Thread t1 = new Thread(v1);
		Thread t2 = new Thread(v2);

		t1.start();
		t2.start();
		
		v1.demander(Operation.DESCENDRE);
		v2.demander(Operation.DESCENDRE);

		synchronized (verrou){
			while((v1.getPosition() == Position.HAUTE) || (v2.getPosition() == Position.HAUTE)) {
				try {
					System.out.println("On attend la descente des verres");
					verrou.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Pliage de la capote complete.");

		v1.demander(Operation.MONTER);
		v2.demander(Operation.MONTER);
		
		synchronized (verrou){
			while((v1.getPosition() == Position.BASSE) || (v2.getPosition() == Position.BASSE)) {
				try {
					System.out.println("On attend la monte des verres");
					verrou.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		t1.interrupt();
		t2.interrupt();
		
	}

}