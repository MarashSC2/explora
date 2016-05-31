package explora.de.exploramaterial.tour.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
                DatabaseConstants.TourEntry.COLUMN_NAME_ADDRESS
        };
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

            tours.add(new Tour (Integer.parseInt(id), dateTime, meetingSpot, tourGuide, Integer.parseInt(price), title, description, rating, Integer.parseInt(address)));
        }

        return tours;
    }

    public List<Tour> findByCity(String city) {
        List<Tour> tours = new ArrayList<>();

        Cursor cursor = database.query(
                DatabaseConstants.TourEntry.TABLE_NAME,
                projection,
                "meetingSpot = ?",
                new String[] { String.valueOf(city) },
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

            tours.add(new Tour (Integer.parseInt(id), dateTime, meetingSpot, tourGuide, Integer.parseInt(price), title, description, rating, Integer.parseInt(address)));
        }

        return tours;
    }
}
