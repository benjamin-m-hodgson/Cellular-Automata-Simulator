package simulation.cell;

import javafx.scene.shape.Rectangle;

public class SugarCell extends Cell {

	private int PATCH = 0;
	private int AGENT = 1;
	private int mySugar;
	private int myMaxSugar;
	private int myAgentSugar;
	private int myAgentMetabolism;
	private int myAgentVision;
	private int myTicks;
	
	public SugarCell(int x, int y, int state, int sugar, int maxSugar, int agentSugar, int agentMetabolism, int agentVision) {
		super(x, y, state);
		mySugar = sugar;
		myMaxSugar = maxSugar;
		myAgentSugar = agentSugar;
		myAgentMetabolism = agentMetabolism;
		myAgentVision = agentVision;
	}

	@Override
	public void setState(int state) {
		myPreviousState = myState;
		myNextState = state;
	}

	@Override
	public void colorCell(int state) {
		// TODO Sugar color should have gradient proportional to mySugar, Agent color should be different

	}
	
	public int getTicks() {
		return myTicks;
	}
	
	public void resetTicks() {
		myTicks = 0;
	}
	
	public int getSugar() {
		return mySugar;
	}
	
	public int getAgentSugar() {
		return myAgentSugar;
	}
	
	public void setAgentSugar(int sugar) {
		myAgentSugar = sugar;
	}
	
	public void setSugar(int sugar) {
		mySugar = sugar;
	}
	
	public int getMaxSugar() {
		return myMaxSugar;
	}
	
	public int getVision() {
		return myAgentVision;
	}
	
	public void setVision(int vision) {
		myAgentVision = vision;
	}
	
	public int getMetabolism() {
		return myAgentMetabolism;
	}
	
	public void setMetabolism(int metabolism) {
		myAgentMetabolism = metabolism;
	}

}
