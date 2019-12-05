package com.company;

import java.util.ArrayList;
import java.util.Calendar;

abstract class Pessoa {

    protected ResearchCenter researchCenter;
    protected ArrayList<Task> tasks;
    protected String nome;
    protected String email;

    public Pessoa(String nome, String email, ResearchCenter researchCenter){
        this.nome = nome;
        this.email = email;
        this.tasks = new ArrayList<Task>();
        this.researchCenter = researchCenter;
    }

    public ResearchCenter getResearchCenter() {
        return this.researchCenter;
    }

    public void setResearchCenter(ResearchCenter researchCenter) {
        this.researchCenter = researchCenter;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void removeTask(Task task){//remove uma task das tasks da pessoa
        this.tasks.remove(task);
        task.setResponsavel(null);//volta a colocar a task como livre, ou seja nao tem um responsavel
    }

    public void addTask(Task task){//adiciona uma task as tasks da pessoa
        this.tasks.add(task);
        task.setResponsavel(this);//penso que isto funcione
    }

    public double getSobrecarga(Calendar dia){//retorna a sobrecarga da pessoa
        double sobrecarga = 0;
/*
        for(Task task : this.tasks){
            if(task.getPercentage() != 100){//se a tarefa estiver por completar adicionamos o seu esforco a sobrecarga da pessoa
                sobrecarga = task.getEsforco();
            }
        }
*/

        for(Task task : this.tasks){

            if(dia.after(task.getInicio()) && dia.before(task.getEtc())){

                sobrecarga += task.getEsforco();

            }

        }

        return sobrecarga;
    }
}
