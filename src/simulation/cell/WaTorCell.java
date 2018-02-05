package simulation.cell;

public class WaTorCell extends Cell {
	
	private boolean MOVED;
	private int ENERGY;
	private int ENERGY_HOLDER;
	private int BREEDINGTIME;
	
	private int VACANT = 2;

	public WaTorCell(int x, int y, int state, int initEnergy) {
		super(x, y, state);
		MOVED = false;
		ENERGY = initEnergy;
		ENERGY_HOLDER = initEnergy;
		BREEDINGTIME = 0;
	}
	
	public void incrementEnergy() {
		ENERGY++;
	}
	
	public void decrementEnergy() {
		ENERGY--;
	}
	
	public void incrementBreedingTime() {
		BREEDINGTIME++;
	}
	
	public int getBreedingTime() {
		return BREEDINGTIME;
	}
	
	public void setBreedingTime(int time) {
		BREEDINGTIME = time;
	}
	
	public int getEnergy() {
		return ENERGY;
	}
	
	public void setEnergy(int e) {
		ENERGY = e;
	}
	
	public void kill() {
		this.BREEDINGTIME = 0;
		this.ENERGY = ENERGY_HOLDER;
		this.myState = VACANT;
	}
	
	public void setMoved(boolean b) {
		this.MOVED = b;
	}
	
	public boolean getMoved() {
		return this.MOVED;
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
