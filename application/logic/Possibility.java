package application.logic;

import java.util.ArrayList;

import utils.TupleInt;

public class Possibility {

	/** Number of USA-controlled countries adjacent to the country in question. */
	private static int adjAm;
	
	/** Number of USSR-controlled countries adjacent to the country in question. */
	private static int adjRus;
	
	/** The USA's influence in this scenario. "influenceAmerica". */
	private int infAm;
	/** The USSR's influence in this scenario. "influenceRussia". */
	private int infRus;
	
	public Possibility(int usInfluence, int ussrInfluence) {
		infAm = usInfluence;
		infRus = ussrInfluence;
	}
	
	public Possibility(TupleInt tpl) {
		infAm = tpl.x;
		infRus = tpl.y;
	}
	
	public ArrayList<Possibility> realign() {
		ArrayList<Possibility> ret = new ArrayList<>();
		for (int blueDie = 1; blueDie <= 6; blueDie++) {
			for (int redDie = 1; redDie <= 6; redDie++) {
				int newAm, newRus;
				int netChange = redDie - blueDie; //Net change positive: US will lose influence.  Net change negative: USSR will lose influence.
				netChange += (adjRus - adjAm);
				if (infAm < infRus)
					netChange++;
				else if (infRus < infAm)
					netChange--;
				int lossAm = Math.max(netChange, 0);
				newAm = Math.max(infAm - lossAm, 0);
				
				int lossRus = Math.max(netChange * -1, 0);
				newRus = Math.max(infRus - lossRus, 0);
				ret.add(new Possibility(newAm, newRus));

			}
		}
		return ret;
	}
	
	public int getUSAInfluence() {
		return infAm;
	}
	
	public int getUSSRInfluence() {
		return infRus;
	}
	
	public static void setUSSRControlledAdjacent(int adj) {
		adjRus = adj;
	}
	
	public static void setUSControlledAdjacent(int adj) {
		adjAm = adj;
	}
	
	public TupleInt toTuple() {
		return new TupleInt(infAm, infRus);
	}
	
	@Override
	public String toString() {
		return "Possibility (am: " + infAm + ", rus: " + infRus + ")";
	}
	
}
