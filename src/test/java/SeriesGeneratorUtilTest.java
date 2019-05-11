import org.junit.Assert;
import org.junit.Test;
import series_generator.Range;
import series_generator.SeriesGeneratorUtil;

public class SeriesGeneratorUtilTest {
	
	@Test
	public void getRandomInt_inRange() {
		Range range = new Range(10, 20);
		int someInt = SeriesGeneratorUtil.getIntInRange(range);
		Assert.assertTrue(someInt <= range.getMax());
		Assert.assertTrue(someInt >= range.getMin());
	}

	@Test
	public void getRandomInt_belowRange() {
		Range range = new Range(10, 20);
		int someInt = SeriesGeneratorUtil.getIntBelowRange(range);
		Assert.assertTrue(someInt <= range.getMin());
	}

	@Test
	public void getRandomInt_aboveRange() {
		Range range = new Range(10, 20);
		int someInt = SeriesGeneratorUtil.getIntAboveRange(range);
		Assert.assertTrue(someInt >= range.getMax());
	}

}
