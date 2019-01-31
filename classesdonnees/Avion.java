	// Donnez la réponse à la question 2 ici

public class Avion{

	private int nbRangees;
	private int nbSieges;
	private int etat[];

		// A compléter

	public Avion(int nbRangees, int nbSieges){
			// A compléter
		this.nbRangees=nbRangees;
		this.nbSieges=nbSieges;
		etat=new int[nbRangees];
		for(int i=0;i<nbRangees;i++){
			etat[i]=-nbSieges-1;
		}	
	}

	public int getNbRangees(){
		return nbRangees;
	}

	public void attendreEnregistrement(int rangee){
		// A compléter
	}

	public void enregistrerPassager(int rangee){
		// A compléter
	}

	public void attendreAutorisation(int rangee){
		// A compléter
	}

	public void autoriserEmbarquement(int rangee){
		// A compléter
	}

	public void attendreEmbarquement(int rangee){
		// A compléter
	}

	public void terminerEmbarquement(int rangee){
		// A compléter
	}

}
