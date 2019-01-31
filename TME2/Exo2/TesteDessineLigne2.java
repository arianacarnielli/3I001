import java.awt.Point;
import java.util.Random;

import graphic.*;

public class TesteDessineLigne2 {

	public static void main(String[] args) {
		
		Window w = new Window(500, 500, "test"); 
		
		Random gen = new Random();

		Point [] p = new Point[3];
		for (int i =0; i < 3; i++) {
			p[i] = new Point(gen.nextInt(499), gen.nextInt(499)); 
		}
		
		DessineLigne2[] ds = new DessineLigne2[3];
		Thread[] th = new Thread[3];

		ds[0] = new DessineLigne2(p[0], p[1], w);
		ds[1] = new DessineLigne2(p[0], p[2], w);
		ds[2] = new DessineLigne2(p[1], p[2], w);

		
		for (int i = 0; i < 3; i++) {
			th[i] = new Thread(ds[i]);
			th[i].start();
		}	

	}

}
