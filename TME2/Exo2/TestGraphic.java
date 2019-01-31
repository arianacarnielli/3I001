import java.awt.Point;
import graphic.*;

public class TestGraphic {

	public static void main(String[] args) {
		Window w = new Window(500, 500, "test"); 
		Point p2 = new Point(100, 100); 
		Point p1 = new Point(200, 200); 
		Point p3 = new Point(150, 300); 

		w.plotLine(p1, p2);
		w.plotLine(p1,p3);
		w.plotLine(p2, p3);
	}

}
