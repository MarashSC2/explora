package explora.de.exploramaterial.MainActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import java.io.Serializable;
import java.util.List;

import explora.de.exploramaterial.LoginActivity.LoginActivity;
import explora.de.exploramaterial.NavigationDraw.FragmentDrawer;
import explora.de.exploramaterial.NavigationDraw.FragmentDrawerListener;
import explora.de.exploramaterial.R;
import explora.de.exploramaterial.address.entity.Address;
import explora.de.exploramaterial.area.view.AreaCardClickListener;
import explora.de.exploramaterial.area.view.AreaCardFragment;
import explora.de.exploramaterial.database.DatabaseHelper;
import explora.de.exploramaterial.tour.dao.TourDAO;
import explora.de.exploramaterial.tour.entity.Tour;
import explora.de.exploramaterial.tour.view.CreateTourFragment;
import explora.de.exploramaterial.tour.view.SingleTourFragment;
import explora.de.exploramaterial.tour.view.TourCardClickListener;
import explora.de.exploramaterial.tour.view.TourCardFragment;

/**
 * Einstiegspunkt der App
 * Tauscht fragmente aus
 */
public class MainActivity extends AppCompatActivity implements AreaCardClickListener, TourCardClickListener, FragmentDrawerListener {

    public static DatabaseHelper databaseHelper;

    //navdrawer elemente
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    //TAG für debuggen und die login preferences
    public static final String PREFS_LOGIN = "login_prefs";
    private static final String TAG = "Main Activity";
    private String currentUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sidebar fragment wird erstellt
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);


        //speichern des aktuellen Nutzers in den preferences
        SharedPreferences settings = getSharedPreferences(PREFS_LOGIN, 0);
        currentUser = settings.getString("userName", "");
        Log.d(TAG, "login string: " + settings.getString("logged", "").toString() + " User:" + currentUser);

        //Falls nicht eingeloggt, wird man  zur LoginActivity weitergeleitet
        if (!settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        databaseHelper = new DatabaseHelper(this.getApplicationContext());

        displayView(0);
    }

    /**
     * Wechselt das Fragment innerhalb der Activity aus
     * @param containerId
     * @param targetFragment
     * @param title
     */
    public void changeFragment(int containerId, Fragment targetFragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, targetFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        if (title != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    /**
     * Holt die Touren für eine Area aus der Datenbank und wechselt das Fragment aus
     * @param address
     */
    @Override
    public void onAreaCardClick(Address address) {
        if (address == null || address.getCity() == null) {
            return;
        }
        TourDAO tourDao = new TourDAO(databaseHelper);
        List<Tour> tours = tourDao.findByCity(address.getCity());
        TourCardFragment topTourFragment = new TourCardFragment();
        Bundle args = new Bundle();
        args.putString(SingleTourFragment.ARG_USER, currentUser);
        args.putSerializable(SingleTourFragment.ARG_TOUR, (Serializable) tours);
        topTourFragment.setArguments(args);
        changeFragment(R.id.fragment_container, topTourFragment, getString(R.string.tour_card_fragment));
    }

    /**
     * Die gewählte Tour wird verwendet und das Fragment ausgetauscht
     * @param tour
     */
    @Override
    public void onTourCardClick(Tour tour) {
        if (tour == null) {
            return;
        }

        SingleTourFragment singleTourFragment = new SingleTourFragment();
        Bundle args = new Bundle();
        args.putString(SingleTourFragment.ARG_USER, currentUser);
        args.putSerializable(SingleTourFragment.ARG_TOUR, (Serializable) tour);
        singleTourFragment.setArguments(args);
        changeFragment(R.id.fragment_container, singleTourFragment, getString(R.string.single_tour_fragment));
    }

    /**
     * Erstellt das Optionsmenu mit der Suche
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Implementiert die Searchbar/View
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * Die übermittelte Query wird ausgeführt und das Fragment ausgetauscht um die Ergebnisse anzuzeigen
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                TourDAO tourDao = new TourDAO(databaseHelper);
                List<Tour> tours;
                if (query.equals("*"))
                    tours = tourDao.getAllTours();
                else
                    tours = tourDao.findByTitle(query);
                TourCardFragment topTourFragment = new TourCardFragment();
                Bundle args = new Bundle();
                args.putString(SingleTourFragment.ARG_USER, currentUser);
                args.putSerializable(SingleTourFragment.ARG_TOUR, (Serializable) tours);
                topTourFragment.setArguments(args);
                changeFragment(R.id.fragment_container, topTourFragment, getString(R.string.tour_card_fragment));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    /**
     * Funktion welche aufgerufen wird, bei Auswahl eines Optionsmenüs
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_about:
                String about = "Impressum:\n" +
                        "Explora GmbH\n";
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.ic_info);
                builder.setTitle("About");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setMessage(about);
                builder.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    /**
     * Bei Auswahl eines Menüs in der Sidebar wir das entsprechende Fragment eingewechselt
     * @param position
     */
    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new AreaCardFragment();
                changeFragment(R.id.fragment_container, fragment, getString(R.string.area_card_fragment));
                break;
            case 1:
                fragment = new CreateTourFragment();
                Bundle args = new Bundle();
                args.putString(SingleTourFragment.ARG_USER, currentUser);
                fragment.setArguments(args);
                changeFragment(R.id.fragment_container, fragment, getString(R.string.create_tour_fragment));
                break;
            case 2:
                SharedPreferences settings = getSharedPreferences(PREFS_LOGIN, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.remove("logged");
                editor.commit();
                Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                this.startActivity(myIntent);
                finish();
                break;
            default:
                break;
        }
    }

}
