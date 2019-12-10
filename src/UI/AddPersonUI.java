package UI;

import core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPersonUI {
    private int index;
    private JFrame frame;
    private JPanel topPanel;
    private JLabel addPersonLabel;

    private ArrayList<ResearchCenter> researchCenters;
    public AddPersonUI(ArrayList<ResearchCenter> researchCenters, int index){
        this.researchCenters = researchCenters;
        this.index = index;
        drawer();
    }

    private void drawer(){
        frame = new JFrame();
        frame.setTitle("Project Manager");
        frame.setSize(400,400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
