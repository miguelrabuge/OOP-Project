package UI;

import core.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CenterUI {
    private int index;
    private ArrayList<ResearchCenter> researchCenters;
    private JFrame frame;
    private JDialog listerDialog, projectCreaterDialog;
    private JLabel welcomeLabel, docentesLabel, bolseirosLabel, projetosLabel;
    private JScrollPane docentesScroller, bolseirosScroller, projetosScroller;
    private JButton backListerFrameButton, backButton, createProjectButton, listConcludedButton, listNotConcludedButton, addPersonButton, openProjectButton, trueCreateProjectButton, backCreateProjectButton;
    private JPanel topPanel, centerPanel, leftPanel, rightPanel, bottomPanel;
    private ButtonListener buttonListener;
    private ListListener listListener;
    private JList<Object> docentesList, bolseirosList, docentesCreateProjectList, projetosList;
    private DefaultListModel<Object> docentesListObjs, bolseirosListObjs, projetosListObjs;
    private JTextField nameTextField, acronimoTextField, diaInicioTextField, diaFimTextField, mesInicioTextField, mesFimTextField, anoInicioTextField, anoFimTextField;

    public CenterUI(ArrayList<ResearchCenter> researchCenters, int index) {
        this.researchCenters = researchCenters;
        this.index = index;
        drawer();
    }

    private void drawer() {
        frame = new JFrame();
        buttonListener = new ButtonListener();
        listListener = new ListListener();

        frame.setTitle("Project Manager");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topPanel = new JPanel();
        centerPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();

        createProjectButton = new JButton("Criar Projeto");
        createProjectButton.addActionListener(buttonListener);
        listConcludedButton = new JButton("Listar Projetos Concluídos");
        listConcludedButton.addActionListener(buttonListener);
        listNotConcludedButton = new JButton("Listar Projetos Não Concluídos");
        listNotConcludedButton.addActionListener(buttonListener);
        addPersonButton = new JButton("Adicionar Pessoa");
        addPersonButton.addActionListener(buttonListener);
        openProjectButton = new JButton("Abrir Projeto");
        openProjectButton.addActionListener(buttonListener);
        openProjectButton.setEnabled(false);
        backButton = new JButton("Voltar");
        backButton.addActionListener(buttonListener);

        welcomeLabel = new JLabel(" Bem-Vindo ao " + researchCenters.get(index).getName());
        welcomeLabel.setFont(new Font(welcomeLabel.getFont().getName(), Font.PLAIN, 30));
        docentesLabel = new JLabel("Docentes:", JLabel.CENTER);
        bolseirosLabel = new JLabel("Bolseiros:", JLabel.CENTER);
        projetosLabel = new JLabel("Projetos:", JLabel.CENTER);

        docentesListObjs = new DefaultListModel<Object>();
        bolseirosListObjs = new DefaultListModel<Object>();
        projetosListObjs = new DefaultListModel<Object>();
        //TODO: change fors to .addall()
        for (Pessoa pessoa : researchCenters.get(index).getDocentes()) {
            docentesListObjs.addElement(pessoa);
        }

        for (Pessoa pessoa : researchCenters.get(index).getBolseiros()) {
            bolseirosListObjs.addElement(pessoa);
        }

        for (Project projeto : researchCenters.get(index).getProjects()) {
            projetosListObjs.addElement(projeto);
        }

        docentesList = new JList<Object>(docentesListObjs);
        docentesList.setEnabled(false);
        docentesList.setFixedCellHeight(20);
        docentesList.setFixedCellWidth(200);
        bolseirosList = new JList<Object>(bolseirosListObjs);
        bolseirosList.setEnabled(false);
        bolseirosList.setFixedCellHeight(20);
        bolseirosList.setFixedCellWidth(200);
        projetosList = new JList<Object>(projetosListObjs);
        projetosList.setFixedCellHeight(20);
        projetosList.setFixedCellWidth(200);
        projetosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        projetosList.addListSelectionListener(listListener);

        docentesScroller = new JScrollPane(docentesList);
        bolseirosScroller = new JScrollPane(bolseirosList);
        projetosScroller = new JScrollPane(projetosList);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(welcomeLabel, BorderLayout.CENTER);
        topPanel.add(backButton, BorderLayout.EAST);

        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(docentesLabel, BorderLayout.NORTH);
        leftPanel.add(docentesScroller, BorderLayout.CENTER);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(bolseirosLabel, BorderLayout.NORTH);
        centerPanel.add(bolseirosScroller, BorderLayout.CENTER);

        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(projetosLabel, BorderLayout.NORTH);
        rightPanel.add(projetosScroller, BorderLayout.CENTER);

        bottomPanel.add(createProjectButton);
        bottomPanel.add(listConcludedButton);
        bottomPanel.add(listNotConcludedButton);
        bottomPanel.add(addPersonButton);
        bottomPanel.add(openProjectButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void lister(ArrayList<Project> projects, String title) {
        listerDialog = new JDialog();
        listerDialog.setModal(true);
        listerDialog.setSize(400, 400);
        listerDialog.setLocationRelativeTo(null);
        listerDialog.setLayout(new BorderLayout());
        listerDialog.setTitle(title);

        JPanel topListerPanel = new JPanel();
        JPanel middleListerPanel = new JPanel();
        JPanel bottomListerPanel = new JPanel();

        backListerFrameButton = new JButton("Voltar");
        backListerFrameButton.addActionListener(buttonListener);
        JLabel titleListerLabel = new JLabel(title);
        titleListerLabel.setFont(new Font(titleListerLabel.getFont().getName(), Font.PLAIN, 20));
        DefaultListModel<Object> projetosListModel = new DefaultListModel<Object>();
        if (projects != null) {
            for (Project p : projects) {
                projetosListModel.addElement(p);
            }
        }
        JList<Object> listerList = new JList<Object>(projetosListModel);
        listerList.setFixedCellWidth(170);
        listerList.setFixedCellHeight(30);
        JScrollPane listerScroller = new JScrollPane(listerList);

        topListerPanel.add(titleListerLabel);
        middleListerPanel.add(listerScroller);
        bottomListerPanel.add(backListerFrameButton);

        listerDialog.add(topListerPanel, BorderLayout.NORTH);
        listerDialog.add(middleListerPanel, BorderLayout.CENTER);
        listerDialog.add(bottomListerPanel, BorderLayout.SOUTH);

        listerDialog.setVisible(true);
    }

    private void projectCreateDrawer() {
        projectCreaterDialog = new JDialog();
        projectCreaterDialog.setModal(true);
        projectCreaterDialog.setSize(700, 500);
        projectCreaterDialog.setLocationRelativeTo(null);
        projectCreaterDialog.setLayout(new GridLayout(1, 2));
        projectCreaterDialog.setTitle("Criar Projeto");

        JLabel subtitleProjectCreaterLabel = new JLabel("    Criar Projeto");
        subtitleProjectCreaterLabel.setFont(new Font(subtitleProjectCreaterLabel.getFont().getName(), Font.BOLD, 30));
        JLabel instructionLabel = new JLabel("  Preencha os seguintes campos:");

        JLabel nameLabel = new JLabel("Nome:      ");
        JLabel acronimoLabel = new JLabel("Acrónimo:");
        nameTextField = new JTextField(20);
        acronimoTextField = new JTextField(20);

        JLabel diaInicioLabel = new JLabel("Dia:");
        JLabel diaFimLabel = new JLabel("Dia:");
        JLabel mesInicioLabel = new JLabel("Mês:");
        JLabel mesFimLabel = new JLabel("Mês:");
        JLabel anoInicioLabel = new JLabel("Ano:");
        JLabel anoFimLabel = new JLabel("Ano:");

        JLabel inicioLabel = new JLabel("Data de Início:");
        inicioLabel.setFont(new Font(inicioLabel.getFont().getName(), Font.BOLD, 16));
        diaInicioTextField = new JTextField(2);
        mesInicioTextField = new JTextField(2);
        anoInicioTextField = new JTextField(2);

        JLabel fimLabel = new JLabel("Data Estimada de Conclusão:");
        fimLabel.setFont(new Font(fimLabel.getFont().getName(), Font.BOLD, 16));
        diaFimTextField = new JTextField(2);
        mesFimTextField = new JTextField(2);
        anoFimTextField = new JTextField(2);

        JLabel principalLabel = new JLabel("Escolha um Investigador Principal:");
        DefaultListModel<Object> docentesCreateProjectDefaultListModel = new DefaultListModel<Object>();
        for (Pessoa pessoa : researchCenters.get(index).getDocentes()) {
            docentesCreateProjectDefaultListModel.addElement(pessoa);
        }
        docentesCreateProjectList = new JList<Object>(docentesCreateProjectDefaultListModel);
        docentesCreateProjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        docentesCreateProjectList.addListSelectionListener(listListener);
        JScrollPane docentesCreateProjectScroller = new JScrollPane(docentesCreateProjectList);
        trueCreateProjectButton = new JButton("Criar Projeto");
        trueCreateProjectButton.addActionListener(buttonListener);
        trueCreateProjectButton.setEnabled(false);
        backCreateProjectButton = new JButton("Voltar");
        backCreateProjectButton.addActionListener(buttonListener);

        JPanel leftProjectCreaterPanel = new JPanel(new GridLayout(2, 1));

        JPanel topLeftProjectCreaterPanel = new JPanel(new GridLayout(4, 1));
        JPanel nameInputPanel = new JPanel(new FlowLayout());
        JPanel acronimoInputPanel = new JPanel(new FlowLayout());

        JPanel bottomLeftProjectCreaterPanel = new JPanel(new GridLayout(4, 1));
        JPanel inicioInputPanel = new JPanel(new FlowLayout());
        JPanel fimInputPanel = new JPanel(new FlowLayout());
        JPanel dataInicioPanel = new JPanel(new FlowLayout());
        JPanel dataFimPanel = new JPanel(new FlowLayout());

        JPanel rightProjectCreaterPanel = new JPanel(new BorderLayout());
        JPanel buttonerPanel = new JPanel(new FlowLayout());

        //LEFT PANELS
        //topLeftPanel
        nameInputPanel.add(nameLabel);
        nameInputPanel.add(nameTextField);

        acronimoInputPanel.add(acronimoLabel);
        acronimoInputPanel.add(acronimoTextField);

        topLeftProjectCreaterPanel.add(subtitleProjectCreaterLabel);
        topLeftProjectCreaterPanel.add(instructionLabel);
        topLeftProjectCreaterPanel.add(nameInputPanel);
        topLeftProjectCreaterPanel.add(acronimoInputPanel);

        //bottomLeftPanel
        dataInicioPanel.add(inicioLabel);

        inicioInputPanel.add(diaInicioLabel);
        inicioInputPanel.add(diaInicioTextField);
        inicioInputPanel.add(mesInicioLabel);
        inicioInputPanel.add(mesInicioTextField);
        inicioInputPanel.add(anoInicioLabel);
        inicioInputPanel.add(anoInicioTextField);

        dataFimPanel.add(fimLabel);

        fimInputPanel.add(diaFimLabel);
        fimInputPanel.add(diaFimTextField);
        fimInputPanel.add(mesFimLabel);
        fimInputPanel.add(mesFimTextField);
        fimInputPanel.add(anoFimLabel);
        fimInputPanel.add(anoFimTextField);

        bottomLeftProjectCreaterPanel.add(dataInicioPanel);
        bottomLeftProjectCreaterPanel.add(inicioInputPanel);
        bottomLeftProjectCreaterPanel.add(dataFimPanel);
        bottomLeftProjectCreaterPanel.add(fimInputPanel);

        //adding "lefts" panels to the leftPanel
        leftProjectCreaterPanel.add(topLeftProjectCreaterPanel);
        leftProjectCreaterPanel.add(bottomLeftProjectCreaterPanel);

        //RIGHT PANELS
        buttonerPanel.add(backCreateProjectButton);
        buttonerPanel.add(trueCreateProjectButton);

        rightProjectCreaterPanel.add(principalLabel, BorderLayout.NORTH);
        rightProjectCreaterPanel.add(docentesCreateProjectScroller, BorderLayout.CENTER);
        rightProjectCreaterPanel.add(buttonerPanel, BorderLayout.SOUTH);
        //adding main panels to the JDialog
        projectCreaterDialog.add(leftProjectCreaterPanel);
        projectCreaterDialog.add(rightProjectCreaterPanel);

        projectCreaterDialog.setVisible(true);
    }

    private void projectCreater() {
        String name = nameTextField.getText();
        String acronimo = acronimoTextField.getText();
        int diaInicio, mesInicio, anoInicio;
        int diaFim, mesFim, anoFim;
        if (!(name.isBlank() || acronimo.isBlank())) {
            try {
                diaInicio = Integer.parseInt(diaInicioTextField.getText());
                mesInicio = Integer.parseInt(mesInicioTextField.getText());
                anoInicio = Integer.parseInt(anoInicioTextField.getText());

                diaFim = Integer.parseInt(diaFimTextField.getText());
                mesFim = Integer.parseInt(mesFimTextField.getText());
                anoFim = Integer.parseInt(anoFimTextField.getText());

                Calendar inicio = new GregorianCalendar();
                inicio.set(Calendar.DAY_OF_MONTH, diaInicio);
                inicio.set(Calendar.MONTH, mesInicio - 1);
                inicio.set(Calendar.YEAR,anoInicio);

                Calendar etc = new GregorianCalendar();
                etc.set(Calendar.DAY_OF_MONTH, diaFim);
                etc.set(Calendar.MONTH, mesFim - 1);
                etc.set(Calendar.YEAR,anoFim);

                if (inicio.before(etc)) {
                    if ((1 <= mesInicio && mesInicio <= 12) && (1 <= mesFim && mesFim <= 12) &&
                            (1 <= diaInicio && diaInicio <= 31) && (1 <= diaFim && diaFim <= 31) &&
                            (anoInicio > 0) && (anoFim > 0)) {

                        Pessoa docente = researchCenters.get(index).getDocentes().get(docentesCreateProjectList.getSelectedIndex());
                        Project projeto = new Project(name, acronimo, inicio, etc, (Docente) docente);
                        researchCenters.get(index).addProject(projeto);
                        projetosListObjs.addElement(projeto);
                    } else {
                        projectCreaterDialog.dispose();
                        JOptionPane.showMessageDialog(null, "Introduza Valores Válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    projectCreaterDialog.dispose();
                    JOptionPane.showMessageDialog(null, "A data de início tem de ser anterior à data de fim!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException exp) {
                projectCreaterDialog.dispose();
                JOptionPane.showMessageDialog(null, "Introduza Valores Válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            projectCreaterDialog.dispose();
            JOptionPane.showMessageDialog(null, "Campos vazios!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void personAdder() {
        int diaInicio, mesInicio, anoInicio, diaFim, mesFim, anoFim;
        String name, email, mecanografico, investigationArea;

        JTextField firstTextField = new JTextField();
        JTextField secondTextField = new JTextField();
        JTextField diaInicioTextField = new JTextField();
        JTextField mesInicioTextField = new JTextField();
        JTextField anoInicioTextField = new JTextField();
        JTextField diaFimTextField = new JTextField();
        JTextField mesFimTextField = new JTextField();
        JTextField anoFimTextField = new JTextField();
        JComboBox<String> optionBox = new JComboBox<String>();


        String[] options = {"Docente", "Licenciado", "Mestre", "Doutorado"};
        String[] titles = {"Adicionar Docente", "Adicionar Licenciado", "Adicionar Mestre", "Adicionar Doutorado"};

        for (String s : options) {
            optionBox.addItem(s);
        }
        Object[] questions = new Object[]{"Nome:", firstTextField, "Email:", secondTextField, "Escolha uma opção:", optionBox};
        if (JOptionPane.showConfirmDialog(null, questions, "Adicionar Pessoa", JOptionPane.OK_CANCEL_OPTION) == 0) {
            name = firstTextField.getText();
            email = secondTextField.getText();
            firstTextField.setText("");
            secondTextField.setText("");
            if (!(name.isBlank() || email.isBlank())) {
                try {
                    if (optionBox.getSelectedIndex() == 0) {
                        questions = new Object[]{"Número Mecanográfico:", firstTextField, "Área de Investigação:", secondTextField};
                        if (JOptionPane.showConfirmDialog(null, questions, "Adicionar Docente", JOptionPane.OK_CANCEL_OPTION) == 0) {
                            mecanografico = firstTextField.getText();
                            investigationArea = secondTextField.getText();
                            if (!(mecanografico.isBlank() || investigationArea.isBlank())) {
                                Docente docente = new Docente(name, email, mecanografico, investigationArea);
                                researchCenters.get(index).addPessoa(docente);
                                docentesListObjs.addElement(docente);
                                docentesList = new JList<>(docentesListObjs);
                            } else {
                                JOptionPane.showMessageDialog(null, "Campos vazios!", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        questions = new Object[]{"Data de Inicio:", "Dia:", diaInicioTextField, "Mês:", mesInicioTextField, "Ano:", anoInicioTextField, "\n", "Data de Fim:", "Dia:", diaFimTextField, "Mês:", mesFimTextField, "Ano:", anoFimTextField, "\n"};
                        if (JOptionPane.showConfirmDialog(null, questions, titles[optionBox.getSelectedIndex()], JOptionPane.OK_CANCEL_OPTION) == 0) {
                            diaInicio = Integer.parseInt(diaInicioTextField.getText());
                            mesInicio = Integer.parseInt(mesInicioTextField.getText());
                            anoInicio = Integer.parseInt(anoInicioTextField.getText());

                            diaFim = Integer.parseInt(diaFimTextField.getText());
                            mesFim = Integer.parseInt(mesFimTextField.getText());
                            anoFim = Integer.parseInt(anoFimTextField.getText());

                            Calendar inicio = new GregorianCalendar();
                            inicio.set(Calendar.DAY_OF_MONTH, diaInicio);
                            inicio.set(Calendar.MONTH, mesInicio - 1);
                            inicio.set(Calendar.YEAR,anoInicio);

                            Calendar fim = new GregorianCalendar();
                            fim.set(Calendar.DAY_OF_MONTH, diaFim);
                            fim.set(Calendar.MONTH, mesFim - 1);
                            fim.set(Calendar.YEAR,anoFim);

                            if (inicio.before(fim)) {
                                if ((1 <= mesInicio && mesInicio <= 12) && (1 <= mesFim && mesFim <= 12) &&
                                        (1 <= diaInicio && diaInicio <= 31) && (1 <= diaFim && diaFim <= 31) &&
                                        (anoInicio > 0) && (anoFim > 0)) {

                                    Pessoa pessoa;
                                    switch (optionBox.getSelectedIndex()) {
                                        case 1:
                                            pessoa = new Licenciado(name, email, inicio, fim);
                                            break;
                                        case 2:
                                            pessoa = new Mestre(name, email, inicio, fim);
                                            break;
                                        case 3:
                                            pessoa = new Doutorado(name, email, inicio, fim);
                                            break;
                                        default:
                                            pessoa = null;
                                            System.out.println("Erro");
                                            break;
                                    }
                                    researchCenters.get(index).addPessoa(pessoa);
                                    bolseirosListObjs.addElement(pessoa);
                                    bolseirosList = new JList<>(bolseirosListObjs);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Introduza Valores Válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "A data de início tem de ser anterior à data de fim!", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } catch (NumberFormatException exp) {
                    JOptionPane.showMessageDialog(null, "Introduza valores válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Campos vazios!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                if (JOptionPane.showConfirmDialog(null, "Tem a certeza?", "Voltar", JOptionPane.YES_NO_OPTION) == 0) {
                    frame.setVisible(false);
                    frame.dispose();
                    new IntroUI(researchCenters);
                }
            } else if (e.getSource() == trueCreateProjectButton) {
                projectCreater();
                projectCreaterDialog.setVisible(false);
                projectCreaterDialog.dispose();
            } else if (e.getSource() == backCreateProjectButton) {
                projectCreaterDialog.dispose();
            } else if (e.getSource() == backListerFrameButton) {
                listerDialog.dispose();
            } else if (e.getSource() == createProjectButton) {
                projectCreateDrawer();
            } else if (e.getSource() == listConcludedButton) {
                lister(researchCenters.get(index).getFinishedProjects(), "Projetos Concluídos");
            } else if (e.getSource() == listNotConcludedButton) {
                lister(researchCenters.get(index).getUnfinishedProjects(), "Projetos Não Concluídos");
            } else if (e.getSource() == addPersonButton) {
                personAdder();
            } else if (e.getSource() == openProjectButton) {
                frame.setVisible(false);
                frame.dispose();
                new ProjectUI(researchCenters, index, projetosList.getSelectedIndex());
            }
        }
    }

    private class ListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == projetosList) {
                if (projetosList.getSelectedIndex() == -1) {
                    openProjectButton.setEnabled(false);
                } else {
                    openProjectButton.setEnabled(true);
                }
            } else if (e.getSource() == docentesCreateProjectList) {
                if (docentesCreateProjectList.getSelectedIndex() == -1) {
                    trueCreateProjectButton.setEnabled(false);
                } else {
                    trueCreateProjectButton.setEnabled(true);
                }
            }
        }
    }
}
