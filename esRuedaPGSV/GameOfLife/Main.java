package esRuedaPGSV.GameOfLife;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

	/**
	 * Creates a world
	 * Creates a scanner for std input
	 * Creates a controller and starts the simulation
	 * 
	 * @param args Arguments ignored
	 */
	public static void main(String[] args) {
		
		World world = new World();
		Scanner in = new Scanner(System.in);
		Controller controller = new Controller(world, in);
		controller.executeSimulation();
		
	}

}
