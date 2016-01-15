package tp.pr3;

import java.util.Scanner;

import tp.pr3.controller.Controller;
import tp.pr3.logic.SimpleWorld;
import tp.pr3.logic.World;

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
		World world = new SimpleWorld(4, 4);
		Scanner in = new Scanner(System.in);
		Controller controller = new Controller(world, in);
		controller.executeSimulation();
		
	}

}
