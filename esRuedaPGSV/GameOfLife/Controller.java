package esRuedaPGSV.GameOfLife;

import java.util.Scanner;

public class Controller {
	private World world;
	private Scanner in;
	
	/**
	 * Controller constructor
	 * 
	 * @param world world to be used by the controller
	 * @param in input for commands (ie. std input)
	 */
	public Controller(World world, Scanner in){
		this.world = world;
		this.in = in;
	}
	
	/**
	 * Main loop
	 */
	@SuppressWarnings("unused")
	public void executeSimulation(){
		boolean keepLoop = true;
		String command = "";
		int row = -1, col = -1;
		while (keepLoop) {
			System.out.println(this.world);
			System.out.print("Command: ");
			
			
			
			if (true) { // Used for debugging. Change to false to "simulate" a step every second.
				command = in.next(); //read input
				command = command.toLowerCase();
			} else {
				System.out.println("");
				command = "step";
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
			
			switch(command)
			{
				case "step": 
					this.world.evolve();
					break;
	
				case "init": 
					this.world.initWorld();
					break;
	
				case "clean":
					this.world.cleanWorld();
					break;
	
				case "create":
					row = in.nextInt();
					col = in.nextInt();
					in.nextLine();
					world.createCell(new Coords(row, col));
					break;
	
				case "delete":
					row = in.nextInt();
					col = in.nextInt();
					in.nextLine();
					world.deleteCell(new Coords(row, col));
					break;
	
				case "help":
					System.out.println(getHelp());
					break;
	
				case "exit":
					keepLoop = false;
					break;
	
				default: 
					System.out.println("Invalid Command.");
					break;
			}
		}
	}
	
	public String getHelp() {
		return "AVAILABLE COMMANDS: \n" +
			"    STEP: execute a simulation step. \n" + 
			"    HELP: show this help. \n" +
			"    EXIT: close the game. \n" +
			"    INIT: restart the game. \n" +
			"    CLEAN: delete all the cells. \n" +
			"    CREATE R C: create a new cell at position (r,c). \n" +
			"    DELETE R C: delete the cell at position (r,c). \n";
	}
}
