//avec Lisa Kacel 3702697

import graphic.Window;
import java.awt.Point;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Version donnee
/*public class Cote {
	private final static double LG_MIN = 8.0;
	private final Window f;
	private final Point m, n;*/

public class Cote implements Runnable{
	private final static double LG_MIN = 8.0;
	private final Window f;
	private final Point m, n;
//Question 3
	//private ExecutorService pool = Executors.newFixedThreadPool(1);
//Question 4
	private ExecutorService pool;
	
	public Cote (Window f, Point m, Point n, ExecutorService pool) {
		this.f = f;
		this.m = m;
		this.n = n;
//Question 4
		this.pool = pool;
	}
//Version donnée
/*	public void tracer () {
		final double xa, ya, xb, yb, xc, yc;
		if( Segment.longueur(m, n) > LG_MIN ) {
			
			xa = (2*m.x + n.x) / 3.0;
			xc = (m.x + 2*n.x) / 3.0;
			ya = (2*m.y + n.y) / 3.0;
			yc = (m.y + 2*n.y) / 3.0;	
			xb = xa + ( xc - xa - (Math.sqrt(3.0)*(yc - ya)) ) / 2.0;
			yb = ya + ( yc - ya + (Math.sqrt(3.0)*(xc - xa)) ) / 2.0;
			
			Point a = new Point();
			a.setLocation(xa, ya);
			Point b = new Point();
			b.setLocation(xb, yb);
			Point c = new Point();
			c.setLocation(xc, yc);
			
			new Cote(f, m, a).tracer();
			new Cote(f, a, b).tracer();
			new Cote(f, b, c).tracer();
			new Cote(f, c, n).tracer();
			
		}else{
			Segment.tracer(f, m, n);
		}
	}*/
	
//Question 2
/*	public void run () {
		final double xa, ya, xb, yb, xc, yc;
		if( Segment.longueur(m, n) > LG_MIN ) {
			
			xa = (2*m.x + n.x) / 3.0;
			xc = (m.x + 2*n.x) / 3.0;
			ya = (2*m.y + n.y) / 3.0;
			yc = (m.y + 2*n.y) / 3.0;	
			xb = xa + ( xc - xa - (Math.sqrt(3.0)*(yc - ya)) ) / 2.0;
			yb = ya + ( yc - ya + (Math.sqrt(3.0)*(xc - xa)) ) / 2.0;
			
			Point a = new Point();
			a.setLocation(xa, ya);
			Point b = new Point();
			b.setLocation(xb, yb);
			Point c = new Point();
			c.setLocation(xc, yc);
			
			new Thread(new Cote(f, m, a)).start();
			new Thread(new Cote(f, a, b)).start();
			new Thread(new Cote(f, b, c)).start();
			new Thread(new Cote(f, c, n)).start();
			
		}else{
			Segment.tracer(f, m, n);
		}
	}*/
	
//Question 3
	public void run () {
		final double xa, ya, xb, yb, xc, yc;
		if( Segment.longueur(m, n) > LG_MIN ) {

			xa = (2*m.x + n.x) / 3.0;
			xc = (m.x + 2*n.x) / 3.0;
			ya = (2*m.y + n.y) / 3.0;
			yc = (m.y + 2*n.y) / 3.0;	
			xb = xa + ( xc - xa - (Math.sqrt(3.0)*(yc - ya)) ) / 2.0;
			yb = ya + ( yc - ya + (Math.sqrt(3.0)*(xc - xa)) ) / 2.0;
			
			Point a = new Point();
			a.setLocation(xa, ya);
			Point b = new Point();
			b.setLocation(xb, yb);
			Point c = new Point();
			c.setLocation(xc, yc);
			
/*			pool.execute(new Cote(f, m, a));
			pool.execute(new Cote(f, a, b));
			pool.execute(new Cote(f, b, c));
			pool.execute(new Cote(f, c, n));

			pool.shutdown();*/
//Question 4
			pool.execute(new Cote(f, m, a, pool));
			pool.execute(new Cote(f, a, b, pool));
			pool.execute(new Cote(f, b, c, pool));
			pool.execute(new Cote(f, c, n, pool));

		}else{
			Segment.tracer(f, m, n);

		}
	}
}
