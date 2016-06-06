package explora.de.exploramaterial.tour.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class CreateTourFragment extends Fragment {

    private static final String TAG = "SingleTourFragment";

    public static final String ARG_TOUR = "argTour";
    public static final String ARG_USER= "userName";

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tour_fragment, container, false);

        final TourDAO tourDao = new TourDAO(MainActivity.databaseHelper);
        final String userMail = getArguments().getString(ARG_USER);
        UserDAO userdao = new UserDAO(MainActivity.databaseHelper);
        final User creator = userdao.findByEmail(userMail);


        final EditText dateTextView = (EditText) rootView.findViewById(R.id.date);
        dateTextView.setEnabled(true);

        final EditText timeTextView = (EditText) rootView.findViewById(R.id.time);
        timeTextView.setEnabled(true);

        final EditText meetingSpotTextView = (EditText) rootView.findViewById(R.id.meeting_spot);
        meetingSpotTextView.setEnabled(true);

        final EditText tourGuideTextView = (EditText) rootView.findViewById(R.id.tour_guide);
        tourGuideTextView.setEnabled(true);

        final EditText descriptionTextView = (EditText) rootView.findViewById(R.id.description);
        descriptionTextView.setEnabled(true);

        final EditText ratingTextView = (EditText) rootView.findViewById(R.id.rating);
        ratingTextView.setEnabled(true);

        Button saveButton = (Button) rootView.findViewById(R.id.saveButton);
        saveButton.setText("Anlegen");
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tour savedTour = new Tour();
                savedTour.setTitle("Statisch Title");
                savedTour.setAddress(1);
                savedTour.setPrice(25);
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
