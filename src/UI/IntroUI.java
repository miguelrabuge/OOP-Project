package UI;

import core.Booter;
import core.ResearchCenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 * Represents the Intro UI where the user chooses the center
 */
public class IntroUI extends JFrame {
    private ArrayList<ResearchCenter> researchCenters;
    private JFrame frame;
    private JButton exitButton, chooseButton, createCenterButton;
    private JComboBox<Object> centerList;
    private String pathName;
    private JanelaListener windowListener;

    /**
     * Creates a IntroUI with the reserchCenters data base and the saving path for the file.obj
     *
     * @param researchCenters ArrayList Data base from the program
     * @param pathName        saving path for file.obj
     */
    public IntroUI(ArrayList<ResearchCenter> researchCenters, String pathName) {
        this.researchCenters = researchCenters;
        this.pathName = pathName;
        drawer();
    }

    /**
     * Draws a IntroIU
     */
    private void drawer() {
        frame = new JFrame();

        /*Frame Settings*/
        frame.setTitle("Project Manager");
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        /*Listeners*/
        ButtonListener listener = new ButtonListener();
        windowListener = new JanelaListener();
        frame.addWindowListener(windowListener);

        /*Panels*/
        JPanel bottomPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel topPanel = new JPanel();

        /*Buttons*/
        exitButton = new JButton("Exit");
        exitButton.addActionListener(listener);
        createCenterButton = new JButton("Adicionar Centro");
        createCenterButton.addActionListener(listener);
        chooseButton = new JButton("Escolher");
        chooseButton.addActionListener(listener);

        /*ComboBoxes*/
        centerList = new JComboBox<>();
        JLabel subtitle = new JLabel("Escolha um centro:");
        subtitle.setFont(new Font(subtitle.getFont().getName(), Font.BOLD, 22));
        //Adding options to the comboBox
        centerList.addItem("---------------------------------------------");
        for (ResearchCenter r : researchCenters) {
            centerList.addItem(r);
        }
        /*Adding Components to Panels*/
        topPanel.add(subtitle);

        middlePanel.add(centerList);
        bottomPanel.add(chooseButton);
        bottomPanel.add(createCenterButton);

        bottomPanel.add(exitButton);

        /*Adding Panels to the main Frame*/
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * Represents a ButtonListener that implements the ActionListener class
     */
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == chooseButton) {    // Botão de Selecionar o Centro
                if (centerList.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor escolha um centro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    frame.setVisible(false);
                    frame.dispose();
                    new CenterUI(researchCenters, centerList.getSelectedIndex() - 1, pathName);
                }
            } else if (e.getSource() == exitButton) { //Botão de Terminar o Programa
                if (JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION) == 0) {
                    Booter booter = new Booter();
                    if (booter.saveObjectFile(pathName, researchCenters)) {
                        System.out.println("Ficheiro Objeto guardado com Sucesso");
                    } else {
                        System.out.println("Erro ao Guardar Ficheiro Objeto");
                    }
                    frame.dispose();
                    System.exit(0);
                }
            } else if (e.getSource() == createCenterButton) { //Botão para criar um novo Centro
                String name = JOptionPane.showInputDialog(null, "Indique o nome do centro de investigação:", "Adicionar novo Centro", JOptionPane.QUESTION_MESSAGE);
                if (name != null) {
                    ResearchCenter center = new ResearchCenter(name);
                    researchCenters.add(center);
                    centerList.addItem(center);
                }
            }
        }
    }

    /**
     * Represents a WindowListener
     */
    private class JanelaListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        /**
         * Saves the current state of the program if the frame is closed
         *
         * @param e WindowEvent Window Closing
         */
        @Override
        public void windowClosing(WindowEvent e) {
            Booter booter = new Booter();
            if (booter.saveObjectFile(pathName, researchCenters)) {
                System.out.println("Ficheiro Objeto guardado com Sucesso");
            } else {
                System.out.println("Erro ao Guardar Ficheiro Objeto");
            }
            frame.dispose();
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}
