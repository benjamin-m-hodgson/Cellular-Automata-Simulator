package simulation.ruleSet;
import neighbormanager.WaTorNeighborManager;
import simulation.cell.*;
import simulation.grid.Grid;

public class WaTorRuleset implements Ruleset {

	private int FISH = 0;
	private int SHARK = 1; 
	private int VACANT = 2;
	private Grid GRID;
	private int FISH_BREEDTIME;
	private int SHARK_BREEDENERGY;
	private WaTorNeighborManager NEIGHBOR_MANAGER = new WaTorNeighborManager();

	/**
	 * RULES:
	 * Fish: move randomly to free neighboring cells
	 * Once breed time is up, new fish is born in free neighboring cell
	 * Shark: move randomly to neighboring cells that are free/occupied by shark
	 * If fish, fish is eaten and energy increases, shark loses energy w/ every 
	 * time step, dies if it reaches 0
	 * 
	 * @param fishBreedTime
	 * @param sharkBreedTime
	 * @param sharkInitEnergy
	 * @param fishEnergy
	 */
	public WaTorRuleset(int fishBreedTime, int sharkBreedEnergy) {
		this.FISH_BREEDTIME = fishBreedTime;
		this.SHARK_BREEDENERGY = sharkBreedEnergy;
		this.NEIGHBOR_MANAGER = new WaTorNeighborManager();
	}

	/**
	 * Sets grid to current grid 
	 * 
	 * @param g: Current simulation grid
	 */
	@Override
	public void setGrid(Grid g) {
		GRID = g;
	}

	/**
	 * Processes all cells in current grid
	 */
	@Override
	public void processCells() {
		for(Cell[] row : (Cell[][]) GRID.getCells())
			for(Cell cell : row) {
				WaTorCell wCell = (WaTorCell) cell;
				if(wCell.getState() == FISH) {
					wCell.incrementBreedingTime();
					checkBreedingTime(wCell);
					moveFish(wCell);
				}
				else if(cell.getState() == SHARK) {
					wCell.decrementEnergy();
					checkEnergy(wCell);
					moveShark(wCell);
				}
			}
		cleanMove();
	}

	/**
	 * Moves fish cell on grid
	 * 
	 * @param fish
	 */
	private void moveFish(WaTorCell fish) {
		WaTorCell freeNeighbor = NEIGHBOR_MANAGER.vacantNeighbor(fish, GRID);
		if(freeNeighbor == null) {
			fish.setState(fish.getState());
			return;
		}
		else {
			freeNeighbor.setBreedingTime(fish.getEnergy());
			freeNeighbor.setState(FISH);
			freeNeighbor.setMove(true);
			fish.kill();
		}
	}

	/**
	 * Moves shark cell on grid
	 * 
	 * @param shark
	 */
	private void moveShark(WaTorCell shark) {
		WaTorCell freeNeighbor = NEIGHBOR_MANAGER.vacantOrFishNeighbor(shark, GRID);
		if(freeNeighbor == null) {
			shark.setState(shark.getState());
			return;
		}
		else if(freeNeighbor.getState() == VACANT) {
			freeNeighbor.setEnergy(shark.getEnergy());
		}
		else if(freeNeighbor.getState() == FISH) {
			shark.incrementEnergy();
			freeNeighbor.setEnergy(shark.getEnergy());
		}
		
		freeNeighbor.setState(SHARK);
		shark.setState(VACANT);
		freeNeighbor.setMove(true);
		shark.setMove(true);
	}

	/**
	 * Checks energy of shark cell
	 * 
	 * @param shark
	 */
	private void checkEnergy(WaTorCell shark) {
		if(shark.getEnergy() > SHARK_BREEDENERGY) {
			giveBirth(shark);
		}
		else if(shark.getEnergy() <= 0) {
			shark.kill();
		}
	}

	/**
	 * Checks if fish should give birth
	 * 
	 * @param fish
	 */
	private void checkBreedingTime(WaTorCell fish) {
		if(fish.getBreedingTime() > FISH_BREEDTIME) {
			giveBirth(fish);
		}
	}

	/**
	 * Method that lets cell c give birth
	 * 
	 * @param cell: Fish cell giving birth
	 */
	private void giveBirth(WaTorCell cell) {
		WaTorCell baby = NEIGHBOR_MANAGER.vacantNeighbor(cell, GRID);
		if(baby != null) {
			baby.setState(cell.getState());
			baby.setMove(true);
			cell.setEnergy(0);
			cell.setBreedingTime(0);
		}
	}

	/**
	 * Changes all setMoves() to false (so that cells don't overwrite each other)
	 */
	private void cleanMove() {
		for(int r = 0; r < GRID.getXSize(); r++) {
			for(int c = 0; c < GRID.getYSize(); c++) {
				WaTorCell cell = (WaTorCell) GRID.getCell(r,c);
				cell.setMove(false);
			}
		}
	}

}