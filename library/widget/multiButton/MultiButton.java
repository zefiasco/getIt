package com.getit.deliverymanager;

/**
 * Created by ZE FIASCO on 21-Aug-15.
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;
import android.net.Uri;
import android.telephony.SmsManager;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;


public class MultiButton extends Button {

    ArrayList <ButtonSettings> buttonOptions = new ArrayList <ButtonSettings>();
    private int current = -1;

    public void nextButton(){

        int nextIndex = current + 1;

        Log.d("MultiButton", "size is " + buttonOptions.size());

        if(nextIndex >= buttonOptions.size())
            nextIndex = 0;

        setCurrent(nextIndex);
    }

    public MultiButton(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MultiButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MultiButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    //
    public boolean setCurrent(int i)
    {
        current = i;
        setText(buttonOptions.get(current).getLabel());
        return true;
    }

    //Function that add new settings to button settings
    public void add(ButtonSettings settings)
    {
        buttonOptions.add(settings);

        if(current == -1)
        {
            setCurrent(0);

            this.setOnClickListener(new ClickHandler());
            this.setOnLongClickListener(new LongClickHandler());
        }
    }

    class LongClickHandler implements View.OnLongClickListener
    {
        public boolean onLongClick(View v)
        {
            Log.d("MultiButton", "Current: " + current);
            nextButton();
            Log.d("MultiButton", "After nextButton)(),  current: " + current);
            return(true);
        }
    }

    class ClickHandler implements View.OnClickListener
    {
        public void onClick(View v)
        {
            Log.d("MultButton " + getText(), "Fire off proper Intent " + current);
            CharSequence s = getText();

            if (!(s.equals("On the Way") || s.equals("Arrived") || s.equals("Call") || s.equals("Delivered")))
                return;

            if ((current == 0) && s.equals("Delivered"))
            {
                sendText(v, "0786544523", "Delivered from GetIt - thanks!");
                Intent mainIntent = new Intent(v.getContext(), DeliveryListActivity.class);
                v.getContext().startActivity(mainIntent);
            }
            else if (current == 0)
            {
                sendText(v, "0786544523", "On my way now with your delivery from GetIt - should be there within 15 minutes!");
                nextButton();
            }
            else if (current == 2)
            {
                String number = "tel: " + "0784332994"; //Lauren 0786544523 Fiacre 0784332994
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                v.getContext().startActivity(callIntent);
            }

            else if(current == 1)
            {
                Intent displayOrder = new Intent(v.getContext(), Order.class);
                v.getContext().startActivity(displayOrder);
            }
        }
    }
    public void sendText(View v, String phoneNum, String message)
    {
        SimpleDateFormat df = new SimpleDateFormat();
        String datedMessage = df.format(new Date()) + ": " + message;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNum, null, datedMessage, null, null);
            Log.d("TXT", datedMessage + " Sent  to: " + phoneNum);
        }

        catch (Exception e) {
            Log.d("TXT", "Not Sent");
            e.printStackTrace();
        }
    }


}
class ButtonSettings{
    private CharSequence label = "";
    Intent action;
    String data;

    //Constructor with 3 arguments #label, intent, data
    ButtonSettings(CharSequence label, Intent intent, String data)
    {
        this.label = label;
        this.action = intent;
        this.data = data;
    }

    //Constructor with two arguments
    ButtonSettings(CharSequence label, Intent intent)
    {
        this(label, intent, null);
    }

    public CharSequence getLabel() {
        return label;
    }

    public void setLabel(CharSequence label) {
        this.label = label;
    }

    public Intent getAction() {
        return action;
    }

    public void setAction(Intent action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
