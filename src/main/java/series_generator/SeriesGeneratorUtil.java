package series_generator;

import java.util.Random;

public class SeriesGeneratorUtil {
	public static int getIntInRange(Range range) {
		return new Random().nextInt((range.getMax() - range.getMin()) + 1) + range.getMin();
	}

	public static int getIntAboveRange(Range range) {
		Range aboveRange = new Range(range.getMax(), range.getMax() * 3);
		return new Random().nextInt((aboveRange.getMax() - aboveRange.getMin()) + 1) + aboveRange.getMin();
	}

	public static int getIntBelowRange(Range range) {
		Range belowRange = new Range(0, range.getMin());
		return new Random().nextInt((belowRange.getMax() - belowRange.getMin()) + 1) + belowRange.getMin();
	}
	
	public static int getIntOutsideRange(Range range) {
		if (getIntInRange(new Range(0, 1)) == 0)
			return getIntAboveRange(range);
		else 
			return getIntBelowRange(range);
	}
}
