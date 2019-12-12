package core;

import java.util.ArrayList;

/**
 * Represents a Docente.
 */
public class Docente extends Pessoa {

    private String mecano;
    private String researchArea;
    private ArrayList<Estudante> orientados;
    private ArrayList<Project> projetos;

    /**
     * Creates a Docente with his name, email, research center, mecanographic number and research area.
     * @param nome String with the docente's name.
     * @param email String with the docente's email.
     * @param mecano String with the docente's mecanographic number.
     * @param researchArea String with the docente's research area.
     */
    public Docente(String nome, String email,String mecano, String researchArea){
        super(nome, email);
        this.mecano = mecano;
        this.researchArea = researchArea;
        this.orientados = new ArrayList<Estudante>();
        this.projetos = new ArrayList<Project>();
    }

    /**
     * Gets the docente's mecanographic number.
     * @return String with the docentes's mecanographic number.
     */
    public String getMecano() {
        return this.mecano;
    }

    /**
     * Sets the docente's mecanographic number.
     * @param mecano String with the docente's mecanographic number.
     */
    public void setMecano(String mecano) {
        this.mecano = mecano;
    }

    /**
     * Gets the docente's research area.
     * @return String with the docente's research area.
     */
    public String getResearchArea() {
        return this.researchArea;
    }

    /**
     * Sets the docente's research area.
     * @param researchArea String with the docente's research area.
     */
    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    /**
     * Gets the docente's oriented estudantes.
     * @return ArrayList with the docente's oriented estudantes.
     */
    public ArrayList<Estudante> getOrientados() {
        return this.orientados;
    }

    /**
     * Sets the docente's oriented estudantes.
     * @param orientados ArrayList with the docente's oriented estudantes.
     */
    public void setOrientados(ArrayList<Estudante> orientados) {
        this.orientados = orientados;
    }

    /**
     * Gets the projects that the calling docente is involved in.
     * @return ArrayList with the projects that the docente is involved in.
     */
    public ArrayList<Project> getProjetos() {
        return this.projetos;
    }

    /**
     * Sets the projects that the docente is involved in.
     * @param projetos ArrayList with the projects that the docente is involved in.
     */
    public void setProjetos(ArrayList<Project> projetos) {
        this.projetos = projetos;
    }

    @Override
    public int getCusto() {
        return 0;
    }

    /**
     * Adds a project to the dodente's projects.
     * @param project Project object that will be added to the docente's projects.
     */
    public void addProject(Project project){
        this.projetos.add(project);
    }

    /**
     * Adds a estudante to the docente's oriented;
     * @param orientado Estudante object that will be added to the docente's orientaded;_
     */
    public void addOrientado(Estudante orientado){
        this.orientados.add(orientado);
    }
}
