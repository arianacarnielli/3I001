//avec Lisa Kacel 3702697

public class TestRestaurant {
	static int gClients;
	static int nbTables;
	static int tGroupes;
	public static void main(String[] args) {
		try {
			gClients = Integer.parseInt(args[0]);
			nbTables = Integer.parseInt(args[1]);
			tGroupes = Integer.parseInt(args[2]);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Arguments requis, quantite de groupes de clients, nombre de tables et taille max des groupes");
			return;
		}
		
		Restaurant resto = new Restaurant(nbTables);
		GroupeClients[] groupes = new GroupeClients[gClients];
				
		for(int i = 0; i < gClients; i++) {
			groupes[i] = new GroupeClients(resto, tGroupes);			
		}
		
	}

}
