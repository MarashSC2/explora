package explora.de.exploramaterial.database;

import android.provider.BaseColumns;

/**
 * Created by Marash on 25.05.2016.
 */
public class DatabaseConstants {

    public static int DB_VERSION = 3;
    public static final String DB_NAME = "explora.db";
    public static final String COMMA_SEP = ",";
    public static final String CREATE_TABLE = "CREATE TABLE ";

    public static final String TEXT = " TEXT";
    public static final String INTEGER = " INT";

    public static final String NOT_NULL = " NOT NULL";
    public static final String UNIQUE = " UNIQUE";
    public static final String PRIMARY_KEY = " PRIMARY KEY";

    public static final String SQL_CREATE_ENTRIES_1 =
            CREATE_TABLE + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + INTEGER +  PRIMARY_KEY + COMMA_SEP +
                    UserEntry.COLUMN_NAME_NAME + TEXT + NOT_NULL + COMMA_SEP +
                    UserEntry.COLUMN_NAME_MAIL + TEXT + UNIQUE + NOT_NULL + COMMA_SEP +
                    UserEntry.COLUMN_NAME_PASSWORD + TEXT + NOT_NULL + ");";

    public static final String SQL_CREATE_ENTRIES_2 =
            CREATE_TABLE + TourEntry.TABLE_NAME + " (" +
                    TourEntry._ID + INTEGER + PRIMARY_KEY + COMMA_SEP +
                    TourEntry.COLUMN_NAME_DATETIME + TEXT + NOT_NULL + COMMA_SEP +
                    TourEntry.COLUMN_NAME_MEETING_SPOT + TEXT + NOT_NULL + COMMA_SEP +
                    TourEntry.COLUMN_NAME_TOUR_GUIDE + TEXT + NOT_NULL + COMMA_SEP +
                    TourEntry.COLUMN_NAME_PRICE + INTEGER + NOT_NULL + COMMA_SEP +
                    TourEntry.COLUMN_NAME_TITLE + TEXT + NOT_NULL + COMMA_SEP +
                    TourEntry.COLUMN_NAME_DESCRIPTION + TEXT + NOT_NULL + COMMA_SEP +
                    TourEntry.COLUMN_NAME_RATING + TEXT + NOT_NULL + COMMA_SEP +
                    TourEntry.COLUMN_NAME_ADDRESS + INTEGER + NOT_NULL + COMMA_SEP +
                    TourEntry.COLUMN_NAME_OWNER + INTEGER + NOT_NULL + ");";

    public static final String SQL_CREATE_ENTRIES_3 =
            CREATE_TABLE + AddressEntry.TABLE_NAME + " (" +
                    AddressEntry._ID + INTEGER + PRIMARY_KEY + COMMA_SEP +
                    AddressEntry.COLUMN_NAME_COUNTRY + TEXT + NOT_NULL + COMMA_SEP +
                    AddressEntry.COLUMN_NAME_CITY + TEXT + NOT_NULL + COMMA_SEP +
                    AddressEntry.COLUMN_NAME_STREET + TEXT + NOT_NULL + COMMA_SEP +
                    AddressEntry.COLUMN_NAME_ZIP_CODE + TEXT + NOT_NULL + ");";

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";

        public static final String COLUMN_NAME_MAIL = "mail";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static class TourEntry implements BaseColumns {
        public static final String TABLE_NAME = "tour";

        public static final String COLUMN_NAME_DATETIME = "dateTime";
        public static final String COLUMN_NAME_MEETING_SPOT = "meetingSpot";
        public static final String COLUMN_NAME_TOUR_GUIDE = "tourguide";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "beschreibung";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_OWNER = "owner";
    }

    public static class AddressEntry implements BaseColumns {
        public static final String TABLE_NAME = "address";

        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STREET = "street";
        public static final String COLUMN_NAME_ZIP_CODE = "zipcode";
    }

}
