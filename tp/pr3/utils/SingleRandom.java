package tp.pr3.utils;

import java.util.Random;

/**
 * Random object wrapper, singleton
 */
public class SingleRandom {
	private static SingleRandom Instance = null;
	private Random rnd;
	
	/**
	 * Private constructor
	 */
	private SingleRandom() {
		this.rnd = new Random();
	}
	
	/**
	 * Instance getter
	 * @return the instance
	 */
	public static SingleRandom getInstance() {
		if (SingleRandom.Instance == null) {
			SingleRandom.Instance = new SingleRandom();
		}
		
		return SingleRandom.Instance;
	}
	
	/**
	 * Gets the random object
	 * @return the random object
	 */
	public Random getRandom() {
		return this.rnd;
	}
}
