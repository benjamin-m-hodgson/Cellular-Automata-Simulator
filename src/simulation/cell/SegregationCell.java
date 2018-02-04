package simulation.cell;

public class SegregationCell extends Cell {
	
	private boolean MOVED;
	private boolean SATISFIED;

	public SegregationCell(int x, int y, int state) {
		super(x, y, state);
		MOVED = false;
	}
	
	
	public void setMove(boolean b) {
		MOVED = b;
	}
	
	public boolean getMove() {
		return MOVED;
	}
	
	public void setSatisfaction(boolean b) {
		SATISFIED = b;
	}
	
	public boolean getSatisfaction() {
		return SATISFIED;
	}


	@Override
	public void drawShape() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setState(int state) {
		// TODO Auto-generated method stub
		
	}
	
	
}
