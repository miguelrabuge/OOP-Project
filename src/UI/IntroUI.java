package UI;

import core.ResearchCenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IntroUI extends JFrame {
    private ArrayList<ResearchCenter> researchCenters;
    private JFrame frame;
    private JButton exitButton, chooseButton, createCenterButton;
    private JComboBox<Object> centerList;

    public IntroUI(ArrayList<ResearchCenter> researchCenters) {
        this.researchCenters = researchCenters;
        Drawer();
    }

    private void Drawer() {
        frame = new JFrame();
        ButtonListener listener = new ButtonListener();
        JPanel bottomPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel topPanel = new JPanel();
        exitButton = new JButton("Exit");
        exitButton.addActionListener(listener);
        createCenterButton = new JButton("Adicionar Centro");
        createCenterButton.addActionListener(listener);
        chooseButton = new JButton("Escolher");
        chooseButton.addActionListener(listener);

        centerList = new JComboBox<>();
        JLabel subtitle = new JLabel("Escolha um centro:");
        subtitle.setFont(new Font(subtitle.getFont().getName(), Font.BOLD, 22));
        centerList.addItem("---------------------------------------------");
        for (ResearchCenter r : researchCenters) {
            centerList.addItem(r);
        }
        topPanel.add(subtitle);

        middlePanel.add(centerList);
        bottomPanel.add(chooseButton);
        bottomPanel.add(createCenterButton);

        bottomPanel.add(exitButton);
        frame.setTitle("Project Manager");
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == chooseButton) {
                if (centerList.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor escolha um centro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    frame.setVisible(false);
                    frame.dispose();
                    new CenterUI(researchCenters, centerList.getSelectedIndex() - 1);
                }
            } else if (e.getSource() == exitButton) {
                if (JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION) == 0) {
                    //TODO: save configs
                    System.exit(0);
                }
            } else if (e.getSource() == createCenterButton) {
                String name = JOptionPane.showInputDialog(null, "Indique o nome do centro de investigação:", "Adicionar novo Centro", JOptionPane.QUESTION_MESSAGE);
                if (name != null) {
                    ResearchCenter center = new ResearchCenter(name);
                    researchCenters.add(center);
                    centerList.addItem(center);
                }
            }
        }
    }
}
