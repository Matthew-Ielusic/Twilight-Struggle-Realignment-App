package utils;

public class TupleInt {
	public final int x;
	public final int y;
	public TupleInt(int xIn, int yIn) {
		this.x = xIn;
		this.y = yIn;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TupleInt) {
			TupleInt tp = (TupleInt) obj;
			return tp.x == this.x && tp.y == this.y;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		final int hashCnst1 = 31;
		final int hashCnst2 = 23;
		int hash = hashCnst1;
		hash = hash * hashCnst2 + this.x;
		hash = hash * hashCnst2 + this.y;
		return hash;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("(" + this.x + "," + this.y + ")");
		return s.toString();
	}
}

