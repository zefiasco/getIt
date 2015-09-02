package com.getit.deliverymanager;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.getit.model.Delivery;
import com.getit.model.DeliveryList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single Delivery detail screen.
 * This fragment is either contained in a {@link DeliveryListActivity}
 * in two-pane mode (on tablets) or a {@link DeliveryDetailActivity}
 * on handsets.
 */
public class DeliveryDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Delivery mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DeliveryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID))
        {
            // Load the Element from the list via the arguments. 
            // In a real-world scenario, use a Loader
            // to load content from a content provider.
            String id = getArguments().getString(ARG_ITEM_ID);
            Logger.getAnonymousLogger().log(Level.INFO, "id: " + id);
            int i = Integer.valueOf(id) - 1;
            mItem = DeliveryList.get(i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delivery_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.delivery_detail)).setText(mItem.getDetails());
        }

        return rootView;
    }
}
