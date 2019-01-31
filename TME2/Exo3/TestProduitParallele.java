import java.io.File;
import java.io.FileNotFoundException;

public class TestProduitParallele {

	public static void main(String[] args) {
		String p1 = "/Infos/lmd/2018/licence/ue/3I001-2018oct/TME/TME1/donnees_produit1";
		MatriceEntiere m_p1;
		
		String p2 = "/Infos/lmd/2018/licence/ue/3I001-2018oct/TME/TME1/donnees_produit2";
		MatriceEntiere m_p2;
		
		File f_p1 = new File(p1);
		File f_p2 = new File(p2);
		
		try {
			m_p1 = new MatriceEntiere(f_p1);
			m_p2 = new MatriceEntiere(f_p2);
			
			m_p1.afficher();
			m_p2.afficher();
			
			MatriceEntiere m_p3 = new MatriceEntiere(m_p1.getNbLignes(), m_p2.getNbColonnes());
			Thread[][] t = new Thread[m_p1.getNbLignes()][m_p2.getNbColonnes()];
			
			for(int i = 0; i < m_p3.getNbLignes(); i++){
				for(int j = 0; j < m_p3.getNbColonnes(); j++){
					t[i][j] = new Thread(new CalculElem(i, j, m_p1, m_p2, m_p3));
					t[i][j].start();
				}
			}
			/* Si on veut etre certain que tous les thread auront fini avant l'affichage.
			for(int i = 0; i < m_p3.getNbLignes(); i++){
				for(int j = 0; j < m_p3.getNbColonnes(); j++){
					t[i][j].join();
				}
			}*/
			m_p3.afficher();
			
		}catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		/* Obligatoire si on use join()
		 * catch(InterruptedException e){
			System.out.println(e.getMessage());
		}*/
	} 
}
