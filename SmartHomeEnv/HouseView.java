package SmartHomeEnv;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.FlowLayout;
import java.util.List;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class HouseView extends JPanel implements MouseListener, ActionListener {

    private JFrame window;
    private HouseModel model;
    JCheckBox temp;
    JCheckBox smoke;

    public HouseView(HouseModel model){
        
        window = new JFrame("SmartHome");// creating instance of JFrame
      //  window.getContentPane().add(this);
        
        window.setSize(600, 400);
       
        window.addMouseListener( (MouseListener) this);
        this.model = model;


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(200,50));

        JComboBox roomList = new JComboBox(model.getRoomNames());
        roomList.setMaximumSize(new Dimension(150,30));
        temp = new JCheckBox("High Temp");
        smoke = new JCheckBox("Smoke");
        
        roomList.addActionListener(this);
        temp.addActionListener(this);
        smoke.addActionListener(this);
        
        panel.add(roomList);
        panel.add(temp);
        panel.add(smoke);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(360,400));
        container.add(this);
        container.add(panel);

        window.add(container);
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

    public void actionPerformed(ActionEvent e) {
        String source = e.getSource().getClass().getSimpleName();
        if(source.equals("JComboBox")){
            JComboBox cb = (JComboBox) e.getSource();
            String roomName = (String) cb.getSelectedItem();
            Room room = model.selectRoom(roomName);
            if(room == null) return;
            temp.setSelected(room.getTemperature());
            smoke.setSelected(room.getSmoke());
            
        }
        else if(source.equals("JCheckBox")){
            JCheckBox cb = (JCheckBox) e.getSource();
            String fieldName = cb.getText();

            if(fieldName.equals("High Temp"))
                model.setHighTemp(cb.isSelected());
            if(fieldName.equals("Smoke"))
                model.setSmoke(cb.isSelected());
        }
    }
}