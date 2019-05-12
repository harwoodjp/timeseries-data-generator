import org.junit.Assert;
import org.junit.Test;
import series_generator.Range;

public class RangeTest {
	
	@Test
	public void getRandomInt_inRange() {
		Range range = new Range(10, 20);
		int someInt = range.getIntInRange();
		Assert.assertTrue(someInt <= range.getMax());
		Assert.assertTrue(someInt >= range.getMin());
	}

	@Test
	public void getRandomInt_belowRange() {
		Range range = new Range(10, 20);
		int someInt = range.getIntBelowRange();
		Assert.assertTrue(someInt <= range.getMin());
	}

	@Test
	public void getRandomInt_aboveRange() {
		Range range = new Range(10, 20);
		int someInt = range.getIntAboveRange();
		Assert.assertTrue(someInt >= range.getMax());
	}

}
