package mas_a3;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Bote {
	
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	
	private String name;
	
	
	public Bote(ContinuousSpace<Object> space, Grid<Object> grid, int id) {
		this.space = space;
		this.grid = grid;
		name = "Bote_" + id;
	}

	//@ScheduledMethod(start = 1, interval = 1)
	public void run() {
		
	}
	
	public void moveTowards(GridPoint pt) {
		if (!pt.equals(grid.getLocation(this))) {
//			moved = true;
			NdPoint myPoint = space.getLocation(this);
			NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
			double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint, otherPoint);
			space.moveByVector(this, 1, angle, 0);
			myPoint = space.getLocation(this);
			grid.moveTo(this, (int) myPoint.getX(), (int) myPoint.getY());
//			checkArrived(pt);
		}
	}
	
	public void receiveMessage(FipaMessage m) {
		if (m.receiver.equals(name)) {
			switch (m.perfomative) {
			case accept:
				System.out.println("Wurde akzeptiert? Oder sowas ähnliches");
				break;

			default:
				break;
			}
		}
	}
	
	public String getName() {
		return name;
	}

	
	// public void verhandeln?
}
