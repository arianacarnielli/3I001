
public class Kong extends Babouin {
	private String kong = "kong";
	
	public Kong(Corde c) {
		super(c);
	}

	public String toString() {
		return "kong sens " + this.position;
	}

	public void	run() {
		try	{
			laCorde.accederKong(position);
			System.out.println(this.toString() + " a pris la corde.");
			traverser();
			System.out.println(this.toString() + " est arrive.");
			laCorde.lacherKong(position);
		}catch (InterruptedException e) {
			System.out.println("Pb babouin !");
		}
	}
}
