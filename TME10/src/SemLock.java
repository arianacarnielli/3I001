import java.util.concurrent.Semaphore;

public class SemLock {
	private final Semaphore lock;
	
	public SemLock() {
		lock = new Semaphore(1,true);
	}
	
	public void lock() {
		try {
			lock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void unlock() {
		lock.release();
	}
	
}
