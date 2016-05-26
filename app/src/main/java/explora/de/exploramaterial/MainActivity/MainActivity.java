package explora.de.exploramaterial.MainActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import explora.de.exploramaterial.CityCard.CityCardClickListener;
import explora.de.exploramaterial.CityCard.CityFragment;
import explora.de.exploramaterial.R;
import explora.de.exploramaterial.TourCard.TourFragment;
import explora.de.exploramaterial.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements CityCardClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }
            CityFragment cityFragment = new CityFragment();
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
    public void onCityCardClick(String cityName) {
        TourFragment newTourFragment = new TourFragment();
        Bundle args = new Bundle();
        args.putString(TourFragment.ARG_CITY_NAME,cityName);
        newTourFragment.setArguments(args);
        changeFragment(R.id.fragment_container,newTourFragment);
    }
}
