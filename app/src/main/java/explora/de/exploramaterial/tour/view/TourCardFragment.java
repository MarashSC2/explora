package explora.de.exploramaterial.tour.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import explora.de.exploramaterial.MainActivity.MainActivity;
import explora.de.exploramaterial.R;
import explora.de.exploramaterial.tour.dao.TourDAO;
import explora.de.exploramaterial.tour.entity.Tour;

/**
 * Created by Marash on 26.05.2016.
 */
public class TourCardFragment extends Fragment {

    private View rootView;
    private Context context;
    private int containerId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containerId = container.getId();
        rootView = inflater.inflate(R.layout.tour_cardlist_fragment, container, false);
        context = inflater.getContext();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView tourListView = (ListView) rootView.findViewById(R.id.tour_list_view_fragment);

        TourDAO tourDao = new TourDAO(MainActivity.databaseHelper);

        List<Tour> tours = tourDao.getAllTours();

        ArrayAdapter adapter = new TourCardAdapter(context, tours,(TourCardClickListener)getActivity());
        tourListView.setAdapter(adapter);
    }
}
