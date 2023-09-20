package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import modellayer.Currency;

public class TestInvalidAndValidCoins {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}
	
	// TEST 4 - VALID AND INVALID COINS
	@Test
	public void shouldRejectIllegalCurrencyNokCoinAndAcceptDKK() throws IllegalCoinException {
		// Arrange
		boolean exceptionThrown = false;
		int expectedParkingTime = 27;
		int coinValue = 5;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.NKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		int coinValue2 = 5;
		Currency.ValidCurrency coinCurrency2 = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType2 = Currency.ValidCoinType.INTEGER;
		//Act
		try {
			
			ps.addPayment(coinValue2, coinCurrency2, coinType2);
			ps.addPayment(coinValue, coinCurrency, coinType);

		
	} catch (IllegalCoinException e) {
		exceptionThrown = true;
		
	}
		//Assert
		assertTrue(exceptionThrown);
		assertEquals(expectedParkingTime, ps.readDisplay());
		
		
	    
	}
	
}