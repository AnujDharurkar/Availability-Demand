package AvailabilityDemand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 'AvailabilityDemand' is the controller of this systems called 'AvailabilityDemand System'. 
 * It contains 3 methods called 'processInput', 'getAggregatedOutput' and 'reset'. 
 * It interacts with 'Customer', 'BnBProvider' and 'formatNotifications' classes. 
 * It gives sequential calls to 'processInput' to input the data. Then, 'getAggregatedOutput' is called to get the availability notifications. 
 * 'reset' is then called to clear the list. This is what 'AvailabilityDemand' encompasses.
 */

public class AvailabilityDemand {

	//Unused code from Forward Engineering 
	//private IPublisher iPublisher;
	//private ISubscriber iSubscriber;
	//private Customer[] customer;
	//private BnBProvider[] bnBProvider;

	public void processInput(String command){			//Processes different events
		
		try {											//try/catch to handle Parsing errors for incorrect inputs
		String custName, providerName, location, tmpfrom, tmpto;		
		String[] commands = command.split("\\s*,\\s*");
		
		if(commands.length <= 5) {						//Check if command is of required length
			if(command.contains("publish")){
				
		        providerName = commands[1];	location = commands[2];	tmpfrom = commands[3];	tmpto = commands[4];
		        
		        Date availableFrom;
				Date availableTo;
					try {
						availableFrom = new SimpleDateFormat("MM/dd/yyyy").parse(tmpfrom);  
						availableTo = new SimpleDateFormat("MM/dd/yyyy").parse(tmpto);
						if(!availableFrom.before(availableTo)) {
							return;
						}
						BnBProvider bnb = new BnBProvider();				//Creates a BnBProvider object to call 'publish' method
				        bnb.publish(providerName, location, availableFrom, availableTo);
					} catch (ParseException e) {
						return;
				}  
		        	
			}
			
			if(commands[0].equals("subscribe")){
	
		        custName = commands[1];	location = commands[2];	tmpfrom = commands[3];	tmpto = commands[4];
		        
		        Date from;
				Date to;
				try {
					from = new SimpleDateFormat("MM/dd/yyyy").parse(tmpfrom);  
					to = new SimpleDateFormat("MM/dd/yyyy").parse(tmpto);
					if(!from.before(to)) {
						return;
					}
					Customer cus = new Customer(custName);				//Creates a Customer object to call 'subscribe' method
					cus.subscribe(location, from, to);
				} catch (ParseException e) {
					return;
				}  
			}
			
			if(commands[0].equals("unsubscribe")){
				
		        custName = commands[1];	location = commands[2];	tmpfrom = commands[3];	tmpto = commands[4];
		        
		        Date from;
				Date to;
				try {
					from = new SimpleDateFormat("MM/dd/yyyy").parse(tmpfrom);  
					to = new SimpleDateFormat("MM/dd/yyyy").parse(tmpto);
					if(!from.before(to)) {
						return;
					}
					Customer cus = new Customer(custName);				//Creates a Customer object to call 'unsubscribe' method
					cus.unsubscribe(location, from, to);
				} catch (ParseException e) {
					return;
				}  

			}
		}
		}
		catch(Exception e) {
			return;
		}
	}

    /**
     * Creates a 'formatNotifications' object and calls 'finalNotification' to receive aggregated output.
     * 1. This method is responsible to return the consolidated notifications.
     * 2. Whenever broker will send out the notification, this notification should be logged by class 'formatNotifications' which will take care
     *   of output formatting.
     * 4. When we request the "getAggregatedOutput", it is expected to return all the notifications stored/logged by the formatter.
     */
	public List<String> getAggregatedOutput() {			
		formatNotifications object = formatNotifications.getInstance();
		return object.finalNotification();

	}

	
    /**
     * This method is responsible to clear out all stored published events, customers subscribed to system
     * and all the information stored with the system which is necessary to process the notifications.
     * This should also reset all the notifications stored with the system which are returned when "getAggregatedOutput" method is
     * called.
     * Your Broker class should provide an operation to clear out stored information. Similarly, your class notification output
     * formatter should provide an operation to clear out the notifications stored with the system.
     */
	public void reset() {
	    Broker reset = Broker.getInstance();
		reset.resetData();
	}
}
