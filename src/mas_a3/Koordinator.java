package mas_a3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import mas_a3.FipaMessage.Performative;
import repast.simphony.engine.schedule.ScheduledMethod;

public class Koordinator {

	private ArrayList<Ziel> ziele;
	private ArrayList<Lieferung> lieferungen;
	private ArrayList<String> transporter;
	private ArrayList<Lieferkosten> lieferKosten = new ArrayList<Lieferkosten>();
	private HashMap<String, Lieferung> minKost;

	private HashMap<String, Bote> boten = new HashMap<String, Bote>();

	public String name = "Koordinator";

	public Koordinator(ArrayList<Ziel> ziele, ArrayList<Lieferung> lieferungen) {
		this.ziele = ziele;
		this.lieferungen = lieferungen;
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void run() {
		// Lieferanten fragen, ob sie was machen wollen.
		informBote();

		// Lieferanten das Ziel mitteilen.
		informTransporter();

		// Kosten der Lieferanten vergleichen.
		calculateCosts();

		// Lieferung dem Lieferanten zuweisen.
		assignTransport();

		// evtl. noch was?
		lieferKosten.clear();
	}

	// Hier könnte noch ein Fehler drin sein, da zwei Boten die selbe Lieferung machen
	private void calculateCosts() {
		minKost = new HashMap<String, Lieferung>();
		Lieferkosten[] lk;

		// Quick and dirty fix
		if  (lieferKosten.size() < 1)
			return;
		
		do {
			Lieferkosten min = lieferKosten.get(0);
			Lieferung tmpL = min.lieferung;

			Object[] tmpArr = lieferKosten.stream().filter(l -> l.lieferung == tmpL).toArray();
			
			lk = new Lieferkosten[tmpArr.length];
			
			System.arraycopy(tmpArr, 0, lk, 0, tmpArr.length);
					
			for (int i = 0; i < lk.length; i++) {
				if (min.kosten > lk[i].kosten) {
					min = lk[i];
				}
			}

			minKost.put(min.bote.getName(), min.lieferung);
			Bote tmpB = min.bote;

			tmpArr = lieferKosten.stream().filter(l -> l.bote != tmpB).toArray();
			lk = new Lieferkosten[tmpArr.length];
			System.arraycopy(tmpArr, 0, lk, 0, tmpArr.length);
			
		} while (lk.length > 0);
	}

	public void receiveMessage(FipaMessage m) {
		if (m.receiver.equals(name)) {
			switch (m.perfomative) {
			case register:
				registriereBote(m);
				break;
			case agree:
				createTransporter(m);
				break;
			case inform:
				// Beim Inform liefern die Boten die Kosten der Lieferungen
				collectCosts(m);
				break;
			default:
				System.out.println("Koordi ... hmm...");
				break;
			}
		}
	}

	private void assignTransport() {
		minKost.forEach((k, v) -> {
			FipaMessage m = new FipaMessage();

			m.perfomative = Performative.agree;
			m.sender = name;
			m.receiver = k;
			m.content = v;

			boten.get(k).receiveMessage(m);
		});
		
		minKost.clear();
	}

	private void collectCosts(FipaMessage m) {
		// Lieferkosten aus Nachricht extrahieren
		Lieferkosten[] kosten = (Lieferkosten[]) m.content;
		for (Lieferkosten k : kosten)
			lieferKosten.add(k);
	}

	private void informTransporter() {
		if (transporter.size() > 0) {
			transporter.forEach(n -> {
				FipaMessage m = new FipaMessage();
				m.perfomative = Performative.inform;
				m.conversationId++;
				m.content = lieferungen.stream().filter(l -> !l.isDelivered()).toArray();
				m.sender = name;
				m.receiver = n;

				boten.get(n).receiveMessage(m);
			});
		}
	}

	private void informBote() {
		// Prüft ob es Lieferungen gibt die noch geliefert werden müssen
		int cntLieferungen = (int) lieferungen.stream().filter(l -> !l.isDelivered()).count();
		System.out.println("non delivered packages = " + cntLieferungen);
		if (cntLieferungen > 0) {
			// Informiert die Boten, dass Lieferugen noch vorhanden sind
			FipaMessage m = new FipaMessage();

			m.perfomative = Performative.inform;
			m.content = this;
			m.sender = name;

			boten.forEach((k, v) -> {
				m.receiver = v.getName();
				v.receiveMessage(m);
			});
		}

	}

	private void createTransporter(FipaMessage m) {
		transporter = new ArrayList<String>();
		transporter.add(m.sender);
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
