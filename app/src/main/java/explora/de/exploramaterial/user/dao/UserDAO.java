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

    public User findById(int id){
        User user = new User();

        Cursor cursor = database.query(
                DatabaseConstants.UserEntry.TABLE_NAME,
                projection,
                "_id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null
        );

        if(cursor.getCount() <1){
            return user;
        }

        if (cursor != null)
            cursor.moveToFirst();

        user.setId(Integer.parseInt(cursor.getString(0)));
        user.setEmail(cursor.getString(1));
        user.setName(cursor.getString(2));
        user.setPassword(cursor.getString(3));

        return user;
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

            users.add(new User (mail, password, name));
        }
        if(users.size()==0){
            return null;
        }
        return users.get(0).getPassword();

    }

    public User findByEmail(String email){
        User user = new User();

        Cursor cursor = database.query(
                DatabaseConstants.UserEntry.TABLE_NAME,
                projection,
                "mail = ?",
                new String[] { email },
                null,
                null,
                null,
                null
        );

        if(cursor.getCount() <1){
            return user;
        }

        if (cursor != null)
            cursor.moveToFirst();

        user.setId(Integer.parseInt(cursor.getString(0)));
        user.setEmail(cursor.getString(1));
        user.setName(cursor.getString(2));
        user.setPassword(cursor.getString(3));

        return user;
    }

    public boolean save(User user){
        ContentValues values = user.getContentValues();
        return database.insert(DatabaseConstants.UserEntry.TABLE_NAME,null,values)>-1;
    }

    public User update(User user){
        return user;
    }

}
