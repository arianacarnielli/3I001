public class GestionnaireCapote {
	public static void main(String[] args) {
		
		MoteurVitre v1 = new MoteurVitre(Cote.GAUCHE);
		MoteurVitre v2 = new MoteurVitre(Cote.DROITE);
		
		Thread t1 = new Thread(v1);
		Thread t2 = new Thread(v2);

		t1.start();
		t2.start();
		
		v1.demander(Operation.DESCENDRE);
		v2.demander(Operation.DESCENDRE);

		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		v1.demander(Operation.MONTER);
		v2.demander(Operation.MONTER);
	
	
	}

}
