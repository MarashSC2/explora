package explora.de.exploramaterial.address.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import explora.de.exploramaterial.address.entity.Address;
import explora.de.exploramaterial.database.DatabaseConstants;
import explora.de.exploramaterial.database.DatabaseHelper;

/**
 * Created by Marash on 25.05.2016.
 */
public class AddressDAO {

    private SQLiteDatabase database;

    public AddressDAO(DatabaseHelper databaseHelper) {
        database = databaseHelper.getWritableDatabase();
    }

    public List<Address> getAllAddresses() {
        List<Address> addresses = new ArrayList<>();

        String[] projection = {
                DatabaseConstants.AddressEntry.COLUMN_NAME_CITY
        };

        Cursor cursor = database.query(
                DatabaseConstants.AddressEntry.TABLE_NAME,
                projection,
                null,
                null,
                DatabaseConstants.AddressEntry.COLUMN_NAME_CITY,
                null,
                null
        );

        if(cursor.getCount() <1){
            return addresses;
        }

        while(cursor.moveToNext()){
            addresses.add(new Address(cursor.getString(cursor.getColumnIndex(DatabaseConstants.AddressEntry.COLUMN_NAME_CITY))));
        }

        return addresses;
    }
}
