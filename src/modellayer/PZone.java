package modellayer;

public class PZone {
	
	// Id of actual parkingzone
	private int id;
	private String name;

	// Hard coded value
	public PZone() {
		this.id = 2;
		this.name = "Zone B";
	}
	
	// Dynamicallt set parkingzone
	public PZone(int pZoneIdent, String pZoneName) {
		this.id = pZoneIdent;
		this.name = pZoneName;
	}	
	
	public int getpZoneId() {
		return id;
	}

	public void setpZoneId(int pZoneId) {
		this.id = pZoneId;
	}
	// Getter for name
	public String getName() {
		return name;
	}
	// Setter for name
	public void setName(String name) {
		this.name = name;
	}
	
}
