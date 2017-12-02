package bg.uni.sofia.fmi.mjt.lambda;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import bg.uni.sofia.fmi.mjt.lambda.Peak;

public class PeakTest {
	private static final double DELTA = 1e-5;

	@Test
	public void testCreatePeak() {
		String first = "1,Mount Everest,8848.0,8848.0,Mahalangur Himalaya,1953,121";
		Peak everest = Peak.createPeak(first);

		assertEquals(everest.getName(), "Mount Everest");
		assertEquals(everest.getPos(), 1);
		assertEquals(everest.getHeight(), 8848.0, DELTA);
		assertEquals(everest.getProminence(), 8848.0, DELTA);
		assertEquals(everest.getRange(), "Mahalangur Himalaya");
		assertEquals(everest.getFirstAscent(), 1953);
		assertEquals(everest.getTotalAscents(), 121);

	}

	@Test
	public void testPeakStats() throws IOException {
		PeakStats a =new PeakStats("a.txt");

	}

}
