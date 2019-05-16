package SmartHomeEnv;

import javax.swing.*;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class HouseView extends JPanel implements MouseListener {

    private JFrame window;
    private HouseModel model;

    public HouseView(HouseModel model){
        
        window = new JFrame("SmartHome");// creating instance of JFrame
      //  window.getContentPane().add(this);
        
        window.setSize(800, 600);
       
        window.addMouseListener( (MouseListener) this);
        this.model = model;

        window.add(this);
        window.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Object object : model.getObjects()) {
            object.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        model.selectHuman(e.getX()+6, e.getY()-20);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        model.selectHuman(e.getX()+6, e.getY()-20);
        repaint();
    }
    @Override
    public void mouseEntered(MouseEvent arg0) {
    }
    @Override
    public void mouseExited(MouseEvent arg0) {
    }
}