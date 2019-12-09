package core;

import java.util.Calendar;

public class Doutorado extends Bolseiro {

    public Doutorado(String nome, String email, ResearchCenter researchCenter,Calendar inicioBolsa, Calendar fimBolsa){
        super(nome, email, researchCenter,inicioBolsa, fimBolsa);
    }

    public int getCusto(){
        return 1200;
    }

}
