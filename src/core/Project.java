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
    private Calendar etc;
    private Calendar dataFim;
    private ArrayList<Task> tasks;
    private ArrayList<Docente> docentes;
    private ArrayList<Bolseiro> bolseiros;

    private boolean acabado;

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
        this.acabado = false;
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
        task.setProjeto(this);
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
        if (assignResp(responsavel, task)) {        //if task is assignable to responsavel
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
        task.setProjeto(null);
        return (this.tasks).remove(task);   //retorna true (se for removido com sucesso) ou false (se nao for removido)
    }

    /**
     * Updates the percentage of the task passed as argument, adds the percentageToAdd to the current task completion percentage.
     *
     * @param task            Task object that will get its percentage updated.
     * @param percentageToAdd int that will be added to the current task's percentage of completion.
     * @return boolean that confirms the increment or decrement
     */
    public boolean updateTaskPercentage(Task task, int percentageToAdd) {//percentageToAdd é a percentagem que é para adicionar há que já estava, pode até ser negativa, se a pessoa quiser reduzir a taxa de realização da tarefa
        int percentageActual = task.getPercentage();
        if (percentageToAdd + percentageActual >= 0 && percentageToAdd + percentageActual <= 100) {     //tem de verificar isto para ser um valor adequado
            task.setPercentage(percentageActual + percentageToAdd);
            if (task.getPercentage() == 100) {
                task.setFim(new GregorianCalendar());   //como a taxa de conclusao chegou a 100%, declara a task como finalizada e guarda a data em que isso aconteceu
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
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getPercentage() == 0) {
                temp.add(task);
            }
        }
        return temp;//depois ver se o temp esta vazio ou nao
    }

    /**
     * Gets the project's tasks that aren't concluded.
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
        return temp;
    }

    /**
     * Gets the project's tasks that were not concluded before the ETC
     *
     * @return ArrayList with all of the project's tasks that were not concluded before the ETC
     */
    public ArrayList<Object> getTasksNotConcludedInEtc() {
        ArrayList<Object> tarefas = new ArrayList<>();
        Calendar diaAtual = new GregorianCalendar();
        for (Task task : this.tasks) {
            // Verifica primeiro se foi concluída. Se sim, vê se o Fim foi depois do Etc e adiciona. Caso contrário verifica se já ultrapassou o etc e adiciona
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
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getPercentage() == 100) {
                temp.add(task);
            }
        }
        return temp;
    }

    /**
     * Gets the total cost of the project until the current day.
     *
     * @return int with the cost of the project until the current day.
     */
    public int getCost() {
        int custo = 0;
        for (Task task : this.tasks) {
            //Percorre-se todas as tasks do projeto. Ao responsável de cada uma, calcula se o salário médio diário e multiplica-se pelo número de dias em que este executa a tarefa.
            //Este valor é posteriormente somado à variável int custo que irá ser retornada.
            custo += ((task.getResponsavel().getCusto() * 12) / 365) * numeroDeDias(task);
        }
        return custo;
    }

    /**
     * Counts the days that were spent by the tasks responsible to complete it.
     *
     * @param task Tasks object that we want to know the time taken to finish it.
     * @return int with the number of days spent to complete the task.
     */
    private int numeroDeDias(Task task) {
        int daysCounter;
        Calendar inicio = (Calendar) task.getInicio().clone();
        if (task.getFim() != null) { //Se a tarefa foi concluída, conta-se até à data de Fim
            for (daysCounter = 0; inicio.compareTo(task.getFim()) <= 0; daysCounter++) {
                inicio.add(Calendar.DAY_OF_MONTH, 1);
            }
        } else {    //Caso contrário conta-se até à Data prevista
            for (daysCounter = 0; inicio.compareTo(task.getEtc()) <= 0; daysCounter++) {
                inicio.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        return daysCounter;
    }

    /**
     * Verifies if a project can be ended.
     * If it can, sets the boolean acabado to true
     * Else returns false
     *
     * @return On success, sets the ta and returns true and , otherwise returns false.
     */
    public boolean endProject() {
        Calendar diaAtual = new GregorianCalendar();
        if (!this.acabado) {
            if (this.etc.compareTo(diaAtual) <= 0) {//se o tempo estimado para concluir o projeto já tiver passado, estamos, provavelmente, em condições de finalizar o projeto
                if (this.tasks.size() == this.getTasksConcluded().size()) {
                    this.acabado = true;
                    this.dataFim = diaAtual;
                    return true; // Caso o ETC tenha passado e as tasks estiverem todas concluídas
                }
            }
        } else {
            return true; //Se já estiver acabado
        }
        return false; // Tudo o resto que não está em condições de finalizar
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
     * Gets the project's starting date.
     *
     * @return Calendar with the project's starting date.
     */
    public Calendar getDataInicio() {
        return this.dataInicio;
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
     * Gets the project's docentes.
     *
     * @return ArrayList with the project's docentes.
     */
    public ArrayList<Docente> getDocentes() {
        return this.docentes;
    }

    /**
     * Gets the project's Pessoas (docentes + bolseiros).
     *
     * @return ArrayList with the project's people
     */
    public ArrayList<Pessoa> getPessoas() {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        pessoas.addAll(this.docentes);
        pessoas.addAll(this.bolseiros);
        return pessoas;
    }

    /**
     * Assignes a project's task to a responsible
     * Only if sobrecarga less than maximum possible and task is contained in the responsible contract period in case of a Bolseiro.
     * IP can receive ALL tasks
     *
     * @param responsavel Pessoa object that will become responsible by the task.
     * @param task        Task object that will have the pessoa as responsible.
     * @return boolean true if it it Assignable; false if not
     */
    public boolean assignResp(Pessoa responsavel, Task task) {//atribui uma task, se possivel, à pessoa passada como parametro
        if ((responsavel.getCusto() == 0) || ((responsavel.getCusto() > 0) && (((Bolseiro) responsavel).getInicioBolsa().before(task.getInicio())) //Se a data de inicio da tarefa for depois ou igual à data de inicio da bolsa
                && (((Bolseiro) responsavel).getFimBolsa().after(task.getEtc())))) { //Se a data de fim da tarefa for antes à data de fim da bolsa
            if (task.checkAvailability(responsavel) && task.getPercentage() != 100) {
                responsavel.addTask(task);
                System.out.println("Tarefa atribuida com sucesso.");
            } else if (responsavel.equals(this.principal)) {    // O investigador principal aceita todas as tasks
                responsavel.addTask(task);
                System.out.println("Tarefa atribuida com sucesso ao IP.");
            } else {
                if (task.getPercentage() == 100) {
                    System.out.println("Tarefa nao atribuída, pois está concluída.");
                } else {
                    System.out.println("Tarefa nao atribuída, pois a pessoa está sobrecarregada no periodo de execução da tarefa.");
                }
                return false;
            }
        } else {
            System.out.println("Não foi possível atribuir a tarefa à pessoa em questão, porque o contrato acaba antes do periodo de execução da tarefa.");
            return false;
        }
        return true;
    }

    /**
     * Adds a bolseiro to the project's bolseiros if it has not been assigned to another project.
     *
     * @param bolseiro Bolseiro object that will be added to the project's bolseiros.
     */
    public void addBolseiro(Bolseiro bolseiro) {
        if (bolseiro.getProjeto() == null) { // Se não estiver destacado para outro projeto
            this.bolseiros.add(bolseiro);
            bolseiro.setProjeto(this);
        }
    }

    /**
     * Adds a docente to the project's docentes.
     *
     * @param docente Docente object that will be added to the project's docentes.
     * @return On success, returns true, otherwise returns false.
     */
    public boolean addDocente(Docente docente) {
        if (!this.docentes.contains(docente)) {//se o docente não estiver ja no projeto, adiciona-o.
            this.docentes.add(docente);
            docente.addProject(this);
            return true;
        }
        return false;   //se o docente já estiver adicionado ao projeto retorna false.
    }

    @Override
    public String toString() {
        return acronimo;
    }
}
