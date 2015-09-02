package com.getit.deliverymanager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import org.json.JSONObject;


public class Order extends ActionBarActivity {

    private  String DELIVERY_DATE;
    private  String TIME;
    private  String ORDER_NUMBER;
    private  String PAYMENT_METHOD;
    private  int TOTAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Bundle importedData = new Bundle();
        importedData.putString(DeliveryDetailFragment.ARG_ITEM_ID,getIntent().getStringExtra(DeliveryDetailFragment.ARG_ITEM_ID));
        JSONObject jObject = new JSONObject();
        Log.d("Imported data",""+importedData.toString());
        MultiButton deliveredButton = (MultiButton) findViewById(R.id.button2);

        ButtonSettings buttonSetting;
        buttonSetting = new ButtonSettings("Delivered", null);
        deliveredButton.add(buttonSetting);
        buttonSetting = new ButtonSettings("Delivery but incomplete", null);
        deliveredButton.add(buttonSetting);
        buttonSetting = new ButtonSettings("Call", null);
        deliveredButton.add(buttonSetting);
        deliveredButton.setCurrent(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
