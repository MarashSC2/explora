package explora.de.exploramaterial.Entity;

import android.content.ContentValues;

import explora.de.exploramaterial.database.DatabaseConstants;

/**
 * Created by Marash on 25.05.2016.
 */
public class User implements Entity{

    private long id;
    private String email;
    private String name;
    private String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();

        values.put(DatabaseConstants.UserEntry.COLUMN_NAME_NAME, name);
        values.put(DatabaseConstants.UserEntry.COLUMN_NAME_MAIL, email);
        values.put(DatabaseConstants.UserEntry.COLUMN_NAME_PASSWORD, password);
        values.put(DatabaseConstants.UserEntry._ID, id);
        return values;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
