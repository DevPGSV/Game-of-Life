package esRuedaPGSV.GameOfLife;

import java.util.Scanner;

public class Controller {
	private World world;
	private Scanner in;
	
	public Controller(World world, Scanner in){
		this.world = world;
		this.in = in;
		
	}
	
	/**
	 * Main loop
	 */
	public void executeSimulation(){
		boolean keepLoop = true;
		String command = "";
		System.out.println(this.world);
		while (keepLoop) {
			System.out.print("Command: ");
			command = in.nextLine(); //read input
			//command = "step";
			if (command.equalsIgnoreCase("step")) {
				this.world.evolve();
				System.out.println(this.world);
			} else if (command.equalsIgnoreCase("init")) {
				
			} else if (command.equalsIgnoreCase("clean")) {
				
			} else if (command.equalsIgnoreCase("create")) {
				
			} else if (command.equalsIgnoreCase("delete")) {
				
			} else if (command.equalsIgnoreCase("help")) {
				
			} else if (command.equalsIgnoreCase("exit")) {
				keepLoop = false;
			} else {
				
			}
		}
	}
}
