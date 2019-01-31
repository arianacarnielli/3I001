import java.util.concurrent.ThreadFactory;

public class UsineThread implements ThreadFactory {
	private ThreadGroup groupe;
	
	public UsineThread(ThreadGroup groupe) {
		this.groupe = groupe;
	}
	
	public Thread newThread(Runnable r) {
		return new Thread(this.groupe, r);
	}
}
