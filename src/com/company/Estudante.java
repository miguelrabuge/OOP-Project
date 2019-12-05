package com.company;

import java.util.Calendar;

abstract class Estudante extends Bolseiro {

    protected Docente orientador;

    public Estudante(String nome, String email, ResearchCenter researchCenter,Calendar inicioBolsa, Calendar fimBolsa, Docente orientador){
        super(nome, email, researchCenter,inicioBolsa, fimBolsa);
        this.orientador = orientador;
    }

    public Docente getOrientador(){
        return this.orientador;
    }

    public void setOrientador(Docente orientador){
        this.orientador = orientador;
    }

}
