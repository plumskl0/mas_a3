package mas_a3;

import java.util.ArrayList;
import java.util.Random;

import mas_a3.FipaMessage.Performative;
import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.SimpleCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.StrictBorders;

public class CustomBuilder implements ContextBuilder<Object> {

	private ContinuousSpace<Object> space;
	private Grid<Object> grid;

	private ArrayList<Ziel> zielListe = new ArrayList<Ziel>();
	private ArrayList<Lieferung> lieferungListe = new ArrayList<Lieferung>();

	private Koordinator coordinator;
	
	@Override
	public Context build(Context<Object> context) {
		context.setId("mas_a3");

		// Space und Grid
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		space = spaceFactory.createContinuousSpace("space", context, new SimpleCartesianAdder<>(),
				new repast.simphony.space.continuous.StrictBorders(), 50, 50);

		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);

		grid = gridFactory.createGrid("grid", context,
				new GridBuilderParameters<Object>(new StrictBorders(), new SimpleGridAdder<Object>(), true, 50, 50));

		// Ziele
		erzeugeZiele(context, new Ziel(space, grid, 10, 10));
		erzeugeZiele(context, new Ziel(space, grid, 10, 40));
		erzeugeZiele(context, new Ziel(space, grid, 40, 10));
		erzeugeZiele(context, new Ziel(space, grid, 40, 40));

		// Lieferungen
		erzeugeLieferung(2);

		// Verhandlung?
		coordinator = new Koordinator(zielListe, lieferungListe);

		// Boten
		erzeugeBote(context, new Bote(space, grid, 1), 24, 25);
		erzeugeBote(context, new Bote(space, grid, 2), 26, 25);

		return context;
	}

	private void erzeugeZiele(Context<Object> ctx, Ziel z) {
		ctx.add(z);
		space.moveTo(z, (int) z.getX(), (int) z.getY());
		grid.moveTo(z, (int) z.getX(), (int) z.getY());
		zielListe.add(z);
	}

	private void erzeugeBote(Context<Object> ctx, Bote b, int x, int y) {
		ctx.add(b);
		space.moveTo(b, (int) x, (int) y);
		grid.moveTo(b, (int) x, (int) y);
		
		// Bote meldet sich beim Koordinator an
		FipaMessage m = new FipaMessage();
		
		m.perfomative = Performative.register;
		m.sender = b.getName();
		m.content = b;
		m.receiver = coordinator.name;
		
		coordinator.receiveMessage(m);
	}

	private void erzeugeLieferung(int cnt) {
		Random r = new Random();

		for (int i = 0; i < cnt; i++) {
			lieferungListe.add(new Lieferung(zielListe.get(r.nextInt(zielListe.size()))));
		}
	}
}
