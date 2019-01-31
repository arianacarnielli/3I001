import java.awt.Point;
import java.util.ArrayList;

public class Groupe2 implements Runnable {
	private static int cpt = 0;  
	private int id;
	private int nb_pers;
	private ArrayList<Point> places;
	
	// On utilise Point pour garder les coordonnees x, y de la position dans la salle de chaque personne du groupe.
	private Salle2 s1;
	
	public Groupe2 (int nb, Salle2 s1) {
		this.id = cpt;
		cpt++;
		this.nb_pers = nb;		
		this.s1 = s1;
		this.places = new ArrayList<Point>(this.nb_pers);
	}
	
	public void run() {
		boolean s = s1.reserver(this);
		System.out.println("g"+this.id + " : " + s + this.places);
		
	}
	
	public String toString() {
		String s = "g";
		s += this.id;
		s += " : ";
		s += this.nb_pers;
		s += " personnes";
		return s;
	}
	
	public int getNbPers() {
		return this.nb_pers;
	}
	
	public int getNbManque() { //voir combien de places sont pas reservés par le groupe
		return this.nb_pers - this.places.size();
	}
	
	public void addPlace(int i, int j) {
		Point p = new Point(i, j);
		this.places.add(p);
	}
	
	public Point removeUnPlace() {
		return this.places.remove(0); 
	}
	
	public int getPlacesTaille() {
		return this.places.size();
	}
}
