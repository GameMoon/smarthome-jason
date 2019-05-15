package SmartHomeEnv;

import java.awt.Graphics;

public abstract class Object{
    int width;
    int height;
    int posX;
    int posY;
    
    abstract void draw(Graphics g);
}   