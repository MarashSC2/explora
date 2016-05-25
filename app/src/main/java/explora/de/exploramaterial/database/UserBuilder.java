package explora.de.exploramaterial.database;

import java.util.ArrayList;
import java.util.List;

import explora.de.exploramaterial.Entity.User;

/**
 * Created by Marash on 25.05.2016.
 */
public class UserBuilder {

    public List<User> getDummyUsers (){
        List<User> users = new ArrayList<>();
        users.add(new User("admin@admin.de","Admin","padmin"));
        users.add(new User("guide@admin.de","Guide","pguide"));
        users.add(new User("kunde@admin.de","Kunde","pkunde"));

        return users;
    }
}
