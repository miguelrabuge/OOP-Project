package com.company;

import java.util.ArrayList;

public class ResearchCenter {
    private String name;
    private ArrayList<Project> projects;
    private ArrayList<Pessoa> pessoas;

    public ResearchCenter(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<Project> getProjects(){
        return this.projects;
    }

    public void setProjects(ArrayList<Project> projects){
        this.projects = projects;
    }

    public ArrayList<Pessoa> getPessoas(){
        return this.pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas){
        this.pessoas = pessoas;
    }

    public void addProject(Project projeto ){
        this.projects.add(projeto);
    }

    public ArrayList<Project> getUnfinishedProjects(){
        //retorna os projetos inacabados
        ArrayList<Project> temp = new ArrayList<Project>();

        for(Project project : this.projects){
            if(project.getAcabado() == 0){
                temp.add(project);
            }
        }
        return temp;//depois verificar se o temp esta vazio ou nao
    }

    public ArrayList<Project> getFinishedProjects(){
        //retorna projetos acabados
        ArrayList<Project> temp = new ArrayList<Project>();

        for(Project project : this.projects){
            if(project.getAcabado() == 1){
                temp.add(project);
            }
        }

        return temp;//depois verificar se o temp esta vazio ou nao
    }


}
