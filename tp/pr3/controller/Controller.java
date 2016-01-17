package tp.pr3.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

import tp.pr3.command.Command;
import tp.pr3.command.CommandParser;
import tp.pr3.exceptions.ErrorOnLoadException;
import tp.pr3.exceptions.ErrorOnSaveException;
import tp.pr3.exceptions.FileFormatException;
import tp.pr3.exceptions.NoFileSelectedException;
import tp.pr3.exceptions.ParseCommandException;
import tp.pr3.exceptions.UnknownCommandException;
import tp.pr3.exceptions.UnknownWorldTypeException;
import tp.pr3.logic.cell.Cell;
import tp.pr3.logic.world.World;
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
			
			try {
				cmd = CommandParser.parseCommand(command.split(" "));
				cmd.execute(world, this);
			} catch (ParseCommandException | UnknownCommandException | UnknownWorldTypeException | NumberFormatException | FileFormatException | IOException | ErrorOnSaveException | NoFileSelectedException | ErrorOnLoadException e) {
				System.err.println(e.getMessage());	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void clean() {
		System.out.println("Cleaning the game...");
		world.cleanWorld();
	}
	
	public void step() {
		Printer.getInstance().print(world.evolve());
	}
	
	public void init() {
		world.initWorld();
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
	
	public void delete(Coords coords) {
		if (world.deleteCell(coords)) {
			System.out.println("Cell deleted at " + coords);
		} else {
			System.err.println("Couldn't delete cell at " + coords);
		}
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public void createCell(Coords coords, Cell cell) {
		world.createCell(coords, cell);
	}
	
	public void load(File file) throws ErrorOnLoadException{
		try {
			world = World.load(new Scanner(file));
		} catch (Exception e) {
			throw new ErrorOnLoadException("Unknown error parsing file", e);
		}
	}
	
	public void save(File file) throws ErrorOnSaveException {
		try (Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"))) {
			world.save(fileWriter);
		} catch (Exception e) {
			throw new ErrorOnSaveException(e);
		}
	}
}

