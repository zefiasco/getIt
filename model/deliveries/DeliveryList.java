package com.getit.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;

import com.getit.remoteData.AsyncResult;
import com.getit.remoteData.DownLoadGoogleSheet;

public class DeliveryList implements DataList
{

    /**
     * An array of Delivery items.
     */     
    public static List<Delivery> ITEMS = new ArrayList<Delivery>();
    
    static
    {
        // Add 3 sample items.
        int i;

        for (i=1; i < 2; i++)
        {
            addItem(new Delivery("Delivery " + i));
        }
    }

    public static void addItem(Delivery item)
    {
        ITEMS.add(item);
    }

	public static Delivery get(int i)
    {
    	if ((i < 0) || i >= ITEMS.size())
    		return null;
    	return ITEMS.get(i);
    }


}
