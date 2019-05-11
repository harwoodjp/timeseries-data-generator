import series_generator.Range;
import series_generator.SeriesGenerator;

import java.util.ArrayList;
import java.util.HashMap;

import static config.Constants.Interval.MINUTE;
import static series_generator.SeriesVisualizer.visualizeSeries;

public class Main {
	public static void main(String[] args) {
		ArrayList<HashMap<String, Object>> series = new SeriesGenerator(new Range(50, 60), MINUTE)
				.generateSeries(25)
				.generateAnomalies(2)
				.generateSeries(25)
				.getSeries();
		
		visualizeSeries(series);
	}
}
