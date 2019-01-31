import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Chariot {
	private int poidsMax;
	private int nbMax;
	private int poids;
	private ArrayList<AleaObjet> march; 
	
	private boolean rempli;
	private boolean stockfini;
	
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition plein = lock.newCondition();
	private final Condition vide = lock.newCondition();
	
	public Chariot (int poidsMax, int nbMax) {
		this.nbMax = nbMax;
		this.poids = 0;
		this.poidsMax = poidsMax;
		this.march = new ArrayList<AleaObjet>(this.nbMax);
		this.rempli = false;
		this.stockfini = false;
	}
	
	public boolean ajouterMarch(AleaObjet obj) throws InterruptedException {
		lock.lock();
		try {
			while(rempli) {
				plein.await();
			}
			if ((march.size() < nbMax) && (poids + obj.getPoids() < poidsMax)){
				march.add(obj);
				poids+=obj.getPoids();
				return true;
			}else {
				this.rempli = true;
				vide.signalAll();
				return false;
			}
		}finally {
			lock.unlock();
		}
	}
	
	public void retirerToutMarch() throws InterruptedException {
		lock.lock();
		AleaObjet obj;
		try {
			while(!stockfini) {
				while((!rempli) && (!stockfini)){
					vide.await();
				}
				while(!this.march.isEmpty()){
					obj = this.march.remove(0);
					this.poids-=obj.getPoids();
				}
				this.rempli = false;
				plein.signalAll();
			}
		}finally {
			lock.unlock();
		}
	}
	
	public void setStockFini() {
		lock.lock();
		try{
			stockfini = true;
			vide.signalAll();
		}finally {
			lock.unlock();
		}
		
	}
	
	public void affichage() {
		System.out.println("poidsMax du chariot : " + this.poidsMax + " nbMax du chariot : " + this.nbMax);
		int i = 0;
		if(!this.march.isEmpty()) {
			for(i = 0; i < march.size(); i++) {
				march.get(i).affichage();
			}
		}
		System.out.println("Poids dans le chariot : " + this.poids + " Il y a " + i + " objets dans le chariot.");
	}
	
}
