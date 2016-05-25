package explora.de.exploramaterial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

import explora.de.exploramaterial.Entity.Address;
import explora.de.exploramaterial.Entity.User;

/**
 * Created by Marash on 25.05.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private DummyBuilder userBuilder;

    public DatabaseHelper(Context context) {
        super(context, DatabaseConstants.DB_NAME, null, DatabaseConstants.DB_VERSION);
        userBuilder = new DummyBuilder();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DatabaseConstants.SQL_CREATE_ENTRIES);
        database.execSQL(DatabaseConstants.SQL_CREATE_ENTRIESS);
        insertDummyUsers(database);
        insertDummyAddresses(database);
        Log.d(TAG,"OnCreate() aufgerufen.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void insertDummyUsers(SQLiteDatabase database){

        List<User> startUsers = userBuilder.getDummyUsers();

        for(User user : startUsers){
            ContentValues values = user.getContentValues();
            database.insert(DatabaseConstants.UserEntry.TABLE_NAME,null,values);
        }
    }

    private void insertDummyAddresses(SQLiteDatabase database){
        Log.d(TAG,"ADDRESS INSERT");
        List<Address> startAddresses = userBuilder.getDummyAddresses();

        for(Address address : startAddresses){
            ContentValues values = address.getContentValues();
            database.insert(DatabaseConstants.AddressEntry.TABLE_NAME,null,values);
        }
    }
}
