import java.awt.Point;

public class Salle2 {
	private int nbRang;
	private int nbPlacesParRang;
	private boolean [][] placesLibres;
	
	public Salle2(int nbRang, int nbPlaces) {
		this.nbRang = nbRang;
		this.nbPlacesParRang = nbPlaces;
		this.placesLibres = new boolean [nbRang][nbPlacesParRang];	
		for(int i = 0; i < this.nbRang; i++) {
			for(int j = 0; j < this.nbPlacesParRang; j++) {
				this.placesLibres[i][j] =  true;
			}
		}
	}
	
	private boolean capaciteOK(int n) {
		int cpt = 0;
		for(int i = 0; i < this.nbRang; i++) {
			for(int j = 0; j < this.nbPlacesParRang; j++) {
				if (placesLibres[i][j]) {
					cpt++;
				}
				if (cpt == n) {
					return true;
				}
			}
		}
		return false;
	}
	
	private int nContigueAuRangI(int n, int i) {
		int cpt = 0;
		int j = 0;
		while(j < nbPlacesParRang) {
			if (placesLibres[i][j]) {
				cpt++;
				if (cpt == n) {
					int res = j - cpt + 1;
					return res;
				}
			}else {
				cpt = 0;
			}
			j++;
		}
		return -1;
	}
	
	private boolean reserverContigues(Groupe2 g) {
		int val;
		for (int i = 0; i < nbRang; i++) {
			val = this.nContigueAuRangI(g.getNbManque(), i);
			if (val != -1) {
				this.reservetab(i, val, g);
				return true;
			}
		}
		return false;		
	}
	
	synchronized public boolean reserver (Groupe2 g) {
		int n = g.getNbManque();
		if (!this.capaciteOK(n)) {
			return false;
		}
		if (this.reserverContigues(g)) {
			return true;
		}
		int cpt= 0;
		for(int i = 0; i < nbRang; i++) {
			for(int j = 0; j < nbPlacesParRang; j++){
				if(placesLibres[i][j]) {
					this.reser(i, j);
					cpt++;
					g.addPlace(i, j);
				}
				if (cpt == n) {
					return true;
				}
			}
		}
		return true;
	}
	
	private void reser(int i, int j) {
		placesLibres[i][j] = false;
	}
	
	private void reservetab(int i, int j1, Groupe2 g) {
		int j2 = j1 + g.getNbManque();
		for(int j = j1; j < j2; j++) {
			this.reser(i, j);
			g.addPlace(i, j);
		}
	}
	
	public boolean annuler(int i, Groupe2 g) { 
		int nb = g.getPlacesTaille();
		Point p;
		
		//si on essaie d'annuler plus de places que la quantité reservé pour le groupe, rien se passe.
		if (nb < i) {
			return false;
		}
		for(int j = 0; j < i; j++) {
			p = g.removeUnPlace();
			this.placesLibres[p.x][p.y] = true;
		}
		return true;
	}
	
	public String toString() {
		String s="";
		for(int i=0;i<nbRang;i++) {
			for(int j=0;j<nbPlacesParRang;j++) {
				if(placesLibres[i][j]) {
					s+= " 0 ";
				}
				else
					s+=" X ";
				
			}
			s+="\n";
		}
		return s;
	}
	
}
