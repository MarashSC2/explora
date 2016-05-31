package explora.de.exploramaterial.database;

import java.util.ArrayList;
import java.util.List;

import explora.de.exploramaterial.address.entity.Address;
import explora.de.exploramaterial.tour.entity.Tour;
import explora.de.exploramaterial.user.entity.User;

/**
 * Created by Marash on 25.05.2016.
 */
public class DummyBuilder {

    public List<User> getDummyUsers (){
        List<User> users = new ArrayList<>();
        users.add(new User(1,"admin@admin.de","Admin","padmin"));
        users.add(new User(2,"guide@admin.de","Guide","pguide"));
        users.add(new User(3,"kunde@admin.de","Kunde","pkunde"));

        return users;
    }

    public List<Address> getDummyAddresses () {
        List<Address> addresses = new ArrayList<>();

        addresses.add(new Address(1,"Deutschland","Stuttgart","Königstraße 28","70173"));
        addresses.add(new Address(2,"Deutschland","Stuttgart","Königstraße 29","70173"));
        addresses.add(new Address(3,"Deutschland","Stuttgart","Königstraße 30","70173"));

        addresses.add(new Address(4,"Deutschland","Berlin","Platz der Republik 1","11011"));
        addresses.add(new Address(5,"Deutschland","Berlin","Platz der Republik 2","11011"));

        addresses.add(new Address(6,"Vereinigtes Königreich","London","Wilton Pl","SW1X 7RL"));

        return addresses;
    }

    public List<Tour> getDummyTours (){
        List<Tour> tours = new ArrayList<>();
        tours.add(new Tour(1, "29.05.2016 15:00", "Neuler", "Ursula", 50, "Neuler", "Schöne Umgebung", "Gut", 1));
        tours.add(new Tour(2, "29.05.2016 15:00", "Stuttgart", "Ursula", 50, "Stuttgart", "Schöne Umgebung", "Gut", 1));
        tours.add(new Tour(3, "29.05.2016 15:00", "Karlsruhe Forest", "Ursula", 50, "Karlsruhe Forest", "Schöne Umgebung", "Gut", 4));
        tours.add(new Tour(4, "29.05.2016 15:00", "Durlach", "Ursula", 50, "Durlach", "Schöne Umgebung", "Gut", 4));
        tours.add(new Tour(5, "29.05.2016 15:00", "Schwarzwald", "Ursula", 50, "Schwarzwald", "Schöne Umgebung", "Gut", 6));
        return tours;
    }
}
