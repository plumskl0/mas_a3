package mas_a3;

import java.util.ArrayList;
import java.util.HashMap;

import mas_a3.FipaMessage.Performative;

public class Koordinator {

	private ArrayList<Ziel> ziele;
	private ArrayList<Lieferung> lieferungen;

	private HashMap<String, Bote> boten = new HashMap<String, Bote>();

	public String name = "Koordinator";

	public Koordinator(ArrayList<Ziel> ziele, ArrayList<Lieferung> lieferungen) {
		this.ziele = ziele;
		this.lieferungen = lieferungen;
	}

	public void run() {
		// Was muss der machen?
		// Lieferanten fragen, ob sie was machen wollen.
		// Lieferanten das Ziel mitteilen.
		// Kosten der Lieferanten vergleichen.
		// Lieferung dem Lieferanten zuweisen.
		
	}

	public void receiveMessage(FipaMessage m) {
		if (m.receiver.equals(name)) {
			switch (m.perfomative) {
			case register:
				registriereBote(m);
				break;

			default:
				break;
			}
		}
	}

	private void registriereBote(FipaMessage m) {
		String sender = m.sender;
		boten.put(sender, (Bote) m.content);

		m.conversationId++;
		m.perfomative = Performative.accept;
		m.sender = name;
		m.receiver = sender;
		m.content = this;
		
		response(m);
	}

	private void response(FipaMessage m) {
		Bote b = boten.get(m.receiver);
		b.receiveMessage(m);
	}
}
