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

	private int FISH_BREEDTIME;
	private int SHARK_BREEDENERGY;
	private int SHARK_ENERGY;
	private int FISH_ENERGY;


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
	public WaTorRuleset(int fishBreedTime, int sharkBreedEnergy, int sharkInitEnergy, int fishEnergy) {
		this.FISH_BREEDTIME = fishBreedTime;
		this.SHARK_BREEDENERGY = sharkBreedEnergy;
		this.SHARK_ENERGY = sharkInitEnergy;
		this.FISH_ENERGY = fishEnergy;
	}

	public void moveFish(SegregationCell c, Grid g) {
		for()


	}


	public void moveShark(WaTorCell c, Grid g) {
		Random rand = new Random();
		WaTorCell[] cells =  (WaTorCell[]) getNeighbors(c, g);
		WaTorCell cell = cells[rand.nextInt(cells.length)];

		if(cell.getState() == VACANT) continue;
		else if(cell.getState() == FISH) {
			moveShark(cell, g);
		}
		else if(cell.getState() == FISH) {
			moveFish(cell, g);
		}

	}



	private void killFish() {

	}


	@Override
	public Cell[] getNeighbors(Cell c, Grid g) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		NeighborManager nm = new NeighborManager();
		neighbors.addAll(Arrays.asList(nm.NSEWCells(c ,g)));

		return (Cell[]) neighbors.toArray();
	}

	@Override
	public void processCells(Grid g) {
		// TODO Auto-generated method stub
		for(int r = 0; r < g.getXSize(); r++) {
			for(int c = 0; c < g.getYSize(); r++) {
				Cell cell = g.getCell(r, c);
				if(cell.getState() == FISH) {
					moveFish(cell, g);
				}
				else if(cell.getState() == SHARK) {
					moveShark(cell, g);
				}
			}
		}
	}


}