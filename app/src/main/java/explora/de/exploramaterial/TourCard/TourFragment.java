package explora.de.exploramaterial.TourCard;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import explora.de.exploramaterial.R;
import explora.de.exploramaterial.database.DatabaseHelper;

/**
 * Created by Marash on 26.05.2016.
 */
public class TourFragment extends Fragment {

    public static final String ARG_CITY_NAME = "argCityName";

    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tour_cardlist_fragment, container, false);

        Bundle args = getArguments();
        TextView view = (TextView) rootView.findViewById(R.id.city_name);
        view.setText(args.getString(ARG_CITY_NAME,"DEFAULT"));
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
