package tp.pr3.logic.world;

import java.util.Collections;
import java.util.List;

import tp.pr3.logic.cell.ComplexCell;
import tp.pr3.logic.cell.SimpleCell;
import tp.pr3.logic.surface.Surface;
import tp.pr3.utils.Coords;
import tp.pr3.utils.Utils;
import tp.pr3.utils.Values;

public class ComplexWorld extends World{
	
	public int numberSimpleCells;
	public int numberComplexCells;
	
	public ComplexWorld() {
		this(4, 5);
	}
	
	public ComplexWorld(int rows, int cols) {
		this(rows, cols, rows * cols / 4, rows * cols / 4);
	}
	
	public ComplexWorld(Surface surface) {
		this(surface, surface.getRows() * surface.getColumns() / 4, surface.getRows() * surface.getColumns() / 4);
	}
	
	public ComplexWorld(Surface surface, int numberSimpleCells, int numberComplexCells) {
		super(surface);
		this.numberSimpleCells = numberSimpleCells;
		this.numberComplexCells = numberComplexCells;
	}
	
	public ComplexWorld(int rows, int cols, int numberSimpleCells, int numberComplexCells) {
		super(rows, cols);
		this.numberSimpleCells = numberSimpleCells;
		this.numberComplexCells = numberComplexCells;
	}
	
	public String getWorldTypeAsString() {
		return "complex";
	}

	@Override
	public void initWorld() {
		surface.cleanBoard();
		List<Coords> positionsList = Utils.getAllPositions(surface);
		Collections.shuffle(positionsList);
		
		for (int i = 0; i < this.numberSimpleCells; i++) {
			surface.createCell(positionsList.get(i), new SimpleCell(Values.MAX_LP, Values.MAX_MP));
		}
		
		for (int i = this.numberSimpleCells; i < this.numberSimpleCells + this.numberComplexCells; i++) {
			surface.createCell(positionsList.get(i), new ComplexCell(Values.MAX_EAT));
		}
	}
}
