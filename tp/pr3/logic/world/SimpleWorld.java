package tp.pr3.logic.world;

import java.util.Collections;
import java.util.List;

import tp.pr3.logic.cell.SimpleCell;
import tp.pr3.logic.surface.Surface;
import tp.pr3.utils.Coords;
import tp.pr3.utils.Utils;
import tp.pr3.utils.Values;

public class SimpleWorld extends World{
	
	public int numberSimpleCells;
	
	public SimpleWorld() {
		this(4, 5);
	}
	
	public SimpleWorld(int rows, int cols) {
		this(rows, cols, rows * cols / 2);
	}
	
	public SimpleWorld(Surface surface) {
		this(surface, surface.getRows() * surface.getColumns() / 2);
	}
	
	public SimpleWorld(Surface surface, int numberSimpleCells) {
		super(surface);
		this.numberSimpleCells = numberSimpleCells;
	}
	
	public SimpleWorld(int rows, int cols, int numberSimpleCells) {
		super(rows, cols);
		this.numberSimpleCells = numberSimpleCells;
	}
	
	public String getWorldTypeAsString() {
		return "simple";
	}

	@Override
	public void initWorld() {
		surface.cleanBoard();
		List<Coords> positionsList = Utils.getAllPositions(surface);
		Collections.shuffle(positionsList);
		
		for (int i = 0; i < this.numberSimpleCells; i++) {
			surface.createCell(positionsList.get(i), new SimpleCell(Values.MAX_LP, Values.MAX_MP));
		}
	}
}
