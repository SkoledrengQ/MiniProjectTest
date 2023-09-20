package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import controllayer.ControlPrice;

import org.junit.*;
import java.time.LocalDate;

import databaselayer.*;
import modellayer.*;
import controllayer.*;

//import static org.junit.Assert.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestDatabaseAccess {
	
	static DBConnection con = null;
	static PBuy tempPBuy;

	/** Fixture for pay station testing. */
	//@Test Department
	//Ændret fra @Before til @BeforeAll
	@BeforeAll
	public static void setUp() {
		con = DBConnection.getInstance();
	}
	
	
	@Test
	public void wasConnected() {
		assertNotNull(con, "Connected - connection cannot be null");
		
		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue(wasNullified, "Disconnected - instance set to null");
		
		con = DBConnection.getInstance();
		boolean connectionIsOpen = DBConnection.getOpenStatus();
		assertTrue(connectionIsOpen);	
	}
	
	
	@Test
	public void wasInsertedBuy() {
		//@Test Department
		//Metoden er skrevet udfra antagelsen at vi køber en parkeringsbillet
		//og tester om vi som forventet får et ID tilbage fra databasen
		//hvor købet bliver indsat.
		
		// Arrange
		LocalDate timeNow = java.time.LocalDate.now();
		double payedCentAmount = 100;
		
		tempPBuy = new PBuy();
		
		PPayStation pStat = new PPayStation(1, "P-423E");
		pStat.setAmount(payedCentAmount);
		tempPBuy.setAssociatedPaystation(pStat);
		tempPBuy.setBuyTime(timeNow);
		
		DatabasePBuy dbPbuy = new DatabasePBuy();
		
		// Act
		int key = 0;
		try {
			key = dbPbuy.insertParkingBuy(tempPBuy);
			tempPBuy.setId(key);
		} catch (DatabaseLayerException e) {
			System.out.println(e.getMessage());
		}		
		
		// Assert
		assertTrue(key > 0);
		
	}	
	
	
	@Test
	public void wasRetrievedPriceDatabaselayer() {
		//@Test Department
		//Metoden skrevet udfra antagelsen at vi skal fetche prisen direkte via
		//Database filen, altså udenom eventuelle control interfaces og tester
		//om kaldet op imod datasen fungerer som det skal.
		
		// Arrange
		PPrice foundPrice = null;
		int pZoneId = 2;
		DatabasePPrice dbPrice = new DatabasePPrice();

		
		// Act
		try {
			foundPrice = dbPrice.getPriceByZoneId(pZoneId);
		} catch (DatabaseLayerException e) {
			System.out.println(e.getMessage());
		}

		// Assert
		assertEquals(25, foundPrice.getParkingPrice());
		
	}
	
	
	@Test
	public void wasRetrievedPriceControllayer() {
		//@Test Department
		//Metode skrevet udfra antagelsen af vi skal teste om control interfacet
		//fetcher det nyeste pris data fra databasen for den zone der bliver
		//specificeret i kaldet.
		
		// Arrange
		ControlPrice cp = new ControlPrice();
		PPrice foundPrice = null;
		int zID = 2;
		
		// Act
		try {
			foundPrice = cp.getPriceRemote(zID);
		} catch (DatabaseLayerException e) {
			System.out.println(e.getMessage());
		}

		// Assert
		assertEquals(25, foundPrice.getParkingPrice());
		
	}	
	
	
	/** Fixture for pay station testing. */
	@AfterAll
	public static void cleanUp() {
		DBConnection.closeConnection();
	}	
	//@Test Department
	//Ændret fra @AfterClass til @AfterAll
	//da @AfterClass ikke fungerede.
	@AfterAll
	public static void cleanUpWhenFinish() {

		// Arrange
		DatabasePBuy dbPbuy = new DatabasePBuy();
		int numDeleted = 0;
		
		// Act
		try {
			numDeleted = dbPbuy.deleteParkingBuy(tempPBuy);
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		} finally {
			DBConnection.closeConnection();
		}
	
		// Assert
		assertEquals(1, numDeleted, "One row deleted");
	}	

}
