import java.util.concurrent.Semaphore;

public class SemCondition {
	private final Semaphore cond;
	private SemLock lock;
	
	public SemCondition(SemLock lock) {
		cond = new Semaphore(0,true);
		this.lock = lock;
	}
	
	public void await() throws InterruptedException {
		lock.unlock();
		cond.acquire();
		lock.lock();
	}
	
	public void signal() {
		cond.release();
	}
}
