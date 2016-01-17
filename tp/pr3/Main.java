package tp.pr3;

import java.util.Scanner;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import tp.pr3.controller.Controller;
import tp.pr3.logic.world.SimpleWorld;
import tp.pr3.logic.world.World;
import tp.pr3.utils.SingleRandom;

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
		
		SingleRandom.getInstance().getRandom().setSeed(1);
		
		try {
		    for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(laf.getName())) {
		            UIManager.setLookAndFeel(laf.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		if (!UIManager.getLookAndFeel().getName().equals("Nimbus")) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		World world = new SimpleWorld();
		Scanner in = new Scanner(System.in);
		Controller controller = new Controller(world, in);
		controller.executeSimulation();
	}

}
