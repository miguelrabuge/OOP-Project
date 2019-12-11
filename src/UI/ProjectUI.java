package UI;

import core.*;
import core.ResearchCenter;

import javax.print.attribute.standard.PresentationDirection;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProjectUI {
    int centerIndex, projectIndex;
    private ArrayList<ResearchCenter> researchCenters;
    private JFrame frame;
    private JButton createTaskButton, removeTaskButton, listTaskButton, updateTaskButton, addDocenteButton, addBolseiroButton, changeRespButton, totalCostButton, endButton, backButton;
    private ButtonListener listener;

    public ProjectUI(ArrayList<ResearchCenter> researchCenters, int centerIndex, int projectIndex) {
        this.researchCenters = researchCenters;
        this.centerIndex = centerIndex;
        this.projectIndex = projectIndex;
        drawer();
    }

    private void drawer() {
        frame = new JFrame();
        listener = new ButtonListener();

        frame.setTitle("Project Manager");
        frame.setSize(650, 350);
        frame.setLayout(new GridLayout(4,1));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel titlePanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        JLabel projectLabel = new JLabel("Projeto " + researchCenters.get(centerIndex).getProjects().get(projectIndex).getNome());
        projectLabel.setFont(new Font(projectLabel.getFont().getName(), Font.BOLD,28));
        JLabel tarefasLabel = new JLabel("Tarefas:");
        JLabel pessoasLabel = new JLabel("Pessoas:");
        JLabel othersLabel = new JLabel("Outros:");

        //Tasks related Buttons
        createTaskButton = new JButton("Criar");
        removeTaskButton = new JButton("Eliminar");
        listTaskButton = new JButton("Listar");
        updateTaskButton = new JButton("Atualizar");
        //People related Buttons
        addDocenteButton = new JButton("Associar Docente ao Projeto");
        addBolseiroButton = new JButton("Associar Bolseiro ao Projeto");
        changeRespButton = new JButton("Atribuir");
        //Other Buttons
        totalCostButton = new JButton("Custo Total");
        endButton = new JButton("TERMINAR!");
        backButton = new JButton("Voltar");
        backButton.addActionListener(listener);

        titlePanel.add(projectLabel);

        topPanel.add(tarefasLabel);
        topPanel.add(createTaskButton);
        topPanel.add(removeTaskButton);
        topPanel.add(listTaskButton);
        topPanel.add(updateTaskButton);
        topPanel.add(changeRespButton);

        middlePanel.add(pessoasLabel);
        middlePanel.add(addDocenteButton);
        middlePanel.add(addBolseiroButton);


        bottomPanel.add(othersLabel);
        bottomPanel.add(totalCostButton);
        bottomPanel.add(endButton);
        bottomPanel.add(backButton);

        frame.add(titlePanel);
        frame.add(topPanel);
        frame.add(middlePanel);
        frame.add(bottomPanel);

        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == backButton){
                frame.setVisible(false);
                frame.dispose();
                new CenterUI(researchCenters,centerIndex);
            } else if (e.getSource() == createTaskButton){
                
            }
        }
    }
}
