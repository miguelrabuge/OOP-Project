package core;

import java.util.ArrayList;

public class Docente extends Pessoa {

    private String mecano;
    private String researchArea;
    private ArrayList<Estudante> orientados;
    private ArrayList<Project> projetos;

    public Docente(String nome, String email, ResearchCenter researchCenter,String mecano, String researchArea){
        super(nome, email, researchCenter);
        this.mecano = mecano;
        this.researchArea = researchArea;
    }

    public String getMecano() {
        return this.mecano;
    }

    public void setMecano(String mecano) {
        this.mecano = mecano;
    }

    public String getResearchArea() {
        return this.researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public ArrayList<Estudante> getOrientados() {
        return this.orientados;
    }

    public void setOrientados(ArrayList<Estudante> orientados) {
        this.orientados = orientados;
    }

    public ArrayList<Project> getProjetos() {
        return this.projetos;
    }

    public void setProjetos(ArrayList<Project> projetos) {
        this.projetos = projetos;
    }
}
