import java.util.Random;

public class AleaObjet {
	private static Random gen = new Random();
	private int poids;
	private int min;
	private int max;
	private static int cpt = 1;
	private int id;
	
	public AleaObjet(int min, int max) {
		this.min = min;
		this.max = max;
		int i = max - min + 1;
		this.poids = gen.nextInt(i) + min ;	
		this.id = cpt;
		cpt++;
	}
	
	public int getPoids() {
		return this.poids;
	}
		
	public int getMin() {
		return this.min;
	}
	
	public int getMax() {
		return this.max;
	}
		
	public String toString() {
		String s = "";
		s+= "Objet : " + this.id + "\nle poids est : " + this.poids + " le poids min est : " + this.min + " le poids max est : " + this.max;
		return s;
	}
	
	public void affichage() {
		System.out.println(this.toString());
	}	
}
