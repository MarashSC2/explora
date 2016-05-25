package explora.de.exploramaterial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

import explora.de.exploramaterial.Entity.User;

/**
 * Created by Marash on 25.05.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private UserBuilder userBuilder;

    public DatabaseHelper(Context context) {
        super(context, DatabaseConstants.DB_NAME, null, DatabaseConstants.DB_VERSION);
        userBuilder = new UserBuilder();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DatabaseConstants.SQL_CREATE_ENTRIES);
        insertDummyUsers(database);
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

    private void insertDummyAddresses(){

    }
}
