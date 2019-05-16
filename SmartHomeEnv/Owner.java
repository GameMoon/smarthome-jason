package SmartHomeEnv;

import java.awt.Color;


public class Owner extends Human {

    public Owner(int posX,int posY) {
        super(posX,posY,Color.pink);
    }
    public String getType(){
        return "owner";
    }

}