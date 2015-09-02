package com.getit.deliverymanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.getit.model.Delivery;
import com.getit.model.DeliveryList;
import com.getit.remoteData.AsyncResult;
import com.getit.remoteData.DownLoadGoogleSheet;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;




/**
 * An activity representing a list of Deliveries. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DeliveryDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link DeliveryListFragment} and the item details
 * (if present) is a {@link DeliveryDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link DeliveryListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class DeliveryListActivity extends FragmentActivity
        implements DeliveryListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    Button btnDownload;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_list);

        if (findViewById(R.id.delivery_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((DeliveryListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.delivery_list))
                    .setActivateOnItemClick(true);
        }

//        btnDownload = (Button) findViewById(R.id.btnDownload);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//            btnDownload.setEnabled(true);
//        } else {
//            btnDownload.setEnabled(false);
//        }
        // TODO: If exposing deep links into your app, handle intents here.

        String url1="https://spreadsheets.google.com/tq?key=";
        String keyEx="1yyTcjWA6RAUwkI7sKOevWXAJfpITs__Zb0TwilihDCw";
        String keyTest="1yDJ0htVLgs0jmmqtm2nr6txlVf7FSd2id3LqYVoS8hk";
        String urlEx = url1 + keyEx;

        String worksheetId="owuxt34";
        String urlTest1 = url1 + keyTest + "&gid=0";
        String encodedAnd = "%20%26%20"; // space & space
        String urlTest1a = url1 + keyTest + "&sheet=DELIVERIES%20%26%20APPOINTMENTS";

        String url2="https://spreadsheets.google.com/feeds/cells/";
        String url3="/public/values?alt=json";
        	
    new DownLoadGoogleSheet(new AsyncResult() {
        @Override
        public void onResult(JSONObject object) {
            processJson(object);

        }
    }).execute(
    		urlTest1a
    		);
}

private void processJson(JSONObject object) {

    try {
        JSONArray rows = object.getJSONArray("rows");
        DeliveryListFragment f;
        
        f = ((DeliveryListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.delivery_list));
        ArrayAdapter<DeliveryList>a = (ArrayAdapter<DeliveryList>)
				(f.getListAdapter());

        for (int r = 0; r < rows.length(); ++r) {
            JSONObject row = rows.getJSONObject(r);
            JSONArray columns = row.getJSONArray("c");

            Delivery del = new Delivery(columns);
            DeliveryList.addItem(del);
			
             a.notifyDataSetChanged();
        }

    } catch (JSONException e) {
    	Toast.makeText(this, "No Orders - check permissions", Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }
}

    /**
     * Callback method from {@link DeliveryListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(DeliveryDetailFragment.ARG_ITEM_ID, id);
            DeliveryDetailFragment fragment = new DeliveryDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.delivery_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            /*
            Intent detailIntent = new Intent(this, DeliveryDetailActivity.class);
            detailIntent.putExtra(DeliveryDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
            */

            // Launch Map activity (MainActivityC)
            Intent intent = new Intent(this, MainActivityC.class);
            intent.putExtra(DeliveryDetailFragment.ARG_ITEM_ID, id);
            startActivity(intent);
        }
    }
}
