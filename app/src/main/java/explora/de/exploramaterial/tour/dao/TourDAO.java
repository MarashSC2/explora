package explora.de.exploramaterial.tour.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import explora.de.exploramaterial.tour.entity.Tour;
import explora.de.exploramaterial.database.DatabaseConstants;
import explora.de.exploramaterial.database.DatabaseHelper;

/**
 * Created by Marash on 25.05.2016.
 */
public class TourDAO {

    private SQLiteDatabase database;
    private String [] projection;

    public TourDAO(DatabaseHelper databaseHelper) {
        database = databaseHelper.getWritableDatabase();
        projection = new String []{
                DatabaseConstants.TourEntry._ID,
                DatabaseConstants.TourEntry.COLUMN_NAME_DATETIME,
                DatabaseConstants.TourEntry.COLUMN_NAME_MEETING_SPOT,
                DatabaseConstants.TourEntry.COLUMN_NAME_TOUR_GUIDE,
                DatabaseConstants.TourEntry.COLUMN_NAME_PRICE,
                DatabaseConstants.TourEntry.COLUMN_NAME_TITLE,
                DatabaseConstants.TourEntry.COLUMN_NAME_DESCRIPTION,
                DatabaseConstants.TourEntry.COLUMN_NAME_RATING,
                DatabaseConstants.TourEntry.COLUMN_NAME_ADDRESS,
                DatabaseConstants.TourEntry.COLUMN_NAME_OWNER

        };
    }

    public boolean saveTour(Tour tour){
        database.update(  DatabaseConstants.TourEntry.TABLE_NAME,  tour.getContentValues(), DatabaseConstants.TourEntry._ID + " = ? ", new String[] { Integer.toString(tour.getId()) } );
        return true;
    }
    public boolean createTour(Tour tour) {
        tour.setId(getLastId()+1);
        ContentValues values = tour.getContentValues();
        database.insert(DatabaseConstants.TourEntry.TABLE_NAME, null, values);
        return true;
    }
    public List<Tour>findByTitle(String query){
        List<Tour> tours = new ArrayList<>();

        Cursor cursor = database.query(DatabaseConstants.TourEntry.TABLE_NAME
                , null,"title"+" LIKE '%"+query+"%'", null, null, null, null);

        if(cursor.getCount()<1)
            return tours;

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry._ID));
            String dateTime = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_DATETIME));
            String meetingSpot = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_MEETING_SPOT));
            String tourGuide = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_TOUR_GUIDE));
            String price = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_PRICE));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_DESCRIPTION));
            String rating = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_RATING));
            String address = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_ADDRESS));
            String userId =  cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_OWNER));

            tours.add(new Tour (Integer.parseInt(id), dateTime, meetingSpot, tourGuide, Integer.parseInt(price), title, description, rating, Integer.parseInt(address),Integer.parseInt(userId)));
        }
        return tours;
    }

    public int getLastId() {
        String query = "SELECT MAX(_id) AS max_id FROM tour";
        Cursor cursor = database.rawQuery(query,null);

        int id = 0;
        if (cursor.moveToFirst())
        {
            do
            {
                id = cursor.getInt(0);
            } while(cursor.moveToNext());
        }
        return id;

    }


    public Tour findById(int id){
        Tour tour = new Tour();

        Cursor cursor = database.query(
                DatabaseConstants.TourEntry.TABLE_NAME,
                projection,
                "_id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null
        );

        if(cursor.getCount() <1){
            return tour;
        }

        if (cursor != null)
            cursor.moveToFirst();

        tour.setId(Integer.parseInt(cursor.getString(0)));
        tour.setDateTime(cursor.getString(1));
        tour.setMeetingSpot(cursor.getString(2));
        tour.setTourGuide(cursor.getString(3));
        tour.setPrice(Integer.parseInt(cursor.getString(4)));
        tour.setTitle(cursor.getString(5));
        tour.setDescription(cursor.getString(6));
        tour.setRating(cursor.getString(7));
        tour.setAddress(Integer.parseInt(cursor.getString(8)));

        return tour;
    }

    public List<Tour> getAllTours() {
        List<Tour> tours = new ArrayList<>();

        Cursor cursor = database.query(
                DatabaseConstants.TourEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry._ID));
            String dateTime = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_DATETIME));
            String meetingSpot = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_MEETING_SPOT));
            String tourGuide = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_TOUR_GUIDE));
            String price = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_PRICE));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_DESCRIPTION));
            String rating = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_RATING));
            String address = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_ADDRESS));
            String userId =  cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_OWNER));

            tours.add(new Tour (Integer.parseInt(id), dateTime, meetingSpot, tourGuide, Integer.parseInt(price), title, description, rating, Integer.parseInt(address),Integer.parseInt(userId)));
        }

        return tours;
    }

    public List<Tour> findByCity(String city) {
        List<Tour> tours = new ArrayList<>();

        String MY_QUERY = "SELECT a._id,a.dateTime,a.meetingSpot,a.tourguide,a.price,a.title,a.beschreibung,a.rating,a.address,a.owner FROM "
                +DatabaseConstants.TourEntry.TABLE_NAME+" a INNER JOIN "+DatabaseConstants.AddressEntry.TABLE_NAME+" b ON "+DatabaseConstants.TourEntry.COLUMN_NAME_ADDRESS+"=b."+DatabaseConstants.AddressEntry._ID+" WHERE b."+DatabaseConstants.AddressEntry.COLUMN_NAME_CITY+"=?";

        Cursor cursor = database.rawQuery(MY_QUERY, new String[]{city});

        if(cursor.getCount()<1)
            return tours;

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry._ID));
            String dateTime = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_DATETIME));
            String meetingSpot = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_MEETING_SPOT));
            String tourGuide = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_TOUR_GUIDE));
            String price = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_PRICE));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_DESCRIPTION));
            String rating = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_RATING));
            String address = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_ADDRESS));
            String userId =  cursor.getString(cursor.getColumnIndex(DatabaseConstants.TourEntry.COLUMN_NAME_OWNER));

            tours.add(new Tour (Integer.parseInt(id), dateTime, meetingSpot, tourGuide, Integer.parseInt(price), title, description, rating, Integer.parseInt(address),Integer.parseInt(userId)));
        }
        return tours;
    }
}
