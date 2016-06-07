package explora.de.exploramaterial.area.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import explora.de.exploramaterial.MainActivity.MainActivity;
import explora.de.exploramaterial.R;
import explora.de.exploramaterial.address.dao.AddressDAO;
import explora.de.exploramaterial.address.entity.Address;

/**
 * Created by Marash on 25.05.2016.
 */

/**
 * Stellt eine View einer einzelnen Area dar (z.B. Berlin)
 */
public class AreaCardFragment extends Fragment {

    private View rootView;
    private Context context;
    private int containerId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containerId = container.getId();
        rootView = inflater.inflate(R.layout.city_cardlist_fragment, container, false);
        context = inflater.getContext();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        ListView cityListView = (ListView) rootView.findViewById(R.id.city_list_view_fragment);

        AddressDAO addressDao = new AddressDAO(MainActivity.databaseHelper);

        List<Address> addresses = addressDao.getAllAddresses();

        ArrayAdapter adapter = new AreaCardAdapter(context, addresses,(AreaCardClickListener)getActivity());
        cityListView.setAdapter(adapter);
    }
}
