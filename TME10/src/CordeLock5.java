import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CordeLock5 implements Corde {
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition accesOK = lock.newCondition();
	private Position sensUtil;
	private int cpt;
	
	public CordeLock5() {
		sensUtil = null;
		cpt = 0;
	}
	
	public void acceder(Position sens) throws InterruptedException{
		lock.lock();
		try {
			while(((sensUtil != null) && (sensUtil != sens)) || (cpt >= 5) ) {
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
	
	public void accederKong(Position sens) throws InterruptedException{
		lock.lock();
		try {
			while(((sensUtil != null) && (sensUtil != sens)) || (cpt > 0) ) {
				accesOK.await();
			}
			this.cpt = 5;
			if(sensUtil == null) {
				sensUtil = sens;
			}
		}finally {
			lock.unlock();
		}
	}
	
	public void lacherKong(Position sens) {
		lock.lock();
		try {
			this.cpt = 0;
			if(this.cpt == 0) {
				sensUtil = null;
			}
			accesOK.signalAll();
		}finally {
			lock.unlock();
		}
	}
}
