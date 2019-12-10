package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
//TODO fazer o getter e setter do principal
//TODO fazer metodo para adicionar bolseiro
//TODO fazer metodo para adicionar docente
//TODO fazer um get cost de maneira inteligente

/**
 * Represents a Project.
 */
public class Project {
    private ResearchCenter researchCenter;
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
     * @param nome String with the project's name.
     * @param acronimo String with the project's acronym.
     * @param dataInicio Calendar with the projects starting date.
     * @param etc Calendar with the estimated date to end the project.
     * @param principal Docente object with the projects principal investigator.
     */
    public  Project(String nome, String acronimo, Calendar dataInicio, Calendar etc, Docente principal){
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicio = dataInicio;
        this.etc = etc;
        this.dataFim = null;
        this.principal = principal;
        this.tasks = new ArrayList<Task>();
        this.docentes = new ArrayList<Docente>();
        this.bolseiros = new ArrayList<Bolseiro>();
    }

    /**
     * Gets the project's tasks.
     * @return ArrayList with the project's tasks.
     */
    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    /**
     * Adds a task to the project's tasks.
     * @param task Task object that will be added to the project's tasks.
     */
    public void addTask(Task task){
        this.tasks.add(task);
    }

    /**
     * Gets the project's end state.
     * @return Boolean that reflects the end state of the project, false if it isn't finished and true if it is.
     */
    public boolean getAcabado(){
        return this.acabado;
    }

    /**
     * Changes the person responsible for the task passed as argument to the other person passed as argument.
     * @param responsavel Pessoa object that will become responsible for the task.
     * @param task Task object that will get its responsible changed.
     * @return On success, returns true, otherwise returns false.
     */
    public boolean changeTaskResp(Pessoa responsavel, Task task){
        Pessoa temp;
        Calendar dia = Calendar.getInstance();
        Bolseiro aux;
        int cond = 0;


        //verifica se o responsavel e um bolseiro ou um docente
        if(this.bolseiros.contains(responsavel)){
            aux = (Bolseiro) responsavel;//nao sei se posso fazer isto
            if( task.getEtc().before(aux.getFimBolsa()) ){//se o etc da task for antes do fim da bolsa do bolseiro, e provavel que a task lhe possa ser atribuida
               cond = 1;
            }

        }
        else if(this.docentes.contains(responsavel)){
            cond = 1;
        }

        if(cond == 1) {
            if (responsavel.getSobrecarga(dia) + task.getEsforco() <= 1 && task.percentage != 100) {//Se isto acontecer, entao a tarefa pode ser atribuida ao novo responsavel

                //retirar a tarefa das tarefas do atual responsavel
                temp = task.getResponsavel();//para saber quem e o responsavel atual da tarefa
                temp.removeTask(task);

                //faz o mesmo que as duas linhas em cima
                //task.getResponsavel().removeTask(task);

                //adicionar a tarefa ao novo responsavel
                responsavel.addTask(task);


                return true;
            } else {
                return false;

            }
        }
        else{
            return false;
        }

    }

    /**
     * Removes a task from the project's tasks.
     * @param task Task object that will be removed from the project's tasks.
     * @return On success, returns true, otherwise returns false.
     */
    public boolean removeTask(Task task){//remove uma task das tasks do projeto, para isso acontecer a task tem de ser removida das tasks do responsavel pela task
        //para poder remover a task da lista de tasks do responsavel pela task
        Pessoa temp = task.getResponsavel();
        temp.removeTask(task);

        return this.tasks.remove(task);//retorna true (se for removido com sucesso) ou false (se nao for removido)
    }

    /**
     * Updates the percentage of the task passed as argument, adds the percentageToAdd to the current task completion percentage.
     * If the result value is less than 0, the task's percentage of completion is set to 0.
     * If the result value is higher than 100, the task's percentage of completion is set to 100.
     * @param task Task object that will get its percentage updated.
     * @param percentageToAdd int that will be added to the current task's percentage of completion.
     */
    //TODO se a task chegar a 100% automaticamente declarar a task como concluida
    public void updateTaskPercentage(Task task, int percentageToAdd){//percentageToAdd e a percentagem que e para adicionar a que ja estava, pode ate ser negativa, se a pessoa quiser reduzir a taxa de realizacao da tarefa
        int temp = task.getPercentage();
        if(temp != 100) {//se a taxa de conclusao for 100, nao da para mudar a sua percentagem
            if(percentageToAdd + temp >= 0 && percentageToAdd + temp <= 100) {//tem de verificar isto para ser um valor adequado
                task.setPercentage(temp + percentageToAdd);
            }
            else if(percentageToAdd + task.getPercentage() < 0){
                task.setPercentage(0);
            }
            else if(percentageToAdd + task.getPercentage() > 100){
                task.setPercentage(100);
            }
        }
        else{
            System.out.printf("Não pode alterar a taxa de conclusao da tarefa uma vez que ja se encontra a 100%%.\n");
        }
    }

    /**
     * Gets the project's not started tasks.
     * @return ArrayList with all of the project's non started tasks.
     */
    public ArrayList<Task> getTasksNotStarted(){
        //retorna um ArrayList<Task> com as tasks do projeto nao comecadas (percentagem == 0)
        ArrayList<Task> temp = new ArrayList<Task>();

        for(Task task : this.tasks){
            if(task.getPercentage() == 0){
                temp.add(task);
            }
        }

        return temp;//depois ver se o temp esta vazio ou nao
    }

    /**
     * Gets the project's taks that aren't concluded.
     * @return ArrayList with all of the project's tasks that aren't concluded.
     */
    public ArrayList<Task> getTasksNotConcluded(){
        //retorna um ArrayList<Task> com as tasks por concluir (0 < pergentagem < 100)
        ArrayList<Task> temp = new ArrayList<Task>();

        for(Task task : this.tasks){
            if(task.getPercentage() > 0 && task.getPercentage() < 100){
                temp.add(task);
            }
        }

        return temp;//depois ver se o temp esta vazio ou nao
    }

    /**
     * Gets the project's concluded tasks.
     * @return ArrayList with all of the project's concluded tasks.
     */
    public ArrayList<Task> getTasksConcluded(){
        //retorna um ArrayList<Task> com as tasks concluidas (percentagem == 100)
        ArrayList<Task> temp = new ArrayList<Task>();

        for(Task task : this.tasks){
            if(task.getPercentage() == 100){
                temp.add(task);
            }
        }

        return temp;//depois ver se o temp esta vazio ou nao
    }

    /**
     * Gets the total cost of the project until the current day.
     * @return int with the cost of the project until the current day.
     */
    public int getCost(){
        //retorna o custo total do projeto
        int custo = 0;

        for(Bolseiro bolseiro : this.bolseiros){
            custo += bolseiro.getCusto();
        }

        return custo;
    }

    /**
     * Sets a project as finished, if it is not already finished and if all the project's tasks are completed.
     * @return On success, returns true, otherwise returns false.
     */
    public boolean endProject(){//TODO Apenas deixar acabar um projeto se todas as tarefas estiverem concluidas e o etc tiver passado

        if(this.acabado != true) {
            if (this.tasks.isEmpty() && this.getTasksConcluded().isEmpty()) {
                this.acabado = true;
                this.dataFim = new GregorianCalendar();
                return true;
            }
            else if ((!this.tasks.isEmpty() && this.getTasksConcluded().isEmpty()) || (this.tasks.size() != this.getTasksConcluded().size())) {
                return false;
            }
            else if(this.compareTaskLists()){
                this.acabado = true;
                this.setDataFim(new GregorianCalendar());
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * Compares the project's tasks and concluded tasks lists.
     * @return If they're equal to each other, return true, otherwise return false.
     */
    private boolean compareTaskLists(){
        int flag = 0;
        for(Task task : this.tasks){
            if(!this.getTasksConcluded().contains(task)){
                flag = 1;
            }
        }
        for(Task task : this.getTasksConcluded()){
            if(!this.tasks.contains(task)){
                flag = 1;
            }
        }

        return flag != 1;
    }

    /**
     * Gets the project's name.
     * @return String with the project's name.
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Sets the project's name.
     * @param nome String with the project's name.
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Gets the project's acronym.
     * @return String with the project's acronym.
     */
    public String getAcronimo(){
        return this.acronimo;
    }

    /**
     * Sets the project's acronym.
     * @param acronimo String with the project's acronym.
     */
    public void sertAcronimo(String acronimo){
        this.acronimo = acronimo;
    }

    /**
     * Gets the project's starting date.
     * @return Calendar with the project's starting date.
     */
    public Calendar getDataInicio(){
        return this.dataInicio;
    }

    /**
     * Sets the project's starting date.
     * @param dataInicio Calendar with the project's starting date.
     */
    public void setDataInicio(Calendar dataInicio){
        this.dataInicio = dataInicio;
    }

    /**
     * Gets the project's estimated date of completion.
     * @return Calendar with the project's estimated date of completion.
     */
    public Calendar getEtc(){
        return this.etc;
    }

    /**
     * Sets the project's estimated date of completion.
     * @param etc Calendar with the project's estimated date of completion.
     */
    public void setEtc(Calendar etc){
        this.etc = etc;
    }

    /**
     * Gets the project's ending date.
     * @return Calendar with the project's ending date.
     */
    public Calendar getDataFim(){
        return this.dataFim;
    }

    /**
     * Sets the project's ending date.
     * @param dataFim Calendar with the project's ending date.
     */
    private void setDataFim(Calendar dataFim){
        this.dataFim = dataFim;
    }

    /**
     * Gets the project's research center.
     * @return ResearchCenter object with the project's research center.
     */
    public ResearchCenter getResearchCenter() {
        return this.researchCenter;
    }

    /**
     * Sets the project's research center.
     * @param researchCenter ResearcCenter object with the project's research center.
     */
    public void setResearchCenter(ResearchCenter researchCenter){
        this.researchCenter = researchCenter;
    }

    /**
     * Gets the project's bolseiros.
     * @return ArrayList with the project's bolseiros.
     */
    public ArrayList<Bolseiro> getBolseiros(){
        return this.bolseiros;
    }

    /**
     * Sets the project's bolseiros.
     * @param bolseiros ArrayList with the project's bolseiros.
     */
    public void setBolseiros(ArrayList<Bolseiro> bolseiros){
        this.bolseiros = bolseiros;
    }

    /**
     * Gets the project's docentes.
     * @return ArrayList twith the project's docentes.
     */
    public ArrayList<Docente> getDocentes(){
        return this.docentes;
    }

    /**
     * Sets the project's docentes.
     * @param docentes ArrayList with the project's docentes.
     */
    public void setDocentes(ArrayList<Docente> docentes){
        this.docentes = docentes;
    }

    public void setTasks(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Assignes a project's task to a responsible.
     * @param responsavel Pessoa object that will become responsible by the task.
     * @param task Task object that will have the pessoa as responsible.
     */
    public void assignResp(Pessoa responsavel, Task task){//atribui uma task, se possivel, a pessoa passada como parametro

        Calendar dia = Calendar.getInstance();
        Bolseiro aux;
        int cond = 0;

        if(this.bolseiros.contains(responsavel)){
            aux = (Bolseiro) responsavel;
            if(aux.getFimBolsa().after(task.getEtc())){
                cond = 1;
            }
        }
        else if(this.docentes.contains(responsavel)){
            cond = 1;
        }


        if(cond == 1){
            if(responsavel.getSobrecarga(dia) + task.getEsforco() <= 1 && task.getPercentage() != 100){
                responsavel.addTask(task);
                System.out.printf("Tarefa atribuida com sucesso.\n");
            }
            else{
                if(task.getPercentage() == 100)
                    System.out.print("Tarefa nao atribuida, pois a tarefa ja esta concluida.\n");
                else
                    System.out.print("Tarefa nao atribuida, pois a pessoa esta sobrecarregada no periodo de execucao da tarefa.\n");
            }
        }
        else{
            System.out.print("Nao foi possivel atribuir a tarefa a pessoa em questao, porque o contrato acaba antes do periodo de execucao da tarefa.\n");
        }

    }
}
