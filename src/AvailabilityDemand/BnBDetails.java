package AvailabilityDemand;

/**
 * 'BnBDetails' is a custom class created to store the 'bnbName' and the 'StayPeriod' of that particular publisher. 
 * It has getter and setter methods to get the 'bnbName' and 'StayPeriod'. 
 * This object is instantiated when the Broker class is called by the 'Customer' or 'BnBProvider'. 
 * 'CustDetails' object is stored as an ArrayList in 'BnbStates' attribute of the 'Broker' class. 
 * This is what is encompassed by BnBDetails.
 */

public class BnBDetails {

	private String bnbName;
	private StayPeriod stayPeriod;
	
	//Constructor of class
	public BnBDetails(String bnbName, StayPeriod stayPeriod) {
		super();
		this.bnbName = bnbName;
		this.stayPeriod = stayPeriod;
	}
	
	//Getter and Setter methods
	public String getBnbName() {		
		return bnbName;
	}
	public void setBnbName(String bnbName) {
		this.bnbName = bnbName;
	}
	public StayPeriod getStayPeriod() {
		return stayPeriod;
	}
	public void setStayPeriod(StayPeriod stayPeriod) {
		this.stayPeriod = stayPeriod;
	}
}
