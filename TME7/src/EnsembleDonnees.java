//avec Lisa Kacel 3702697 

import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EnsembleDonnees {
	private ArrayList<Integer> liste;
	private static ReadWriteLock lock;
	
	public EnsembleDonnees() {
		liste = new ArrayList<Integer>();
		lock = new ReentrantReadWriteLock();
	}
	
	public void ajouter(int n) {
		lock.writeLock().lock();
		liste.add(n);
		lock.writeLock().unlock();
	}
	
	public void effacer(int n) throws PasOcurrence{
		lock.writeLock().lock();
		int res = liste.lastIndexOf(n);		

		try {
			if(res == -1) {
			throw new PasOcurrence("Pas d'ocurrence de " + n);
		}else {
			liste.remove(res);
		}

		}finally {
			lock.writeLock().unlock();
		}
	}
	
	public void lire() {
		lock.readLock().lock();
		System.out.println(this);
		lock.readLock().unlock();
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < liste.size(); i++) {
			s+= liste.get(i);
			s+= " ";
		}
		return s;
	}
	
}
