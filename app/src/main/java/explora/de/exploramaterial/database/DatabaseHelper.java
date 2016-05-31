package explora.de.exploramaterial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

import explora.de.exploramaterial.address.entity.Address;
import explora.de.exploramaterial.tour.entity.Tour;
import explora.de.exploramaterial.user.entity.User;

/**
 * Created by Marash on 25.05.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private DummyBuilder dBuilder;

    public DatabaseHelper(Context context) {
        super(context, DatabaseConstants.DB_NAME, null, DatabaseConstants.DB_VERSION);
        dBuilder = new DummyBuilder();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DatabaseConstants.SQL_CREATE_ENTRIES_1);
        database.execSQL(DatabaseConstants.SQL_CREATE_ENTRIES_2);
        database.execSQL(DatabaseConstants.SQL_CREATE_ENTRIES_3);
        insertDummyUsers(database);
        insertDummyAddresses(database);
        insertDummyTours(database);

        Log.d(TAG,"OnCreate() aufgerufen.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void insertDummyUsers(SQLiteDatabase database){

        List<User> startUsers = dBuilder.getDummyUsers();

        for(User user : startUsers){
            ContentValues values = user.getContentValues();
            database.insert(DatabaseConstants.UserEntry.TABLE_NAME, null, values);
        }
    }

    private void insertDummyAddresses(SQLiteDatabase database){
        Log.d(TAG,"ADDRESS INSERT");
        List<Address> startAddresses = dBuilder.getDummyAddresses();

        for(Address address : startAddresses){
            ContentValues values = address.getContentValues();
            database.insert(DatabaseConstants.AddressEntry.TABLE_NAME, null, values);
        }
    }

    private void insertDummyTours(SQLiteDatabase database){
        Log.d(TAG,"TOURS INSERT");
        List<Tour> startTours = dBuilder.getDummyTours();

        for(Tour tour : startTours){
            ContentValues values = tour.getContentValues();
            database.insert(DatabaseConstants.TourEntry.TABLE_NAME, null, values);
        }
    }
}
