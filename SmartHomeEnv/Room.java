package SmartHomeEnv;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;

public class Room extends Object{
    private String name;

    public Room(String name, int posX, int posY,int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }
    
    public void draw(Graphics g){
        g.drawRect(posX, posY, width, height);

        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth(name);
        int h = fm.getAscent();
        g.drawString(name, posX + width/2-w/2, posY + height/2 );
    }
}