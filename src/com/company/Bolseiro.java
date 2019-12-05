package com.company;

import java.util.ArrayList;
import java.util.Calendar;

abstract class Bolseiro extends Pessoa {

    protected Calendar inicoBolsa;
    protected Calendar fimBolsa;
    protected Project projeto;

    public Bolseiro(String nome, String email, ResearchCenter researchCenter,Calendar inicioBolsa, Calendar fimBolsa){
        super(nome, email, researchCenter);
        this.inicoBolsa = inicioBolsa;
        this.fimBolsa = fimBolsa;
    }

    public abstract int getCusto();

    public Calendar getInicoBolsa() {
        return this.inicoBolsa;
    }

    public void setInicoBolsa(Calendar inicoBolsa) {
        this.inicoBolsa = inicoBolsa;
    }

    public Calendar getFimBolsa() {
        return this.fimBolsa;
    }

    public void setFimBolsa(Calendar fimBolsa) {
        this.fimBolsa = fimBolsa;
    }

    public Project getProjeto() {
        return this.projeto;
    }

    public void setProjeto(Project projetos) {
        this.projeto = projetos;
    }
}
