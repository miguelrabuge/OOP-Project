package UI;

import core.*;

import javax.swing.*;
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
    private JDialog listerDialog,projectCreaterDialog;
    private JLabel welcomeLabel, docentesLabel, bolseirosLabel, projetosLabel;
    private JScrollPane docentesScroller, bolseirosScroller, projetosScroller;
    private JButton backListerFrameButton, backButton, createProjectButton, listConcludedButton, listNotConcludedButton, addPersonButton;
    private JPanel topPanel, centerPanel, leftPanel, rightPanel, bottomPanel;
    private Listener listener;
    private JList<Pessoa> docentesList;
    private JList<Pessoa> bolseirosList;
    private JList<Project> projetosList;
    private DefaultListModel<Pessoa> docentesListObjs, bolseirosListObjs;
    private DefaultListModel<Project> projetosListObjs;

    public CenterUI(ArrayList<ResearchCenter> researchCenters, int index) {
        this.researchCenters = researchCenters;
        this.index = index;
        drawer();
    }

    private void drawer() {
        frame = new JFrame();
        listener = new Listener();

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
        createProjectButton.addActionListener(listener);
        listConcludedButton = new JButton("Listar Projetos Concluídos");
        listConcludedButton.addActionListener(listener);
        listNotConcludedButton = new JButton("Listar Projetos Não Concluídos");
        listNotConcludedButton.addActionListener(listener);
        addPersonButton = new JButton("Adicionar Pessoa");
        addPersonButton.addActionListener(listener);
        backButton = new JButton("Voltar");
        backButton.addActionListener(listener);

        welcomeLabel = new JLabel(" Bem-Vindo ao " + researchCenters.get(index).getName());
        welcomeLabel.setFont(new Font(welcomeLabel.getFont().getName(), Font.PLAIN, 30));
        docentesLabel = new JLabel("Docentes:", JLabel.CENTER);
        bolseirosLabel = new JLabel("Bolseiros:", JLabel.CENTER);
        projetosLabel = new JLabel("Projetos:", JLabel.CENTER);

        docentesListObjs = new DefaultListModel<Pessoa>();
        bolseirosListObjs = new DefaultListModel<Pessoa>();
        projetosListObjs = new DefaultListModel<Project>();

        ArrayList<Pessoa> pessoas = researchCenters.get(index).getPessoas();
        if (pessoas != null) {
            for (Pessoa p : pessoas) {
                if (p.getCusto() == 0) {
                    docentesListObjs.addElement(p);
                } else {
                    bolseirosListObjs.addElement(p);
                }
            }
        }

        ArrayList<Project> projetos = researchCenters.get(index).getProjects();
        if (projetos != null) {
            for (Project p : projetos) {
                projetosListObjs.addElement(p);
            }
        }
        docentesList = new JList<>(docentesListObjs);
        docentesList.setEnabled(false);
        docentesList.setFixedCellHeight(20);
        docentesList.setFixedCellWidth(200);
        bolseirosList = new JList<>(bolseirosListObjs);
        bolseirosList.setEnabled(false);
        bolseirosList.setFixedCellHeight(20);
        bolseirosList.setFixedCellWidth(200);
        projetosList = new JList<>(projetosListObjs);
        projetosList.setFixedCellHeight(20);
        projetosList.setFixedCellWidth(200);

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
        backListerFrameButton.addActionListener(listener);
        JLabel titleListerLabel = new JLabel(title);
        titleListerLabel.setFont(new Font(titleListerLabel.getFont().getName(), Font.PLAIN, 20));
        DefaultListModel<Project> projetosListModel = new DefaultListModel<Project>();
        if (projects != null) {
            for (Project p : projects) {
                projetosListModel.addElement(p);
            }
        }
        JList listerList = new JList(projetosListModel);
        JScrollPane listerScroller = new JScrollPane(listerList);

        topListerPanel.add(titleListerLabel);
        middleListerPanel.add(listerScroller);
        bottomListerPanel.add(backListerFrameButton);

        listerDialog.add(topListerPanel, BorderLayout.NORTH);
        listerDialog.add(middleListerPanel, BorderLayout.CENTER);
        listerDialog.add(bottomListerPanel, BorderLayout.SOUTH);

        listerDialog.setVisible(true);
    }

    private void projectCreater() {
        projectCreaterDialog = new JDialog();
        projectCreaterDialog.setModal(true);
        projectCreaterDialog.setSize(600, 400);
        projectCreaterDialog.setLocationRelativeTo(null);
        projectCreaterDialog.setLayout(new BorderLayout());
        projectCreaterDialog.setTitle("Criar Projeto");

        projectCreaterDialog.setVisible(true);
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
                                docentesList = new JList(docentesListObjs);
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

                            Calendar inicio = new GregorianCalendar(anoInicio, mesInicio, diaInicio);
                            Calendar fim = new GregorianCalendar(anoFim, mesFim, diaFim);

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
                                    bolseirosList = new JList(bolseirosListObjs);
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

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                if (JOptionPane.showConfirmDialog(null, "Tem a certeza?", "Voltar", JOptionPane.YES_NO_OPTION) == 0) {
                    frame.setVisible(false);
                    frame.dispose();
                    new IntroUI(researchCenters);
                }
            } else if (e.getSource() == backListerFrameButton) {
                listerDialog.dispose();
            } else if (e.getSource() == listConcludedButton) {
                lister(researchCenters.get(index).getFinishedProjects(), "Projetos Concluídos");
            } else if (e.getSource() == listNotConcludedButton) {
                lister(researchCenters.get(index).getUnfinishedProjects(), "Projetos Não Concluídos");
            } else if (e.getSource() == createProjectButton) {
                projectCreater();
            } else if (e.getSource() == addPersonButton) {
                personAdder();
            }
        }
    }
}
