package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Project {
    private ResearchCenter researchCenter;
    private String nome;
    private String acronimo;
    private Docente principal;
    private Calendar dataInicio;
    private int etc;//tempo estimado para terminar o projeto em meses
    private Calendar dataFim;//TODO vamos inicializar a data fim com que valor
    private ArrayList<Task> tasks;
    private ArrayList<Docente> docentes;
    private ArrayList<Bolseiro> bolseiros;

    private int acabado = 0;//acrescentei esta variavel para saber se um projeto esta ou nao acabado (1 == acabado, 0 == por acabar)


    public void Project(String nome, String acronimo, Calendar dataInicio, Calendar dataFim, int etc, Docente principal){
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;//TODO penso que isto possa ser null porque no inicio nao se sabe qual vai ser a data fim do projeto, ou o projeto ja tem uma data de fim predefinida (esta tudo acabado esta, nao esta estivesse)
        this.etc = etc;
        this.principal = principal;
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public int getAcabado(){
        return this.acabado;
    }

    public boolean changeTaskResp(Pessoa responsavel, Task task){
        Pessoa temp;
        //colocar codigo para trocar o responsavel de uma determinada tarefa
        //TODO tenho de avaliar, caso seja um bolseiro, se o novo responsavel tem tempo de contrato suficiente para concluir a tarefa
        if(responsavel.getSobrecarga() + task.getEsforco() <= 1 && task.percentage != 100){//Se isto acontecer, entao a tarefa pode ser atribuida ao novo responsavel

            //retirar a tarefa das tarefas do atual responsavel
            temp = task.getResponsavel();//para saber quem e o responsavel atual da tarefa
            temp.removeTask(task);

            //adicionar a tarefa ao novo responsavel
            responsavel.addTask(task);


            return true;
        }
        else{
            return false;
        }
    }

    public boolean removeTask(Task task){//remove uma task das tasks do projeto, para isso acontecer a task tem de ser removida das tasks do responsavel pela task
        //para poder remover a task da lista de tasks do responsavel pela task
        Pessoa temp = task.getResponsavel();
        temp.removeTask(task);

        return this.tasks.remove(task);//retorna true (se for removido com sucesso) ou false (se nao for removido)
    }

    public void updateTaskPercentage(Task task, int percentageToAdd){//percentageToAdd e a percentagem que e para adicionar a que ja estava, pode ate ser negativa, se a pessoa quiser reduzir a taxa de realizacao da tarefa
        int temp = task.getPercentage();
        if(temp != 100) {//se a taxa de conclusao for 100, nao da para mudar a sua percentagem
            if(percentageToAdd + temp >= 0 && percentageToAdd + temp <= 100) {//tem de verificar isto para ser um valor adequado
                task.setPercentage(temp + percentageToAdd);
            }
            else{
                //TODO --> nao sei o que queres fazer se nao der, queres dar print de uma mensagem a dizer porque e que nao deu?
            }
        }
        else{
            //TODO --> nao sei o que queres fazer se nao der, queres dar print de uma mensagem a dizer porque e que nao deu?
        }
    }

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

    public int getCost(){
        //retorna o custo total do projeto
        int custo = 0;

        for(Bolseiro bolseiro : this.bolseiros){
            custo += bolseiro.getCusto();
        }

        return custo;
    }

    public void endProject(){//TODO podia ser feito por um setAcabado, mas acho que assim fica mais legivel
        //define o projeto como concluido
        //quando o projeto e concluido, de acordo com o "to do" que esta mais acima a data fim tem de ser registada
        this.acabado = 1;
        this.dataFim = new GregorianCalendar();//guarda a data em que o projeto foi terminado
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getAcronimo(){
        return this.acronimo;
    }

    public void sertAcronimo(String acronimo){
        this.acronimo = acronimo;
    }

    public Calendar getDataInicio(){
        return this.dataInicio;
    }

    public void setDataInicio(Calendar dataInicio){
        this.dataInicio = dataInicio;
    }

    public int getEtc(){
        return this.etc;
    }

    public void setEtc(int etc){
        this.etc = etc;
    }

    public Calendar getDataFim(){
        return this.dataFim;
    }

    public void setDataFim(Calendar dataFim){
        this.dataFim = dataFim;
    }

    public ResearchCenter getResearchCenter() {
        return researchCenter;
    }

    public void setResearchCenter(ResearchCenter researchCenter){
        this.researchCenter = researchCenter;
    }

    public ArrayList<Bolseiro> getBolseiros(){
        return this.bolseiros;
    }

    public void setBolseiros(ArrayList<Bolseiro> bolseiros){
        this.bolseiros = bolseiros;
    }

    public ArrayList<Docente> getDocentes(){
        return this.docentes;
    }

    public void setDocentes(ArrayList<Docente> docentes){
        this.docentes = docentes;
    }

    public void setTasks(ArrayList<Task> tasks){
        this.tasks = tasks;
    }


    public void assignResp(Pessoa responsavel, Task task){

    }
}
