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
import explora.de.exploramaterial.CityCard.CityCardClickListener;
import explora.de.exploramaterial.CityCard.CityFragment;
import explora.de.exploramaterial.R;
import explora.de.exploramaterial.TourCard.TourFragment;
import explora.de.exploramaterial.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements CityCardClickListener{

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
