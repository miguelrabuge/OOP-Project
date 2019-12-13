package UI;

import core.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Represents the Center UI
 */
public class CenterUI {
    private String pathName;
    private int index;
    private ArrayList<ResearchCenter> researchCenters;
    private JFrame frame;
    private JDialog listerDialog, projectCreaterDialog;
    private JButton backListerFrameButton, backButton, createProjectButton, listConcludedButton, listNotConcludedButton, addPersonButton, openProjectButton, trueCreateProjectButton, backCreateProjectButton;
    private ButtonListener buttonListener;
    private ListListener listListener;
    private JList<Object> docentesList, bolseirosList, docentesCreateProjectList, projetosList;
    private DefaultListModel<Object> docentesListObjs, bolseirosListObjs, projetosListObjs;
    private JTextField nameTextField, acronimoTextField, diaInicioTextField, diaFimTextField, mesInicioTextField, mesFimTextField, anoInicioTextField, anoFimTextField;
    private JanelaListener windowListener;

    /**
     * Creates a CenterUI with the chosen researchCenter
     * @param researchCenters Arraylist with all the researchCenters
     * @param index int of the chosen researchCenter in the researchCenters Arralist
     * @param pathName saving path for file.obj
     */
    public CenterUI(ArrayList<ResearchCenter> researchCenters, int index, String pathName) {
        this.researchCenters = researchCenters;
        this.index = index;
        this.pathName = pathName;
        drawer();
    }

    /**
     * Draws a CenterUI
     */
    private void drawer() {
        frame = new JFrame();
        windowListener = new JanelaListener();
        frame.addWindowListener(windowListener);
        buttonListener = new ButtonListener();
        listListener = new ListListener();

        /*Frame settings*/
        frame.setTitle("Project Manager");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);;

        /*Panels*/
        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        /*Buttons*/
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

        /*Labels*/
        JLabel welcomeLabel = new JLabel(" Bem-Vindo ao " + researchCenters.get(index).getName());
        welcomeLabel.setFont(new Font(welcomeLabel.getFont().getName(), Font.PLAIN, 30));
        JLabel docentesLabel = new JLabel("Docentes:", JLabel.CENTER);
        JLabel bolseirosLabel = new JLabel("Bolseiros:", JLabel.CENTER);
        JLabel projetosLabel = new JLabel("Projetos:", JLabel.CENTER);

        /*Scroller Lists*/
        //Initialization
        docentesListObjs = new DefaultListModel<>();
        bolseirosListObjs = new DefaultListModel<>();
        projetosListObjs = new DefaultListModel<>();

        //Adding the Objects
        docentesListObjs.addAll(researchCenters.get(index).getDocentes());
        bolseirosListObjs.addAll(researchCenters.get(index).getBolseiros());
        projetosListObjs.addAll(researchCenters.get(index).getProjects());

        //Adding the Objects to the JLists
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
        projetosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        projetosList.addListSelectionListener(listListener);

        //Adding the JLists to JScrollPanes
        JScrollPane docentesScroller = new JScrollPane(docentesList);
        JScrollPane bolseirosScroller = new JScrollPane(bolseirosList);
        JScrollPane projetosScroller = new JScrollPane(projetosList);

        /*Adding Components to Panels and Settings*/
        //Top Panel
        topPanel.setLayout(new BorderLayout());
        topPanel.add(welcomeLabel, BorderLayout.CENTER);
        topPanel.add(backButton, BorderLayout.EAST);

        //Left Panel
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(docentesLabel, BorderLayout.NORTH);
        leftPanel.add(docentesScroller, BorderLayout.CENTER);

        //Center Panel
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(bolseirosLabel, BorderLayout.NORTH);
        centerPanel.add(bolseirosScroller, BorderLayout.CENTER);

        //Right Panel
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(projetosLabel, BorderLayout.NORTH);
        rightPanel.add(projetosScroller, BorderLayout.CENTER);

        //Bottom Panel
        bottomPanel.add(createProjectButton);
        bottomPanel.add(listConcludedButton);
        bottomPanel.add(listNotConcludedButton);
        bottomPanel.add(addPersonButton);
        bottomPanel.add(openProjectButton);

        /*Adding the Panels to the main Frame*/
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * Draws a generic list see-only display for the User
     *
     * @param projects Arraylist with the projects to List
     * @param title String to the User know what is being listed
     */
    private void lister(ArrayList<Project> projects, String title) {
        /*JDialog*/
        //Initialization
        listerDialog = new JDialog();

        //Settings
        listerDialog.setModal(true);
        listerDialog.setSize(400, 400);
        listerDialog.setLocationRelativeTo(null);
        listerDialog.setLayout(new BorderLayout());
        listerDialog.setTitle(title);

        /*Panels*/
        JPanel topListerPanel = new JPanel();
        JPanel middleListerPanel = new JPanel();
        JPanel bottomListerPanel = new JPanel();

        /*Button*/
        backListerFrameButton = new JButton("Voltar");
        backListerFrameButton.addActionListener(buttonListener);

        /*Label*/
        JLabel titleListerLabel = new JLabel(title);
        titleListerLabel.setFont(new Font(titleListerLabel.getFont().getName(), Font.PLAIN, 20));

        /*Scroller Pane*/
        //Initialization
        DefaultListModel<Object> projetosListModel = new DefaultListModel<>();
        //Adding the Projects
        if (projects != null) {
            projetosListModel.addAll(projects);
        }
        //Adding the Projects to the JList and Settings
        JList<Object> listerList = new JList<>(projetosListModel);
        listerList.setFixedCellWidth(170);
        listerList.setFixedCellHeight(30);

        //Adding the JList to a JScrollPane
        JScrollPane listerScroller = new JScrollPane(listerList);

        /*Adding the Components to the Panels*/
        topListerPanel.add(titleListerLabel);
        middleListerPanel.add(listerScroller);
        bottomListerPanel.add(backListerFrameButton);

        /*Adding Panels to the JDialog frame*/
        listerDialog.add(topListerPanel, BorderLayout.NORTH);
        listerDialog.add(middleListerPanel, BorderLayout.CENTER);
        listerDialog.add(bottomListerPanel, BorderLayout.SOUTH);

        listerDialog.setVisible(true);
    }

    /**
     * Draws the menu for a Project Creation
     */
    private void projectCreateDrawer() {
        /*JDialog*/
        //Initialization
        projectCreaterDialog = new JDialog();

        //Settings
        projectCreaterDialog.setModal(true);
        projectCreaterDialog.setSize(700, 500);
        projectCreaterDialog.setLocationRelativeTo(null);
        projectCreaterDialog.setLayout(new GridLayout(1, 2));
        projectCreaterDialog.setTitle("Criar Projeto");

        /*Labels*/
        //Subtitle
        JLabel subtitleProjectCreaterLabel = new JLabel("    Criar Projeto");
        subtitleProjectCreaterLabel.setFont(new Font(subtitleProjectCreaterLabel.getFont().getName(), Font.BOLD, 30));
        //Intruction Label
        JLabel instructionLabel = new JLabel("  Preencha os seguintes campos:");

        //First Pop-Up Labels
        JLabel nameLabel = new JLabel("Nome:      ");
        nameTextField = new JTextField(20);
        JLabel acronimoLabel = new JLabel("Acrónimo:");
        acronimoTextField = new JTextField(20);

        //"Dia", "Mes" and "Ano" Labels
        JLabel diaInicioLabel = new JLabel("Dia:");
        JLabel diaFimLabel = new JLabel("Dia:");
        JLabel mesInicioLabel = new JLabel("Mês:");
        JLabel mesFimLabel = new JLabel("Mês:");
        JLabel anoInicioLabel = new JLabel("Ano:");
        JLabel anoFimLabel = new JLabel("Ano:");

        /*TextFields*/
        //Data de Inicio Label
        JLabel inicioLabel = new JLabel("Data de Início:");
        inicioLabel.setFont(new Font(inicioLabel.getFont().getName(), Font.BOLD, 16));
        //Data de Inicio TextFields
        diaInicioTextField = new JTextField(2);
        mesInicioTextField = new JTextField(2);
        anoInicioTextField = new JTextField(2);

        //Data de Fim Label
        JLabel fimLabel = new JLabel("Data Estimada de Conclusão:");
        fimLabel.setFont(new Font(fimLabel.getFont().getName(), Font.BOLD, 16));
        //Data de Fim TextFields
        diaFimTextField = new JTextField(2);
        mesFimTextField = new JTextField(2);
        anoFimTextField = new JTextField(2);

        //Principal Label
        JLabel principalLabel = new JLabel("Escolha um Investigador Principal:");

        /*Scroller Pane*/
        //Initialization
        DefaultListModel<Object> docentesCreateProjectDefaultListModel = new DefaultListModel<>();

        //Adding Objects to List
        for (Pessoa pessoa : researchCenters.get(index).getDocentes()) {
            docentesCreateProjectDefaultListModel.addElement(pessoa);
        }

        //Adding Objects to JList and Settings
        docentesCreateProjectList = new JList<>(docentesCreateProjectDefaultListModel);
        docentesCreateProjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        docentesCreateProjectList.addListSelectionListener(listListener);

        //JScrollPane Inicialization
        JScrollPane docentesCreateProjectScroller = new JScrollPane(docentesCreateProjectList);

        /*Buttons*/
        trueCreateProjectButton = new JButton("Criar Projeto");
        trueCreateProjectButton.addActionListener(buttonListener);
        trueCreateProjectButton.setEnabled(false);
        backCreateProjectButton = new JButton("Voltar");
        backCreateProjectButton.addActionListener(buttonListener);

        /*Panels*/
        //LEFT PANEL
        JPanel leftProjectCreaterPanel = new JPanel(new GridLayout(2, 1));

        //top LEFT panel and subpanels
        JPanel topLeftProjectCreaterPanel = new JPanel(new GridLayout(4, 1));
        JPanel nameInputPanel = new JPanel(new FlowLayout());
        JPanel acronimoInputPanel = new JPanel(new FlowLayout());

        //bottom LEFT panel and subpanels
        JPanel bottomLeftProjectCreaterPanel = new JPanel(new GridLayout(4, 1));
        JPanel inicioInputPanel = new JPanel(new FlowLayout());
        JPanel fimInputPanel = new JPanel(new FlowLayout());
        JPanel dataInicioPanel = new JPanel(new FlowLayout());
        JPanel dataFimPanel = new JPanel(new FlowLayout());

        //RIGHT PANEL and subpanels
        JPanel rightProjectCreaterPanel = new JPanel(new BorderLayout());
        JPanel buttonerPanel = new JPanel(new FlowLayout());

        /*Adding components into*/
        //LEFT PANELS
        //top LEFT Panel
        nameInputPanel.add(nameLabel);
        nameInputPanel.add(nameTextField);

        acronimoInputPanel.add(acronimoLabel);
        acronimoInputPanel.add(acronimoTextField);

        topLeftProjectCreaterPanel.add(subtitleProjectCreaterLabel);
        topLeftProjectCreaterPanel.add(instructionLabel);
        topLeftProjectCreaterPanel.add(nameInputPanel);
        topLeftProjectCreaterPanel.add(acronimoInputPanel);

        //bottom LEFT Panel
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

        /*Adding panels into main Panel*/
        projectCreaterDialog.add(leftProjectCreaterPanel);
        projectCreaterDialog.add(rightProjectCreaterPanel);

        projectCreaterDialog.setVisible(true);
    }

    /**
     * Connection from projectCreateDrawer function to BackEnd
     */
    private void projectCreater() {
        String name = nameTextField.getText();
        String acronimo = acronimoTextField.getText();
        int diaInicio, mesInicio, anoInicio;
        int diaFim, mesFim, anoFim;
        if (!(name.isBlank() || acronimo.isBlank())) {
            try {
                //Parsing Dia de Inicio
                diaInicio = Integer.parseInt(diaInicioTextField.getText());
                mesInicio = Integer.parseInt(mesInicioTextField.getText());
                anoInicio = Integer.parseInt(anoInicioTextField.getText());

                //Parsing Dia de Fim
                diaFim = Integer.parseInt(diaFimTextField.getText());
                mesFim = Integer.parseInt(mesFimTextField.getText());
                anoFim = Integer.parseInt(anoFimTextField.getText());

                /*Calendar Inicio Initialization*/
                Calendar inicio = new GregorianCalendar();
                inicio.set(Calendar.DAY_OF_MONTH, diaInicio);
                inicio.set(Calendar.MONTH, mesInicio - 1);
                inicio.set(Calendar.YEAR,anoInicio);

                /*Calendar Fim Initialization*/
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

    /**
     * Connection from FrontEnd to BackEnd to add a person
     */
    private void personAdder() {
        int diaInicio, mesInicio, anoInicio, diaFim, mesFim, anoFim;
        String name, email, mecanografico, investigationArea;

        /*TextFields*/
        JTextField firstTextField = new JTextField();
        JTextField secondTextField = new JTextField();
        JTextField diaInicioTextField = new JTextField();
        JTextField mesInicioTextField = new JTextField();
        JTextField anoInicioTextField = new JTextField();
        JTextField diaFimTextField = new JTextField();
        JTextField mesFimTextField = new JTextField();
        JTextField anoFimTextField = new JTextField();
        JComboBox<String> optionBox = new JComboBox<>();

        /*String Arrays*/
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
            if (!(name.isBlank() || email.isBlank())) { // Se os dois campos estiverem preenchidos avança
                try {
                    if (optionBox.getSelectedIndex() == 0) {
                        questions = new Object[]{"Número Mecanográfico:", firstTextField, "Área de Investigação:", secondTextField};
                        if (JOptionPane.showConfirmDialog(null, questions, "Adicionar Docente", JOptionPane.OK_CANCEL_OPTION) == 0) {
                            mecanografico = firstTextField.getText();
                            investigationArea = secondTextField.getText();
                            if (!(mecanografico.isBlank() || investigationArea.isBlank())) {// Se os dois campos estiverem preenchidos avança
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

    /**
     * Represents a ButtonListener that implements the ActionListener class
     */
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) { //Botão de voltar IntroUI
                if (JOptionPane.showConfirmDialog(null, "Tem a certeza?", "Voltar", JOptionPane.YES_NO_OPTION) == 0) {
                    frame.setVisible(false);
                    frame.dispose();
                    new IntroUI(researchCenters, pathName);
                }
            } else if (e.getSource() == trueCreateProjectButton) {  //Botão de criar um projeto (true)
                projectCreater();
                projectCreaterDialog.setVisible(false);
                projectCreaterDialog.dispose();
            } else if (e.getSource() == backCreateProjectButton) {  //Botão de sair do menu de criar um projeto
                projectCreaterDialog.dispose();
            } else if (e.getSource() == backListerFrameButton) {    //Botão de sair do Lister
                listerDialog.dispose();
            } else if (e.getSource() == createProjectButton) {      //Botão para aparecer o menu para criar um projeto
                projectCreateDrawer();
            } else if (e.getSource() == listConcludedButton) {      //Botão para Listar os projetos Concluídos
                lister(researchCenters.get(index).getFinishedProjects(), "Projetos Concluídos");
            } else if (e.getSource() == listNotConcludedButton) {   //Botão para Listar os projetos Não Concluídos antes do ETC
                lister(researchCenters.get(index).getUnfinishedProjectsInEtc(), "Projetos Não Concluídos na Data Estimada");
            } else if (e.getSource() == addPersonButton) {          //Botão Para adicionar uma Pessoa ao Centro
                personAdder();
            } else if (e.getSource() == openProjectButton) {        //Botão para abrir um projeto selecionado
                frame.setVisible(false);
                frame.dispose();
                new ProjectUI(researchCenters, index, projetosList.getSelectedIndex(),pathName);
            }
        }
    }

    /**
     * Represents a ListListener that implements the ListSelectionListener class
     */
    private class ListListener implements ListSelectionListener {
        /**
         * Enable/ Disable of buttons if a list value is selected, or not, respectively
         * @param e ListSelectionEvent choosing an element in a given list
         */
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
            if (booter.saveObjectFile(pathName,researchCenters)){
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
