package mas_a3;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Ziel {

	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	
	public Ziel (ContinuousSpace<Object> space, Grid<Object> grid) {
		this.space = space;
		this.grid = grid;
	}
	
}
