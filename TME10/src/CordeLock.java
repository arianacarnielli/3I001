import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CordeLock implements Corde {
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition accesOK = lock.newCondition();
	private Position sensUtil;
	private int cpt;
	
	public CordeLock() {
		sensUtil = null;
		cpt = 0;
	}
	
	public void acceder(Position sens) throws InterruptedException{
		lock.lock();
		try {
			while((sensUtil != null) && (sensUtil != sens)) {
				accesOK.await();
			}
			this.cpt++;
			if(sensUtil == null) {
				sensUtil = sens;
			}
		}finally {
			lock.unlock();
		}
	}
	
	public void lacher(Position sens) {
		lock.lock();
		try {
			this.cpt--;
			if(this.cpt == 0) {
				sensUtil = null;
			}
			accesOK.signalAll();
		}finally {
			lock.unlock();
		}
	}

	public void accederKong(Position sens) throws InterruptedException {
		System.out.println("Ne doit pas etre appele!");
	}

	public void lacherKong(Position sens) {
		System.out.println("Ne doit pas etre appele!");
	}

}
