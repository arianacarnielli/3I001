import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// Question 1
// Integer.parseInt(String s);


public class MatriceEntiere{
	private int lignes;
	private int colonnes;
	private int matrice [][];

	public MatriceEntiere(int l, int c){
		this.lignes = l;
		this.colonnes = c;
		this.matrice = new int[l][c];
	}

	public MatriceEntiere(File fichier) throws FileNotFoundException{
		Scanner in = new Scanner(new BufferedReader(new FileReader(fichier)));

		this.lignes = in.nextInt();
		this.colonnes = in.nextInt();
		this.matrice = new int[this.lignes][this.colonnes];
		
		int i,j;
		
		for (i = 0; i < this.lignes; i++) {
			for(j = 0; j < this.colonnes; j++) {
				this.matrice[i][j] = in.nextInt();
			}
		}
		in.close();
	}

	public int getElem(int i, int j){
		return this.matrice[i][j];
	}

	public void setElem(int i, int j, int val){
		this.matrice[i][j] = val;
	}
	
	public int getNbColonnes() {
		return this.colonnes;
	}
	
	public int getNbLignes() {
		return this.lignes;
	}

	public String toString() {
		String s1 = "La matrice est:\n" + this.lignes + " x " + this.colonnes + "\n";
		int i, j;
		for(i = 0; i < this.lignes; i++) {
			for(j = 0; j < this.colonnes; j++) {
				s1 = s1 + this.matrice[i][j] + " ";
			}
			s1 = s1 + "\n";
		}
		return s1;
	}

	public void afficher() {
		System.out.println(this);
	}
	
	public void setZero() {
		int i, j;
		for (i = 0; i < this.lignes; i++) {
			for (j = 0; j < this.colonnes; j++) {
				this.matrice[i][j] = 0;
			}
		}		
	}
	
	public MatriceEntiere somme(MatriceEntiere m1) throws TaillesNonConcordantesException{
		int i = m1.getNbLignes();
		int j = m1.getNbColonnes();
		
		if ((i != this.lignes)||(j != this.colonnes)){
			throw new TaillesNonConcordantesException("Taille non concordante");
		}
		
		MatriceEntiere m = new MatriceEntiere(i, j);
		
		for(i = 0; i < this.lignes; i++){
			for(j = 0; j < this.colonnes; j++){
				m.setElem(i, j, this.matrice[i][j] + m1.getElem(i, j));
			}
		}		
		return m;
	}
	
	
	public MatriceEntiere multiEntiere(int entier) {
		MatriceEntiere m = new MatriceEntiere(this.lignes, this.colonnes);
		for(int i = 0; i < this.lignes; i++){
			for(int j = 0; j < this.colonnes; j++){
				m.setElem(i, j, this.matrice[i][j] * entier);
			}
		}
		return m;
	}
	
	
	public MatriceEntiere transposee(){
		MatriceEntiere m = new MatriceEntiere(this.colonnes, this.lignes);
		for(int i = 0; i < this.colonnes; i++){
			for(int j = 0; j < this.lignes; j++){
				m.setElem(i, j, this.matrice[j][i]);
			}
		}		
		return m;
	}

	
	public MatriceEntiere multi(MatriceEntiere m1) throws TaillesNonConcordantesException{
		int i = m1.getNbLignes();
		int j = m1.getNbColonnes();
		
		if (this.colonnes != i){
			throw new TaillesNonConcordantesException("Taille non concordante");
		}
		
		MatriceEntiere m = new MatriceEntiere(this.getNbLignes(), j);
		m.setZero();
		
		for(i = 0; i < this.lignes; i++){
			for(j = 0; j < m1.getNbColonnes(); j++){
				for(int k = 0; k < this.colonnes; k++) {
					m.setElem(i, j, m.getElem(i, j) + (this.matrice[i][k] * m1.getElem(k, j)));
				}
			}
		}		
		return m;
	}
	
	static public int produitLigneColonne(MatriceEntiere m1, int i, MatriceEntiere m2, int j) throws TaillesNonConcordantesException{
		int taille = m1.getNbColonnes();
		int res = 0;
		
		if (taille != m2.getNbLignes()){
			throw new TaillesNonConcordantesException("Taille non concordante");
		}

		for(int k = 0; k < taille; k++) {
			res = res + (m1.getElem(i,k) * m2.getElem(k, j));
		}		
		return res;
	}
	
	
	
}