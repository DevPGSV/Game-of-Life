package tp.pr2.controller;

import java.util.Scanner;

import tp.pr2.logic.World;
import tp.pr2.utils.Coords;

/**
 * <p>Controller class</p> 
 * <p>Has the main loop</p>
 * <p>Handles the execution of the program</p>
 */
public class Controller {
	private World world;
	private Scanner in;
	
	private static final boolean AUTO_STEP = true; // Used for debugging. Change to true to "simulate" a step every second.
	
	/**
	 * <p>Controller constructor</p>
	 * 
	 * @param world world to be used by the controller
	 * @param in input for commands (ie. std input)
	 */
	public Controller(World world, Scanner in){
		this.world = world;
		this.in = in;
	}
	
	/**
	 * <p>Main loop</p>
	 */
	public void executeSimulation(){
		boolean keepLoop = true;
		String command = "";
		int row = -1, col = -1;
		while (keepLoop) {
			System.out.println(this.world);
			System.out.print("Command: ");
			
			
			if (!AUTO_STEP) { 
				command = in.next(); //read input
			} else {
				System.out.println("step");
				command = "step";
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
			command = command.toLowerCase();
			switch(command)
			{
				case "step": 
					System.out.println(this.world.evolve());
					break;
	
				case "init": 
					this.world.initWorld();
					break;
	
				case "clean":
					this.world.cleanWorld();
					System.out.println("cleaning the game...");
					break;
	
				case "create":
					row = in.nextInt() - 1;
					col = in.nextInt() - 1;
					in.nextLine();
					if (world.createCell(new Coords(row, col))) {
						System.out.println("New cell created at " + new Coords(row, col));
					} else {
						System.err.println("Couldn't create a cell at " + new Coords(row, col));
					}
					break;
	
				case "delete":
					row = in.nextInt() - 1;
					col = in.nextInt() - 1;
					in.nextLine();
					if (world.deleteCell(new Coords(row, col))) {
						System.out.println("Cell deleted at " + new Coords(row, col));
					} else {
						System.err.println("Couldn't delete cell at " + new Coords(row, col));
					}
					break;
	
				case "help":
					System.out.println(getHelp());
					break;
	
				case "exit":
					keepLoop = false;
					System.out.println("Game over...");
					break;
	
				default: 
					System.err.println("Invalid Command.\nWrite \"help\" to get a list of commands."); 
					break;
			}
		}
	}
	
	/**
	 * <p>Gets a help message</p>
	 * @return a help message as a String.
	 */
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
