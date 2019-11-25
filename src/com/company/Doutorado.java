package com.company;

import java.util.Calendar;

public class Doutorado extends Bolseiro {

    public Doutorado(String nome, String email, Calendar inicioBolsa, Calendar fimBolsa){
        super(nome, email, inicioBolsa, fimBolsa);
    }

    public int getCusto(){
        return 1200;
    }

}
