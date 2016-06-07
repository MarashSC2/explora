package explora.de.exploramaterial.tour.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import explora.de.exploramaterial.MainActivity.MainActivity;
import explora.de.exploramaterial.R;
import explora.de.exploramaterial.tour.dao.TourDAO;
import explora.de.exploramaterial.tour.entity.Tour;
import explora.de.exploramaterial.user.dao.UserDAO;
import explora.de.exploramaterial.user.entity.User;

/**
 * Created by Marash on 26.05.2016.
 */

/**
 * View für das Erstellen einer Tour
 */
public class CreateTourFragment extends Fragment {

    private static final String TAG = "SingleTourFragment";

    public static final String ARG_TOUR = "argTour";
    public static final String ARG_USER= "userName";

    public int citySelection = 1;

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tour_fragment, container, false);

        final TourDAO tourDao = new TourDAO(MainActivity.databaseHelper);
        final String userMail = getArguments().getString(ARG_USER);
        UserDAO userdao = new UserDAO(MainActivity.databaseHelper);
        final User creator = userdao.findByEmail(userMail);


        final EditText titleTextView = (EditText) rootView.findViewById(R.id.title);
        titleTextView.setEnabled(true);

        //Spinner (SelectBox) für die Areas (Städte)
        final Spinner spinner = (Spinner) rootView.findViewById(R.id.city);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.city_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                    citySelection = 4;
                else if(position ==1)
                    citySelection =6;
                else if(position==2)
                    citySelection =1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final EditText dateTextView = (EditText) rootView.findViewById(R.id.date);
        dateTextView.setEnabled(true);

        final EditText timeTextView = (EditText) rootView.findViewById(R.id.time);
        timeTextView.setEnabled(true);

        final EditText meetingSpotTextView = (EditText) rootView.findViewById(R.id.meeting_spot);
        meetingSpotTextView.setEnabled(true);

        final EditText tourGuideTextView = (EditText) rootView.findViewById(R.id.tour_guide);
        tourGuideTextView.setEnabled(true);

        final EditText priceTextView = (EditText) rootView.findViewById(R.id.price);
        priceTextView.setEnabled(true);

        final EditText descriptionTextView = (EditText) rootView.findViewById(R.id.description);
        descriptionTextView.setEnabled(true);

        final EditText ratingTextView = (EditText) rootView.findViewById(R.id.rating);
        ratingTextView.setEnabled(true);

        Button saveButton = (Button) rootView.findViewById(R.id.saveButton);
        saveButton.setText("Anlegen");
        //Auslesen der Werte und Speichern der Tour in der Datenbank
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tour savedTour = new Tour();
                savedTour.setAddress(citySelection);
                savedTour.setTitle(titleTextView.getText().toString());
                savedTour.setPrice(Integer.parseInt(priceTextView.getText().toString()));
                savedTour.setOwner(creator.getId());
                savedTour.setDateTime(dateTextView.getText().toString());
                savedTour.setMeetingSpot(meetingSpotTextView.getText().toString());
                savedTour.setTourGuide(tourGuideTextView.getText().toString());
                savedTour.setDescription(descriptionTextView.getText().toString());
                savedTour.setRating(ratingTextView.getText().toString());
                if(tourDao.createTour(savedTour))
                Toast.makeText(getActivity(), "Tour wurde Angelegt!",
                        Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
