package series_generator;

import tech.tablesaw.api.DateTimeColumn;
import tech.tablesaw.api.FloatColumn;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.ScatterTrace;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static config.Constants.*;

public class SeriesVisualizer {
	
	public static void visualizeSeries(ArrayList<HashMap<String, Object>> series) {
		DateTimeColumn dateTimeColumn = buildDateTimeColumn(series);
		FloatColumn floatColumn = buildFloatColumn(series);

		Layout layout = Layout.builder()
				.height(700)
				.width(1200)
				.build();

		ScatterTrace trace = ScatterTrace.builder(dateTimeColumn, floatColumn)
				.mode(ScatterTrace.Mode.LINE)
				.build();

		Figure figure = new Figure(layout, trace);
		File outputFile = Paths.get(VISUALIZER_OUTPUT_PATH).toFile();

		Plot.show(figure, "target", outputFile);
	}
	
	private static DateTimeColumn buildDateTimeColumn(ArrayList<HashMap<String, Object>> series) {
		DateTimeColumn dateTimeColumn = DateTimeColumn.create("DateTime");
		series.stream().forEach(s ->
				dateTimeColumn.append(
						LocalDateTime.of(
								Integer.parseInt(s.get(TIMESTAMP).toString().split(T)[0].substring(0, 4)),
								Integer.parseInt(s.get(TIMESTAMP).toString().split(T)[0].substring(5, 7)),
								Integer.parseInt(s.get(TIMESTAMP).toString().split(T)[0].substring(8, 10)),
								Integer.parseInt(s.get(TIMESTAMP).toString().split(T)[1].substring(0, 2)),
								Integer.parseInt(s.get(TIMESTAMP).toString().split(T)[1].substring(3, 5))
						)
				)
		);
		return dateTimeColumn;
	}

	private static FloatColumn buildFloatColumn(ArrayList<HashMap<String, Object>> series) {
		FloatColumn floatColumn = FloatColumn.create("Values");
		series.stream().forEach(s -> floatColumn.append(new Float((Integer) s.get(VALUE))));
		return floatColumn;
	}

}
