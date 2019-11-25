package com.company;

import java.util.ArrayList;
import java.util.Calendar;

abstract class Pessoa {

    protected ResearchCenter researchCenter;
    protected ArrayList<Task> tasks;
    protected String nome;
    protected String email;

    public Pessoa(String nome, String email){
        this.nome = nome;
        this.email = email;
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

    public double getSobrecarga(Calendar dia){
        //percorre o array de tasks e ve se ,no dia passado por parametro, qual e o estado de sobrecarga da pessoa
    }
}
