package AvailabilityDemand;

import java.util.Date;

/**
 * 'Customer' class is instantiated whenever a command which contains 'subscribe' or 'unsubscribe' is encountered in 'processInput' method. 
 * It implements the 'ISubscriber' interface and implements it's methods. 
 * It has an attribute called 'CustomerName' which defines the name of different subscriber. 
 * This 'Customer' object can be instantiated infinite number of times by the 'AvailabilityDemand' class.
 */

public class Customer implements ISubscriber {
	
	private String CustomerName;

	//Constructor of class
	public Customer(String customerName) {
		super();
		CustomerName = customerName;
	}

	//Creates a 'Broker' object and calls 'processSubscribe' to handle the subscribe event
	public boolean subscribe(String location, Date from, Date to) {
		Broker object = Broker.getInstance();
		object.processSubscribe(CustomerName, location, from, to);
		return false;
	}

	//Creates a 'Broker' object and calls 'processUnsubscribe' to handle the unsubscribe event
	public boolean unsubscribe(String location, Date from, Date to) {
		Broker object = Broker.getInstance();
		object.processUnsubscribe(CustomerName, location, from, to);
		return false;
	}

}
