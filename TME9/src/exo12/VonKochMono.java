//avec Lisa Kacel 3702697

import graphic.Window;
import java.awt.Point;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VonKochMono {
	private final static double LG_MIN = 8.0;
	Window f;
	//private ExecutorService pool = Executors.newFixedThreadPool(50);
	private ExecutorService pool = Executors.newWorkStealingPool();

	public VonKochMono (Window f, Point a, Point b, Point c) {
		this.f = f;
		
		//Version donnee
/*		new Cote(f, b, a).tracer();
		new Cote(f, a, c).tracer();
		new Cote(f, c, b).tracer();*/
//Question 2 et 3		
/*		new Thread(new Cote(f, b, a)).start();
		new Thread(new Cote(f, a, c)).start();
		new Thread(new Cote(f, c, b)).start();*/
//Question 4
		pool.execute(new Cote(f, b, a, pool));
		pool.execute(new Cote(f, a, c, pool));
		pool.execute(new Cote(f, c, b, pool));
				
	}
}