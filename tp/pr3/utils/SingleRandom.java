package tp.pr3.utils;

import java.util.Random;

public class SingleRandom {
	private static SingleRandom Instance = null;
	private Random rnd;
	
	private SingleRandom() {
		this.rnd = new Random();
	}
	
	public static SingleRandom getInstance() {
		if (SingleRandom.Instance == null) {
			SingleRandom.Instance = new SingleRandom();
		}
		
		return SingleRandom.Instance;
	}
	
	public Random getRandom() {
		return this.rnd;
	}
}
