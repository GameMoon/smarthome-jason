package SmartHomeEnv;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class Room extends Object{
    private String name;

    private boolean temperature;
    private boolean light;
    private boolean smoke;
    private boolean movement;

    private boolean extinguish;

    public Room(String name, int posX, int posY,int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }
    
    public void draw(Graphics g){
        
        if(light){
            g.setColor(Color.yellow);
            g.fillRect(posX, posY, width, height);
        }

        if (extinguish)
            g.setColor(Color.red);

        g.setColor(Color.black);
        g.drawRect(posX, posY, width, height);

        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth(name);
        int h = fm.getAscent();
        g.drawString(name.substring(0, 1).toUpperCase() + name.substring(1), posX + width/2-w/2, posY + height/2 );
    }

    public String getName(){
        return name;
    }
    public void setTemperature(boolean value){
        temperature = value;
    }
    public boolean getTemperature(){
        return temperature;
    }
  
    public void setLight(boolean state){
        light = state;
    }
    public void extinguish(boolean state){
        extinguish = state;
    }

    public boolean getLight(){
        return light;
    }
    
    public void setSmoke(boolean state){
        smoke = state;
    }

    public boolean getSmoke(){
        return smoke;
    }

}