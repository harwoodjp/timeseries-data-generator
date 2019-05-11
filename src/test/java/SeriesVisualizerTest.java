import org.junit.Ignore;
import org.junit.Test;
import series_generator.Range;
import series_generator.SeriesGenerator;

import static config.Constants.Interval.HOUR;
import static series_generator.SeriesVisualizer.visualizeSeries;

public class SeriesVisualizerTest {
	
	@Test
	@Ignore
	public void visualizeSeries_plotsSeries() {
		SeriesGenerator sg = new SeriesGenerator(new Range(1800, 2000), HOUR);
		sg.generateSeries(40);
		sg.generateAnomalies(1);
		sg.generateSeries(100);

		visualizeSeries(sg.getSeries());
	}


}
