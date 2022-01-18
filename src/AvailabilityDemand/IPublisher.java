package AvailabilityDemand;

import java.util.Date;

/**
 * 'IPublisher' is an interface which declares the functions 'publish'. 
 * This interface is realized by the class 'BnBProvider' which goes on to implement this method. 
 * IPublisher contains abstract method.
 */

public interface IPublisher {
	public abstract boolean publish(String providerName, String location, Date availableFrom, Date availableTo);
}
