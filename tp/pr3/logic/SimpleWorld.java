package tp.pr3.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tp.pr3.utils.Coords;

public class SimpleWorld extends World{
	
	public SimpleWorld(int rows, int cols) {
		super(rows, cols);
	}
	
	public void initWorld(int numberSimpleCells)  {
		this.surface.cleanBoard();
		List<Coords> boardCoordinates = new ArrayList<Coords>();
		for (int i = 0; i < surface.getRows(); i++)
			for (int j = 0; j < surface.getColumns(); j++)
				boardCoordinates.add(new Coords(i, j));
		Collections.shuffle(boardCoordinates);
		
		for (int i = 0; i < numberSimpleCells; i++) {
			surface.createCell(boardCoordinates.get(i), new SimpleCell(Values.MAX_LP, Values.MAX_MP));
		}
		
		/*
		for (int i = 0; i < surface.getRows(); i++){
			for (int j = 0; j < surface.getColumns(); j++){
				if (SingleRandom.getInstance().getRandom().nextInt(101) <= percentage) {
					surface.createCell(new Coords(i,  j), (SingleRandom.getInstance().getRandom().nextInt(2) == 0 ? new SimpleCell(Values.MAX_LP, Values.MAX_MP) : new ComplexCell(Values.MAX_EAT)));
				}
			}
		}
		*/
		
	}
}
