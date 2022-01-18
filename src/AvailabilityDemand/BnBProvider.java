package AvailabilityDemand;

import java.util.Date;

/**
 * 'BnBProvider' class is instantiated whenever a command which contains 'publish' is encountered in 'processInput' method. 
 * It has no attributes. It implements the 'IPublisher' interface and implements it's methods. 
 * This 'BnBProvider' object can be instantiated infinite number of times by the 'AvailabilityDemand' class.
 */

public class BnBProvider implements IPublisher {

	//Creates a 'Broker' object and calls 'processPublish' to handle the publish event
	public boolean publish(String providerName, String location, Date availableFrom, Date availableTo) {
		Broker object = Broker.getInstance();
		object.processPublish(providerName, location, availableFrom, availableTo);
		return false;
	}
}
