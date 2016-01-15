package tp.pr3.controller;

import java.util.Scanner;

import tp.pr3.command.Command;
import tp.pr3.command.CommandParser;
import tp.pr3.logic.World;
import tp.pr3.utils.Coords;
import tp.pr3.view.Printer;

/**
 * <p>Controller class.</p> 
 * <p>Has the main loop.</p>
 * <p>Handles the execution of the program.</p>
 */
public class Controller {
	private World world;
	private Scanner in;
	private boolean simulationFinished;
	
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
		this.simulationFinished = false;
	}
	
	/**
	 * <p>Main loop</p>
	 */
	public void executeSimulation(){
		Printer p = Printer.getInstance();
		String command = "";
		Command cmd;
		while (!isSimulationFinished()) {
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
				cmd.execute(world, this);
			} else {
				System.err.println("Invalid Command.\nWrite \"help\" to get a list of commands."); 
			}
		}
	}
	
	public void cleanWorld() {
		System.out.println("cleaning the game...");
		world.cleanWorld();
	}
	
	public void deleteCell(Coords coords) {
		if (world.deleteCell(coords)) {
			System.out.println("Cell deleted at " + coords);
		} else {
			System.err.println("Couldn't delete cell at " + coords);
		}
	}
	
	/**
	 * <p>Checks if the simulation is finished</p>
	 * 
	 * @return if the simulation is finished
	 */
	public boolean isSimulationFinished() {
		return this.simulationFinished;
	}
	
	/**
	 * <p>Sets the simulation as finished</p> 
	 */
	public void setSimulationFinished() {
		this.simulationFinished = true;
	}
	
	public void initWorld() {
		world.initWorld();
	}
	
	public void step() {
		Printer.getInstance().print(world.evolve());
	}
}
