import java.util.Random;

public class MoteurVitre implements Runnable {
	private Cote c;
	private Position p;
	private Operation o;
	
	public MoteurVitre(Cote c){
		this.c = c;	
		this.p = Position.HAUTE;
		this.o = Operation.NIL;
	}
	public Position getPosition() {
		return this.p;		
	}
	
	public synchronized void demander(Operation o) {
		System.out.println("Operation : " + o);
		this.o = o;
		notifyAll();
	}
	
	private void attente() {
		int i = new Random().nextInt(200);
		System.out.println("il faut attendre " + i + " pour terminer l'operation");
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public synchronized void run() {
		while(true) {

			switch(this.o) {
				case MONTER:
					if(this.p == Position.BASSE) {
						this.attente();
						this.p = Position.HAUTE;
						System.out.println("Le vitre " + this.c + " est en position " + this.p);
					}
				break;
			
				case DESCENDRE:
					if(this.p == Position.HAUTE) {
						this.attente();
						this.p = Position.BASSE;
						System.out.println("Le vitre " + this.c + " est en position " + this.p);
					}
				break;
				
			}
			this.o = Operation.NIL;
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
