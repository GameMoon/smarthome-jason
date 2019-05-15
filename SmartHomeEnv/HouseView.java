package SmartHomeEnv;

import javax.swing.*;
import java.awt.Graphics;
import java.util.List;


public class HouseView extends JComponent{

    private JFrame window;
    private HouseModel model;

    public HouseView(HouseModel model){
        
        window = new JFrame("SmartHome");// creating instance of JFrame
        window.getContentPane().add(this);
        window.setSize(800, 600);
        window.setVisible(true);
        this.model = model;
    }

    public void paint(Graphics g) {
        for(Object object : model.getObjects()){
            object.draw(g);
        }
    }
}