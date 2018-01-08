package mas_a3;

import mas_a3.FipaMessage.Performative;
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

	private Koordinator coordinator;

	private boolean delivery;

	private Lieferung lieferung;
	private Ziel ziel;

	public Bote(ContinuousSpace<Object> space, Grid<Object> grid, int id) {
		this.space = space;
		this.grid = grid;
		name = "Bote_" + id;
		setDelivery(false);
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void run() {
		if (isDelivery()) {
			GridPoint pt = grid.getLocation(ziel);
			moveTowards(pt);
		}
	}

	public void moveTowards(GridPoint pt) {
		if (!pt.equals(grid.getLocation(this))) {
			NdPoint myPoint = space.getLocation(this);
			NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
			double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint, otherPoint);
			space.moveByVector(this, 1, angle, 0);
			myPoint = space.getLocation(this);
			grid.moveTo(this, (int) myPoint.getX(), (int) myPoint.getY());
		}
		checkArrived(pt);
	}

	private void checkArrived(GridPoint pt) {

		GridPoint myPos = grid.getLocation(this);

//		System.out.println("Bote arrived? " + grid.getDistance(myPos, pt));
		if (grid.getDistance(myPos, pt) <= 1) {
			setDelivery(false);
			lieferung.setDelivered(true);
		}
	}

	public void receiveMessage(FipaMessage m) {
		if (m.receiver.equals(name)) {
			switch (m.perfomative) {
			case accept:
				// Koordinator hat Boten registriert
				this.coordinator = (Koordinator) m.content;
				break;
			case inform:
				// Information über mögliche Lieferungen / Berechnung von Kosten
				// für Lieferungen
				handleInform(m);
				break;
			case agree:
				// Zugewiesene Lieferung für den Boten
				setDelivery(m);
				break;
			default:
				System.out.println("Bote ... hmm...");
				break;
			}
		}
	}

	private void setDelivery(FipaMessage m) {
		if (!isDelivery()) {
			setDelivery(true);
			System.out.println("Received new delivery!");
			lieferung = (Lieferung) m.content;
			lieferung.setInDelivery(true);
			ziel = lieferung.getZiel();
		} else {
			System.out.println("---- Nah");
		}
	}

	public String getName() {
		return name;
	}

	private void handleInform(FipaMessage m) {
		if (m.sender.equals(coordinator.name)) {
			FipaMessage ans = new FipaMessage();

			if (m.conversationId == 0) {
				/*
				 * Information über vorhandene Lieferungen muss ein agree an
				 * Koordinator schicken
				 */
				if (isDelivery()) {
					System.out.println("I'm delivering no time for more");
					ans.perfomative = Performative.disconfirm;
				} else {
					System.out.println("I can do dat");
					ans.perfomative = Performative.agree;
				}
				ans.sender = name;
				ans.receiver = coordinator.name;
			} else {
				/*
				 * Wenn convoId > 1 ist bedeutet es es kommen Lieferugen für die
				 * Kosten berechnet werden müssen und ein Lieferkosten (evlt.
				 * Array) an den Koordinator schicken
				 */
				int arrLength = ((Object[]) m.content).length;
				Lieferung[] lieferungen = new Lieferung[arrLength];// (Lieferung[])
																	// m.content;
				System.arraycopy(m.content, 0, lieferungen, 0, arrLength);
				Lieferkosten[] lk = calculateCost(lieferungen);

				ans.perfomative = Performative.inform;
				ans.sender = name;
				ans.receiver = coordinator.name;
				ans.content = lk;
			}

			coordinator.receiveMessage(ans);
		}

	}

	private Lieferkosten[] calculateCost(Lieferung[] lieferungen) {
		GridPoint pt = grid.getLocation(this);

		Lieferkosten[] lk = new Lieferkosten[lieferungen.length];

		for (int i = 0; i < lieferungen.length; i++) {
			Lieferkosten tmp = new Lieferkosten();

			tmp.lieferung = lieferungen[i];
			tmp.bote = this;

			GridPoint zielPt = grid.getLocation(lieferungen[i].getZiel());
			tmp.kosten = (int) grid.getDistance(pt, zielPt);

			lk[i] = tmp;
		}

		return lk;
	}

	private boolean isDelivery() {
		return delivery;
	}

	private void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}

	// public void verhandeln?
}
