package test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import databaselayer.DatabaseLayerException;
import modellayer.Currency;
import modellayer.PReceipt;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestBuyTicket {
    private static ControlPayStation ps;
    
    //TEST CASE 1 - Succesful: Buy ticket

    @BeforeAll
    public static void setUp() {
        ps = new ControlPayStation();
    }

    @AfterAll
    public static void cleanUp() {
        ps.setReady();
    }

    @Test
    public void shouldGet4MinFor1CentAnd50Ore() throws IllegalCoinException, DatabaseLayerException {
    	
    	//Arrange
        int expectedMinutes = 4;
        
        //Act
        ps.addPayment(50, Currency.ValidCurrency.DKK, Currency.ValidCoinType.FRACTION);
        ps.addPayment(1, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
        PReceipt receipt = ps.buy();
        
        //Assert
        assertNotNull(receipt);
        assertEquals(expectedMinutes, receipt.getValue());
    }
}