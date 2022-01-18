package AvailabilityDemand;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *'formatNotifications' is a Singleton class which only has one object instantiated. 
 * It is called by 'Broker' class whenever a 'processInput' method leads to an addition into the 'notifications' ArrayList. 
 * It contains the static attribute 'Reference' which is required to declare a Singleton class. Also, it has methods as 'capitalizeString',
 * 'formatnotification', and 'finalNotification' to format and return the notifications which have occurred. 
 * It also contains 'Reset' method, which is called from the 'AvailabilityDemand' class to clear out the notification ArrayList. 
 * This is what 'formatNotifications' class encompasses.
 */

public class formatNotifications {

	private List<String> notifications = new ArrayList<String>();					//List of strings to store the 'Notifications
	
	private static formatNotifications Reference = new formatNotifications();		//Declaration for Singleton class
    private formatNotifications() {}
    public static formatNotifications getInstance() {
        return Reference;
    }
    
	public static String capitalizeString(String string) {							//Method to capitalize String to our required format by "https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string"
		  char[] chars = string.toLowerCase().toCharArray();
		  boolean found = false;
		  for (int i = 0; i < chars.length; i++) {
		    if (!found && Character.isLetter(chars[i])) {
		      chars[i] = Character.toUpperCase(chars[i]);
		      found = true;
		    } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') {
		      found = false;
		    }
		  }
		  return String.valueOf(chars);
		}
    
	public void Reset() {															//Method to reset the 'Notifications' ArrayList
		notifications = new ArrayList<String>();
	}
	
    public void formatnotification(String location, String CustName, Date start, Date end, String BnBName) {	//Main Method which is called from Broker class to format the String
    	
    	String loc = capitalizeString(location);
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    String startDate = formatter.format(start);  
	    String endDate = formatter.format(end);  
		String s2 = String.format("%s notified of B&B availability in %s from %s to %s by %s B&B", loc, capitalizeString(CustName),startDate,endDate , BnBName);
		notifications.add(s2);
    }
    
	public List<String> finalNotification(){										//Returns the Aggregated output when 'getAggregated' method is called in AvailabilityDemand Class
		return notifications;
	}
}
