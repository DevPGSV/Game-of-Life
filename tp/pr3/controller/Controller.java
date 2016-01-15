package tp.pr3.controller;

import java.util.Scanner;

import tp.pr3.command.Command;
import tp.pr3.command.CommandParser;
import tp.pr3.logic.World;
import tp.pr3.view.Printer;

/**
 * <p>Controller class.</p> 
 * <p>Has the main loop.</p>
 * <p>Handles the execution of the program.</p>
 */
public class Controller {
	private World world;
	private Scanner in;
	
	private static final boolean AUTO_STEP = false; // Used for debugging. Change to true to "simulate" a step every second.
	
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
		Printer p = Printer.getInstance();
		String command = "";
		Command cmd;
		while (!world.isSimulationFinished()) {
			p.print(this.world);
			System.out.print("Command: ");
			
			
			if (!AUTO_STEP) { 
				command = in.nextLine(); //read input
			} else {
				System.out.println("step");
				command = "step";
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			cmd = CommandParser.parseCommand(command.split(" "));
			if (cmd != null) {
				cmd.execute(world);
			} else {
				System.err.println("Invalid Command.\nWrite \"help\" to get a list of commands."); 
			}
			
		}
	}
}
