package simulation.ruleSet;
import java.util.ArrayList;

import simulation.cell.*;
import simulation.grid.*;

public class FireRuleset implements Ruleset {
	
	public FireRuleset(double probCatch) {
		
	}

	@Override
	public int processCell(Cell c, Cell[] neighbors) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int neighborCount(Cell[] neighbors) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cell[] getNeighbors(Cell c, Grid g) {
			int x = c.getX();
			int y = c.getY();
		
			ArrayList<Cell> neighbors = new ArrayList<Cell>();
			if (x == 0 && y == 0) {
				neighbors.add(g.getCell(x + 1, y));
				neighbors.add(g.getCell(x, y + 1));
				neighbors.add(g.getCell(x + 1, y + 1));
			} else if (x == 0 && y == g.getYSize() - 1) {
				neighbors.add(g.getCell(x + 1, y));
				neighbors.add(g.getCell(x + 1, y - 1));
				neighbors.add(g.getCell(x, y - 1));
			} else if (x == g.getXSize() && y == 0) {
				neighbors.add(g.getCell(x + 1, y));
				neighbors.add(g.getCell(x + 1, y));
				neighbors.add(g.getCell(x + 1, y));
			}
			
			return (Cell[]) neighbors.toArray();
		
	}

	

}
