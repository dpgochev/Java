package blocks;

public class Pair {
	int first;
	int second;

	public Pair(int first, int second) {
		this.first = first;
		this.second = second;

	}

	public int getFirst() {
		return this.first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return this.second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public String toString() {

		return " " + first + " " + second;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + first;
		result = prime * result + second;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
	 
		Pair other = (Pair) obj;
		if (first != other.first)
			return false;
		if (second != other.second)
			return false;
		return true;
	}

}
