package classes;

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
        //colocar codigo para retornar os projetos inacabados
    }

    public ArrayList<Project> getFinishedProjects(){
        //colocar codigo para retornar projetos acabados
    }


}
