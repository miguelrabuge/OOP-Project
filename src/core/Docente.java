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
     *
     * @param nome         String with the docente's name.
     * @param email        String with the docente's email.
     * @param mecano       String with the docente's mecanographic number.
     * @param researchArea String with the docente's research area.
     */
    public Docente(String nome, String email, String mecano, String researchArea) {
        super(nome, email);
        this.mecano = mecano;
        this.researchArea = researchArea;
        this.orientados = new ArrayList<Estudante>();
        this.projetos = new ArrayList<Project>();
    }

    @Override
    public int getCusto() {
        return 0;
    }

    /**
     * Adds a project to the dodente's projects.
     *
     * @param project Project object that will be added to the docente's projects.
     */
    public void addProject(Project project) {
        this.projetos.add(project);
    }

    /**
     * Adds a estudante to the docente's oriented;
     *
     * @param orientado Estudante object that will be added to the docente's orientaded;_
     */
    public void addOrientado(Estudante orientado) {
        this.orientados.add(orientado);
    }

    @Override
    public String toString() {
        return "[" + mecano + " / " + researchArea + "] " + nome;
    }
}
