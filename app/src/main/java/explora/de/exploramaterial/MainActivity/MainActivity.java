package explora.de.exploramaterial.MainActivity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import explora.de.exploramaterial.LoginActivity.LoginActivity;

import java.io.Serializable;
import java.util.List;

import explora.de.exploramaterial.address.entity.Address;
import explora.de.exploramaterial.area.view.AreaCardClickListener;
import explora.de.exploramaterial.area.view.AreaCardFragment;
import explora.de.exploramaterial.tour.entity.Tour;
import explora.de.exploramaterial.R;
import explora.de.exploramaterial.tour.view.SingleTourFragment;
import explora.de.exploramaterial.tour.view.TourCardFragment;
import explora.de.exploramaterial.tour.view.TourCardClickListener;
import explora.de.exploramaterial.tour.dao.TourDAO;
import explora.de.exploramaterial.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements AreaCardClickListener, TourCardClickListener {

    public static DatabaseHelper databaseHelper;

    public static final String PREFS_LOGIN = "login_prefs";
    private static final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_LOGIN, 0);
        Log.d(TAG,"login string: "+settings.getString("logged", "").toString());

            if(!settings.getString("logged", "").toString().equals("logged")){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
        }

        databaseHelper = new DatabaseHelper(this.getApplicationContext());


        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }
            AreaCardFragment cityFragment = new AreaCardFragment();
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
    public void onAreaCardClick(Address address) {
        if (address == null || address.getCity() == null){
            return;
        }
        TourDAO tourDao = new TourDAO(databaseHelper);
        List<Tour> tours = tourDao.findByCity(address.getCity());
        TourCardFragment topTourFragment = new TourCardFragment();
        Bundle args = new Bundle();
        args.putSerializable(SingleTourFragment.ARG_TOUR, (Serializable)tours);
        topTourFragment.setArguments(args);
        changeFragment(R.id.fragment_container, topTourFragment);
    }

    @Override
    public void onTourCardClick(Tour tour) {
        if (tour == null){
            return;
        }

        SingleTourFragment singleTourFragment = new SingleTourFragment();
        Bundle args = new Bundle();
        args.putSerializable(SingleTourFragment.ARG_TOUR, (Serializable)tour);
        singleTourFragment.setArguments(args);
        changeFragment(R.id.fragment_container, singleTourFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
               /* Intent myIntent = new Intent(this, SettingsActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                this.startActivity(myIntent);
                 */
                return true;

            case R.id.action_logout:
                SharedPreferences settings = getSharedPreferences(PREFS_LOGIN, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.remove("logged");
                editor.commit();
                Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                this.startActivity(myIntent);
                finish();
                return true;
            case R.id.action_about:
                String about = "Impressum:\n" +
                        "Explora GmbH\n";
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.ic_info);
                builder.setTitle("About");
                builder.setPositiveButton(android.R.string.ok,null);
                builder.setMessage(about);
                builder.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
