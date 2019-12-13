package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Represents a Project.
 */
public class Project implements Serializable {
    private String nome;
    private String acronimo;
    private Docente principal;
    private Calendar dataInicio;
    private Calendar etc;//tempo estimado para terminar o projeto em meses
    private Calendar dataFim;
    private ArrayList<Task> tasks;
    private ArrayList<Docente> docentes;
    private ArrayList<Bolseiro> bolseiros;

    private boolean acabado = false;

    /**
     * Creates a Project with its name, acronym, beginning date, estimated time to complete it and a principal investigator.
     *
     * @param nome       String with the project's name.
     * @param acronimo   String with the project's acronym.
     * @param dataInicio Calendar with the projects starting date.
     * @param etc        Calendar with the estimated date to end the project.
     * @param principal  Docente object with the projects principal investigator.
     */
    public Project(String nome, String acronimo, Calendar dataInicio, Calendar etc, Docente principal) {
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicio = dataInicio;
        this.etc = etc;
        this.dataFim = null;
        this.principal = principal;
        this.tasks = new ArrayList<Task>();
        this.docentes = new ArrayList<Docente>();
        this.docentes.add(principal);
        this.bolseiros = new ArrayList<Bolseiro>();
    }

    /**
     * Gets the project's tasks.
     *
     * @return ArrayList with the project's tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the project's tasks.
     *
     * @param task Task object that will be added to the project's tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets the project's end state.
     *
     * @return Boolean that reflects the end state of the project, false if it isn't finished and true if it is.
     */
    public boolean getAcabado() {
        return this.acabado;
    }

    /**
     * Sets the end state of the project.
     *
     * @param b boolean to represent the state of completion fo the project.
     */
    public void setAcabado(boolean b) {
        this.acabado = b;
    }

    /**
     * Changes the person responsible for the task passed as argument to the other person passed as argument.
     *
     * @param responsavel Pessoa object that will become responsible for the task.
     * @param task        Task object that will get its responsible changed.
     * @return On success, returns true, otherwise returns false.
     */
    public boolean changeTaskResp(Pessoa responsavel, Task task) {
        if (assignResp(responsavel, task)) {
            task.getResponsavel().removeTask(task);
            task.setResponsavel(responsavel);
            return true;
        }
        return false;
    }

    /**
     * Removes a task from the project's tasks.
     *
     * @param task Task object that will be removed from the project's tasks.
     * @return On success, returns true, otherwise returns false.
     */
    public boolean removeTask(Task task) {
        task.getResponsavel().removeTask(task);
        return (this.tasks).remove(task);//retorna true (se for removido com sucesso) ou false (se nao for removido)
    }

    /**
     * Updates the percentage of the task passed as argument, adds the percentageToAdd to the current task completion percentage.
     * If the result value is less than 0, the task's percentage of completion is set to 0.
     * If the result value is higher than 100, the task's percentage of completion is set to 100.
     *
     * @param task            Task object that will get its percentage updated.
     * @param percentageToAdd int that will be added to the current task's percentage of completion.
     * @return boolean that confirms the increment or decrement
     */
    public boolean updateTaskPercentage(Task task, int percentageToAdd) {//percentageToAdd e a percentagem que e para adicionar a que ja estava, pode ate ser negativa, se a pessoa quiser reduzir a taxa de realizacao da tarefa
        int percentageActual = task.getPercentage();
        if (percentageToAdd + percentageActual >= 0 && percentageToAdd + percentageActual <= 100) {//tem de verificar isto para ser um valor adequado
            task.setPercentage(percentageActual + percentageToAdd);
            if (task.getPercentage() == 100) {
                task.setFim(new GregorianCalendar());//como a taxa de conclusao chegou a 100%, declara a task como finalizada e guarda a data em que isso aconteceu
            }
            return true;
        }
        return false;
    }


    /**
     * Gets the project's not started tasks.
     *
     * @return ArrayList with all of the project's non started tasks.
     */
    public ArrayList<Task> getTasksNotStarted() {
        //retorna um ArrayList<Task> com as tasks do projeto nao comecadas (percentagem == 0)
        ArrayList<Task> temp = new ArrayList<Task>();
        for (Task task : this.tasks) {
            if (task.getPercentage() == 0) {
                temp.add(task);
            }
        }
        return temp;//depois ver se o temp esta vazio ou nao
    }

    /**
     * Gets the project's taks that aren't concluded.
     *
     * @return ArrayList with all of the project's tasks that aren't concluded.
     */
    public ArrayList<Object> getTasksNotConcluded() {
        //retorna um ArrayList<Task> com as tasks por concluir (0 <= pergentagem < 100)
        ArrayList<Object> temp = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getPercentage() >= 0 && task.getPercentage() < 100) {
                temp.add(task);
            }
        }
        return temp;//depois ver se o temp esta vazio ou nao
    }

    public ArrayList<Object> getTasksNotConcludedInEtc() {
        ArrayList<Object> tarefas = new ArrayList<>();
        Calendar diaAtual = new GregorianCalendar();
        for (Task task : this.tasks) {
            if (((task.getPercentage() == 100) && task.getEtc().before(task.getFim())) || (task.getEtc().before(diaAtual))) {
                tarefas.add(task);
            }
        }
        return tarefas;
    }

    /**
     * Gets the project's concluded tasks.
     *
     * @return ArrayList with all of the project's concluded tasks.
     */
    public ArrayList<Task> getTasksConcluded() {
        //retorna um ArrayList<Task> com as tasks concluidas (percentagem == 100)
        ArrayList<Task> temp = new ArrayList<Task>();
        for (Task task : this.tasks) {
            if (task.getPercentage() == 100) {
                temp.add(task);
            }
        }
        return temp;//depois ver se o temp esta vazio ou nao
    }

    /**
     * Gets the total cost of the project until the current day.
     *
     * @return int with the cost of the project until the current day.
     */
    public int getCost() {
        int custo = 0;
        for (Task task : this.tasks) {
            custo += ((task.getResponsavel().getCusto() * 12) / 365) * numeroDeDias(task);
        }
        return custo;
    }

    /**
     * Counts the days that were spent by the tasks reponsible to complete it.
     *
     * @param task Tasks object that we want to know the time taken to finish it.
     * @return int with the number of days spent to complete the task.
     */
    private int numeroDeDias(Task task) {
        int daysCounter;
        Calendar inicio = (Calendar) task.getInicio().clone();
        if (task.getFim() != null) {
            for (daysCounter = 0; inicio.compareTo(task.getFim()) <= 0; daysCounter++) {
                inicio.add(Calendar.DAY_OF_MONTH, 1);
            }
        } else {
            for (daysCounter = 0; inicio.compareTo(task.getEtc()) <= 0; daysCounter++) {
                inicio.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        return daysCounter;
    }

    /**
     * Sets a project as finished, if it is not already finished and if all the project's tasks are completed.
     *
     * @return On success, returns true, otherwise returns false.
     */
    public boolean endProject() {

        if (!this.acabado) {
            if (this.etc.compareTo(new GregorianCalendar()) <= 0) {//se o tempo estimado para concluir o projeto ja tiver passado, estamos provavelmente em condicao de finalizar o projeto

                if (this.tasks.isEmpty() && this.getTasksConcluded().isEmpty()) {//se o projeto nao tiver tarefas e o etc tiver passado, o projeto pode ser finalizado
                    this.acabado = true;
                    this.dataFim = new GregorianCalendar();
                    return true;
                } else if ((!this.tasks.isEmpty() && this.getTasksConcluded().isEmpty()) || (this.tasks.size() != this.getTasksConcluded().size())) {//se as tarefas nao tiverem sido todas concluidas, mesmo que o etc tenha passado, o projeto nao pode ser terminado
                    return false;
                } else {//se as tarefas estiverem todas concluidas e o etc ja tiver passado, o projeto pode ser terminado
                    this.acabado = true;
                    this.dataFim = new GregorianCalendar();
                    return true;
                }
            } else {
                return false;
            }

        } else {
            return false;
        }
    }


    /**
     * Gets the project's name.
     *
     * @return String with the project's name.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Sets the project's name.
     *
     * @param nome String with the project's name.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the project's acronym.
     *
     * @return String with the project's acronym.
     */
    public String getAcronimo() {
        return this.acronimo;
    }

    /**
     * Sets the project's acronym.
     *
     * @param acronimo String with the project's acronym.
     */
    public void sertAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    /**
     * Gets the project's starting date.
     *
     * @return Calendar with the project's starting date.
     */
    public Calendar getDataInicio() {
        return this.dataInicio;
    }

    /**
     * Sets the project's starting date.
     *
     * @param dataInicio Calendar with the project's starting date.
     */
    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Gets the project's estimated date of completion.
     *
     * @return Calendar with the project's estimated date of completion.
     */
    public Calendar getEtc() {
        return this.etc;
    }

    /**
     * Sets the project's estimated date of completion.
     *
     * @param etc Calendar with the project's estimated date of completion.
     */
    public void setEtc(Calendar etc) {
        this.etc = etc;
    }

    /**
     * Gets the project's ending date.
     *
     * @return Calendar with the project's ending date.
     */
    public Calendar getDataFim() {
        return this.dataFim;
    }


    /**
     * Sets the project's ending date.
     *
     * @param dataFim Calendar with the project's ending date.
     */
    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Gets the project's bolseiros.
     *
     * @return ArrayList with the project's bolseiros.
     */
    public ArrayList<Bolseiro> getBolseiros() {
        return this.bolseiros;
    }

    /**
     * Sets the project's bolseiros.
     *
     * @param bolseiros ArrayList with the project's bolseiros.
     */
    public void setBolseiros(ArrayList<Bolseiro> bolseiros) {
        this.bolseiros = bolseiros;
    }

    /**
     * Gets the project's docentes.
     *
     * @return ArrayList twith the project's docentes.
     */
    public ArrayList<Docente> getDocentes() {
        return this.docentes;
    }

    /**
     * Sets the project's docentes.
     *
     * @param docentes ArrayList with the project's docentes.
     */
    public void setDocentes(ArrayList<Docente> docentes) {
        this.docentes = docentes;
    }

    /**
     * Sets the project's tasks.
     *
     * @param tasks ArrayList with the project's tasks.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the project's Pessoas (docentes + bolseiros).
     */
    public ArrayList<Pessoa> getPessoas() {
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        pessoas.addAll(this.docentes);
        pessoas.addAll(this.bolseiros);
        return pessoas;
    }

    /**
     * Assignes a project's task to a responsible.
     *
     * @param responsavel Pessoa object that will become responsible by the task.
     * @param task        Task object that will have the pessoa as responsible.
     */
    public boolean assignResp(Pessoa responsavel, Task task) {//atribui uma task, se possivel, a pessoa passada como parametro
        if ((responsavel.getCusto() == 0) || ((responsavel.getCusto() > 0) && (((Bolseiro) responsavel).getInicioBolsa().before(task.getInicio())) //Se a data de inicio da tarefa for depois ou igual à data de inicio da bolsa
                && (((Bolseiro) responsavel).getFimBolsa().after(task.getEtc())))) { //Se a data de fim da tarefa for antes ou igual à data de fim da bolsa
            if (task.checkAvailability(responsavel) && task.getPercentage() != 100) {
                responsavel.addTask(task);
                System.out.println("Tarefa atribuida com sucesso.\n");
            } else {
                if (task.getPercentage() == 100) {
                    System.out.println("Tarefa nao atribuída, pois está concluída.\n");
                } else {
                    System.out.println("Tarefa nao atribuída, pois a pessoa está sobrecarregada no periodo de execução da tarefa.\n");
                }
                return false;
            }
        } else {
            System.out.println("Não foi possível atribuir a tarefa à pessoa em questão, porque o contrato acaba antes do periodo de execução da tarefa.\n");
            return false;
        }
        return true;
    }

    /**
     * Gets the project's principal investigator.
     *
     * @return Docente object with the project's principal investigator.
     */
    public Docente getPrincipal() {
        return this.principal;
    }

    /**
     * Sets the project's principal investigator.
     *
     * @param principal Docente object with the project's principal investigator.
     */
    public void setPrincipal(Docente principal) {
        this.principal = principal;
    }

    /**
     * Adds a bolseiro to the project's bolseiros.
     *
     * @param bolseiro Bolseiro object that will be added to the project's bolseiros.
     * @return On success, returns true, otherwise returns false.
     */
    public boolean addBolseiro(Bolseiro bolseiro) {
        if (bolseiro.getProjeto() == null) {
            this.bolseiros.add(bolseiro);
            bolseiro.setProjeto(this);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a docente to the project's docentes.
     *
     * @param docente Docente object that will be added to the project's docentes.
     * @return On success, returns true, otherwise returns false.
     */
    public boolean addDocente(Docente docente) {
        if (!this.docentes.contains(docente)) {//se o docente nao estiver ja no projeto, adiciona-o.
            this.docentes.add(docente);
            docente.addProject(this);
            return true;
        }
        return false;//se o docente ja estiver adicionado ao projeto retorna false.
    }

    @Override
    public String toString() {
        return acronimo;
    }
}
