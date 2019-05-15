package SmartHomeEnv;

import javax.swing.*;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class HouseView extends JComponent implements MouseListener {

    private JFrame window;
    private HouseModel model;

    public HouseView(HouseModel model){
        
        window = new JFrame("SmartHome");// creating instance of JFrame
        window.getContentPane().add(this);
        window.setSize(800, 600);
        window.setVisible(true);
        window.addMouseListener( (MouseListener) this);
        this.model = model;
    }

    public void paint(Graphics g) {
        for(Object object : model.getObjects()){
            object.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        System.out.println("here was a click ! "+ arg0.getButton());
        
    }
    @Override
    public void mousePressed(MouseEvent arg0) {
    }
    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
    @Override
    public void mouseEntered(MouseEvent arg0) {
    }
    @Override
    public void mouseExited(MouseEvent arg0) {
    }
}