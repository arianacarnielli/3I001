import java.awt.Point;
import java.util.Random;

import graphic.*;

public class TestDessineLigne {

	public static void main(String[] args) {
		
		Window w = new Window(500, 500, "test"); 
		
		Random gen = new Random();

		Point [] p = new Point[3];
		for (int i =0; i < 3; i++) {
			p[i] = new Point(gen.nextInt(499), gen.nextInt(499)); 
		}
		
		DessineLigne[] ds = new DessineLigne[3];

		ds[0] = new DessineLigne(p[0], p[1], w);
		ds[1] = new DessineLigne(p[0], p[2], w);
		ds[2] = new DessineLigne(p[1], p[2], w);

		for (int i = 0; i < 3; i++) {
			ds[i].start();
		}	
	}
}
