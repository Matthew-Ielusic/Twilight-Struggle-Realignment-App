package application.logic;

import java.util.ArrayList;
import java.util.HashMap;

import utils.DoubleMap;
import utils.TupleInt;

public class PossibilityManager {
	/**
	 * At the moment I write this code (December 2016), I've been writing Python code so long I can only
	 * think in terms of procedural code. So everything here is static when it probably shouldn't be...
	 */
	
	/*
	 * Refactoring time: This will be a class with instance fields and methods.  Fields are:
	 *  - Four DoubleMaps.  One for "after 1 realign", one for "after 2 realigns", and so on.
	 */
	
	private Possibility basis = new Possibility(0, 0);
		
	public DoubleMap<TupleInt> oddsMapFirst = new DoubleMap<>();
	public DoubleMap<TupleInt> oddsMapSecond = new DoubleMap<>();
	public DoubleMap<TupleInt> oddsMapThird = new DoubleMap<>();
	public DoubleMap<TupleInt> oddsMapFourth = new DoubleMap<>();

	
	
	public void update(int usInf, int ussrInf) {
		basis = new Possibility(usInf, ussrInf);
		oddsMapFirst.clear();
		ArrayList<Possibility> outcomeFirst = basis.realign();
		for (Possibility pos : outcomeFirst) {
			oddsMapFirst.increment(pos.toTuple());
		}
		oddsMapFirst.scale();
		
		this.feedOddsForward(this.oddsMapFirst, this.oddsMapSecond);
		this.feedOddsForward(this.oddsMapSecond, this.oddsMapThird);
		this.feedOddsForward(this.oddsMapThird, this.oddsMapFourth);
	}
	
	private void feedOddsForward(DoubleMap<TupleInt> oddsCurrent, DoubleMap<TupleInt> oddsNext) {
		oddsNext.clear();
		for (TupleInt key : oddsCurrent.keySet()) {
			ArrayList<Possibility> outcomesHere = new Possibility(key).realign();
			DoubleMap<TupleInt> oddsHere = new DoubleMap<>();
			for (Possibility pos : outcomesHere) {
				oddsHere.increment(pos.toTuple());
			}		
			oddsHere.scale();
			for (TupleInt tpl : oddsHere.keySet()) {
				oddsNext.add(tpl, oddsHere.get(tpl) * oddsCurrent.get(key));
			}
		}
		oddsNext.scale();
	}
	


}
