package AvailabilityDemand;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * The 'Broker' class is the logic unit of this Availability Demand System and it utilizes Singleton Design Pattern. 
 * Implementation of Publisher-Subscriber model is performed in this system and 'Broker' is the intermediary which handle all the events sequentially.
 * It has attributes of 'BnbStates' and 'CustStates' which are HashMap(of String and ArrayList<BnBDetails/CustDetails>) which is used to store the publisher and subscriber
 * which are incoming.
 * Methods 'processPublish', 'processSubscribe', and 'processUnsubscribe' are used to handle the checking,storing/removing of data from the HashMap and calling the 
 * 'formatNotifications' to store the notifications when the conditions are met. 'resetData' is called to clear the data in HashMaps.
 * Other methods are helper functions to help the aforementioned methods.
 * All these operations and methods encompasses the Broker class.
 */

public class Broker {

	//HashMaps to store he BnBProvider and Customer details in an ordered way
	private HashMap<String, ArrayList<BnBDetails>> BnbStates = new HashMap<String, ArrayList<BnBDetails>>();
	private HashMap<String, ArrayList<CustDetails>> CustStates = new HashMap<String, ArrayList<CustDetails>>();
    
	//Declaration for Singleton class
	private static Broker INSTANCE = new Broker();
    private Broker() {}
    public static Broker getInstance() {
        return INSTANCE;
    }
	
    //Method to check Provider Stay period is correct
	private boolean checkProviderStayPeriod(StayPeriod incoming, StayPeriod existing) {
		if(incoming.getStartDate().equals(existing.getStartDate()) && incoming.getEndDate().equals(existing.getEndDate())) {
			return true;
		}
		if(incoming.getStartDate().before(existing.getEndDate()) && incoming.getEndDate().after(existing.getStartDate())) {
			return true;
		}
		
		return false;
	}
	
	//Method to check Subscriber Stay period is correct
	private boolean checkSubscriberStayPeriod(StayPeriod incoming, StayPeriod existing) {
		if(incoming.getStartDate().equals(existing.getStartDate()) && incoming.getEndDate().equals(existing.getEndDate())) {
			return true;
		}
		return false;
	}
	
	//Method for checking Stay Period while Notifying
	private boolean publisherNotification(StayPeriod incoming, StayPeriod existing) {
		if(incoming.getStartDate().equals(existing.getStartDate()) && incoming.getEndDate().equals(existing.getEndDate())) {
			return true;
		}
		return false;
	}
		
	//Check if Stay Period is within Range
	private boolean isWithinRange(StayPeriod incoming, StayPeriod existing) {
		   return ((!incoming.getStartDate().before(existing.getStartDate())) && !incoming.getEndDate().after(existing.getEndDate()));
		}

	//Reset the HashMap values by re-initializing
	public void resetData() {
		BnbStates = new HashMap<String, ArrayList<BnBDetails>>();
		CustStates = new HashMap<String, ArrayList<CustDetails>>();
		formatNotifications object = formatNotifications.getInstance();
		object.Reset();
	}
	
	//Handles the Publisher Events
	public boolean processPublish(String providerName, String location, Date availableFrom, Date availableTo) {
		
		StayPeriod stayPeriod = new StayPeriod(availableFrom, availableTo);
		BnBDetails publisher = new BnBDetails(providerName, stayPeriod);
		location = location.toLowerCase();
		
		//Check if the publisher is already in the list
		if(BnbStates.containsKey(location)) {
			ArrayList<BnBDetails> list = BnbStates.get(location);
			
			for(BnBDetails num : list) {
				if(num.getBnbName().equalsIgnoreCase(providerName) && checkProviderStayPeriod(stayPeriod,num.getStayPeriod()) ){
					//System.out.println("Provider Duplicate");
					return false;
				}
			}
			//Add to HashMap if publisher is Unique
			list.add(publisher);
			BnbStates.put(location, list);
		}
		else {
			ArrayList<BnBDetails> pub= new ArrayList<BnBDetails>();
			pub.add(publisher);
			BnbStates.put(location, pub);
		}

		
		//Conditions for notification
		ArrayList<CustDetails> customersSubscribedToLocation=CustStates.get(location);
		if(customersSubscribedToLocation == null) {
			return true;
		}
		for (CustDetails cd:customersSubscribedToLocation) {
			if(publisherNotification(publisher.getStayPeriod(), cd.getStayPeriod()) || isWithinRange(cd.getStayPeriod(), publisher.getStayPeriod())){
				
			    Date start = publisher.getStayPeriod().getStartDate();
			    Date end = publisher.getStayPeriod().getEndDate();
				formatNotifications object = formatNotifications.getInstance();
				object.formatnotification(cd.getCustomerName(), location, start,end, providerName);
			}
		}
		return true;
	}

	//Handles the Subscribe Events
	public boolean processSubscribe(String CustName, String location, Date from, Date to) {
		
		StayPeriod stayPeriod = new StayPeriod(from, to);
		CustDetails subscriber = new CustDetails(CustName, stayPeriod);
		location = location.toLowerCase();
		
		//Check if the subscriber is already in the list
		if(CustStates.containsKey(location)) {
			ArrayList<CustDetails> list = CustStates.get(location);
			
			for(CustDetails num: list) {
				if(num.getCustomerName().equalsIgnoreCase(subscriber.getCustomerName()) && checkSubscriberStayPeriod(subscriber.getStayPeriod(),num.getStayPeriod())) {
					//System.out.println("Subscriber Duplicate");
					return false;
				}
			}
			//Add to HashMap if subscriber is Unique
			list.add(subscriber);
			CustStates.put(location, list);
		}
		else {
			ArrayList<CustDetails> sub= new ArrayList<CustDetails>();
			sub.add(subscriber);
			CustStates.put(location, sub);
		}
		
		//Conditions for notification
		ArrayList<BnBDetails> publisherPublishedToLocation=BnbStates.get(location);
		if(publisherPublishedToLocation == null) {
			return true;
		}
			
		for (BnBDetails pd:publisherPublishedToLocation) {
			if(publisherNotification(subscriber.getStayPeriod(), pd.getStayPeriod()) || isWithinRange(subscriber.getStayPeriod(), pd.getStayPeriod())){
			    Date start = pd.getStayPeriod().getStartDate();
			    Date end = pd.getStayPeriod().getEndDate();
				formatNotifications object = formatNotifications.getInstance();
				object.formatnotification(subscriber.getCustomerName(), location, start,end, pd.getBnbName());
			}
		}
		return true;
	}
	
	//Handles the Unsubscribe Events
	public boolean processUnsubscribe(String CustName, String location, Date from, Date to) {
		
		StayPeriod stayPeriod = new StayPeriod(from, to);
		CustDetails subscriber = new CustDetails(CustName, stayPeriod);
		location = location.toLowerCase();
		
		ArrayList<CustDetails> customersSubscribedToLocation=CustStates.get(location);
		if(!customersSubscribedToLocation.isEmpty()) {
			for(CustDetails cd : customersSubscribedToLocation) {
				if(cd.getCustomerName().equalsIgnoreCase(CustName) && checkSubscriberStayPeriod(subscriber.getStayPeriod(), cd.getStayPeriod())) {
					customersSubscribedToLocation.remove(cd);
					CustStates.put(location, customersSubscribedToLocation);
					return true;
				}
			}
		}
		return false;
	}
	
}
