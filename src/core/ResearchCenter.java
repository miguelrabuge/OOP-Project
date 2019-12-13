package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Represents a ResearchCenter
 */
public class ResearchCenter implements Serializable {
    private String name;
    private ArrayList<Project> projects;
    private ArrayList<Pessoa> pessoas;

    /**
     * Creates a ResearchCenter with its name.
     * @param name String with the researchcenter's name.
     */
    public ResearchCenter(String name){
        this.name = name;
        this.projects = new ArrayList<Project>();
        this.pessoas = new ArrayList<Pessoa>();
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
     * Gets a Pessoa on the Center by name
     * @param name
     * @return On success Pessoa object that matches the given String else returns null
     */
    public Pessoa getPessoaByName(String name){
        for (Pessoa pessoa: this.pessoas) {
            if(pessoa.getNome().equals(name)){
                return pessoa;
            }
        }
        return null;
    }

    /**
     * Gets the researchcenter's docentes.
     * @return ArrayList with the researchcenter's docentes.
     */
    public ArrayList<Pessoa> getDocentes(){
        ArrayList<Pessoa> docentes = new ArrayList<Pessoa>();
            for (Pessoa pessoa : this.pessoas) {
                if (pessoa.getCusto() == 0){
                    docentes.add(pessoa);
                }
            }
        return docentes;
    }

    /**
     * Gets the researchcenter's docentes that are not in a given project.
     * @param project
     * @return ArrayList with the researchcenter's docentes not int the Project project.
     */
    public ArrayList<Pessoa> getDocentesNotInProject(Project project){
        ArrayList<Pessoa> docentes = new ArrayList<>();
        for (Pessoa docente : this.getDocentes()) {
            if (!project.getDocentes().contains((Docente)docente)){
                docentes.add(docente);
            }
        }
        return docentes;
    }

    /**
     * Gets the researchcenter's bolseiros.
     * @return ArrayList with the researchcenter's bolseiros.
     */
    public ArrayList<Pessoa> getBolseiros(){
        ArrayList<Pessoa> bolseiros = new ArrayList<Pessoa>();{
            for (Pessoa pessoa : this.pessoas) {
                if (pessoa.getCusto() != 0){
                    bolseiros.add(pessoa);
                }
            }
        }
        return bolseiros;
    }
    /**
     * Sets the researchcenter's pessoas.
     * @param pessoas ArrayList with the researchcenter's pessoas.
     */
    public void setPessoas(ArrayList<Pessoa> pessoas){
        this.pessoas = pessoas;
    }

    /**
     * Adds a Pessoa to the reasearchCenter
     * @param pessoa Pessoa object that will be added to the researchCenter Pessoa's
     */
    public void addPessoa(Pessoa pessoa){
        if (pessoa != null && !this.pessoas.contains(pessoa)){
            this.pessoas.add(pessoa);
        }
    }

    /**
     * Adds a project to the researchcenter's projects.
     * @param projeto Project object that will be added to the researchcenter's projects.
     */
    public void addProject(Project projeto){
        this.projects.add(projeto);
    }

    /**
     * Gets the researchcenter's unfinished in ETC projects.
     * @return ArrayList with all of the researchcenter's unfinished in etc projects.
     */
    public ArrayList<Project> getUnfinishedProjectsInEtc(){
        //retorna os projetos inacabados
        Calendar diaAtual = new GregorianCalendar();
        ArrayList<Project> temp = new ArrayList<Project>();
        for(Project project : this.projects){
            if((project.getAcabado() && project.getEtc().before(project.getDataFim())) || (project.getEtc().before(diaAtual))){
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
