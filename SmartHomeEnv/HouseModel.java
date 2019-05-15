package SmartHomeEnv;

import jason.environment.grid.GridWorldModel;
import java.util.List;
import java.util.Arrays;
import java.util.*;

public class HouseModel{

    private List<Object> objects = new ArrayList();

    public HouseModel() {
        objects.add(new Room("Konyha",20,20,100,170));
        objects.add(new Room("Fürdőszoba", 20, 190, 100, 80));
        objects.add(new Room("Előszoba",120,20,70,250));
        objects.add(new Room("Nappali",190,20,150,150));
        objects.add(new Room("Hálószoba",190,170,150,100));

        objects.add(new Owner(40,40));
        objects.add(new Guest(40,100));
    }

    public List<Object> getObjects(){
        return objects;
    }
}