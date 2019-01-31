import java.util.Random;

public class MoteurVitre2 implements Runnable {
	private Cote c;
	private Position p;
	private Operation o;
	public static Object cap = new Object();
	
	public MoteurVitre2(Cote c){
		this.c = c;	
		this.p = Position.HAUTE;
		this.o = Operation.NIL;
	}
	public Position getPosition() {
		return this.p;		
	}
	
	public synchronized void demander(Operation o) {
		System.out.println("Operation : " + o + " vitre " + this.c);
		this.o = o;
		notifyAll();
	}
	
	private void attente() {
		int i = new Random().nextInt(200);
		System.out.println("il faut attendre " + i + " pour terminer l'operation sur le vitre " + this.c);
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
						synchronized (cap) {
							this.p = Position.HAUTE;
							System.out.println("Le vitre " + this.c + " est en position " + this.p);
							cap.notifyAll();
						}
						
					}
				break;
			
				case DESCENDRE:
					if(this.p == Position.HAUTE) {
						this.attente();
						synchronized (cap) {
							this.p = Position.BASSE;
							System.out.println("Le vitre " + this.c + " est en position " + this.p);
							cap.notifyAll();
						}

					}
				break;
				
			}
			this.o = Operation.NIL;
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				System.out.println("Moteur " + this.c + " desactive");
				return;
			}
			
		}
	}

}
