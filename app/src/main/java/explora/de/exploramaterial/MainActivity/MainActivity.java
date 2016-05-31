package explora.de.exploramaterial.MainActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import explora.de.exploramaterial.city.view.CityCardClickListener;
import explora.de.exploramaterial.city.view.CityCardFragment;
import explora.de.exploramaterial.tour.entity.Tour;
import explora.de.exploramaterial.R;
import explora.de.exploramaterial.tour.view.SingleTourFragment;
import explora.de.exploramaterial.tour.view.TourCardFragment;
import explora.de.exploramaterial.tour.view.TourCardClickListener;
import explora.de.exploramaterial.tour.dao.TourDAO;
import explora.de.exploramaterial.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements CityCardClickListener, TourCardClickListener {

    public static DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this.getApplicationContext());

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }
            CityCardFragment cityFragment = new CityCardFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, cityFragment).commit();
        }
    }

    public void changeFragment(int containerId, Fragment targetFragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, targetFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onCityCardClick(int tourId) {
        TourDAO tourDao = new TourDAO(databaseHelper);
        List<Tour> tours = tourDao.getAllTours();
        Log.d("Tour: ", tours.toString());

        TourCardFragment topTourFragment = new TourCardFragment();
        changeFragment(R.id.fragment_container, topTourFragment);
    }

    @Override
    public void onTourCardClick(Tour tour) {
        if (tour == null){
            Log.d("Tour: ", "tour: ");
            return;
        }

        SingleTourFragment singleTourFragment = new SingleTourFragment();
        Bundle args = new Bundle();
        args.putSerializable(SingleTourFragment.ARG_TOUR, (Serializable)tour);
        singleTourFragment.setArguments(args);
        changeFragment(R.id.fragment_container, singleTourFragment);
    }
}
