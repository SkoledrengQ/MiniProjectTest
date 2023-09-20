package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;

import controllayer.*;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestReset {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that the pay station is cleared and display shows 0 after a buy scenario
	 */
	@Test
	public void shouldClearAfterBuy() throws IllegalCoinException, Exception {
		//

        int coinValue = 50;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;

        ps.addPayment(coinValue, coinCurrency, coinType);

        assertEquals(3, ps.readDisplay());

        ps.buy();

        assertEquals(0,ps.readDisplay());
	}

	/**
	 * Verify that cancel() clears the pay station
	 */
	//@Test Department added functionality for canceling
	@Test
	public void shouldClearAfterCancel() throws IllegalCoinException {
		
		int coinValue = 50;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		ps.addPayment(coinValue, coinCurrency, coinType);
		
		assertEquals(3, ps.readDisplay());
		
		ps.cancel();
		assertEquals(0, ps.readDisplay());
	}
}
