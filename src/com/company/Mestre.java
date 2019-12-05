package com.company;

import java.util.Calendar;

public class Mestre extends Estudante {

    public Mestre(String nome, String email, ResearchCenter researchCenter,Calendar inicioBolsa, Calendar fimBolsa, Docente orientador){
        super(nome, email, researchCenter,inicioBolsa, fimBolsa, orientador);
    }

    public int getCusto(){
        return 1000;
    }

}
