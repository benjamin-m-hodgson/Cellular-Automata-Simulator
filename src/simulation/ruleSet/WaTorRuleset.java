package simulation.ruleSet;
import simulation.cell.*;
import simulation.neighborhoods.Neighborhood;
import simulation.ruleSet.neighborManager.*;

/**
 * WaTor Predator-Prey Simulation
 * 
 * @param fishBreedTime
 * @param sharkBreedTime
 * @param sharkInitEnergy
 * @param fishEnergy
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 */
public class WaTorRuleset extends Ruleset {
    private int FISH = 0;
    private int SHARK = 1; 
    private int VACANT = 2;
    private int FISH_BREEDTIME;
    private int SHARK_BREEDENERGY;
    private int FISH_INITENERGY;
    private int SHARK_INITENERGY;
    private WaTorNeighborManager NEIGHBOR_MANAGER;

    /**
     * Constructor that sets simulation parameters
     * 
     * @param fishBreedTime
     * @param sharkBreedEnergy
     */
    public WaTorRuleset(int fishBreedTime, int sharkBreedEnergy, int fishInitEnergy, int sharkInitEnergy) {
	this.FISH_BREEDTIME = fishBreedTime;
	this.SHARK_BREEDENERGY = sharkBreedEnergy;
	this.FISH_INITENERGY = fishInitEnergy;
	this.SHARK_INITENERGY = sharkInitEnergy;
    }

    /**
     * Set neighbor manager according to shape of cells and grid edge type
     */
    @Override
    public void setNeighborManager(Neighborhood n, boolean finite) {
	this.NEIGHBOR_MANAGER = new WaTorNeighborManager(n, finite);
    }

    /**
     * Returns next state of all cells that need to be processed
     * 
     * @param: c, cell to be processed
     */
    @Override
    public int processCell(Cell c) {
	WaTorCell wCell = (WaTorCell) c;
	if(wCell.getState() == SHARK) {
	    if(checkEnergy(wCell)) {		
		wCell.reset();
		return VACANT;
	    }
	    else return moveShark(wCell);
	}
	else if(wCell.getState() == FISH) {
	    if(checkBreedingTime(wCell)) {
		wCell.reset();
		return FISH;
	    }
	    else return moveFish(wCell);
	}
	else  return wCell.getState();
    }

    /**
     * Moves fish cell on grid
     * 
     * @param fish
     */
    private int moveFish(WaTorCell fish) {
	fish.incrementBreedingTime();
	WaTorCell freeNeighbor = (WaTorCell) NEIGHBOR_MANAGER.getNeighbor(fish, GRID, VACANT);
	if(freeNeighbor != null) {
	    swapCells(fish, freeNeighbor);
	    freeNeighbor.setState(FISH);
	    freeNeighbor.setMove(true);
	    return VACANT;
	}
	return FISH;
    } 

    /**
     * Moves shark cell on grid
     * 
     * @param shark
     */
    private int moveShark(WaTorCell shark) {
	shark.decrementEnergy();
	WaTorCell freeNeighbor = (WaTorCell) NEIGHBOR_MANAGER.vacantOrFishNeighbor(shark, GRID);
	if(freeNeighbor == null) {
	    return SHARK;
	}
	else if(freeNeighbor.getState() == FISH) {
	    shark.incrementEnergy();
	}
	swapCells(shark, freeNeighbor);
	freeNeighbor.setState(SHARK);
	freeNeighbor.setMove(true);
	return VACANT;
    }

    /**
     * Checks energy of shark cell
     * 
     * @param shark
     */
    private boolean checkEnergy(WaTorCell shark) {
	int E = shark.getEnergy();
	if(E > SHARK_BREEDENERGY) {
	    giveBirth(shark);
	    return true;
	}
	else if(E <= 0) {
	    kill(shark);
	    return true;
	}
	return false;
    }

    /**
     * Kills specific cell
     * 
     * @param shark
     */
    private void kill(WaTorCell cell) {
	cell.reset();
	cell.setState(VACANT);
	cell.setMove(true);
    }

    /**
     * Checks if fish should give birth
     * 
     * @param fish
     */
    private boolean checkBreedingTime(WaTorCell fish) {
	if(fish.getBreedingTime() > FISH_BREEDTIME) {
	    giveBirth(fish);
	    return true;
	}
	return false;
    }

    /**
     * Method that lets cell c give birth
     * 
     * @param cell: Fish cell giving birth
     */
    private void giveBirth(WaTorCell cell) {
	WaTorCell baby = (WaTorCell) NEIGHBOR_MANAGER.getNeighbor(cell, GRID, VACANT);
	if(baby != null) {
	    baby.reset();
	    baby.setState(cell.getState());
	    baby.setMove(true);
	}
    }

    /**
     * Swaps attributes of any two cells
     * @param a
     * @param b
     */
    private void swapCells(WaTorCell a, WaTorCell b) {
	int aEnergy = a.getEnergy();
	a.setEnergy(b.getEnergy());
	b.setEnergy(aEnergy);

	int aTime = a.getBreedingTime();
	a.setBreedingTime(b.getBreedingTime());
	b.setBreedingTime(aTime);
    }

    /**
     * @return int representing fish breed time
     */
    public int getFishBreedTime() {
	return FISH_BREEDTIME;
    }
    
    /**
     * @return int representing shark breed energy
     */
    public int getSharkBreedEnergy() {
	return SHARK_BREEDENERGY;
    }
    
    /**
     * @return int representing shark initial energy
     */
    public int getSharkInitEnergy() {
	return SHARK_INITENERGY;
    }

    /**
     * @return int representing fish initial energy
     */
    public int getFishInitEnergy() {
	return FISH_INITENERGY;
    }
    
    /**
     * Sets fish breed time to @param n
     */
    public void setFishBreedTime(int n) {
	FISH_BREEDTIME = n;
    }

    /**
     * Sets shark breed energy to @param n
     */
    public void setSharkBreedEnergy(int n) {
	SHARK_BREEDENERGY = n;
    }

    /**
     * Sets fish initial energy to @param n
     */
    public void setFishInitEnergy(int n) {
	FISH_INITENERGY = n;
    }

    /**
     * Sets shark initial energy to @param n
     */
    public void setSharkInitEnergy(int n) {
	SHARK_INITENERGY = n;
    }  
}

