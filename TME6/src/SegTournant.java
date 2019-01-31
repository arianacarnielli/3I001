//avec 3702697

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;


public class SegTournant implements Runnable {
	private PoolHangars pHangars;
	private int position;
	
	private boolean appele;
	private boolean deplacementJusteFini;
	private boolean vientDeRentrer;
	private boolean vientDeSortir;

	
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition pasAppele = lock.newCondition();
	private final Condition pasSorti = lock.newCondition();
	
	
	
	public SegTournant(PoolHangars pH) {
		this.pHangars = pH;
		this.position = 0;
		
		this.appele = false;
		this.deplacementJusteFini = false;
		this.vientDeRentrer = false;
		this.vientDeSortir = false;
	}
	
	public int getPosition() {
		return this.position - 1;
	}
	
	public void attendreAppel() throws InterruptedException {
		lock.lock();
		System.out.println("Le segment tournant attend un appel");
		try {
			while(!this.appele) {
				pasAppele.await();
			}
			System.out.println("Le segment tournant a ete appele");
		}finally {
			lock.unlock();
		}
	}
	
	public void appeler(int id) throws InterruptedException {
		lock.lock();
		System.out.println("Je suis le train : " + id + " et je veux appeler le segment tournant");
		try {
			while(this.appele) {
				pasSorti.await();
			}
			System.out.println("Je suis le train : " + id + " et j'ai appele le segment tournant");
			this.appele = true;
			this.position = 0;
			pasAppele.signalAll();
		}finally {
			lock.unlock();
		}
	}
	
	public void seDeplacer() throws InterruptedException {
		lock.lock();
		System.out.println("Le segment tournant se deplace a : " + this.position);
		try {
			pasSorti.await(1, TimeUnit.SECONDS);
			pasSorti.signalAll();
			this.deplacementJusteFini = true;
			System.out.println("Le segment tournant est a : " + this.position);
		}finally {
			lock.unlock();
		}
	}
	
	public void attendrePositionOK(int id) throws InterruptedException {
		lock.lock();
		System.out.println("Je suis le train : " + id + " et j'attend que le segment tournant se positionne");
		try {
			while(!this.deplacementJusteFini) {
				pasSorti.await();
			}
			this.deplacementJusteFini = false;
		}finally {
			lock.unlock();
		}
	}
	
	public void entrer(int id) {
		lock.lock();
		try {
			position = this.pHangars.hangarLibre() + 1;	
			System.out.println("Je suis le train : " + id + " et je rentre dans le segment tournant pour aller au hangar : " + this.position);
			pasSorti.signalAll();
			this.vientDeRentrer = true;
		}finally {
			lock.unlock();
		}	
	}

	public void attendreEntree() throws InterruptedException {
		lock.lock();
		System.out.println("Je suis le segment tournant et j'attend l'entree d'un train");
		try {
			while(!this.vientDeRentrer) {
				pasSorti.await();
			}
			this.vientDeRentrer = false;
		}finally {
			lock.unlock();
		}
	}
	
	public void sortir(int id) {
		lock.lock();
		try {
			System.out.println("Je suis le train : " + id + " et je sort du segment tournant");
			pasSorti.signalAll();
			this.vientDeSortir = true;
		}finally {
			lock.unlock();
		}	
	}

	public void attendreVide() throws InterruptedException {
		lock.lock();
		System.out.println("Je suis le segment tournant et j'attend la sortie d'un train");
		try {
			while(!this.vientDeSortir) {
				pasSorti.await();
			}
			this.vientDeSortir = false;
			this.appele = false;
			pasSorti.signalAll();
		}finally {
			lock.unlock();
		}
	}
	
	public void	run() {
		try	{
			while(true) {
				attendreAppel();
				seDeplacer();
				attendreEntree();
				seDeplacer();
				attendreVide();
			}
		}catch(InterruptedException e) {
			System.out.println("Terminaison sur interruption du segment tournant");
		}
	}

}
