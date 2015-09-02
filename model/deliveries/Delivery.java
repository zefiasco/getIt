package com.getit.model;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.JSONUtils;
import utils.Utils;
import android.location.Location;

/**
 * Created by cathybishop on 5/27/15.
 */

public class Delivery implements Data
{	
	private static int nextDeliveryNumber = 1;

	private int deliveryNumber;
	private String orderNumber;
	private String time;
	private Location location;
	private boolean existingLocation;
	private int numBags;
	private String totalDue;
	private Customer customer;

	public Delivery()
	{
	    deliveryNumber = nextDeliveryNumber;
	    nextDeliveryNumber++;
	}
	public Delivery(String orderNumber)
	{
	    this();
	    this.orderNumber = orderNumber;
	}
	public Delivery(JSONArray jsonArray) throws JSONException
	{
		this();

		JSONObject j;
        Logger.getAnonymousLogger().log(Level.INFO, "JSON Array length: " + jsonArray.length());
               
        j = jsonArray.getJSONObject(0);
        Utils.traceHashMap(j, "First");
        try
        {
        	j = jsonArray.getJSONObject(1);
        }
        catch (Exception e)
        {
        	orderNumber=JSONUtils.getString(jsonArray, 0);
        	return;
        }
        
        Utils.traceHashMap(j, "Second");
        
		String onumber =  JSONUtils.getString(jsonArray, 7);
		String amount = JSONUtils.getString(jsonArray, 9);

		//double amt = Utils.convertPrice(amount); // test
        
		orderNumber=onumber;
		totalDue=amount;
	}
	public int getDeliveryNumber()
	{
	    return deliveryNumber;
	}
	public String getKey()
	{
		return String.valueOf(getDeliveryNumber());
	}
	public void setDeliveryOrder(int deliveryOrder)
	{
	    this.deliveryNumber = deliveryOrder;
	}

	public String getOrderNumber()
	{
	    return orderNumber;
	}

	public void setOrderNumber(String orderNumber)
	{
	    this.orderNumber = orderNumber;
	}

	public String getTime()                    { return time; }
	public void setTime(String time)           { this.time = time; }

	public Location getLocation()              { return location; }
	public void setLocation(Location location) { this.location = location; }

	public boolean isExistingLocation()        { return existingLocation; }

	public void setExistingLocation(boolean existingLocation)
	{
	    this.existingLocation = existingLocation;
	}

	public int getNumBags()                     { return numBags; }
	public void setNumBags(int numBags)         { this.numBags = numBags; }

	public String getTotalDue()                 { return totalDue; }
	public void setTotalDue(String totalDue)    { this.totalDue = totalDue; }
	public double getTotalDueDouble()           { return Utils.convertPrice(totalDue); }

	public Customer getCustomer()               { return customer; }
	public void setCustomer(Customer customer)  { this.customer = customer; }

	@Override
	public String toString()
	{
		return "Delivery: " + deliveryNumber + " " + orderNumber;
	}
	public String getDetails()
	{
	    return "Delivery{" +
	            ", orderNumber='" + orderNumber + '\'' +
	            ", time=" + time +
	            ", location=" + location +
	            ", existingLocation=" + existingLocation +
	            ", numBags=" + numBags +
	            ", totalDue=" + totalDue +
	            ", customer=" + customer +
	            '}';
	}

}
