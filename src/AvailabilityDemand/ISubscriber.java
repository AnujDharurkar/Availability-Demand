package AvailabilityDemand;
import java.util.Date;

/**
 * 'ISubscriber' is an interface which declares the functions 'subscribe' and 'unsubscribe'. 
 * This interface is realized by the class 'Customer' which goes on to implement these methods. 
 * ISubscriber contains abstract methods.
 */

public interface ISubscriber {

	public abstract boolean subscribe(String location, Date from, Date to);

	public abstract boolean unsubscribe(String location, Date from, Date to);
}
