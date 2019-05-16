package SmartHomeEnv;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;

public abstract class Human extends Object{
    private Color color;
    private int startX;
    private int startY;

    public Human(int posX, int posY,Color color){
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        this.startX = posX;
        this.startY = posY;
        this.width = this.height = 20;
    }
    public void escape(){
        move(startX,startY);
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(posX-width/2, posY-height/2, width, height);
    }

    public void move(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }
    public int getX(){ return posX;}
    public int getY(){ return posY;}
    public abstract String getType();
}