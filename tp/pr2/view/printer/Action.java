package tp.pr2.view.printer;

import tp.pr2.logic.Cell;
import tp.pr2.utils.Coords;

public class Action {
	
	private String actionMsg;
	
	public Action (Cell cell) {
		this.actionMsg = cell.getClass().getSimpleName();
	}
	
	public Action atCoordinates(Coords coords) {
		this.actionMsg += " at " + coords;
		return this;
	}
	
	public Action dies() {
		this.actionMsg += " dies";
		return this;
	}
	
	public Action dies(String reason) {
		dies();
		this.actionMsg += " of " + reason;
		return this;
	}
	
	public void create() {
		System.out.println(this.actionMsg);
	}
	
	
}
