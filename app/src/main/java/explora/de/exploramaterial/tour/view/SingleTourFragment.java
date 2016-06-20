package explora.de.exploramaterial.tour.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import explora.de.exploramaterial.address.dao.AddressDAO;
import explora.de.exploramaterial.address.entity.Address;
import explora.de.exploramaterial.tour.dao.TourDAO;
import explora.de.exploramaterial.tour.entity.Tour;
import explora.de.exploramaterial.user.dao.UserDAO;
import explora.de.exploramaterial.user.entity.User;

/**
 * Created by Marash on 26.05.2016.
 */

/**
 * Anzeigen einer Tour
 */
public class SingleTourFragment extends Fragment {

    private static final String TAG = "SingleTourFragment";

    public static final String ARG_TOUR = "argTour";
    public static final String ARG_USER= "userName";

    public int citySelection = 1;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tour_fragment, container, false);

        final TourDAO tourDao = new TourDAO(MainActivity.databaseHelper);
        final Tour tour = (Tour)  getArguments().getSerializable(SingleTourFragment.ARG_TOUR);
        final String userMail = getArguments().getString(ARG_USER);

        UserDAO userDao = new UserDAO(MainActivity.databaseHelper);
        boolean editMode = false;

        //Prüfen ob der aktuelle Benutzer der Eigner einer Tour ist
        if(tour != null  && userMail != null) {
            User tourOwner = userDao.findById(tour.getOwner());
            Log.d(TAG,tourOwner.getEmail()+" equals "+userMail);
            if(tourOwner.getEmail().equals(userMail))
                editMode = true;
        }

        //Area Auswahl für den Spinner -  Teilweise gemocked
        AddressDAO addressdao = new AddressDAO(MainActivity.databaseHelper);
        Address address = addressdao.findById(tour.getAddress());
        int addressSelection = 0;
        if(address.getCity().equals("Berlin")) {
            addressSelection = 0;
            citySelection =4;
        }
        else if(address.getCity().equals("London")) {
            addressSelection = 1;
            citySelection=6;
        }
        else if(address.getCity().equals("Stuttgart")) {
            addressSelection = 2;
            citySelection = 1;
        }

        final EditText titleTextView = (EditText) rootView.findViewById(R.id.title);
        titleTextView.setEnabled(editMode);
        titleTextView.setText(tour.getTitle());


        final Spinner spinner = (Spinner) rootView.findViewById(R.id.city);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.city_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setEnabled(editMode);
        spinner.setSelection(addressSelection);
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
        dateTextView.setEnabled(editMode);
        dateTextView.setText(tour.getDateTime());

        final EditText timeTextView = (EditText) rootView.findViewById(R.id.time);
        timeTextView.setEnabled(editMode);
        timeTextView.setText(tour.getDateTime());

        final EditText meetingSpotTextView = (EditText) rootView.findViewById(R.id.meeting_spot);
        meetingSpotTextView.setEnabled(editMode);
        meetingSpotTextView.setText(tour.getMeetingSpot());

        final EditText tourGuideTextView = (EditText) rootView.findViewById(R.id.tour_guide);
        tourGuideTextView.setEnabled(editMode);
        tourGuideTextView.setText(tour.getTourGuide());

        final EditText priceTextView = (EditText) rootView.findViewById(R.id.price);
        priceTextView.setEnabled(editMode);
        priceTextView.setText(String.valueOf(tour.getPrice()));

        final EditText descriptionTextView = (EditText) rootView.findViewById(R.id.description);
        descriptionTextView.setEnabled(editMode);
        descriptionTextView.setText(tour.getDescription());

        final EditText ratingTextView = (EditText) rootView.findViewById(R.id.rating);
        ratingTextView.setEnabled(editMode);
        ratingTextView.setText(tour.getRating());

        Button saveButton = (Button) rootView.findViewById(R.id.saveButton);
        // Speichern bzw. bearbeiten der Tour, falls Rechte vorhanden sind
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tour savedTour = new Tour();
                savedTour.setId(tour.getId());
                savedTour.setOwner(tour.getOwner());
                savedTour.setAddress(citySelection);
                savedTour.setTitle(titleTextView.getText().toString());
                try {
                    savedTour.setPrice(Integer.parseInt(priceTextView.getText().toString()));
                }catch (Exception ex){
                    savedTour.setPrice(0);
                }
                savedTour.setDateTime(dateTextView.getText().toString());
                savedTour.setMeetingSpot(meetingSpotTextView.getText().toString());
                savedTour.setTourGuide(tourGuideTextView.getText().toString());
                savedTour.setDescription(descriptionTextView.getText().toString());
                savedTour.setRating(ratingTextView.getText().toString());
                if(tourDao.saveTour(savedTour))
                Toast.makeText(getActivity(), "Tour wurde gespeichert!",
                        Toast.LENGTH_LONG).show();
            }
        });
        if(editMode)
            saveButton.setVisibility(View.VISIBLE);
        else
            saveButton.setVisibility(View.INVISIBLE);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
