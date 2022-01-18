package AvailabilityDemand;
import java.util.Date;

/**
 * 'StayPeriod' class is used by both 'CustDetails' and 'BnBDetails' classes. 
 * It has the attributes 'startDate' and 'endDate' which is utilized by the above classes to create an ArrayList. 
 * It also contains a constructor and getter/setter methods.
 */

public class StayPeriod {

	private Date startDate;
	private Date endDate;
	
	//Constructor of class
	public StayPeriod(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	//Getter and Setter Methods
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
