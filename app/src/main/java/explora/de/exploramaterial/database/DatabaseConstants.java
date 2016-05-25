package explora.de.exploramaterial.database;

import android.provider.BaseColumns;

import explora.de.exploramaterial.Entity.Address;

/**
 * Created by Marash on 25.05.2016.
 */
public class DatabaseConstants {

    public static int DB_VERSION = 1;
    public static final String DB_NAME = "explora.db";
    public static final String COMMA_SEP = ",";
    public static final String CREATE_TABLE = "CREATE TABLE ";

    public static final String TEXT = " TEXT";
    public static final String INTEGER = " INT";

    public static final String NOT_NULL = " NOT NULL";
    public static final String UNIQUE = " UNIQUE";
    public static final String PRIMARY_KEY = " PRIMARY KEY";

    public static final String SQL_CREATE_ENTRIES =
            CREATE_TABLE + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + INTEGER +  PRIMARY_KEY + COMMA_SEP +
                    UserEntry.COLUMN_NAME_NAME + TEXT + NOT_NULL + COMMA_SEP +
                    UserEntry.COLUMN_NAME_MAIL + TEXT + UNIQUE + NOT_NULL + COMMA_SEP +
                    UserEntry.COLUMN_NAME_PASSWORD + TEXT + NOT_NULL + ");";
    public static final String SQL_CREATE_ENTRIESS = CREATE_TABLE + AddressEntry.TABLE_NAME + " (" +
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

        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_TIME_OF_DAY = "timeofday";


    }

    public static class AddressEntry implements BaseColumns {
        public static final String TABLE_NAME = "address";

        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STREET = "street";
        public static final String COLUMN_NAME_ZIP_CODE = "zipcode";
    }

}
