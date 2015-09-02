package com.getit.deliverymanager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity;

public class MainActivityC extends Activity {
    MultiButton deliveryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_c);

        deliveryButton = (MultiButton) findViewById(R.id.deliveryButton);

        ButtonSettings buttonSetting;
        buttonSetting = new ButtonSettings("On the Way", null);
        deliveryButton.add(buttonSetting);
        buttonSetting = new ButtonSettings("Arrived", null);
        deliveryButton.add(buttonSetting);
        buttonSetting = new ButtonSettings("Call", null);
        deliveryButton.add(buttonSetting);
        deliveryButton.setCurrent(0);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
