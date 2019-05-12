package series_generator;

import java.util.Random;

public class Range {
	int fMin;
	int fMax;
	
	public Range(int min, int max) {
		if (min >= max)
			throw new IllegalArgumentException("Range is invalid.");
		setMin(min);
		setMax(max);
	}

	public int getMin() {
		return fMin;
	}

	public void setMin(Integer min) {
		fMin = min;
	}

	public int getMax() {
		return fMax;
	}

	public void setMax(Integer max) {
		fMax = max;
	}

	/* */
	public int getIntInRange() {
		return new Random().nextInt((fMax - fMin) + 1) + fMin;
	}

	public static int getIntInRange(Range range) {
		return new Random().nextInt((range.getMax() - range.getMin()) + 1) + range.getMin();
	}

	public int getIntAboveRange() {
		Range aboveRange = new Range(getMax(), getMax() * 3);
		return new Random().nextInt((aboveRange.getMax() - aboveRange.getMin()) + 1) + aboveRange.getMin();
	}

	public int getIntBelowRange() {
		Range belowRange = new Range(0, getMin());
		return new Random().nextInt((belowRange.getMax() - belowRange.getMin()) + 1) + belowRange.getMin();
	}

	public int getIntOutsideRange() {
		if (getIntInRange(new Range(0, 1)) == 0)
			return getIntAboveRange();
		else
			return getIntBelowRange();
	}

}
