package SmartHomeEnv;

import java.awt.Graphics;

public abstract class Object{
    int width;
    int height;
    int posX;
    int posY;
    
    abstract void draw(Graphics g);

    public boolean contains(int x,int y){
        if( x >= posX && x <=posX+width && y >=posY && y<=posY+height) return true;
        return false;
    }
}   