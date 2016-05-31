package explora.de.exploramaterial.user.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import explora.de.exploramaterial.database.DatabaseConstants;
import explora.de.exploramaterial.database.DatabaseHelper;
import explora.de.exploramaterial.tour.entity.Tour;
import explora.de.exploramaterial.user.entity.User;

/**
 * Created by Marash on 25.05.2016.
 */
public class UserDAO {

    private SQLiteDatabase database;
    private String [] projection;

    public UserDAO (DatabaseHelper databaseHelper){
        database = databaseHelper.getWritableDatabase();
        projection = new String []{
                DatabaseConstants.UserEntry._ID,
                DatabaseConstants.UserEntry.COLUMN_NAME_MAIL,
                DatabaseConstants.UserEntry.COLUMN_NAME_NAME,
                DatabaseConstants.UserEntry.COLUMN_NAME_PASSWORD
        };
    }

    public User findById(){
        return null;
    }

    public String findPasswordByEMail(String email){
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(
                DatabaseConstants.UserEntry.TABLE_NAME,
                projection,
                DatabaseConstants.UserEntry.COLUMN_NAME_MAIL+" = ?",
                new String[] { String.valueOf(email) },
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            String mail = cursor.getString(cursor.getColumnIndex(DatabaseConstants.UserEntry.COLUMN_NAME_MAIL));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.UserEntry.COLUMN_NAME_NAME));
            String password = cursor.getString(cursor.getColumnIndex(DatabaseConstants.UserEntry.COLUMN_NAME_PASSWORD));

            users.add(new User (mail, name, password));
        }
        if(users.size()==0){
            return null;
        }
        return users.get(0).getEmail();

    }

    public boolean save(User user){
        ContentValues values = user.getContentValues();
        return database.insert(DatabaseConstants.UserEntry.TABLE_NAME,null,values)>-1;
    }

    public User update(User user){
        return user;
    }

}
