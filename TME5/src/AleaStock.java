import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class AleaStock {
	private int taille;
	private ArrayList<AleaObjet> stock;
	private final ReentrantLock lock = new ReentrantLock();
	
	public AleaStock(int taille) {
		this.taille = taille;
		this.stock = new ArrayList<AleaObjet>(this.taille);
	}
	
	public void ajouterStock(AleaObjet obj) {
		lock.lock();
		try {
			if (this.taille > this.stock.size()) {
				this.stock.add(obj);
			}
		}finally {
			lock.unlock();
		}
	}
	
	public AleaObjet sorterStock() {
		lock.lock();
		try{	
			if (!this.stock.isEmpty()) {
				AleaObjet res = this.stock.remove(0);
				return res;
			}else {				
				return null;
			}	
		}finally {
			lock.unlock();
		}
	}
	public int getTaille() {
		return this.taille;
	}

	public void affichage() {
		System.out.println("La taille du stock est : " + this.taille);
		lock.lock();
		try {
			if(!this.stock.isEmpty()) {
				for(int i = 0; i < this.stock.size(); i++) {
					stock.get(i).affichage();
				}
			}
		}finally {
			lock.unlock();
		}
	}
}
