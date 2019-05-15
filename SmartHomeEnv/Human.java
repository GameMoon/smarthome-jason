package SmartHomeEnv;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;

public abstract class Human extends Object{
    private Color color;

    public Human(int posX, int posY,Color color){
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        this.width = this.height = 20;
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(posX, posY, width, height);
    }
}