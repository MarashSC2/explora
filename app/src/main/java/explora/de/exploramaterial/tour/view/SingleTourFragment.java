package explora.de.exploramaterial.tour.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import explora.de.exploramaterial.tour.entity.Tour;
import explora.de.exploramaterial.R;

/**
 * Created by Marash on 26.05.2016.
 */
public class SingleTourFragment extends Fragment {

    public static final String ARG_TOUR = "argTour";

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tour_fragment, container, false);

        Tour tour = (Tour)  getArguments().getSerializable(SingleTourFragment.ARG_TOUR);

        TextView dateTextView = (TextView) rootView.findViewById(R.id.date);
        dateTextView.setText(tour.getDateTime());

        TextView timeTextView = (TextView) rootView.findViewById(R.id.time);
        timeTextView.setText(tour.getDateTime());

        TextView meetingSpotTextView = (TextView) rootView.findViewById(R.id.meeting_spot);
        meetingSpotTextView.setText(tour.getMeetingSpot());

        TextView tourGuideTextView = (TextView) rootView.findViewById(R.id.tour_guide);
        tourGuideTextView.setText(tour.getTourGuide());

        TextView descriptionTextView = (TextView) rootView.findViewById(R.id.description);
        descriptionTextView.setText(tour.getDescription());

        TextView ratingTextView = (TextView) rootView.findViewById(R.id.rating);
        ratingTextView.setText(tour.getDescription());

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
