package mas_a3;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Ziel {

	private ContinuousSpace<Object> space;
	private Grid<Object> grid;

	private int x;
	private int y;

	public Ziel(ContinuousSpace<Object> space, Grid<Object> grid, int x, int y) {
		this.space = space;
		this.grid = grid;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
