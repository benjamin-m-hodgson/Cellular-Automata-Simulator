package simulation.neighbormanager;

import java.util.ArrayList;
import simulation.cell.Cell;
import simulation.grid.Grid;

public class SugarNeighborManager extends NeighborManager {
	
	public Cell[] getNeighbors(Cell c, Grid g, int vision){
		ArrayList<Cell> neighborsList = new ArrayList<Cell>();
		int x = c.getX();
		int y = c.getY();
		for (int k = 1; k <= vision; k++) {
			if(x + k < g.getXSize()) {
				neighborsList.add(g.getCell(x + k, y));
			}
			if(x - k >= 0) {
				neighborsList.add(g.getCell(x - 1, y));
			}
			if(y + k < g.getYSize()) {
				neighborsList.add(g.getCell(x, y + 1));
			}
			if(y - k >= 0) {
				neighborsList.add(g.getCell(x, y - 1));
			}
		}
		Cell[] neighbors = neighborsList.toArray(new Cell[neighborsList.size()]);
		return neighbors;
	}

}
