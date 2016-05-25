package explora.de.exploramaterial.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import explora.de.exploramaterial.database.DatabaseConstants;
import explora.de.exploramaterial.database.DatabaseHelper;
import explora.de.exploramaterial.Entity.User;

/**
 * Created by Marash on 25.05.2016.
 */
public class UserDAO {

    private SQLiteDatabase database;

    public UserDAO (DatabaseHelper databaseHelper){
        database = databaseHelper.getWritableDatabase();
    }

    public User findById(){
        return null;
    }

    public User findByEMail(){
        return null;
    }

    public User save(User user){

        ContentValues values = user.getContentValues();
        database.insert(DatabaseConstants.UserEntry.TABLE_NAME,null,values);

        return user;
    }

    public User update(User user){
        return user;
    }

}
