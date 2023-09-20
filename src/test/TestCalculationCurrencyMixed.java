package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyMixed {

	static ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeAll
	public static void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 1 cent and 50  re should make the display report 4 minutes parking time.
	 */
	@Test
	public void shouldDisplay4MinFor1CentAnd1Ore() throws IllegalCoinException {
		// Arrange

        int expectedParkingTime = 4;	// In minutes

        //Coin 1
		int dkkValue = 50;
		Currency.ValidCurrency oreCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType oreType = Currency.ValidCoinType.FRACTION;

        //Coin 2
		int euroValue = 1;
		Currency.ValidCurrency euroCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType euroType = Currency.ValidCoinType.FRACTION;
		
		// Act
        
        ps.addPayment(dkkValue, oreCurrency, oreType);
        ps.addPayment(euroValue, euroCurrency, euroType);

		// Assert
		assertEquals(expectedParkingTime, ps.readDisplay());		
	}

    @Test
	public void shouldDisplay4MinFor1CentAnd1OreFail() throws IllegalCoinException {
		// Arrange

        int expectedParkingTime = 5;	// In minutes

        //Coin 1
		int dkkValue = 50;
		Currency.ValidCurrency oreCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType oreType = Currency.ValidCoinType.FRACTION;

        //Coin 2
		int euroValue = 1;
		Currency.ValidCurrency euroCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType euroType = Currency.ValidCoinType.FRACTION;
		
		// Act
        
        ps.addPayment(dkkValue, oreCurrency, oreType);
        ps.addPayment(euroValue, euroCurrency, euroType);

		// Assert
		assertNotEquals(expectedParkingTime, ps.readDisplay());		
	}

	
	/** Fixture for pay station testing. */
	@AfterAll
	public static void cleanUp() {
		ps.setReady();
	}
	
}
