package UI;

import core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CenterUI {
    private int index;
    private ArrayList<ResearchCenter> researchCenters;
    private JFrame frame;
    private JLabel welcomeLabel, docentesLabel, bolseirosLabel, projetosLabel;
    private JScrollPane docentesScroller, bolseirosScroller, projetosScroller;
    private JButton backButton, createProjectButton, listConcludedButton, listNotConcludedButton, addPersonButton;
    private JPanel topPanel, centerPanel, leftPanel, rightPanel, bottomPanel;
    private ButtonListener listener;
    private JList docentesList, bolseirosList, projetosList;

    public CenterUI(ArrayList<ResearchCenter> researchCenters, int index){
        this.researchCenters = researchCenters;
        this.index = index;
        drawer();
    }

    private void drawer(){
        frame = new JFrame();
        listener = new ButtonListener();

        frame.setTitle("Project Manager");
        frame.setSize(800,600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topPanel = new JPanel();
        centerPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();

        createProjectButton = new JButton("Criar Projeto");
        createProjectButton.addActionListener(listener);
        listConcludedButton = new JButton("Listar Projetos Concluídos");
        listConcludedButton.addActionListener(listener);
        listNotConcludedButton = new JButton("Listar Projetos Não Concluídos");
        listNotConcludedButton.addActionListener(listener);
        addPersonButton = new JButton("Adicionar Pessoa");
        addPersonButton.addActionListener(listener);
        backButton = new JButton("Voltar");
        backButton.addActionListener(listener);

        welcomeLabel = new JLabel("Bem-Vindo ao "+ researchCenters.get(index).getName());
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        docentesLabel = new JLabel("Docentes:",JLabel.CENTER);
        bolseirosLabel = new JLabel("Bolseiros:",JLabel.CENTER);
        projetosLabel = new JLabel("Projetos:",JLabel.CENTER);

        DefaultListModel docentesListObjs = new DefaultListModel();
        DefaultListModel bolseirosListObjs = new DefaultListModel();
        DefaultListModel projetosListObjs = new DefaultListModel();

        ArrayList<Pessoa> pessoas = researchCenters.get(index).getPessoas();
        if (pessoas != null){
            for (Pessoa p : pessoas) {
                if (p.getCusto() == 0){
                    docentesListObjs.addElement(p);
                } else {
                    bolseirosListObjs.addElement(p);
                }
            }
        }

        ArrayList<Project> projetos = researchCenters.get(index).getProjects();
        if (projetos != null){
            for (Project p : projetos) {
                projetosListObjs.addElement(p);
            }
        }

        docentesList = new JList(docentesListObjs);
        docentesList.setFixedCellHeight(20);
        docentesList.setFixedCellWidth(200);
        bolseirosList = new JList(bolseirosListObjs);
        bolseirosList.setFixedCellHeight(20);
        bolseirosList.setFixedCellWidth(200);
        projetosList = new JList(projetosListObjs);
        projetosList.setFixedCellHeight(20);
        projetosList.setFixedCellWidth(200);

        docentesScroller = new JScrollPane(docentesList);
        bolseirosScroller = new JScrollPane(bolseirosList);
        projetosScroller = new JScrollPane(projetosList);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(welcomeLabel,BorderLayout.CENTER);
        topPanel.add(backButton,BorderLayout.EAST);

        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(docentesLabel,BorderLayout.NORTH);
        leftPanel.add(docentesScroller,BorderLayout.CENTER);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(bolseirosLabel,BorderLayout.NORTH);
        centerPanel.add(bolseirosScroller,BorderLayout.CENTER);

        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(projetosLabel,BorderLayout.NORTH);
        rightPanel.add(projetosScroller,BorderLayout.CENTER);

        bottomPanel.add(createProjectButton);
        bottomPanel.add(listConcludedButton);
        bottomPanel.add(listNotConcludedButton);
        bottomPanel.add(addPersonButton);

        frame.add(topPanel,BorderLayout.NORTH);
        frame.add(leftPanel,BorderLayout.WEST);
        frame.add(centerPanel,BorderLayout.CENTER);
        frame.add(rightPanel,BorderLayout.EAST);
        frame.add(bottomPanel,BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton){
                if (JOptionPane.showConfirmDialog(null,"Tem a certeza?", "Voltar",JOptionPane.YES_NO_OPTION) == 0){
                    frame.setVisible(false);
                    frame.dispose();
                    new IntroUI(researchCenters);
                }
            } else if (e.getSource() == addPersonButton){
                frame.setVisible(false);
                frame.dispose();
                new AddPersonUI(researchCenters,index);
            }
        }
    }
}
