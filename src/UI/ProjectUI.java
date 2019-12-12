package UI;

import core.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ProjectUI {
    private int centerIndex, projectIndex;
    private ArrayList<ResearchCenter> researchCenters;
    private JFrame frame;
    private JDialog createTaskDialog, listerTaskDialog;
    private ButtonListener buttonListener;
    private ListListener listListener;
    private JButton createTaskButton, removeTaskButton, listTaskButton, updateTaskButton, addDocenteButton, addBolseiroButton, changeRespButton, totalCostButton, endButton, backButton;
    private JButton backCreateTaskButton, trueCreateTaskButton , backListerDialogButton;
    private JButton trueRemoveTaskButton;
    private JTextField diaInicioCreateTaskTextField, mesInicioCreateTaskTextField, anoInicioCreateTaskTextField, mesFimCreateTaskTextField;
    private JComboBox typeCreateTaskBox;
    private JList<Object> peopleCreateTaskList, listerList;

    public ProjectUI(ArrayList<ResearchCenter> researchCenters, int centerIndex, int projectIndex) {
        this.researchCenters = researchCenters;
        this.centerIndex = centerIndex;
        this.projectIndex = projectIndex;
        drawer();
    }

    private void drawer() {
        frame = new JFrame();
        buttonListener = new ButtonListener();
        listListener = new ListListener();
        frame.setTitle("Project Manager");
        frame.setSize(650, 350);
        frame.setLayout(new GridLayout(4, 1));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel titlePanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        JLabel projectLabel = new JLabel("Projeto " + researchCenters.get(centerIndex).getProjects().get(projectIndex).getNome());
        projectLabel.setFont(new Font(projectLabel.getFont().getName(), Font.BOLD, 28));
        JLabel tarefasLabel = new JLabel("Tarefas:");
        JLabel pessoasLabel = new JLabel("Pessoas:");
        JLabel othersLabel = new JLabel("Outros:");

        //Tasks related Buttons
        createTaskButton = new JButton("Criar");
        createTaskButton.addActionListener(buttonListener);
        removeTaskButton = new JButton("Eliminar");
        removeTaskButton.addActionListener(buttonListener);
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
        backButton.addActionListener(buttonListener);

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

    private void listerDrawer(ArrayList<Task> tarefas, String title){
        listerTaskDialog = new JDialog();
        listerTaskDialog.setModal(true);
        listerTaskDialog.setSize(400, 400);
        listerTaskDialog.setLocationRelativeTo(null);
        listerTaskDialog.setLayout(new BorderLayout());
        listerTaskDialog.setTitle(title);

        JPanel topListerPanel = new JPanel();
        JPanel middleListerPanel = new JPanel();
        JPanel bottomListerPanel = new JPanel();

        backListerDialogButton = new JButton("Voltar");
        backListerDialogButton.addActionListener(buttonListener);
        JLabel titleListerLabel = new JLabel(title);
        titleListerLabel.setFont(new Font(titleListerLabel.getFont().getName(), Font.PLAIN, 20));
        DefaultListModel<Object> tasksListModel = new DefaultListModel<>();
        if (tarefas != null) {
            tasksListModel.addAll(tarefas);
        }
        listerList = new JList<>(tasksListModel);
        listerList.setFixedCellWidth(230);
        listerList.setFixedCellHeight(40);
        listerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listerList.addListSelectionListener(listListener);
        JScrollPane listerScroller = new JScrollPane(listerList);

        topListerPanel.add(titleListerLabel);
        middleListerPanel.add(listerScroller);
        bottomListerPanel.add(backListerDialogButton);

        listerTaskDialog.add(topListerPanel, BorderLayout.NORTH);
        listerTaskDialog.add(middleListerPanel, BorderLayout.CENTER);
        listerTaskDialog.add(bottomListerPanel, BorderLayout.SOUTH);
    }

    private void createTaskDrawer() {
        createTaskDialog = new JDialog();
        createTaskDialog.setModal(true);
        createTaskDialog.setSize(550, 400);
        createTaskDialog.setLocationRelativeTo(null);
        createTaskDialog.setLayout(new GridLayout(1, 2));
        createTaskDialog.setTitle("Criar Tarefa");

        /*Panels*/
        //Left Panels
        JPanel leftCreateTaskPanel = new JPanel(new GridLayout(4, 1));
        JPanel informationCreateTaskPanel = new JPanel(new BorderLayout());
        JPanel titleCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel fieldFillCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel inicioCreateTaskPanel = new JPanel(new GridLayout(2, 1));
        JPanel topInicioCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel bottomInicioCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel fimCreateTaskPanel = new JPanel(new GridLayout(2, 1));
        JPanel topFimCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel bottomFimCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel typeCreateTaskPanel = new JPanel(new FlowLayout());

        //Right Panels
        JPanel rightCreateTaskPanel = new JPanel(new BorderLayout());
        JPanel buttonerCreateTaskPanel = new JPanel();

        /*Labels*/
        //Left Labels
        JLabel titleCreateTaskLabel = new JLabel("Criar Tarefa");
        titleCreateTaskLabel.setFont(new Font(titleCreateTaskLabel.getFont().getName(), Font.BOLD, 30));
        JLabel fieldFillCreateTaskLabel = new JLabel("Preencha os seguintes campos:");
        fieldFillCreateTaskLabel.setFont(new Font(fieldFillCreateTaskLabel.getFont().getName(), Font.BOLD, 15));
        JLabel inicioCreateTaskLabel = new JLabel("Data de Início:");
        inicioCreateTaskLabel.setFont(new Font(inicioCreateTaskLabel.getFont().getName(), Font.BOLD,14));
        JLabel diaInicioCreateTaskLabel = new JLabel("Dia:");
        JLabel mesInicioCreateTaskLabel = new JLabel("Mês:");
        JLabel anoInicioCreateTaskLabel = new JLabel("Ano:");
        JLabel etcCreateTaskLabel = new JLabel("Data estimada de Conclusão:");
        etcCreateTaskLabel.setFont(new Font(etcCreateTaskLabel.getFont().getName(), Font.BOLD,14));
        JLabel mesFimCreateTaskLabel = new JLabel("Mêses depois da Data de Início:");
        JLabel typeCreateTaskLabel = new JLabel("Tipo:");

        //Right Labels
        JLabel choosePersonCreateTaskLabel = new JLabel("Escolha uma Pessoa:");

        /*Buttons*/
        backCreateTaskButton = new JButton("Voltar");
        backCreateTaskButton.addActionListener(buttonListener);
        trueCreateTaskButton = new JButton("Criar Tarefa");
        trueCreateTaskButton.addActionListener(buttonListener);
        trueCreateTaskButton.setEnabled(false);

        /*TextFields*/
        diaInicioCreateTaskTextField = new JTextField(2);
        mesInicioCreateTaskTextField = new JTextField(2);
        anoInicioCreateTaskTextField = new JTextField(2);

        mesFimCreateTaskTextField = new JTextField(2);

        /*Combo box*/
        typeCreateTaskBox = new JComboBox();
        typeCreateTaskBox.addItem("Documentação");
        typeCreateTaskBox.addItem("Design");
        typeCreateTaskBox.addItem("Desenvolvimento");

        /*List*/
        DefaultListModel<Object> peopleCreateTaskObjs = new DefaultListModel<>();
        peopleCreateTaskObjs.addAll(researchCenters.get(centerIndex).getProjects().get(projectIndex).getPessoas());
        peopleCreateTaskList = new JList<>(peopleCreateTaskObjs);
        peopleCreateTaskList.addListSelectionListener(listListener);
        peopleCreateTaskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /*Setting up left side of the menu*/
        //Title and information
        titleCreateTaskPanel.add(titleCreateTaskLabel);
        fieldFillCreateTaskPanel.add(fieldFillCreateTaskLabel);

        informationCreateTaskPanel.add(titleCreateTaskPanel,BorderLayout.NORTH);
        informationCreateTaskPanel.add(fieldFillCreateTaskPanel,BorderLayout.SOUTH);

        //adding inicioLabels and TextFields
        topInicioCreateTaskPanel.add(inicioCreateTaskLabel);
        bottomInicioCreateTaskPanel.add(diaInicioCreateTaskLabel);
        bottomInicioCreateTaskPanel.add(diaInicioCreateTaskTextField);
        bottomInicioCreateTaskPanel.add(mesInicioCreateTaskLabel);
        bottomInicioCreateTaskPanel.add(mesInicioCreateTaskTextField);
        bottomInicioCreateTaskPanel.add(anoInicioCreateTaskLabel);
        bottomInicioCreateTaskPanel.add(anoInicioCreateTaskTextField);

        inicioCreateTaskPanel.add(topInicioCreateTaskPanel);
        inicioCreateTaskPanel.add(bottomInicioCreateTaskPanel);

        //adding fim Labels and TextFields
        topFimCreateTaskPanel.add(etcCreateTaskLabel);
        bottomFimCreateTaskPanel.add(mesFimCreateTaskLabel);
        bottomFimCreateTaskPanel.add(mesFimCreateTaskTextField);

        fimCreateTaskPanel.add(topFimCreateTaskPanel);
        fimCreateTaskPanel.add(bottomFimCreateTaskPanel);

        //adding type task ComboBox
        typeCreateTaskPanel.add(typeCreateTaskLabel);
        typeCreateTaskPanel.add(typeCreateTaskBox);

        //adding the left subpanels to the main left panel
        leftCreateTaskPanel.add(informationCreateTaskPanel);
        leftCreateTaskPanel.add(inicioCreateTaskPanel);
        leftCreateTaskPanel.add(fimCreateTaskPanel);
        leftCreateTaskPanel.add(typeCreateTaskPanel);

        /*Setting up right side of the menu*/
        //adding buttons to the buttoner
        buttonerCreateTaskPanel.add(backCreateTaskButton);
        buttonerCreateTaskPanel.add(trueCreateTaskButton);

        //adding the right components to the main right panel
        rightCreateTaskPanel.add(choosePersonCreateTaskLabel, BorderLayout.NORTH);
        rightCreateTaskPanel.add(peopleCreateTaskList, BorderLayout.CENTER);
        rightCreateTaskPanel.add(buttonerCreateTaskPanel, BorderLayout.SOUTH);

        /*Adding left and right panels to the main JDialog*/
        createTaskDialog.add(leftCreateTaskPanel);
        createTaskDialog.add(rightCreateTaskPanel);

        createTaskDialog.setVisible(true);
    }

    private void taskCreater() {
        int diaInicio, mesInicio, anoInicio, mesFim, listIndex, boxIndex;
        Calendar inicio, etc;
        Pessoa responsavel;
        Task tarefa;
        try {
            listIndex = peopleCreateTaskList.getSelectedIndex();
            diaInicio = Integer.parseInt(diaInicioCreateTaskTextField.getText());
            mesInicio = Integer.parseInt(mesInicioCreateTaskTextField.getText());
            anoInicio = Integer.parseInt(anoInicioCreateTaskTextField.getText());
            mesFim = Integer.parseInt(mesFimCreateTaskTextField.getText());

            if ((1 <= diaInicio && diaInicio <= 31) && (1 <= mesInicio && mesInicio <= 12) && (anoInicio > 0) && (mesFim > 0)) {
                inicio = new GregorianCalendar();
                inicio.set(Calendar.DAY_OF_MONTH, diaInicio);
                inicio.set(Calendar.MONTH, mesInicio - 1);
                inicio.set(Calendar.YEAR, anoInicio);

                etc = new GregorianCalendar();
                etc.set(Calendar.DAY_OF_MONTH, diaInicio);
                etc.set(Calendar.MONTH, (mesInicio - 1) + mesFim);
                etc.set(Calendar.YEAR, anoInicio);

                boxIndex = typeCreateTaskBox.getSelectedIndex();
                if (boxIndex == 0) {
                    tarefa = new Documentation(inicio, etc);
                } else if (boxIndex == 1) {
                    tarefa = new Design(inicio, etc);
                } else {
                    tarefa = new Development(inicio, etc);
                }

                responsavel = (Pessoa) peopleCreateTaskList.getSelectedValue();
                if (researchCenters.get(centerIndex).getProjects().get(projectIndex).assignResp(responsavel, tarefa)) {
                    tarefa.setResponsavel(responsavel);
                    researchCenters.get(centerIndex).getProjects().get(projectIndex).addTask(tarefa);
                    JOptionPane.showMessageDialog(null, "Tarefa Criada Com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Verifique se:\n  As Datas são Compatíveis\n  A Pessoa não está sobrecarregada", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            createTaskDialog.setVisible(false);
            createTaskDialog.dispose();
            JOptionPane.showMessageDialog(null, "Introduza Valores válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeTaskDialogDrawer(){
        listerDrawer(researchCenters.get(centerIndex).getProjects().get(projectIndex).getTasks(),"Remover Tarefas");
        JPanel bottomPanel = new JPanel();
        trueRemoveTaskButton = new JButton("Eliminar Tarefa");
        trueRemoveTaskButton.addActionListener(buttonListener);
        bottomPanel.add(backListerDialogButton);
        bottomPanel.add(trueRemoveTaskButton);
        listerTaskDialog.add(bottomPanel, BorderLayout.SOUTH);
        listerTaskDialog.setVisible(true);
    }

    private void taskRemover(){
        Task tarefa = (Task) listerList.getSelectedValue();
        listerTaskDialog.setVisible(false);
        listerTaskDialog.dispose();
        if(researchCenters.get(centerIndex).getProjects().get(projectIndex).removeTask(tarefa)){
            JOptionPane.showMessageDialog(null, "Tarefa Removida Com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Erro a remover Tarefa", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                frame.setVisible(false);
                frame.dispose();
                new CenterUI(researchCenters, centerIndex);
            } else if (e.getSource() == createTaskButton) {
                createTaskDrawer();
            } else if (e.getSource() == trueCreateTaskButton) {
                createTaskDialog.setVisible(false);
                createTaskDialog.dispose();
                taskCreater();
            } else if (e.getSource() == backCreateTaskButton) {
                createTaskDialog.setVisible(false);
                createTaskDialog.dispose();
            } else if (e.getSource() == removeTaskButton){
                removeTaskDialogDrawer();
            } else if (e.getSource() ==  trueRemoveTaskButton){
                taskRemover();
            } else if(e.getSource() == backListerDialogButton){
                listerTaskDialog.setVisible(false);
                listerTaskDialog.dispose();
            }
        }
    }

    private class ListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == peopleCreateTaskList) {
                if (peopleCreateTaskList.getSelectedIndex() == -1) {
                    trueCreateTaskButton.setEnabled(false);
                } else {
                    trueCreateTaskButton.setEnabled(true);
                }
            } else if (e.getSource() == listerList){
                if (listerList.getSelectedIndex() == -1) {
                    if(trueRemoveTaskButton != null){
                        trueRemoveTaskButton.setEnabled(false);
                    }
                } else {
                    if(trueRemoveTaskButton != null) {
                        trueRemoveTaskButton.setEnabled(true);
                    }
                }
            }
        }
    }
}
