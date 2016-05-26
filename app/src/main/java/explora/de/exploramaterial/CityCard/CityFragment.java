package explora.de.exploramaterial.CityCard;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import explora.de.exploramaterial.MainActivity.MainActivity;
import explora.de.exploramaterial.R;
import explora.de.exploramaterial.TourCard.TourFragment;
import explora.de.exploramaterial.dao.AddressDAO;
import explora.de.exploramaterial.database.DatabaseHelper;

/**
 * Created by Marash on 25.05.2016.
 */
public class CityFragment extends Fragment {

    private View rootView;
    private Context context;
    private DatabaseHelper dbHelper;

    private int containerId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containerId = container.getId();
        rootView = inflater.inflate(R.layout.city_cardlist_fragment, container, false);
        context = inflater.getContext();
        dbHelper = new DatabaseHelper(context);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        ListView cityListView = (ListView) rootView.findViewById(R.id.city_list_view_fragment);

        AddressDAO addressDao = new AddressDAO(new DatabaseHelper(getActivity().getApplicationContext()));

        List<String> cityNames = addressDao.getAllCities();
        String[] cityNamesArray = cityNames.toArray(new String[cityNames.size()]);

        ArrayAdapter adapter = new CityCardAdapter(context, cityNamesArray,(CityCardClickListener)getActivity());
        cityListView.setAdapter(adapter);

    }
}
