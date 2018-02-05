package configuration.datatemplates;

import java.util.Arrays;
import java.util.List;

import simulation.cell.*;
import simulation.grid.Grid;
import simulation.grid.StandardGrid;
import simulation.ruleSet.*;

public class WaTorXMLData extends XMLData {
	
	private int FISH = 0;
	private int SHARK = 1; 
	private int VACANT = 2;
	
	private int FISHENERGY;
	private int SHARKENERGY;
	private int NOENERGY = 0;
	
	public static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell",
			"fishBreedTime",
			"fishEnergy",
			"sharkInitEnergy",
			"sharkBreedEnergy",
	});

	
	public WaTorXMLData() {
		super();
	}

	@Override
	public WaTorRuleset getRules() {
		int fishBreedTime = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(5)));
		int sharkBreedTime = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(6)));
		this.SHARKENERGY = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(7)));
		this.FISHENERGY = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(8)));
		return new WaTorRuleset(fishBreedTime, sharkBreedTime);
	}
	
	@Override
	public List<String> getDataField() {
		return DATA_FIELDS;
	}
	
	@Override
	public Grid getGrid() {
		int state;
		Grid g = new StandardGrid(this.getXSize(), this.getYSize());
		String[] ints = myDataValues.get(DATA_FIELDS.get(4)).split("\\W+");
		int p=0;
		for(int r= 0; r < this.getXSize(); r++) {
		//	System.out.println("HERE");
			for(int c = 0; c < this.getYSize(); c++) {
				state = Integer.parseInt(ints[p]);
				if(state == FISH) {
					g.addCell(r, c, new WaTorCell(r, c, state, FISHENERGY));
				}
				else if(state == SHARK) {
					g.addCell(r, c, new WaTorCell(r, c, state, SHARKENERGY));
				}
				else {
					g.addCell(r, c, new WaTorCell(r, c, state, NOENERGY));
				}
				p++;
			}
		}
		return g;
	}

}
