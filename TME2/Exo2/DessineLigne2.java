import graphic.*;
import java.awt.Point;

public class DessineLigne2 implements Runnable{
	private Point p1;
	private Point p2;
	private Window w;
	
	public DessineLigne2(Point p1, Point p2, Window w) {
		this.p1 = p1;
		this.p2 = p2;
		this.w = w;
	}
	
	public void run() {
		this.w.plotLine(this.p1, this.p2);
	}
}
