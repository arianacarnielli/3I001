//avec Lisa Kacel 3702697 

public class TestExo10 {
	static int tailleDonnees;
	public static void main(String[] args) {
		try {
			tailleDonnees = Integer.parseInt(args[0]);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Argument requis, taille max des donnees");
			return;
		}
		
		System.out.println("Taille max des donnees : " + tailleDonnees);
		
		EnsembleDonnees ed = new EnsembleDonnees();
		
		Thread[] l = new Thread[2];
		Thread[] e = new Thread[2];
		Thread[] p = new Thread[2];
		
		for(int i = 0; i < 2; i++) {
			l[i] = new Thread(new Lecteur(ed)); 
			e[i] = new Thread(new Effaceur(ed, tailleDonnees)); 
			p[i] = new Thread(new Producteur(ed, tailleDonnees)); 
			l[i].start();
			e[i].start();
			p[i].start();
		
		}
		
		for(int i = 0; i < 2; i++) {
			try {
				l[i].join();
				e[i].join();
				p[i].join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		System.out.println(ed);
		System.out.println("Le main termine");
	}
}
