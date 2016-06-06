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
    String[] projection = {
            DatabaseConstants.AddressEntry._ID,
            DatabaseConstants.AddressEntry.COLUMN_NAME_COUNTRY,
            DatabaseConstants.AddressEntry.COLUMN_NAME_CITY,
            DatabaseConstants.AddressEntry.COLUMN_NAME_STREET,
            DatabaseConstants.AddressEntry.COLUMN_NAME_ZIP_CODE

    };
    public AddressDAO(DatabaseHelper databaseHelper) {
        database = databaseHelper.getWritableDatabase();
    }

    public List<Address> getAllAddresses() {
        List<Address> addresses = new ArrayList<>();

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
            Address address = new Address();
            address.setId(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.AddressEntry._ID)));
            address.setCountry(cursor.getString(cursor.getColumnIndex(DatabaseConstants.AddressEntry.COLUMN_NAME_COUNTRY)));
            address.setCity(cursor.getString(cursor.getColumnIndex(DatabaseConstants.AddressEntry.COLUMN_NAME_CITY)));
            address.setStreet(cursor.getString(cursor.getColumnIndex(DatabaseConstants.AddressEntry.COLUMN_NAME_STREET)));
            address.setZipCode(cursor.getString(cursor.getColumnIndex(DatabaseConstants.AddressEntry.COLUMN_NAME_ZIP_CODE)));
            addresses.add(address);
        }

        return addresses;
    }

    public Address findById(int id){
        Address address = new Address();

        Cursor cursor = database.query(
                DatabaseConstants.AddressEntry.TABLE_NAME,
                projection,
                "_id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null
        );

        if(cursor.getCount() <1){
            return address;
        }

        if (cursor != null)
            cursor.moveToFirst();

        address.setId(Integer.parseInt(cursor.getString(0)));
        address.setCountry(cursor.getString(1));
        address.setCity(cursor.getString(2));
        address.setStreet(cursor.getString(3));
        address.setZipCode(cursor.getString(4));


        return address;
    }
}
