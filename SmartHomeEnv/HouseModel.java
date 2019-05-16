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
    private Room selectedRoom = null;

    private HouseEnv houseEnv;

    public HouseModel(HouseEnv houseEnv) {
        this.houseEnv = houseEnv;

        addRoom(new Room("kitchen",20,20,100,170));
        addRoom(new Room("bathroom", 20, 190, 100, 80));
        addRoom(new Room("hall",120,20,70,250));
        addRoom(new Room("livingroom",190,20,150,150));
        addRoom(new Room("bedroom",190,170,150,100));
        addHuman(new Owner(20, 320));
        addHuman(new Guest(40, 320));

        selectedRoom = rooms.get(0);
           
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
        houseEnv.deletePercept("left("+human.getType()+","+ room.getName()+")");
        houseEnv.createPercept("entered("+human.getType()+","+room.getName()+")");
       
    }
    public void escape(String humanType){
        if(humanType == "guest") humans.get(1).escape();
        if(humanType == "owner") humans.get(0).escape();
    }
    public void leaveRoom(Room room, Human human) {
        
        houseEnv.createPercept("left(" + human.getType() +","+room.getName()+")");
        houseEnv.deletePercept("entered(" + human.getType() +","+room.getName()+")");
    }
    public Room selectRoom(String name){
        selectedRoom = getRoom(name);
        return selectedRoom;
    }

    public void setHighTemp(boolean state){
        if(selectedRoom == null) return;
        selectedRoom.setTemperature(state);
        System.out.println("Room: " + selectedRoom.getName() + " HighTemp:" + state);

        if(state){
            houseEnv.createPerceptAgent("highTemp(" + selectedRoom.getName() + ")", selectedRoom.getName());
            houseEnv.deletePerceptAgent("lowTemp(" + selectedRoom.getName() + ")", selectedRoom.getName());
        }
        else{
            houseEnv.deletePerceptAgent("highTemp(" + selectedRoom.getName() + ")", selectedRoom.getName());
            houseEnv.createPerceptAgent("lowTemp(" + selectedRoom.getName() + ")", selectedRoom.getName());
        }
    }
    public void setSmoke(boolean state){
        if(selectedRoom == null) return;
        selectedRoom.setSmoke(state);
        System.out.println("Room: "+selectedRoom.getName()+ " Smoke:"+state);

        if (state) {
            houseEnv.createPerceptAgent("highSmoke(" + selectedRoom.getName() + ")", selectedRoom.getName());
            houseEnv.deletePerceptAgent("lowSmoke(" + selectedRoom.getName() + ")", selectedRoom.getName());
        } else {
            houseEnv.deletePerceptAgent("highSmoke(" + selectedRoom.getName() + ")", selectedRoom.getName());
            houseEnv.createPerceptAgent("lowSmoke(" + selectedRoom.getName() + ")", selectedRoom.getName());
        }
    }

    public void selectHuman(int x, int y){
        System.out.println("X: "+x+" Y:"+y);

        if (selectedHuman != null) {
            Room currentRoom = findRoomByPos(selectedHuman.getX(), selectedHuman.getY());
            selectedHuman.move(x, y);
            Room newRoom = findRoomByPos(selectedHuman.getX(), selectedHuman.getY());

            if (currentRoom != newRoom && currentRoom != null)
                leaveRoom(currentRoom, selectedHuman);
            if (currentRoom != newRoom && newRoom != null)
                enterRoom(newRoom,selectedHuman);

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
    public String[] getRoomNames(){
        List<String> names = new ArrayList<String>();
        for(Room room : rooms){
            names.add(room.getName());
        }
        return names.toArray(new String[0]);
    }
}