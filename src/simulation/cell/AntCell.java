package simulation.cell;

import java.util.Stack;

import javafx.scene.paint.Color;

public class AntCell extends Cell {
	
	private int BARE = 0;
	private int FOOD = 1;
	private int NEST = 2;
	private int OBSTACLE = 4;
	private int myAnts;
	private Stack<Integer> myOrientations;
	private Stack<Integer> myFoods;
	private double myFoodPheromone;
	private double myHomePheromone;
	private double myMaxPheromone;

	public AntCell(int x, int y, int state) {
		super(x, y, state);
		myAnts = 0;
	}

	@Override
	public void setState(int state) {
		myPreviousState = myState;
		myNextState = state;
	}
	
	public int getState() {
		return myState;
	}

	@Override
	public Color colorCell() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean hasFood() {
		if (myFoods.peek() == 1) return true;
		return false;
	}

}
