package test;


import static org.junit.Assert.assertTrue;

import org.junit.*;

import controllayer.*;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestIllegalCoin {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that illegal coins are rejected.
	 */
	
	
	
	// Norwegian coin
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalCurrencyNokCoin() throws IllegalCoinException {
		// Arrange
		boolean exceptionThrown = false;
		int coinValue = 5;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.NKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		//Act
		try {
			
			ps.addPayment(coinValue, coinCurrency, coinType);
		
	} catch (IllegalCoinException e) {
		exceptionThrown = true;
	} finally {
		ps.addPayment(coinValue, coinCurrency, coinType);
		
	}
		//Assert
		assertTrue(exceptionThrown);
		
		
	    
	}
	
}
