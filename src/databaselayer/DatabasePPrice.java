package databaselayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.SQLException;

import modellayer.*;

public class DatabasePPrice implements IDbPPrice {
	
	static DBConnection con = null;
	
	//Test department: Non functionally hardcoded
	public PPrice getCurrentPrice() {
		
		
		
		return new PPrice();
	}
	
	public PPrice getPriceByZoneId(int zoneId) throws DatabaseLayerException {
		PPrice foundPrice = null;
		
		Calendar calendar = Calendar.getInstance();
		java.sql.Date dateNow = new java.sql.Date(calendar.getTime().getTime());
		
		Connection con = DBConnection.getInstance().getDBcon();
		
		//@Test Department
		//Fixed SQL String with joins
		String baseSelect = "select top 1 p.price, p.pZone_id, z.name from PPrice p"
				+ " join PZone z on p.pZone_id = z.id"
				+ " where p.pZone_id = " + zoneId
				+ " and p.starttime < '" + dateNow + "' "
				+ " order by p.starttime desc";
	
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			
			////@Test Department
			//Added code to fetch the updated price
			ResultSet rs = stmt.executeQuery(baseSelect);			
			while (rs.next()) {
				int price = rs.getInt("price");
				int pZoneId = rs.getInt("pZone_id");
				String pZoneName = rs.getString("name");
				double exchangeRate = 7.5;
				PZone pZone = new PZone(pZoneId, pZoneName);
				foundPrice = new PPrice(price, pZone, exchangeRate);
			}			
			
			stmt.close();
		} catch (SQLException ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Error retrieving data");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (NullPointerException ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Null pointer exception - possibly Connection object");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (Exception ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Data not retrieved! Technical error");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} finally {
			DBConnection.closeConnection();
		}		
		return foundPrice;
	}
	

}
