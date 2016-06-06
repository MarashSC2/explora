package explora.de.exploramaterial.tour.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class SingleTourFragment extends Fragment {

    private static final String TAG = "SingleTourFragment";

    public static final String ARG_TOUR = "argTour";
    public static final String ARG_USER= "userName";

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tour_fragment, container, false);

        final TourDAO tourDao = new TourDAO(MainActivity.databaseHelper);
        final Tour tour = (Tour)  getArguments().getSerializable(SingleTourFragment.ARG_TOUR);
        final String userMail = getArguments().getString(ARG_USER);

        UserDAO userDao = new UserDAO(MainActivity.databaseHelper);
        boolean editMode = false;

        if(tour != null  && userMail != null) {
            User tourOwner = userDao.findById(tour.getOwner());
            Log.d(TAG,tourOwner.getEmail()+" equals "+userMail);
            if(tourOwner.getEmail().equals(userMail))
                editMode = true;
        }


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

        final EditText descriptionTextView = (EditText) rootView.findViewById(R.id.description);
        descriptionTextView.setEnabled(editMode);
        descriptionTextView.setText(tour.getDescription());

        final EditText ratingTextView = (EditText) rootView.findViewById(R.id.rating);
        ratingTextView.setEnabled(editMode);
        ratingTextView.setText(tour.getRating());

        Button saveButton = (Button) rootView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tour savedTour = new Tour();
                savedTour.setId(tour.getId());
                savedTour.setTitle(tour.getTitle());
                savedTour.setAddress(tour.getAddress());
                savedTour.setPrice(tour.getPrice());
                savedTour.setOwner(tour.getOwner());
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
