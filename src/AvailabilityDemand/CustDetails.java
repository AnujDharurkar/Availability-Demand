package AvailabilityDemand;

/**
 * 'CustDetails' is a custom class created to store the 'customerName' and the 'StayPeriod' of that particular customer. 
 * It has getter and setter methods to get the 'customerName' and 'StayPeriod'. 
 * This object is instantiated when the Broker class is called by the 'Customer' or 'BnBProvider'. 'CustDetails' object 
 * is stored as an ArrayList in 'CustStates' attribute of the 'Broker' class.
 */

public class CustDetails {

	private String customerName;
	private StayPeriod stayPeriod;
	
	//Constructor of class
	public CustDetails(String customerName, StayPeriod stayPeriod) {
		super();
		this.customerName = customerName;
		this.stayPeriod = stayPeriod;
	}
	
	//Getter and Setter Methods
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public StayPeriod getStayPeriod() {
		return stayPeriod;
	}
	public void setStayPeriod(StayPeriod stayPeriod) {
		this.stayPeriod = stayPeriod;
	}
}