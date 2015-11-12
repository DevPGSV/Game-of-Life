package tp.pr1;

import java.util.Scanner;

import tp.pr1.controller.Controller;
import tp.pr1.logic.World;

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
