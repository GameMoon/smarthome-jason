package SmartHomeEnv;

import jason.environment.grid.GridWorldModel;
import java.util.List;
import java.util.Arrays;
import java.util.*;

public class HouseModel{

    private List<Object> objects = new ArrayList();

    private List<Room> rooms = new ArrayList();
    private List<Human> humans = new ArrayList();
    
    private Human selectedHuman = null;

    private HouseEnv houseEnv;

    public HouseModel(HouseEnv houseEnv) {
        this.houseEnv = houseEnv;

        addRoom(new Room("kitchen",20,20,100,170));
        addRoom(new Room("bathroom", 20, 190, 100, 80));
        addRoom(new Room("hall",120,20,70,250));
        addRoom(new Room("livingroom",190,20,150,150));
        addRoom(new Room("sleepingroom",190,170,150,100));
        addHuman(new Owner(20, 320));
        addHuman(new Guest(40, 320));
           
    }
    public void addRoom(Room room){
        rooms.add(room);
        objects.add(room);
    }
    public void addHuman(Human human){
        humans.add(human);
        objects.add(human);
    }

    public Room getRoom(String name){
        for(Room room : rooms){
            if(room.getName().equals(name)) return room;
        }
        return null;
    }

    public List<Object> getObjects(){
        return objects;
    }
    
    public void enterRoom(Room room,Human human){
        houseEnv.createPercept("entered("+human.getType()+","+room.getName()+")");
    }
    
    public void leaveRoom(Room room, Human human) {
        houseEnv.createPercept("left(" + human.getType() +","+room.getName()+")");
    }

    public void selectHuman(int x, int y){
        System.out.println("X: "+x+" Y:"+y);

        if (selectedHuman != null) {
            Room currentRoom = findRoomByPos(selectedHuman.getX(), selectedHuman.getY());
            selectedHuman.move(x, y);
            Room newRoom = findRoomByPos(selectedHuman.getX(), selectedHuman.getY());

            if (currentRoom != null)
                leaveRoom(currentRoom, selectedHuman);
            if (newRoom != null)
                enterRoom(newRoom, selectedHuman);

            selectedHuman = null;
            return;
        }
        selectedHuman = findHumanByPos(x, y);
       
    }

    private Room findRoomByPos(int x, int y){
        for (Room room : rooms) {
            if (room.contains(x, y)) {
                return room;
            }
        }
        return null;
    }
    private Human findHumanByPos(int x,int y){
        for (Human human : humans) {
            if (human.contains(x, y)) {
                return human;
            }
        }
        return null;
    }
  
}