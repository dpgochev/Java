package bg.uni.sofia.fmi.mjt.lambda;

/**
 * Representation of a peak with loaded data from the dataset. The class is
 * immutable. If you wish, you could provide suitable equals() and hashCode()
 * implementations
 */
import java.lang.UnsupportedOperationException;

public class Peak {

	private final int pos;
	private final String name;
	private final double height;
	private final double prominence;
	private final String range;
	private final int firstAscent;
	private final int totalAscents;

	private Peak(int pos, String name, double height, double prominence, String range, int firstAscent,
			int totalAscents) {
		this.pos = pos;
		this.name = name;
		this.height = height;
		this.prominence = prominence;
		this.range = range;
		this.firstAscent = firstAscent;
		this.totalAscents = totalAscents;
	}

	public static Peak createPeak(String line) {
		String[] array = line.split(",");
		String a =array[0];
		int pos = Integer.parseInt(a);
		
		 
		String name = array[1];
		String c =array[2];
		double height = Double.parseDouble(c);
		String d =array[3];
		double prominence = Double.parseDouble(d);
		String e =array[4];
		String range = new String(e);
		String f =array[5];
		int firstAscent = Integer.parseInt(f);
		String g =array[6];
		int totalAscents = Integer.parseInt(g);
		Peak newPeak = new Peak(pos,name,height,prominence,range,firstAscent,totalAscents);
		 

		 return newPeak;
		 
	}
	 
	public int getPos() {
		return pos;
	}

	public String getName() {
		return name;
	}

	public double getHeight() {
		return height;
	}

	public double getProminence() {
		return prominence;
	}

	public String getRange() {
		return range;
	}

	public int getFirstAscent() {
		return firstAscent;
	}

	public int getTotalAscents() {
		return totalAscents;
	}

}