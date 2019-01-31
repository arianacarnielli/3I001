public class CordeSem5 implements Corde {
	private final SemLock lock = new SemLock();
	private final SemCondition accesOK = new SemCondition(lock);
	private Position sensUtil;
	private int cpt;
	
	public CordeSem5() {
		sensUtil = null;
		cpt = 0;
	}
	
	public void acceder(Position sens) throws InterruptedException{
		lock.lock();
		try {
			while(((sensUtil != null) && (sensUtil != sens)) || (cpt >= 5) ) {
				accesOK.await();
				accesOK.signal();
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
			accesOK.signal();
		}finally {
			lock.unlock();
		}
	}
	

	public void accederKong(Position sens) throws InterruptedException {
		lock.lock();
		try {
			while(((sensUtil != null) && (sensUtil != sens)) || (cpt > 0) ) {
				accesOK.await();
				accesOK.signal();
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
			accesOK.signal();
		}finally {
			lock.unlock();
		}
	}
}
