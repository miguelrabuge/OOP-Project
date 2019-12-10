package core;

import java.util.ArrayList;

/**
 * Represents a ResearchCenter
 */
public class ResearchCenter {
    private String name;
    private ArrayList<Project> projects;
    private ArrayList<Pessoa> pessoas;

    /**
     * Creates a ResearchCenter with its name.
     * @param name String with the researchcenter's name.
     */
    public ResearchCenter(String name){
        this.name = name;
    }

    /**
     * Gets the researchcenter's name.
     * @return String with the researchcenter's name.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets the researchcenter's name.
     * @param name String with the researchcenter's name.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the researchcenter's projects.
     * @return ArrayList with the researchcenter's projects.
     */
    public ArrayList<Project> getProjects(){
        return this.projects;
    }

    /**
     * Sets the researchcenter's projects.
     * @param projects ArrayList with the researchcenter's projects.
     */
    public void setProjects(ArrayList<Project> projects){
        this.projects = projects;
    }

    /**
     * Gets the researchcenter's pessoas.
     * @return ArrayList with the researchcenter's pessoas.
     */
    public ArrayList<Pessoa> getPessoas(){
        return this.pessoas;
    }

    /**
     * Sets the researchcenter's pessoas.
     * @param pessoas ArrayList with the researchcenter's pessoas.
     */
    public void setPessoas(ArrayList<Pessoa> pessoas){
        this.pessoas = pessoas;
    }

    /**
     * Adds a project to the researchcenter's projects.
     * @param projeto Project object that will be added to the researchcenter's projects.
     */
    public void addProject(Project projeto ){
        this.projects.add(projeto);
    }

    /**
     * Gets the researchcenter's unfinished projects.
     * @return ArrayList with all of the researchcenter's unfinished projects.
     */
    public ArrayList<Project> getUnfinishedProjects(){
        //retorna os projetos inacabados
        ArrayList<Project> temp = new ArrayList<Project>();

        for(Project project : this.projects){
            if(!project.getAcabado()){
                temp.add(project);
            }
        }
        return temp;//depois verificar se o temp esta vazio ou nao
    }

    /**
     * Gets the researchcenter's finished projects.
     * @return ArrayList with all of the researchcenter's finished projects.
     */
    public ArrayList<Project> getFinishedProjects(){
        //retorna projetos acabados
        ArrayList<Project> temp = new ArrayList<Project>();

        for(Project project : this.projects){
            if(project.getAcabado()){
                temp.add(project);
            }
        }
        return temp;//depois verificar se o temp esta vazio ou nao
    }

    @Override
    public String toString() {
        return name;
    }
}
