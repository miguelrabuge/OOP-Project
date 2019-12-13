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
     *
     * @param name String with the researchcenter's name.
     */
    public ResearchCenter(String name) {
        this.name = name;
        this.projects = new ArrayList<Project>();
        this.pessoas = new ArrayList<Pessoa>();
    }

    /**
     * Gets the researchcenter's name.
     *
     * @return String with the researchcenter's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the researchcenter's name.
     *
     * @param name String with the researchcenter's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the researchcenter's projects.
     *
     * @return ArrayList with the researchcenter's projects.
     */
    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    /**
     * Gets a Pessoa on the Center by name
     *
     * @param name
     * @return On success Pessoa object that matches the given String else returns null
     */
    public Pessoa getPessoaByName(String name) {
        for (Pessoa pessoa : this.pessoas) {
            if (pessoa.getNome().equals(name)) {
                return pessoa;
            }
        }
        return null;
    }

    /**
     * Gets the researchcenter's docentes.
     *
     * @return ArrayList with the researchcenter's docentes.
     */
    public ArrayList<Pessoa> getDocentes() {
        ArrayList<Pessoa> docentes = new ArrayList<Pessoa>();
        for (Pessoa pessoa : this.pessoas) {
            if (pessoa.getCusto() == 0) {
                docentes.add(pessoa);
            }
        }
        return docentes;
    }

    /**
     * Gets the researchcenter's docentes that are not in a given project.
     *
     * @param project
     * @return ArrayList with the researchcenter's docentes not int the Project project.
     */
    public ArrayList<Pessoa> getDocentesNotInProject(Project project) {
        ArrayList<Pessoa> docentes = new ArrayList<>();
        for (Pessoa docente : this.getDocentes()) {
            if (!project.getDocentes().contains((Docente) docente)) {
                docentes.add(docente);
            }
        }
        return docentes;
    }

    /**
     * Gets the researchcenter's bolseiros.
     *
     * @return ArrayList with the researchcenter's bolseiros.
     */
    public ArrayList<Bolseiro> getBolseiros() {
        ArrayList<Bolseiro> bolseiros = new ArrayList<>();
        for (Pessoa pessoa : this.pessoas) {
            if (pessoa.getCusto() != 0) {
                bolseiros.add((Bolseiro) pessoa);
            }
        }
        return bolseiros;
    }

    /**
     * Gets the researchcenter's bolseiros free or in the given project
     *
     * @param project Project to add the bolseiros that are in it
     * @return ArrayList with the researchcenter's bolseiros free or in the project
     */
    public ArrayList<Object> getBolseirosNotAssociatedAndProject(Project project) {
        ArrayList<Object> bolseiros = new ArrayList<>();
        for (Bolseiro bolseiro : this.getBolseiros()) {
            if (bolseiro.getProjeto() == null || project.getBolseiros().contains(bolseiro)) {
                bolseiros.add(bolseiro);
            }
        }
        return bolseiros;
    }

    /**
     * Adds a Pessoa to the reasearchCenter if it is not already a current member
     *
     * @param pessoa Pessoa object that will be added to the researchCenter Pessoa's
     */
    public void addPessoa(Pessoa pessoa) {
        if (pessoa != null && !this.pessoas.contains(pessoa)) {
            this.pessoas.add(pessoa);
        }
    }

    /**
     * Adds a project to the researchcenter's projects.
     *
     * @param projeto Project object that will be added to the researchcenter's projects.
     */
    public void addProject(Project projeto) {
        this.projects.add(projeto);
    }

    /**
     * Gets the researchcenter's unfinished in ETC projects.
     *
     * @return ArrayList with all of the researchcenter's unfinished in etc projects.
     */
    public ArrayList<Project> getUnfinishedProjectsInEtc() {
        Calendar diaAtual = new GregorianCalendar();
        ArrayList<Project> temp = new ArrayList<Project>();
        for (Project project : this.projects) {
            if ((project.getAcabado() && project.getEtc().before(project.getDataFim())) || (project.getEtc().before(diaAtual))) {
                temp.add(project);
            }
        }
        return temp;
    }

    /**
     * Gets the researchcenter's finished projects.
     *
     * @return ArrayList with all of the researchcenter's finished projects.
     */
    public ArrayList<Project> getFinishedProjects() {
        ArrayList<Project> temp = new ArrayList<Project>();
        for (Project project : this.projects) {
            if (project.getAcabado()) {
                temp.add(project);
            }
        }
        return temp;
    }

    @Override
    public String toString() {
        return name;
    }
}
