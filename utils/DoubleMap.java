package utils;

import java.util.Collection;
import java.util.HashMap;

/**
 * Extension of HashMap specifically for maps to doubles.
 * Supports get() from keys that don't exist, incrementing values,
 * and scaling values (dividing all elements by the sum of all elements, so the new
 * sum of all elements is 1).
 * Do not serialize, some kind of field I don't understand is missing.
 * @param <K>
 */
public class DoubleMap<K> extends HashMap<K, Double> {
	@Override
	public Double get(Object key) {
		Double gt = super.get(key);
		if (gt == null) {
			return 0.0;
		} else {
			return gt;
		}
	}
	
	public void add(K key, double toAdd) {
		Double prev = this.get(key);
		this.put(key, prev + toAdd);
	}
	
	public void increment(K key) {
		Double prev = this.get(key);
		this.put(key,  prev + 1);
	}
	
	public void scale() {
		double sum = 0;
		for (double val : this.values()) {
			sum += val;
		}
		for (K key : this.keySet()) {
			this.put(key, this.get(key) / sum);
		}
	}
}
