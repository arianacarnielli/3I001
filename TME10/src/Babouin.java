import java.util.Random;

public class Babouin implements Runnable{
	public final int id;
	protected static int cpt = 1;
	protected static Object obj = new Object();
	protected static Random gen = new Random(); 
	protected Corde laCorde;
	protected Position position;
	
	public Babouin(Corde c) {
		this.laCorde = c;
		this.position = (gen.nextInt(2) == 1) ? Position.SUD : Position.NORD ;
		synchronized(obj) {
			this.id = cpt;
			cpt++;
		}
		System.out.println(this.toString() + " cree");
		
	}
	
	protected void traverser() {
		try {
			Thread.sleep(gen.nextInt(1000));
		}catch(InterruptedException e) {
			System.out.println("Babouin interrompu, ne doit pas arriver");
		}
	}
	
	public String toString() {
		return "Babouin " + this.id + " sens " + this.position;
	}
	
	
	public void	run() {
		try	{
			Thread.sleep(gen.nextInt(1000));
			laCorde.acceder(position);
			System.out.println(this.toString() + " a pris la corde.");
			traverser();
			System.out.println(this.toString() + " est arrive.");
			laCorde.lacher(position);
		}catch (InterruptedException e) {
			System.out.println("Pb babouin !");
		}
	}

}
