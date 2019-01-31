// Question 1
// Integer.parseInt(String s);


public class MatriceEntiere{
	private int lignes
	private int colonnes
	private int matrice [][]

//Question 2

	public MatriceEntiere(int l, int c){
		this.lignes = l;
		this.colonnes = c;
		this.matrice = new int[l][c];
	}

	public MatriceEntiere(File fichier){
		BufferedReader in = new BufferedReader(new FileReader(fichier));
		this.lignes=Integer.parseInt(in.readLine());
		this.colonnes=Integer.parseInt(in.readLine());

	}

	int getElem(int i, int j){
		return this.matrice[i][j];
	}

	void setElem(int i, int j, int val){
		this.matrice[i][j] = val;
	}

}