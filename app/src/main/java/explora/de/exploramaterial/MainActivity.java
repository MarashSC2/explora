package explora.de.exploramaterial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import explora.de.exploramaterial.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        dbHelper.getWritableDatabase();

        ArrayAdapter adapter = new CityCardAdapter(this,new String[] {"Madrid", "Stalingrad"});
        ListView cityListView = (ListView) findViewById(R.id.city_list_view);

        cityListView.setAdapter(adapter);
    }
}
