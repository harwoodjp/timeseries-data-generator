package series_generator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Stack;

import static config.Constants.*;

public class SeriesGenerator {
	private Range fRange;
	private Interval fInterval;
	private Calendar fCalendar;
	private Stack fSeriesStack = new Stack();
	private ArrayList<HashMap<String, Object>> fSeries = new ArrayList<>();

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	private enum SeriesType {NORMAL, ANOMALY}

	public SeriesGenerator(Range range, Interval interval) {
		fRange = range;
		fInterval = interval;
		fCalendar = Calendar.getInstance();
		fCalendar.add(Calendar.HOUR, -2);
	}

	public SeriesGenerator generateSeries(Integer count) {
		HashMap<SeriesType, Integer> series = new HashMap<>();
		series.put(SeriesType.NORMAL, count);
		fSeriesStack.add(series);
		return this;
	}

	private void decrementCalendar(Calendar calendar, Interval interval) {
		switch (interval) {
			case SECOND:
				calendar.add(Calendar.SECOND, -1);
				break;
			case MINUTE:
				calendar.add(Calendar.MINUTE, -1);
				break;
			case HOUR:
				calendar.add(Calendar.HOUR, -1);
				break;
		}
	}

	public SeriesGenerator generateAnomaly() {
		generateAnomalies(1);
		return this;
	}

	public SeriesGenerator generateAnomalies(int count) {
		HashMap<SeriesType, Integer> series = new HashMap<>();
		series.put(SeriesType.ANOMALY, count);
		fSeriesStack.push(series);
		return this;
	}

	public Range getRange() {
		return fRange;
	}

	public void setRange(Range range) {
		fRange = range;
	}

	public ArrayList<HashMap<String, Object>> getSeries() {
		processSeriesStack(fSeriesStack);
		return fSeries;
	}

	private void processSeriesStack(Stack stack) {
		while (!stack.empty()) {
			HashMap series = (HashMap) stack.pop();
			for (int i = 0; i < (int) series.values().toArray()[0]; i++)
				processSeries(series);
		}
	}

	private void processSeries(HashMap series) {
		HashMap<String, Object> datum = new HashMap();
		datum.put(TIMESTAMP, dateFormat.format(new Timestamp(fCalendar.getTimeInMillis())));
		if (series.keySet().toArray()[0] == SeriesType.NORMAL)
			datum.put(VALUE, SeriesGeneratorUtil.getIntInRange(fRange));
		else
			datum.put(VALUE, SeriesGeneratorUtil.getIntOutsideRange(fRange));
		fSeries.add(datum);
		decrementCalendar(fCalendar, fInterval);
	}
	
}