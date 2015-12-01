package tp.pr2;

import java.util.Scanner;

import tp.pr2.controller.Controller;
import tp.pr2.logic.Cell;
import tp.pr2.logic.ComplexCell;
import tp.pr2.logic.SimpleCell;
import tp.pr2.logic.World;

/**
 * <p>Main class</p>
 * <p>Contains starting function</p>
 */
public class Main {

	/**
	 * <p>Creates a world</p>
	 * <p>Creates a scanner for std input</p>
	 * <p>Creates a controller and starts the simulation</p>
	 * 
	 * @param args Arguments ignored
	 */
	public static void main(String[] args) {
		//Cell a = new SimpleCell(1, 2);
		//a.ia = 5;
		//System.exit(0);
		
		
		World world = new World();
		Scanner in = new Scanner(System.in);
		Controller controller = new Controller(world, in);
		controller.executeSimulation();
		
	}

}
