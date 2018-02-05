package simulation.ruleSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import simulation.cell.*;
import simulation.grid.Grid;

public class WaTorRuleset implements Ruleset {


	private int FISH = 0;
	private int SHARK = 1; 
	private int VACANT = 2;
	private Grid GRID;
	private int FISH_BREEDTIME;
	private int SHARK_BREEDENERGY;

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
	}


	@Override
	public Cell[] getNeighbors(Cell c) {
		ArrayList<WaTorCell> neighbors = new ArrayList<WaTorCell>();
		ArrayList<WaTorCell> freeNeighbors = new ArrayList<WaTorCell>();
		NeighborManager nm = new NeighborManager();
		WaTorCell[] cells = (WaTorCell[]) nm.NSEWCells(c ,GRID);
		neighbors.addAll(Arrays.asList(cells));
		for(WaTorCell neighbor : neighbors) {
			if(neighbor.getState() == VACANT || neighbor.getState() == FISH && !neighbor.getMoved()) {
				freeNeighbors.add((WaTorCell) c);
			}
		}
		return (Cell[]) freeNeighbors.toArray();
	}

	public Cell[] getVacantNeighbors(Cell c) {
		ArrayList<WaTorCell> neighbors = new ArrayList<WaTorCell>();
		ArrayList<WaTorCell> freeNeighbors = new ArrayList<WaTorCell>();
		NeighborManager nm = new NeighborManager();
		WaTorCell[] cells = (WaTorCell[]) nm.NSEWCells(c ,GRID);
		neighbors.addAll(Arrays.asList(cells));
		for(WaTorCell neighbor : neighbors) {
			if(neighbor.getState() == VACANT && !neighbor.getMoved()) freeNeighbors.add((WaTorCell) c);
		}
		return (Cell[]) freeNeighbors.toArray();
	}

	@Override
	public void processCells() {
		for(int r = 0; r < GRID.getXSize(); r++) {
			for(int c = 0; c < GRID.getYSize(); c++) {
				WaTorCell cell = (WaTorCell) GRID.getCell(r, c);
				if(cell.getState() == FISH) {
					moveFish(cell);
					cell.incrementBreedingTime();
					checkBreedingTime(cell);
				}
				else if(cell.getState() == SHARK) {
					moveShark(cell);
					cell.decrementEnergy();
					checkEnergy(cell);
				}
			}
		}
		cleanMove();
		
	}

	private void moveFish(WaTorCell fish) {
		Random rand = new Random();
		WaTorCell[] freeNeighbors =  (WaTorCell[]) getVacantNeighbors(fish);
		if(freeNeighbors.length == 0) {
			return;
		}

		WaTorCell cell = freeNeighbors[rand.nextInt(freeNeighbors.length)];
		cell.setBreedingTime(fish.getEnergy());
		cell.setState(FISH);
		cell.setMoved(true);
		fish.kill();
	}


	private void moveShark(WaTorCell shark) {
		Random rand = new Random();
		WaTorCell[] freeNeighbors =  (WaTorCell[]) getNeighbors(shark);
		if(freeNeighbors.length == 0) {
			return;
		}
		WaTorCell cell = freeNeighbors[rand.nextInt(freeNeighbors.length)];

		if(cell.getState() == VACANT) {
			cell.setEnergy(shark.getEnergy());
			cell.setState(SHARK);
			shark.setState(VACANT);
		}
		else if(cell.getState() == FISH) {
			shark.incrementEnergy();
			cell.setEnergy(shark.getEnergy());
			cell.setState(SHARK);
			shark.setState(VACANT);
		}
	}

	private void checkEnergy(WaTorCell shark) {
		if(shark.getEnergy() > SHARK_BREEDENERGY) {
			giveBirth(shark);
		}
		else if(shark.getEnergy() == 0) {
			shark.kill();
		}
	}


	private void checkBreedingTime(WaTorCell fish) {
		if(fish.getBreedingTime() > FISH_BREEDTIME) {
			giveBirth(fish);
		}
	}
	
	private void giveBirth(WaTorCell cell) {
		Cell[] neighbors = getVacantNeighbors(cell);
		if(neighbors.length > 0) {
			Random rand = new Random();
			WaTorCell baby = (WaTorCell) neighbors[rand.nextInt(neighbors.length)];
			baby.setState(cell.getState());
			baby.setMoved(true);
			cell.setEnergy(0);
			cell.setBreedingTime(0);
		}
	}

	
	private void cleanMove() {
		for(int r = 0; r < GRID.getXSize(); r++) {
			for(int c = 0; c < GRID.getYSize(); c++) {
				WaTorCell cell = (WaTorCell) GRID.getCell(r,c);
				cell.setMoved(false);
			}
		}
	}

	@Override
	public void setGrid(Grid g) {
		GRID = g;
	}
}