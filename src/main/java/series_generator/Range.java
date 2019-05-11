package series_generator;

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
}
