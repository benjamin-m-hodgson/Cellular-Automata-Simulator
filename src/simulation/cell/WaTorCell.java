package simulation.cell;

public class WaTorCell extends Cell {
	
	private boolean MOVED;

	public WaTorCell(int x, int y, int state) {
		super(x, y, state);
		MOVED = false;
	}
}
