import org.junit.Test;
import series_generator.Range;
import series_generator.SeriesGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import static config.Constants.Interval.HOUR;
import static config.Constants.VALUE;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SeriesGeneratorTest {

	@Test
	public void generateSeries_WithoutAnomaly() {
		Range range = new Range(50, 60);
		
		ArrayList<HashMap<String, Object>> series = new SeriesGenerator(range, HOUR)
				.generateSeries(5)
				.generateSeries(12)
				.getSeries();

		for (int i = 0; i < series.size(); i++) {
			Integer value = (Integer) series.get(i).get(VALUE);
			assertTrue(value >= range.getMin());
			assertTrue(value <= range.getMax());
		}

		assertEquals(series.size(), 17);
	}

	@Test
	public void generateSeries_WithAnomaly() {
		Range range = new Range(50, 60);
		ArrayList<HashMap<String, Object>> series = new SeriesGenerator(range, HOUR)
				.generateSeries(5)
				.generateAnomaly()
				.generateSeries(12)
				.getSeries();

		ArrayList<HashMap<String, Object>> anomalies = series
				.stream()
				.filter(d -> ((Integer) d.get(VALUE) < range.getMin() || (Integer) d.get(VALUE) > range.getMax()))
				.collect(Collectors.toCollection(ArrayList::new));

		assertEquals(anomalies.size(), 1);
	}
	
	@Test
	public void generateSeries_WithAnomalies() {
		Range range = new Range(50, 60);
		ArrayList<HashMap<String, Object>> series = new SeriesGenerator(range, HOUR)
				.generateSeries(1)
				.generateAnomalies(3)
				.generateSeries(1)
				.getSeries();
		
		ArrayList<HashMap<String, Object>> anomalies = series
				.stream()
				.filter(d -> ((Integer) d.get(VALUE) < range.getMin() || (Integer) d.get(VALUE) > range.getMax()))
				.collect(Collectors.toCollection(ArrayList::new));
		
		assertEquals(anomalies.size(), 3);
	}
}
