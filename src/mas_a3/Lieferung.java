package mas_a3;

public class Lieferung {

	private Ziel ziel;
	private boolean delivered;

	public Lieferung(Ziel z) {
		this.ziel = z;
		delivered = false;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public Ziel getZiel() {
		return ziel;
	}

}
