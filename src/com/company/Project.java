package com.company;

import java.util.ArrayList;
import java.util.Calendar;

public class Project {
    private ResearchCenter researchCenter;
    private String nome;
    private String acronimo;
    private Docente principal;
    private Calendar dataInicio;
    private Calendar eta;
    private Calendar dataFim;
    private ArrayList<Task> tasks;
    private ArrayList<Docente> docentes;
    private ArrayList<Bolseiro> bolseiros;


    public void Project(String nome, String acronimo, Calendar dataInicio, Calendar dataFim, Calendar eta, Docente principal){
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.eta = eta;
        this.principal = principal;
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public boolean changeTaskResp(Pessoa responsavel, Task task){
        //colocar codigo para trocar o responsavel de uma determinada tarefa
    }

    public boolean removeTask(Task task){
        return this.tasks.remove(task);
    }

    public void updateTaskPercentage(Task task, int percentageToAdd){

    }

    public ArrayList<Task> getTasksNotStarted(){
        //colocar codigo para retornar as tasks na comecadas (percentagem == 0)
    }

    public ArrayList<Task> getTasksNotConcluded(){
        //colocar codigo para retornar as tasks por concluir (0 < pergentagem < 100)
    }

    public ArrayList<Task> getTasksConcluded(){
        //colcoar codigo para retornar as tasks concluidas (percentagem == 100)
    }

    public int getCost(){
        //colocar codigo para correr o array dos bolseiros e retornar o custo total do projeto
    }

    public void endProject(){
        //colocar codigo para retirar definir o projeto como concluido
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

    public Calendar getEta(){
        return this.eta;
    }

    public void setCalendar(Calendar eta){
        this.eta = eta;
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
}
